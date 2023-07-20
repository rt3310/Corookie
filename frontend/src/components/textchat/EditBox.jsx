import React from 'react'
import styled from 'styled-components'

const EditBox = () => {
    return <S.Wrap>123</S.Wrap>
}

const S = {
    Wrap: styled.div`
        bottom: 0;
        width: inherit;
        height: 64px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
    `,
    Header: styled.div``,
}

export default EditBox
