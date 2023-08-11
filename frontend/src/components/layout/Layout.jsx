import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router'
import { Outlet } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'
import * as hooks from 'hooks'
import * as api from 'api'
import * as components from 'components'

const Layout = () => {
    const { projectId } = useParams()
    const { profileOpened } = hooks.profileState()
    const { project, setProject } = hooks.projectState()
    const { textChannels, setTextChannels } = hooks.textChannelsState()

    useEffect(() => {
        const initProject = async projectId => {
            try {
                const projectRes = await api.apis.getProject(projectId)
                const textChannelsRes = await api.apis.getTextChannels(projectRes.data.id)
                setProject(projectRes.data)
                setTextChannels(textChannelsRes.data)
            } catch (error) {
                console.log(error)
            }
        }

        initProject(projectId)
    }, [])

    if (!project) {
        return
    }

    return (
        <S.Wrap>
            <S.Main>
                <components.TopTab />
                <S.Container>
                    <S.UserAccess>
                        <components.ProjectIntro />
                        <components.MainCategory />
                        <components.ChannelNav />
                    </S.UserAccess>
                    <S.Content>
                        <Outlet />
                    </S.Content>
                    {profileOpened && <components.ProfileBox />}
                </S.Container>
            </S.Main>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 100%;
        height: 100%;
        background-color: ${({ theme }) => theme.color.background};
        display: flex;
        justify-content: center;
        align-items: center;
    `,
    Main: styled.div`
        width: 1920px;
        height: 100%;
        /* overflow-y: hidden; */
        min-height: 100vh;

        @media (max-width: ${utils.MAX_WIDTH}) {
            width: 100%;
            height: 100%;
        }
    `,
    Container: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
        max-height: calc(100vh - 56px);
    `,
    UserAccess: styled.div`
        display: block;
        width: 272px;
        height: 100%;
        padding: 0 16px;
        max-height: calc(100vh - 56px);

        @media (max-width: 800px) {
            display: none;
        }
    `,
    Content: styled.div`
        width: 100%;
    `,
}

export default Layout
