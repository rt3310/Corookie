import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

import { IoIosArrowDown } from 'react-icons/io'
import { BsPlus } from 'react-icons/bs'
import { AiOutlinePushpin, AiFillPushpin } from 'react-icons/ai'

import * as utils from 'utils'
import * as api from 'api'
import * as hooks from 'hooks'

const ChannelNav = () => {
    const navigate = useNavigate()
    const { project } = hooks.projectState()
    const { projectMembers, setProjectMembers } = hooks.projectMembersState()
    const { textChannels } = hooks.textChannelsState()
    const [openText, setOpenText] = useState(true)
    const [openDm, setOpenDm] = useState(true)
    const [openVideo, setOpenVideo] = useState(true)

    useEffect(() => {
        const initProjectMembers = async () => {
            const projectMembersRes = await api.apis.getProjectMembers(project.id)
            setProjectMembers(projectMembersRes.data)
        }

        initProjectMembers()
    }, [])

    return (
        <S.Wrap>
            <S.Container>
                <S.TextChannelList className={openText ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenText(!openText)}>
                        텍스트 채널 &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    {textChannels.map((textChannel, index) => (
                        <S.Channel onClick={() => navigate(utils.URL.CHAT.TEXT)}>
                            {index + 1}. {textChannel.name}
                            {/* <AiOutlinePushpin /> */}
                        </S.Channel>
                    ))}
                    <S.AddChannelButton>
                        <BsPlus /> 채널 추가
                    </S.AddChannelButton>
                </S.TextChannelList>
                <S.DmList className={openDm ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenDm(!openDm)}>
                        Direct Message &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    {projectMembers &&
                        projectMembers.map(member => (
                            <S.DmMember onClick={() => navigate(utils.URL.CHAT.DIRECT)}>
                                <S.DmProfileImage>
                                    <img src={require('images/profile.png').default} alt="프로필" />
                                </S.DmProfileImage>
                                {member.memberName}
                            </S.DmMember>
                        ))}
                </S.DmList>
                <S.VideoChannelList className={openVideo ? 'opened' : ''}>
                    <S.ChannelHead onClick={() => setOpenVideo(!openVideo)}>
                        화상 채널 &nbsp; <IoIosArrowDown />
                    </S.ChannelHead>
                    <S.Channel onClick={() => navigate(utils.URL.CHAT.VIDEO)}>1. 회의</S.Channel>
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
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;

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
            display: flex;
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
