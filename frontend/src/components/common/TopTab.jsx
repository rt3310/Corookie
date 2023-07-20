import React from 'react'
import styled from 'styled-components'

const TopTab = () => {
    return (
        <S.Wrap>
            <S.Title>CoRookie</S.Title>
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
    `,
}

export default TopTab
