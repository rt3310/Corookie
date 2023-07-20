import React from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'

const TopTab = () => {
    const navigate = useNavigate()

    return (
        <S.Wrap>
            <S.Title onClick={() => navigate(utils.URL.HOME.MAIN)}>CoRookie</S.Title>
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
}

export default TopTab
