import React, { useState, useEffect, useRef } from 'react'
import styled from 'styled-components'

import { format } from 'date-fns'
import { startOfMonth, endOfMonth, startOfWeek, endOfWeek } from 'date-fns'
import { isSameMonth, isSameDay, addDays } from 'date-fns'

import * as hooks from 'hooks'

const Calendar = ({ currentMonth }) => {
    const draggingItemIndex = useRef(null)
    const draggingOverItemIndex = useRef(null)
    const monthStart = startOfMonth(currentMonth)
    const monthEnd = endOfMonth(monthStart)
    const startDate = startOfWeek(monthStart)
    const endDate = endOfWeek(monthEnd)
    const { planStartDate, planEndDate } = hooks.planRegisterState()
    const [calendar, setCalendar] = useState([])

    useEffect(() => {
        let rows = []
        let days = []
        let plans = []
        let addedPlan = []
        let day = startDate

        while (day <= endDate) {
            for (let i = 0; i < 7; i++) {
                days.push(
                    <S.DayNumber key={day} className={format(currentMonth, 'M') !== format(day, 'M') ? 'disable' : ''}>
                        {format(day, 'd')}
                    </S.DayNumber>,
                )
                if (isSameDay(day, planStartDate)) {
                    addedPlan.push(
                        <S.PlanRow>
                            <S.DayPlan
                                key={day}
                                style={{
                                    left: `${100.0 * (planStartDate.getDay() / 7)}%`,
                                    width: `${Math.min(
                                        100.0 - 100.0 * (planStartDate.getDay() / 7),
                                        100.0 *
                                            ((Math.floor(
                                                (planEndDate.getTime() - planStartDate.getTime()) /
                                                    (24 * 60 * 60 * 1000),
                                            ) +
                                                1) /
                                                7),
                                    )}%`,
                                }}
                                className="same"></S.DayPlan>
                        </S.PlanRow>,
                    )
                } else if (day.getDay() === 0 && day <= planEndDate && day >= planStartDate) {
                    addedPlan.push(
                        <S.PlanRow>
                            <S.DayPlan
                                key={day}
                                style={{
                                    left: `${100.0 * (day.getDay() / 7)}%`,
                                    width: `${Math.min(
                                        100.0 - 100.0 * (day.getDay() / 7),
                                        100.0 *
                                            ((Math.floor(
                                                (planEndDate.getTime() - day.getTime()) / (24 * 60 * 60 * 1000),
                                            ) +
                                                1) /
                                                7),
                                    )}%`,
                                }}
                                className="same"></S.DayPlan>
                        </S.PlanRow>,
                    )
                }

                day = addDays(day, 1)
            }
            rows.push(
                <S.Week key={day}>
                    <S.DayBox>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                        <S.Day></S.Day>
                    </S.DayBox>
                    <S.DayHeader>{days}</S.DayHeader>
                    <S.PlanBox>
                        <S.PlanRows>{addedPlan}</S.PlanRows>
                    </S.PlanBox>
                </S.Week>,
            )
            addedPlan = []
            days = []
            plans = []
        }
        setCalendar([rows])
    }, [currentMonth, planStartDate, planEndDate])

    return <S.Calendar>{calendar}</S.Calendar>
}

const S = {
    Calendar: styled.ul`
        display: flex;
        flex-direction: column;
        height: 100%;
        max-height: calc(100% - 92px);
    `,
    Week: styled.li`
        position: relative;
        border-top: 1px solid ${({ theme }) => theme.color.middlegray};
        flex: 1 1 0%;
    `,
    DayBox: styled.ul`
        display: flex;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    `,
    Day: styled.li`
        flex: 1 1 0%;

        &:not(:last-child) {
            border-right: 1px solid ${({ theme }) => theme.color.middlegray};
        }
    `,
    DayHeader: styled.ul`
        display: flex;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 24px;
    `,
    DayNumber: styled.li`
        display: flex;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        padding: 0 12px;
        flex: 1 1 0%;

        &:first-child {
            color: ${({ theme }) => theme.color.warning};
        }

        &:last-child {
            color: ${({ theme }) => theme.color.main};
        }

        &.disable {
            color: ${({ theme }) => theme.color.middlegray};
        }
    `,
    PlanBox: styled.div`
        display: flex;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    `,
    PlanRows: styled.ul`
        flex: 1 1 0%;
        margin: 24px 0 0;
    `,
    PlanRow: styled.li`
        display: flex;
        position: relative;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        width: 100%;
        height: 24px;
    `,
    DayPlan: styled.li`
        flex: 1 1 0%;
        top: 0;
        position: absolute;
        height: 100%;
        cursor: pointer;
        border-radius: 4px;

        &.same {
            background-color: ${({ theme }) => theme.color.main};
        }

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            opacity: 0.6;
        }
    `,
}

export default Calendar
