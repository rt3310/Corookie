import React, { useState } from 'react'
import styled from 'styled-components'

import { format } from 'date-fns'
import { startOfMonth, endOfMonth, startOfWeek, endOfWeek } from 'date-fns'
import { isSameMonth, isSameDay, addDays } from 'date-fns'
import { useDrag, useDrop } from 'react-dnd'

const Calendar = ({ currentMonth }) => {
    const [{ isDragging }, dragRef, previewRef] = useDrag(() => ({
        collect: monitor => ({
            isDragging: monitor.isDragging(), // isDragging will be true if the user is dragging an item
        }),
        end: (item, monitor) => {
            const dragIndex = monitor.getItem().index
            const hoverIndex = item.index
        },
    }))
    const monthStart = startOfMonth(currentMonth)
    const monthEnd = endOfMonth(monthStart)
    const startDate = startOfWeek(monthStart)
    const endDate = endOfWeek(monthEnd)
    let rows = []
    let days = []
    let day = startDate

    while (day <= endDate) {
        for (let i = 0; i < 7; i++) {
            days.push(
                <S.DayNumber key={day} className={format(currentMonth, 'M') !== format(day, 'M') ? 'disable' : ''}>
                    {format(day, 'd')}
                </S.DayNumber>,
            )

            day = addDays(day, 1)
        }
        rows.push(
            <S.Week>
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
                    <S.PlanRows>
                        <S.PlanRow>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                            <S.DayPlan></S.DayPlan>
                        </S.PlanRow>
                    </S.PlanRows>
                </S.PlanBox>
            </S.Week>,
        )
        days = []
    }

    return <S.Calendar>{rows}</S.Calendar>
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
        cursor: pointer;
        border-radius: 4px;

        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            opacity: 0.6;
        }
    `,
}

export default Calendar
