import React from 'react'
import styled from 'styled-components'

import * as style from 'style'

const ProfileBox = () => {
    return (
        <S.Wrap>
            <S.Header>프로필</S.Header>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 600px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px 16px 16px;
        padding: 0 26px;
        /* animation: ${style.leftSlide} 0.4s linear; */
    `,
    Header: styled.div`
        display: flex;
        align-items: center;
        white-space: nowrap;
        width: 100%;
        height: fit-content;
        margin: 24px 0 0 0;
        font-size: ${({ theme }) => theme.fontsize.title2};
        color: ${({ theme }) => theme.color.black};
    `,
}

export default ProfileBox
