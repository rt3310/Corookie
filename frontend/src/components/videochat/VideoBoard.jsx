import React, { useRef, useState, useEffect } from 'react'
import styled from 'styled-components'
import { IoIosArrowDown, IoIosArrowUp } from 'react-icons/io'

const VideoBoard = () => {
    return (
        <S.Wrap>
            <S.ContentBox>화면 6개</S.ContentBox>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        padding: 16px 24px;
        margin: 0 16px;
        height: 100%;
    `,

    ContentBox: styled.div`
        width: 100%;
    `,
}

export default VideoBoard
