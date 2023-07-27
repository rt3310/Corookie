import React from 'react'
import styled from 'styled-components'

const Plan = () => {
    return (
        <S.Wrap>
            <S.Container>
                <S.CalendarHeader>
                    <S.CalendarAccess></S.CalendarAccess>
                    <S.WeekHeader></S.WeekHeader>
                </S.CalendarHeader>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
        max-height: calc(100vh - 56px);
    `,
    Container: styled.div`
        width: 100%;
        max-height: calc(100vh - 56px);
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        margin: 16px 16px 16px 0;
    `,
    CalendarHeader: styled.div`
        width: 100%;
    `,
    CalendarAccess: styled.div`
        width: 100%;
        height: 60px;
        display: flex;
        align-items: center;
        background-color: ${({ theme }) => theme.color.main};
    `,
    WeekHeader: styled.div`
        width: 100%;
        height: 32px;
        display: flex;
        align-items: center;
        background-color: ${({ theme }) => theme.color.warning};
    `,
    Calendar: styled.ul`
        border-top: 1px solid ${({ theme }) => theme.color.gray};
    `,
    Week: styled.li``,
}

export default Plan
