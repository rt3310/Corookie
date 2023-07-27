import React, { useState } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import * as components from 'components'
import { IoTrashOutline, IoClose, IoPencil } from 'react-icons/io5'

const IssueDetail = ({ id }) => {
    const { closeIssueDetail } = hooks.issueDetailState()
    const [chosenState, setChosenState] = useState('todo')
    const managerList = ['황상미', '최효빈', '신승수', '박종서', '서원호', '권현수']
    const priorityList = ['Highest', 'High', 'Normal', 'Low', 'Lowest']
    const categoryList = ['frontend', 'backend', 'design', 'development', 'product', 'other']
    return (
        <S.Wrap>
            <S.Container>
                <S.Header>
                    <S.Title>
                        이슈 상세
                        <IoTrashOutline />
                    </S.Title>
                    <S.Close onClick={() => closeIssueDetail()}>
                        <IoClose />
                    </S.Close>
                </S.Header>
                <S.IssueTitle>
                    <S.IssueTitleText>사용자는 프로젝트를 생성한다.</S.IssueTitleText>
                    <IoPencil />
                </S.IssueTitle>
                <S.Status>
                    <S.StatusBox onClick={() => setChosenState('todo')} status="todo" chosen={chosenState}>
                        ToDo
                    </S.StatusBox>
                    <S.StatusBox onClick={() => setChosenState('inprogress')} status="inprogress" chosen={chosenState}>
                        InProgress
                    </S.StatusBox>
                    <S.StatusBox onClick={() => setChosenState('warning')} status="warning" chosen={chosenState}>
                        Done
                    </S.StatusBox>
                </S.Status>
                <S.Manager>
                    <S.Text>담당자</S.Text>
                    <components.ToggleButton defaultVal="detailManager" list={managerList} />
                </S.Manager>
                <S.Priority>
                    <S.Text>중요도</S.Text>
                    <components.ToggleButton defaultVal="detailPriority" list={priorityList} />
                </S.Priority>
                <S.Category>
                    <S.Text>분류</S.Text>
                    <components.ToggleButton defaultVal="detailCategory" list={categoryList} />
                </S.Category>
                <S.DescriptionBox>
                    <S.DescriptionBoxHeader>
                        <S.Text>설명</S.Text>
                        <IoPencil />
                    </S.DescriptionBoxHeader>
                    <S.Line></S.Line>
                    <S.DescriptionBoxContent>
                        사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.사용자는 프로젝트를 생성하고 생성하고
                        생성생성생성한다.사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.사용자는 프로젝트를
                        생성하고 생성하고 생성생성생성한다.
                    </S.DescriptionBoxContent>
                </S.DescriptionBox>
            </S.Container>
        </S.Wrap>
    )
}
const S = {
    Wrap: styled.div`
        display: flex;
        background-color: ${({ theme }) => theme.color.white};
        width: 372px;
        max-height: calc(100vh - 208px);
        padding: 24px 0;
        margin: 0 16px 0 0;
        border-radius: 8px;
    `,
    Container: styled.div`
        width: 100%;
        padding: 0 16px;
    `,
    Header: styled.div`
        display: flex;
        justify-content: space-between;
        height: 18px;
        align-items: center;
        margin-bottom: 12px;
    `,
    Title: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.title2};
        width: 109px;
        color: ${({ theme }) => theme.color.black};
        & svg {
            width: 18px;
            height: 18px;
            cursor: pointer;
        }
    `,
    Close: styled.div`
        display: flex;
        align-items: center;
        & svg {
            width: 20px;
            height: 20px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.main};
        }
    `,
    IssueTitle: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.content};
        padding: 12px 8px 16px;
        & svg {
            width: 16px;
            height: 16px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.black};
        }
    `,
    IssueTitleText: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
    `,
    Status: styled.div`
        display: flex;
        justify-content: space-between;
        margin-bottom: 16px;
        padding: 0 10px;
    `,
    StatusBox: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 60px;
        cursor: pointer;
        color: ${({ theme }) => theme.color.white};
        font-size: ${({ theme }) => theme.fontsize.content};
        background-color: ${({ status, chosen, theme }) =>
            chosen !== status
                ? theme.color.gray
                : chosen === 'todo'
                ? theme.color.success
                : chosen === 'inprogress'
                ? theme.color.pending
                : theme.color.warning};
        border-radius: 8px;
    `,
    Manager: styled.div`
        display: flex;
        align-items: center;
        padding: 4px 16px;
        font-size: ${({ theme }) => theme.fontsize.content};
        & p {
            margin-right: 40px;
        }
    `,
    Text: styled.p`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
    `,
    Priority: styled.div`
        display: flex;
        align-items: center;
        padding: 4px 16px;
        font-size: ${({ theme }) => theme.fontsize.content};
        & p {
            margin-right: 40px;
        }
    `,
    Category: styled.div`
        display: flex;
        align-items: center;
        padding: 4px 16px;
        font-size: ${({ theme }) => theme.fontsize.content};
        & p {
            margin-right: 53px;
        }
    `,
    DescriptionBox: styled.div`
        display: flex;
        flex-direction: column;
        /* border: solid 1px ${({ theme }) => theme.color.gray}; */
        border-radius: 8px;
        margin: 12px 8px;
        height: 100%;
        max-height: calc(100vh - 580px);
    `,
    DescriptionBoxHeader: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        padding: 16px;
        & svg {
            width: 20px;
            height: 20px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.black};
        }
    `,
    Line: styled.div`
        margin: 0 16px;
        height: 1px;
        background-color: ${({ theme }) => theme.color.gray};
    `,
    DescriptionBoxContent: styled.div`
        width: 100%;
        height: 100%;
        padding: 16px;
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
        flex-grow: 1;
        overflow-y: auto;
        &::-webkit-scrollbar {
            height: 0px;
            width: 4px;
        }
        &::-webkit-scrollbar-track {
            background: transparent;
        }
        &::-webkit-scrollbar-thumb {
            background: ${({ theme }) => theme.color.gray};
            border-radius: 45px;
        }
        &::-webkit-scrollbar-thumb:hover {
            background: ${({ theme }) => theme.color.gray};
        }
    `,
}

export default IssueDetail
