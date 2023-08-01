import React, { useState } from 'react'
import styled from 'styled-components'

import { IoHappyOutline, IoSadOutline, IoThumbsUp } from 'react-icons/io5'
import * as utils from 'utils'

const Imoticon = ({ icon, count }) => {
    const [clicked, setClicked] = useState(count)
    const clickImoticon = () => {
        if (clicked < 1000) {
            setClicked(Number(clicked) + 1)
        }
    }
    return (
        <S.Wrap>
            {count > 0 ? (
                <S.Container icon={icon} onClick={() => clickImoticon()}>
                    <S.Icon>
                        {icon === utils.IMOTICON_OPTIONS.thumb ? (
                            <IoThumbsUp />
                        ) : icon === utils.IMOTICON_OPTIONS.happy ? (
                            <IoHappyOutline />
                        ) : (
                            <IoSadOutline />
                        )}
                    </S.Icon>
                    <S.Pressed>{clicked >= 1000 ? '+999' : clicked}</S.Pressed>
                </S.Container>
            ) : null}
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        align-items: center;
        width: auto;
        height: auto;
    `,
    Container: styled.div`
        display: flex;
        min-width: 40px;
        height: 24px;
        justify-content: space-between;
        align-items: center;
        padding: 6px 8px;
        margin: 0 4px;
        border-radius: 8px;
        background-color: ${({ theme, icon }) =>
            icon === utils.IMOTICON_OPTIONS.thumb
                ? theme.color.success
                : icon === utils.IMOTICON_OPTIONS.happy
                ? theme.color.pending
                : theme.color.warning};
        cursor: pointer;
    `,
    Icon: styled.div`
        width: 12px;
        max-height: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        & svg {
            width: 12px;
            height: 12px;
            color: ${({ theme }) => theme.color.white};
        }
    `,
    Pressed: styled.div`
        width: auto;
        max-height: 12px;
        display: flex;
        align-items: center;
        padding-left: 4px;
        font-size: 12px;
        color: ${({ theme }) => theme.color.white};
        -ms-user-select: none;
        -moz-user-select: -moz-none;
        -khtml-user-select: none;
        -webkit-user-select: none;
        user-select: none;
    `,
}

export default Imoticon
