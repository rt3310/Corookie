import React from 'react'
import styled from 'styled-components'

import * as style from 'style'

const CommentBox = () => {
    return <S.Wrap>123</S.Wrap>
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 600px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 0 16px 16px;
        padding: 0 26px;
        /* animation: ${style.leftSlide} 0.4s linear; */
    `,
}

export default CommentBox
