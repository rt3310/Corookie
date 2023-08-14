import React from 'react'
import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'

import { IoExitOutline } from 'react-icons/io5'

const VideoChat = () => {
    const { chatboxOpened } = hooks.chatBoxState()

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
                        <iframe
                            src="https://i9a402.p.ssafy.io:8443"
                            allow="camera;microphone;fullscreen;autoplay"
                            width={1200}
                            height={800}>
                            <p>사용 중인 브라우저는 iframe을 지원하지 않습니다.</p>
                        </iframe>
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
        padding: 0 26px;
    `,
    ChatBox: styled.div`
        display: flex;
        flex-direction: column;
        margin-right: 32px;
        width: 100%;
        height: 100%;
        transition: width 0.2s;
    `,
    ThreadBox: styled.div`
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
        flex-grow: 1;
        overflow-y: auto;
        padding: 0 0 16px;
        margin: 0 8px auto 0;
    `,
}

export default VideoChat
