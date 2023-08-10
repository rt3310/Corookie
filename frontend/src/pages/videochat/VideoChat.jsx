import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'

import { IoExitOutline } from 'react-icons/io5'

import { OpenVidu } from 'openvidu-browser'
import axios from 'axios'
import React, { useState, useEffect } from 'react'

const VideoChat = () => {
    const { chatboxOpened } = hooks.chatBoxState()
    const [mySessionId, setMySessionId] = useState('SessionA123123')
    const [myUserName, setMyUserName] = useState('Participant' + Math.floor(Math.random() * 100))
    const [session, setSession] = useState(undefined)
    const [publisher, setPublisher] = useState(undefined)
    const [subscribers, setSubscribers] = useState([])

    const OPENVIDU_SERVER_URL = 'https://demos.openvidu.io/'

    const onbeforeunload = () => {
        leaveSession()
    }

    useEffect(() => {
        window.addEventListener('beforeunload', onbeforeunload)
        joinSession()
        return () => {
            window.removeEventListener('beforeunload', onbeforeunload)
        }
    }, [])

    const deleteSubscriber = streamManager => {
        setSubscribers(prevSubscribers => prevSubscribers.filter(sub => sub !== streamManager))
    }

    const joinSession = async () => {
        const OV = new OpenVidu()
        const newSession = OV.initSession()
        setSession(newSession)

        newSession.on('streamCreated', event => {
            const subscriber = newSession.subscribe(event.stream, undefined)
            setSubscribers(prevSubscribers => [...prevSubscribers, subscriber])
        })

        newSession.on('streamDestroyed', event => {
            deleteSubscriber(event.stream.streamManager)
        })

        newSession.on('exception', exception => {
            console.warn(exception)
        })

        try {
            const token = await getToken()
            newSession
                .connect(token, { clientData: myUserName })
                .then(async () => {
                    const publisher = await OV.initPublisherAsync(undefined, {
                        publishAudio: true,
                        publishVideo: true,
                        resolution: '640x480',
                        frameRate: 30,
                        insertMode: 'APPEND',
                        mirror: false,
                    })

                    newSession.publish(publisher)

                    const devices = await OV.getDevices()
                    const videoDevices = devices.filter(device => device.kind === 'videoinput')
                    const currentVideoDeviceId = publisher.stream
                        .getMediaStream()
                        .getVideoTracks()[0]
                        .getSettings().deviceId
                    const currentVideoDevice = videoDevices.find(device => device.deviceId === currentVideoDeviceId)

                    setMainStreamManager(publisher)
                    setPublisher(publisher)
                })
                .catch(error => {
                    console.log('There was an error connecting to the session:', error.code, error.message)
                })
        } catch (error) {
            console.error('Error joining session:', error)
        }
    }

    const leaveSession = () => {
        if (session) {
            session.disconnect()
        }

        setSession(undefined)
        setSubscribers([])
        setMySessionId('SessionA')
        setMyUserName('Participant' + Math.floor(Math.random() * 100))
        setPublisher(undefined)
    }

    const getToken = async () => {
        const sessionId = await createSession(mySessionId)
        return await createToken(sessionId)
    }

    const createSession = async sessionId => {
        const response = await axios.post(
            OPENVIDU_SERVER_URL + 'api/sessions',
            { customSessionId: sessionId },
            {
                headers: { 'Content-Type': 'application/json' },
            },
        )
        return response.data // The sessionId
    }

    const createToken = async sessionId => {
        const response = await axios.post(
            OPENVIDU_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
            {},
            {
                headers: { 'Content-Type': 'application/json' },
            },
        )
        return response.data // The token
    }

    // ---------------- 위쪽이 종서가 추가한 코드 ------------------

    return (
        <S.Wrap>
            <S.Header>
                <S.Title>1. 회의</S.Title>
                <S.ExitButton>
                    <IoExitOutline />
                </S.ExitButton>
            </S.Header>
            <S.Container>
                <S.ChatBox>
                    <S.ThreadBox>
                        {subscribers.map((sub, i) => (
                            <div key={i}>
                                <components.VideoBox streamManager={sub} />
                                <span>test + {i}</span>
                            </div>
                        ))}
                        <components.VideoControl />
                    </S.ThreadBox>
                </S.ChatBox>
                {chatboxOpened && <components.TextChatBox />}
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
    `,
    Header: styled.div`
        display: flex;
        align-items: center;
        height: 64px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
    `,
    ExitButton: styled.div`
        margin: 0 0 0 auto;
        cursor: pointer;
        transition-duration: 0.2s;

        & svg {
            width: 24px;
            height: 24px;
        }

        &:hover {
            color: ${({ theme }) => theme.color.warning};
            transform: translateX(1px);
        }
    `,
    Container: styled.div`
        display: flex;
        height: 100%;
        max-height: calc(100vh - 152px);
    `,
    ChatBox: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
        transition: width 0.2s;
    `,
    ThreadBox: styled.div`
        width: 100%;
        flex-grow: 1;
        overflow-y: auto;
        padding: 0 0 16px;
        margin: 0 0 auto 0;
    `,
    VideoBox: styled.div`
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        padding: 8px;
    `,
}

export default VideoChat
