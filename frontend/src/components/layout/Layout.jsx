import React from 'react'
import { Outlet } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'
import * as components from 'components'

const Layout = () => {
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
        overflow-y: auto;
        min-height: 100vh;

        @media (max-width: ${utils.MAX_WIDTH}) {
            width: 100%;
            height: 100%;
        }
    `,
    Container: styled.div`
        display: flex;
        width: 100%;
        height: calc(100vh - 48px);
        padding: 48px 0 0 0;
    `,
    UserAccess: styled.div`
        display: block;
        width: 272px;
        height: 100%;
        padding: 0 16px;

        @media (max-width: 800px) {
            display: none;
        }
    `,
    Content: styled.div`
        width: 100%;
    `,
}

export default Layout
