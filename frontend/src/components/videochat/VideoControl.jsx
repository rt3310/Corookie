import React from 'react'
import styled from 'styled-components'

import { IoIosChatboxes, IoMdSettings, IoMdMic, IoMdMicOff } from 'react-icons/io'
import { IoVideocam, IoVideocamOff } from 'react-icons/io5'
import { LuScreenShare } from 'react-icons/lu'
import { MdCallEnd } from 'react-icons/md'
import * as components from 'components'
import * as hooks from 'hooks'

const VideoControl = () => {
    const { closeProfile } = hooks.profileState()
    const { chatboxOpened, openChatbox, closeChatbox } = hooks.chatBoxState()

    const toggleChatbox = () => {
        if (chatboxOpened) {
            closeChatbox()
        } else {
            openChatbox()
            closeProfile()
        }
    }

    return (
        <S.Wrap>
            <S.ControlBox>
                <S.SettingButton>
                    <IoMdSettings />
                </S.SettingButton>
                <S.ShareButton>
                    <LuScreenShare />
                </S.ShareButton>
                <S.MicButton>
                    <IoMdMic />
                    {/* <IoMdMicOff /> */}
                </S.MicButton>
                <S.VidButton>
                    <IoVideocam />
                    {/* <IoVideocamOff /> */}
                </S.VidButton>
                <S.CallOffButton>
                    <MdCallEnd />
                    {/* <IoVideocamOff /> */}
                </S.CallOffButton>

                <S.TextChatButton onClick={() => toggleChatbox()} open={chatboxOpened}>
                    <IoIosChatboxes />
                </S.TextChatButton>
            </S.ControlBox>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        border: 2px solid
            ${({ open, theme }) => {
                return open ? theme.color.main : theme.color.white
            }};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 8px 16px;

        &:first-child {
            margin-top: 0;
        }

        &:last-child {
            margin-bottom: 0;
        }
    `,
    ControlBox: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
    `,
    SettingButton: styled.div`
        color: ${({ theme }) => theme.color.gray};
        margin: 4px;
        transition-duration: 0.2s;
        cursor: pointer;

        & svg {
            width: 24px;
            height: 24px;
        }
    `,
    MicButton: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        color: ${({ theme }) => theme.color.gray};
        margin: auto 4px;
        border: 1px solid ${({ theme }) => theme.color.gray};
        border-radius: 16px;
        width: 48px;
        height: 100%;
        transition-duration: 0.2s;
        cursor: pointer;

        & svg {
            width: 26px;
            height: 26px;
        }
    `,
    VidButton: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        color: ${({ theme }) => theme.color.gray};
        margin: auto 4px;
        border: 1px solid ${({ theme }) => theme.color.gray};
        border-radius: 16px;
        width: 48px;
        height: 100%;
        transition-duration: 0.2s;
        cursor: pointer;

        & svg {
            width: 26px;
            height: 26px;
        }
    `,
    ShareButton: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        color: ${({ theme }) => theme.color.gray};
        margin: 4px 4px 4px auto;
        border: 1px solid ${({ theme }) => theme.color.gray};
        border-radius: 16px;
        width: 48px;
        height: 100%;
        transition-duration: 0.2s;
        cursor: pointer;

        & svg {
            width: 26px;
            height: 26px;
        }
    `,
    CallOffButton: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        color: ${({ theme }) => theme.color.warning};
        margin: 4px auto 4px 4px;
        border: 1px solid ${({ theme }) => theme.color.warning};
        border-radius: 16px;
        width: 48px;
        height: 100%;
        transition-duration: 0.2s;
        cursor: pointer;

        & svg {
            width: 26px;
            height: 26px;
        }
    `,
    TextChatButton: styled.div`
        display: flex;
        align-items: center;
        margin: 4px 4px auto;
        color: ${({ theme }) => theme.color.gray};
        transition-duration: 0.2s;
        cursor: pointer;

        &:hover {
            color: ${({ theme }) => theme.color.main};

            & > div img {
                margin: 0 4px 0 0;
            }
        }

        & svg {
            width: 24px;
            height: 24px;
        }

        & > div {
            margin: 0 10px 0 0;
        }

        & img {
            width: 24px;
            height: 24px;
            transition-duration: 0.2s;
        }

        & img:not(:last-child) {
            margin: 0 -10px 0 0;
        }
    `,
}

export default VideoControl
