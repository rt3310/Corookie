import React, { useState, useEffect, useRef } from 'react'
import styled from 'styled-components'

import { format } from 'date-fns'
import { startOfMonth, endOfMonth, startOfWeek, endOfWeek } from 'date-fns'
import { isSameMonth, isSameDay, addDays } from 'date-fns'

import * as hooks from 'hooks'

const Calendar = ({ currentMonth }) => {
    const monthStart = startOfMonth(currentMonth)
    const monthEnd = endOfMonth(monthStart)
    const startDate = startOfWeek(monthStart)
    const endDate = endOfWeek(monthEnd)
    const { planStartDate, planEndDate, onDragDate, setPlanStartDate, setPlanEndDate, setOnDragDate } =
        hooks.planDateState()
    const { openPlanRegister } = hooks.planRegisterState()
    const [calendar, setCalendar] = useState([])

    const setDate = date => {
        if (date < planStartDate) {
            setPlanStartDate(date)
            setPlanEndDate(onDragDate)
            return
        }

        if (date > planEndDate) {
            setPlanStartDate(onDragDate)
            setPlanEndDate(date)
        }
    }

    const handleDragStart = e => {
        setOnDragDate(new Date(e.target.firstChild.value))
        setPlanStartDate(new Date(e.target.firstChild.value))
        setPlanEndDate(new Date(e.target.firstChild.value))

        const transparentImg = document.createElement('canvas')
        transparentImg.width = 0
        transparentImg.height = 0
        e.dataTransfer.setDragImage(transparentImg, 0, 0)
        e.dataTransfer.effectAllowed = 'move'
    }

    const handleDragOver = e => {
        e.preventDefault()
        e.currentTarget.style.cursor = 'pointer'
    }

    useEffect(() => {
        let rows = []
        let days = []
        let dayNumbers = []
        let plans = []
        let day = startDate

        while (day <= endDate) {
            for (let i = 0; i < 7; i++) {
                days.push(
                    <S.Day
                        key={day}
                        draggable={true}
                        onDragStart={handleDragStart}
                        onDragEnter={e => setDate(new Date(e.target.firstChild.value))}
                        onDragOver={e => {
                            e.preventDefault()
                            e.currentTarget.style.cursor = 'pointer'
                        }}
                        onDragEnd={() => openPlanRegister()}>
                        <input type="hidden" value={day} />
                    </S.Day>,
                )
                dayNumbers.push(
                    <S.DayNumber key={day} className={format(currentMonth, 'M') !== format(day, 'M') ? 'disable' : ''}>
                        {format(day, 'd')}
                    </S.DayNumber>,
                )
                if (isSameDay(day, planStartDate)) {
                    plans.push(
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
                                onDragOver={handleDragOver}
                                className="same"></S.DayPlan>
                        </S.PlanRow>,
                    )
                } else if (day.getDay() === 0 && day <= planEndDate && day >= planStartDate) {
                    plans.push(
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
                                onDragOver={handleDragOver}
                                className="same"></S.DayPlan>
                        </S.PlanRow>,
                    )
                }

                day = addDays(day, 1)
            }
            rows.push(
                <S.Week key={day}>
                    <S.DayBox>{days}</S.DayBox>
                    <S.DayHeader>{dayNumbers}</S.DayHeader>
                    <S.PlanBox>
                        <S.PlanRows>{plans}</S.PlanRows>
                    </S.PlanBox>
                </S.Week>,
            )
            days = []
            plans = []
            dayNumbers = []
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

        -ms-user-select: none;
        -moz-user-select: -moz-none;
        -khtml-user-select: none;
        -webkit-user-select: none;
        user-select: none;

        z-index: 99;
    `,
    Day: styled.li`
        flex: 1 1 0%;
        cursor: pointer;

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
        z-index: 100;

        &.same {
            background-color: ${({ theme }) => theme.color.main};
        }

        &:active {
            cursor: pointer;
        }

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            opacity: 0.6;
            cursor: pointer;
        }
    `,
}

export default Calendar
