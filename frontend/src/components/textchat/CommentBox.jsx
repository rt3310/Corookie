import React from 'react'
import styled from 'styled-components'

import * as style from 'style'

const CommentBox = () => {
    return (
        <S.Wrap>
            <S.Header>3개의 댓글</S.Header>
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
        margin: 0 16px 16px;
        padding: 0 26px;
        /* animation: ${style.leftSlide} 0.4s linear; */
    `,
    Header: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        white-space: nowrap;
        width: 100%;
        height: fit-content;
        margin: 24px 0 0 0;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        color: ${({ theme }) => theme.color.gray};

        &::before {
            content: '';
            margin: 0 8px 0 0;
            background-color: ${({ theme }) => theme.color.lightgray};
            height: 1px;
            width: 100%;
        }

        &::after {
            content: '';
            margin: 0 0 0 8px;
            background-color: ${({ theme }) => theme.color.lightgray};
            height: 1px;
            width: 100%;
        }
    `,
}

export default CommentBox
