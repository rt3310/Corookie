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
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        max-width: 100vw;
        height: 48px;
        background-color: ${({ theme }) => theme.color.color1};
    `,
    Title: styled.div`
        display: flex;
        align-items: center;
        height: 100%;
        font-size: ${({ theme }) => theme.fontsize.title1};
        color: ${({ theme }) => theme.color.white};
        margin: 0 0 0 20px;
    `,
}

export default TopTab
