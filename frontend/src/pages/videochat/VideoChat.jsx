import React, { useState } from 'react'
import { useParams } from 'react-router'
import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'
import * as api from 'api'

import { IoExitOutline } from 'react-icons/io5'
import { useEffect } from 'react'

const VideoChat = () => {
    const { chatboxOpened } = hooks.chatBoxState()
    const { projectId, channelId } = useParams()
    const [videoChannel, setVideoChannel] = useState(null)

    useEffect(() => {
        const initChannel = async () => {
            const videoChannelRes = await api.apis.getVideoChannel(projectId, channelId)
            console.log(videoChannelRes.data)
            setVideoChannel(videoChannelRes.data)
        }
        setVideoChannel(null)
        initChannel()
    }, [projectId, channelId])

    if (!videoChannel) {
        return
    }

    return (
        <S.Wrap>
            <S.Header>
                <S.Title>{videoChannel.name}</S.Title>
                <S.ExitButton>
                    <IoExitOutline />
                </S.ExitButton>
            </S.Header>
            <S.Container>
                <iframe
                    // src={`http://localhost:4200/#/${videoChannel.sessionId}`} // local 시험할 때
                    src={`http://i9a402.p.ssafy.io:8443/#/${videoChannel.sessionId}`} // 우리 서버에서
                    allow="camera;microphone;fullscreen;autoplay"
                    width="100%"
                    height="100%">
                    <p>사용 중인 브라우저는 iframe을 지원하지 않습니다.</p>
                </iframe>
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
        margin: 16px;
        border-radius: 8px;
    `,
}

export default VideoChat
