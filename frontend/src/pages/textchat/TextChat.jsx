import React, { useState } from 'react'
import styled from 'styled-components'

import * as components from 'components'

const TextChat = () => {
    const [openComment, setOpenComment] = useState(false)

    return (
        <S.Wrap>
            <S.Header>
                <S.Title>1. 공지</S.Title>
                <S.DeleteButton>
                    <img src={require('images/delete.png').default} alt="삭제버튼" />
                </S.DeleteButton>
            </S.Header>
            <S.Container>
                <S.ThreadBox>
                    <components.Thread openComment={openComment} setOpenComment={setOpenComment} />
                </S.ThreadBox>
                {openComment ? <components.CommentBox /> : null}
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 100%;
        height: 100%;
    `,
    Header: styled.div`
        display: flex;
        align-items: center;
        height: 64px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
    `,
    DeleteButton: styled.div`
        margin: 0 0 0 auto;
        cursor: pointer;
        transition-duration: 0.2s;

        & img {
            width: 24px;
            height: 24px;
        }

        &:hover {
            filter: ${({ theme }) => theme.color.warningFilter};
            transform: translateY(-1px);
        }
    `,
    Container: styled.div`
        display: flex;
        height: 100%;
    `,
    ThreadBox: styled.div`
        width: 100%;
    `,
}

export default TextChat
