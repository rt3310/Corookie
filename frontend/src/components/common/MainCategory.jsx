import React from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

import * as utils from 'utils'

const MainCategory = () => {
    const navigate = useNavigate()

    return (
        <S.Wrap>
            <S.Container>
<<<<<<< HEAD
                <S.Button onClick={() => navigate(utils.URL.PLAN.CALENDER)}>일정</S.Button>
=======
                <S.Button onClick={() => navigate(utils.URL.CALENDAR.BOARD)}>일정</S.Button>
>>>>>>> 2faf8c17ad971d851d5c5f36727136b7bc9a9277
                <S.Button onClick={() => navigate(utils.URL.TASK.BOARD)}>이슈</S.Button>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 216px;
        height: 80px;
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
        align-items: center;
        width: 100%;
        height: 40px;
        transition-duration: 0.2s;
        padding: 16px;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        cursor: pointer;

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }

        &:first-child {
            border-radius: 8px 8px 0 0;
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }
    `,
}

export default MainCategory
