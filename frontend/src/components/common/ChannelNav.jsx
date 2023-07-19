import React from 'react'
import styled from 'styled-components'
import { IoIosArrowDown } from 'react-icons/io'

const ChannelNav = () => {
    return (
        <S.Wrap>
            <S.Container>
                <S.TextChannelList>
                    <S.ChannelHead>
                        텍스트 채널 <IoIosArrowDown />
                    </S.ChannelHead>
                    <S.Channel>1. 공지</S.Channel>
                    <S.Channel>2. 자유</S.Channel>
                    <S.Channel>3. Backend</S.Channel>
                    <S.Channel>4. Frontend</S.Channel>
                </S.TextChannelList>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 240px;
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px 0;
    `,
    Container: styled.div`
        width: 100%;
        height: 100%;
    `,
    Button: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 50px;
        transition-duration: 0.2s;
        cursor: pointer;

        &:hover {
            background-color: ${({ theme }) => theme.color.color3};
            color: ${({ theme }) => theme.color.white};
        }

        &:first-child {
            border-radius: 8px 8px 0 0;
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }
    `,
    TextChannelList: styled.ul``,
    ChannelHead: styled.li`
        font-size: 16px;
        display: flex;
        align-items: center;
        padding: 16px 16px 10px;
        cursor: pointer;
    `,
    Channel: styled.li`
        font-size: 14px;
        padding: 12px 16px;
        cursor: pointer;

        &:hover {
            background-color: ${({ theme }) => theme.color.color3};
            color: ${({ theme }) => theme.color.white};
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }
    `,
}

export default ChannelNav
