import React from 'react'
import styled from 'styled-components'

const CommentBox = () => {
    return <S.Wrap></S.Wrap>
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 600px;
        height: 100%;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
    `,
}

export default CommentBox
