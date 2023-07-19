import React from 'react'
import styled from 'styled-components'

const TopTab = () => {
    return <S.Wrap></S.Wrap>
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
}

export default TopTab
