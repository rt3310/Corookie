import React from 'react'
import styled from 'styled-components'

import { IoPeople } from 'react-icons/io5'

const ProjectIntro = () => {
    return (
        <S.Wrap>
            <S.Container>
                <S.Title>Co - 공통 프로젝트</S.Title>
                <S.Description>웹 개발 초보자들을 위한 프로젝트 협업 툴</S.Description>
                <S.Line />
                <S.Manager>
                    <S.ManagerText>관리자</S.ManagerText>
                    <S.ManagerName>최효빈</S.ManagerName>
                </S.Manager>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 216px;
        height: 150px;
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        box-shadow: ${({ theme }) => theme.shadow.card};
        padding: 24px;
        margin: 16px 0;
    `,
    Container: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
        justify-content: flex-start;
        align-items: left;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 0 16px 0;
    `,
    Description: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        line-height: ${({ theme }) => theme.lineheight.content};
        padding-bottom: 8px;
    `,
    Line: styled.div`
        margin: 0 8px;
        min-height: 1px;
        width: 206px;
        align-self: center;
        z-index: 100;
        background-color: ${({ theme }) => theme.color.lightgray};
    `,
    Manager: styled.div`
        display: flex;
        justify-content: space-between;
        width: 81px;
        height: auto;
        padding: 8px 0;
    `,
    ManagerText: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.main};
        white-space: nowrap;
    `,
    ManagerName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
        white-space: nowrap;
    `,
}

export default ProjectIntro
