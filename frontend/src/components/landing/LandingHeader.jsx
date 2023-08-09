import React from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'
import * as hooks from 'hooks'

const LandingHeader = () => {
    const { profileOpened, openProfile, closeProfile } = hooks.profileState()

    const navigate = useNavigate()

    const toggleProfile = () => {
        if (profileOpened) {
            closeProfile()
        } else {
            openProfile()
        }
    }

    return (
        <S.Wrap>
            <S.Title onClick={() => navigate(utils.URL.HOME.LANDING)}>CoRookie</S.Title>
            {/* <S.Profile onClick={() => toggleProfile()}>
                <img src={require('images/thread_profile.png').default} alt="스레드 이미지" />
            </S.Profile> */}
            <S.AboutUs>About Us</S.AboutUs>
            <S.Login onClick={() => navigate(utils.URL.LOGIN.LOGIN)}>Login</S.Login>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        top: 0;
        left: 0;
        width: 100%;
        max-width: 100vw;
        height: 56px;
        display: flex;
        align-items: center;
        justify-content: space-between;
    `,
    Title: styled.div`
        height: 100%;
        width: 167px;
        font-family: 'Futura PT';
        font-size: ${({ theme }) => theme.fontsize.logo};
        font-weight: 700;
        color: ${({ theme }) => theme.color.main};

        padding: 24px 8px 8px 32px;
        cursor: pointer;
    `,
    Profile: styled.div`
        width: 40px;
        margin: 16px 16px 0 0;
        cursor: pointer;
        & img {
            width: 40px;
            height: 40px;
        }
    `,
    AboutUs: styled.div`
        width: 100%;
        margin-left: 24px;
        padding: 24px 8px 0;
        color: ${({ theme }) => theme.color.main};
        cursor: pointer;
    `,
    Login: styled.div`
        width: 100%;
        text-align: right;
        margin-left: auto;
        padding: 24px 32px 0 32px;
        color: ${({ theme }) => theme.color.main};
        cursor: pointer;
    `,
}

export default LandingHeader
