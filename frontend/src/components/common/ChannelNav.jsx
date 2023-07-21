import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import { IoIosArrowDown } from 'react-icons/io'
import { BsPlus } from 'react-icons/bs'

import * as utils from 'utils'

const ChannelNav = () => {
    const navigate = useNavigate()
    const [openText, setOpenText] = useState(true)
    const [openDm, setOpenDm] = useState(true)
    const [openVideo, setOpenVideo] = useState(true)

    return (
        <S.Wrap>
            <S.Container>
                <S.TextChannelList className={openText ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenText(!openText)}>
                        텍스트 채널 &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    <S.Channel onClick={() => navigate(utils.URL.CHAT.TEXT)}>1. 공지</S.Channel>
                    <S.Channel>2. 자유</S.Channel>
                    <S.Channel>3. Backend</S.Channel>
                    <S.Channel>4. Frontend</S.Channel>
                    <S.AddChannelButton>
                        <BsPlus /> 채널 추가
                    </S.AddChannelButton>
                </S.TextChannelList>
                <S.DmList className={openDm ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenDm(!openDm)}>
                        Direct Message &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    <S.DmMember>
                        <S.DmProfileImage>
                            <img src={require('images/profile.png').default} alt="프로필" />
                        </S.DmProfileImage>
                        황상미
                    </S.DmMember>
                    <S.DmMember>
                        <S.DmProfileImage>
                            <img src={require('images/profile.png').default} alt="프로필" />
                        </S.DmProfileImage>
                        황상미
                    </S.DmMember>
                    <S.DmMember>
                        <S.DmProfileImage>
                            <img src={require('images/profile.png').default} alt="프로필" />
                        </S.DmProfileImage>
                        황상미
                    </S.DmMember>
                    <S.DmMember>
                        <S.DmProfileImage>
                            <img src={require('images/profile.png').default} alt="프로필" />
                        </S.DmProfileImage>
                        황상미
                    </S.DmMember>
                    <S.DmMember>
                        <S.DmProfileImage>
                            <img src={require('images/profile.png').default} alt="프로필" />
                        </S.DmProfileImage>
                        황상미
                    </S.DmMember>
                </S.DmList>
                <S.VideoChannelList className={openVideo ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenVideo(!openVideo)}>
                        화상 채널 &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    <S.Channel>1. 회의</S.Channel>
                    <S.Channel>2. 자유</S.Channel>
                    <S.AddChannelButton>
                        <BsPlus /> 채널 추가
                    </S.AddChannelButton>
                </S.VideoChannelList>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 216px;
        max-height: calc(100vh - 344px);
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px 0;
        overflow-y: auto;

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
    Container: styled.div`
        width: 100%;
        height: 100%;
    `,
    Button: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 50px;
        transition-duration: 0.2s;
        cursor: pointer;

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }

        &:first-child {
            border-radius: 8px 8px 0 0;
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }
    `,
    TextChannelList: styled.ul`
        border-bottom: 1px solid ${({ theme }) => theme.color.lightgray};
    `,
    ChannelHead: styled.li`
        font-size: ${({ theme }) => theme.fontsize.title3};
        display: flex;
        align-items: center;
        padding: 16px 16px 10px;
        cursor: pointer;

        :not(.opened) & svg {
            transition-duration: 0.2s;
            transform: rotateZ(180deg);
        }

        .opened & svg {
            transition-duration: 0.2s;
            transform: rotateZ(360deg);
        }
    `,
    Channel: styled.li`
        font-size: ${({ theme }) => theme.fontsize.sub1};
        padding: 12px 16px;
        cursor: pointer;
        transition-duration: 0.2s;

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }

        :not(.opened) & {
            display: none;
        }

        .opened & {
            display: block;
        }
    `,
    AddChannelButton: styled.div`
        display: flex;
        align-items: center;
        justify-content: flex-start;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        padding: 12px 16px;
        transition-duration: 0.2s;
        cursor: pointer;

        &:hover {
            transform: translateY(-2px);
            color: ${({ theme }) => theme.color.main};
        }

        & svg {
            width: 20px;
            height: 20px;
            margin: 0 0 0 -4px;
            color: ${({ theme }) => theme.color.main};
        }
    `,
    DmList: styled.ul`
        border-bottom: 1px solid ${({ theme }) => theme.color.lightgray};
    `,
    DmMember: styled.li`
        display: flex;
        align-items: center;
        padding: 6px 16px;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        cursor: pointer;

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }

        :not(.opened) & {
            display: none;
        }

        .opened & {
            display: flex;
        }
    `,
    DmProfileImage: styled.li`
        display: flex;
        align-items: center;
        margin: 0 10px 0 0;

        & img {
            width: 20px;
        }
    `,
    VideoChannelList: styled.ul``,
}

export default ChannelNav
