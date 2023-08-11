import React, { useState, useEffect, useRef, useCallback } from 'react'
import { useParams } from 'react-router'
import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'
import * as utils from 'utils'
import * as api from 'api'

import * as StompJs from '@stomp/stompjs'
import * as SockJs from 'sockjs-client'

import { IoExitOutline } from 'react-icons/io5'
import { AiOutlinePushpin, AiFillPushpin } from 'react-icons/ai'

const TextChat = () => {
    const { projectId, channelId } = useParams()
    const { commentOpened } = hooks.commentState()
    const [page, setPage] = useState(1)
    const [member, setMember] = useState()
    const [textChannel, setTextChannel] = useState(null)
    const [currentChat, setCurrentChat] = useState({
        textChannelId: null,
        writerId: null,
        content: '',
    })
    const [threads, setThreads] = useState([])
    const [load, setLoad] = useState(false) //로딩 스피너
    const target = useRef(null)
    const preventRef = useRef(true) //옵저버 중복 실행 방지
    const endRef = useRef(false) //모든 글 로드 확인
    const scrollRef = useRef(null)
    const [pinOn, setPinOn] = useState(true)
    const client = useRef({})

    const connectThread = () => {
        client.current = new StompJs.Client({
            brokerURL: utils.WEBSOCKET_BASE_URL + '/ws/chat',
            connectHeaders: {
                Authorization: hooks.getCookie('Authorization'),
            },
            debug: function (message) {
                console.log(message)
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
            onConnect: () => {
                console.log('Connected')
                client.current.subscribe('/topic/thread/' + 1, message => {
                    const jsonBody = JSON.parse(message.body)
                    console.log(jsonBody)
                    setThreads(chats => [...chats, jsonBody])
                })
                client.current.publish({
                    destination: '/app/thread/',
                    body: JSON.stringify(threads),
                })
            },
            onStompError: frame => {
                console.error(frame)
            },
        })

        client.current.activate()
    }

    const disconnect = () => {
        client.current.deactivate()
    }

    const send = () => {
        if (!client.current.connected) {
            return
        }

        if (currentChat.content.replace(/\s+/gi, '') === '') {
            return
        }
        console.log(currentChat)

        client.current.publish({
            destination: '/app/thread',
            body: JSON.stringify(currentChat),
        })

        setCurrentChat({
            ...currentChat,
            content: '',
        })
    }

    useEffect(() => {
        const observer = new IntersectionObserver(obsHandler, { threshold: 0.5 })
        if (target.current) observer.observe(target.current)
        const initChannel = async () => {
            const memberRes = await api.apis.getMe()
            const textChannelRes = await api.apis.getTextChannel(projectId, channelId)
            setMember(memberRes.data)
            setTextChannel(textChannelRes.data)
            setCurrentChat({
                ...currentChat,
                textChannelId: textChannelRes.data.id,
                writerId: memberRes.data.id,
            })
        }
        initChannel()
        connectThread()

        return () => {
            observer.disconnect()
            disconnect()
        }
    }, [projectId, channelId])

    const obsHandler = entries => {
        //옵저버 콜백함수
        const entry = entries[0]
        if (!endRef.current && entry.isIntersecting && preventRef.current) {
            //옵저버 중복 실행 방지
            preventRef.current = false //옵저버 중복 실행 방지
            setPage(prev => prev + 1)
        }
    }

    const getThreads = useCallback(async () => {
        setLoad(true)
        const threadsRes = await api.apis.getThreads(projectId, channelId, page)
        setThreads([...threads, threadsRes.data])
        preventRef.current = true
        setLoad(false)
    }, [projectId, channelId, page])

    useEffect(() => {
        getThreads()
    }, [page])

    useEffect(() => {
        scrollRef.current.scrollTop = scrollRef.current.scrollHeight
    }, [threads])

    return (
        <S.Wrap>
            <S.Header>
                <S.Title>
                    {textChannel && textChannel.name}
                    <S.PinButton onClick={() => setPinOn(!pinOn)}>
                        {pinOn ? <AiFillPushpin /> : <AiOutlinePushpin />}
                    </S.PinButton>
                </S.Title>
                <S.ExitButton>
                    <IoExitOutline />
                </S.ExitButton>
            </S.Header>
            <S.Container>
                <S.ChatBox>
                    <S.ThreadBox ref={scrollRef}>
                        <div ref={target} />
                        {threads && threads.map((thread, idx) => <components.Thread key={idx} thread={thread} />)}
                    </S.ThreadBox>
                    <components.EditBox currentChat={currentChat} setCurrentChat={setCurrentChat} send={send} />
                </S.ChatBox>
                {commentOpened && <components.CommentBox />}
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
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;
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
        /* max-height: calc(100vh - 300px); */
        overflow-y: auto;
        padding: 0 0 16px;
        margin: 0 0 auto 0;

        &::-webkit-scrollbar {
            height: 0px;
            width: 4px;
        }
        &::-webkit-scrollbar-track {
            background: transparent;
        }
        &::-webkit-scrollbar-thumb {
            background: ${({ theme }) => theme.color.gray};
            border-radius: 45px;
        }
        &::-webkit-scrollbar-thumb:hover {
            background: ${({ theme }) => theme.color.gray};
        }
    `,
    PinButton: styled.div`
        margin: auto 16px;
        color: ${({ theme }) => theme.color.main};
        transition-duration: 0.2s;
        cursor: pointer;
        & svg {
            width: 18px;
            height: 18px;
        }
    `,
}

export default TextChat
