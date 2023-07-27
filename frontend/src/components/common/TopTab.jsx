import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'
import * as hooks from 'hooks'

const TopTab = () => {
    const { profileOpened, openProfile, closeProfile } = hooks.profileState()
    const { closeComment } = hooks.commentState()
    const navigate = useNavigate()

    const toggleProfile = () => {
        if (profileOpened) {
            closeProfile()
        } else {
            openProfile()
            closeComment()
        }
    }

    return (
        <S.Wrap style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
            <S.Title onClick={() => navigate(utils.URL.HOME.MAIN)}>CoRookie</S.Title>
            <S.Profile onClick={() => toggleProfile()}>
                <img src={require('images/thread_profile.png').default} alt="스레드 이미지" />
            </S.Profile>
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
    `,
    Title: styled.div`
        height: 100%;
        width: 167px;
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
}

export default TopTab
