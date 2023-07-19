import React from 'react'
import styled from 'styled-components'

const Thread = ({ openComment, setOpenComment }) => {
    return <S.Wrap onClick={() => setOpenComment(!openComment)} open={openComment}></S.Wrap>
}

const S = {
    Wrap: styled.div`
        display: flex;
        flex-grow: 1;
        height: 64px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        border: 2px solid
            ${({ open, theme }) => {
                return open ? theme.color.color3 : theme.color.white
            }};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
        cursor: pointer;
    `,
}

export default Thread
