import React from 'react'
import styled from 'styled-components'

import { IoIosClose } from 'react-icons/io'

import * as components from 'components'
import * as style from 'style'

const PlanRegister = ({ setOpenRegister }) => {
    return (
        <S.Wrap>
            <S.Header>
                <S.Title>일정 생성</S.Title>
                <S.CloseButton onClick={() => setOpenRegister(false)}>
                    <IoIosClose />
                </S.CloseButton>
            </S.Header>
            <S.PlanTitleBox>
                <S.PlanTitleLabel>제목</S.PlanTitleLabel>
                <S.PlanTitleInput placeholder="제목 작성" />
            </S.PlanTitleBox>
            <components.PlanOptionToggle state="date" />
            <components.PlanOptionToggle state="member" />
            <components.PlanOptionToggle state="category" />
            <S.PlanContentBox>
                <S.PlanContentHeader>내용</S.PlanContentHeader>
                <S.PlanContentInput placeholder="내용을 입력하세요"></S.PlanContentInput>
            </S.PlanContentBox>
            <S.SaveButton>저장</S.SaveButton>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 400px;
        min-width: 400px;
        border-radius: 8px;
        flex-direction: column;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 16px;
        /* animation: ${style.leftSlide} 0.4s linear; */
    `,
    Header: styled.div`
        display: flex;
        align-items: center;
        height: 34px;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
    `,
    CloseButton: styled.div`
        color: ${({ theme }) => theme.color.gray};
        margin: 0 0 0 auto;
        cursor: pointer;

        &:hover {
            color: ${({ theme }) => theme.color.main};
        }
        & svg {
            width: 40.4px;
            height: 40.4px;
            margin-right: -16px;
        }
    `,
    PlanTitleBox: styled.div`
        display: flex;
        height: 48px;
        border: 1px solid ${({ theme }) => theme.color.gray};
        border-radius: 8px;
        margin: 32px 0 22px;
        padding: 8px 0;
    `,
    PlanTitleLabel: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        flex-grow: 1;
        border-right: 1px solid ${({ theme }) => theme.color.gray};
    `,
    PlanTitleInput: styled.input`
        display: flex;
        align-items: center;
        flex-grow: 5;
        border: none;
        outline: none;
        font-size: ${({ theme }) => theme.fontsize.title3};
        padding: 0 16px;
    `,
    PlanContentBox: styled.div`
        min-height: 300px;
        border: 1px solid ${({ theme }) => theme.color.gray};
        border-radius: 8px;
        margin: 28px 8px;
        padding: 0 16px 16px;
    `,
    PlanContentHeader: styled.div`
        display: flex;
        align-items: center;
        height: 48px;
        min-height: 48px;
        border-bottom: 1px solid ${({ theme }) => theme.color.lightgray};
    `,
    PlanContentInput: styled.textarea`
        height: calc(100% - 48px);
        width: 100%;
        outline: none;
        resize: none;
        border: none;
        padding: 16px 0;
        font-family: 'Noto Sans KR', 'Pretendard', sans-serif;
        font-size: ${({ theme }) => theme.fontsize.sub1};
    `,
    SaveButton: styled.button`
        width: 60px;
        height: 38px;
        align-self: center;
        border-radius: 8px;
        border: 1px solid ${({ theme }) => theme.color.main};
        background-color: ${({ theme }) => theme.color.main};
        color: ${({ theme }) => theme.color.white};
        margin: auto 0 0 0;
        transition: all 0.2s linear;

        &:hover {
            background-color: ${({ theme }) => theme.color.white};
            color: ${({ theme }) => theme.color.main};
        }
    `,
}

export default PlanRegister