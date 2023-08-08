import React, { useEffect } from 'react'
import styled from 'styled-components'
import * as components from 'components'

import * as hooks from 'hooks'
import * as utils from 'utils'
import { IoSearch, IoAdd } from 'react-icons/io5'

const TaskHeader = () => {
    const { showIssue, openIssue, openKanban } = hooks.taskState()
    const { issueCreateOpened, openIssueCreate } = hooks.issueCreateState()
    const { closeIssueDetail } = hooks.issueDetailState()

    const priorityList = ['Highest', 'High', 'Normal', 'Low', 'Lowest']
    const managerList = ['황상미', '최효빈', '신승수', '박종서', '서원호', '권현수']
    const categoryList = ['frontend', 'backend', 'design', 'development', 'product', 'other']
    const statusList = ['To Do', 'In Progress', 'Done']

    useEffect(() => {
        console.log(issueCreateOpened)
    }, [issueCreateOpened])

    const openKanbanHandle = () => {
        openKanban()
        closeIssueDetail()
    }

    return (
        <S.Header>
            <S.Title>
                <S.IssueTitle onClick={() => openIssue()} showIssue={showIssue}>
                    이슈 리스트
                </S.IssueTitle>
                <S.DivisionLine></S.DivisionLine>
                <S.KanbanTitle onClick={() => openKanbanHandle()} showIssue={showIssue}>
                    칸반 보드
                </S.KanbanTitle>
            </S.Title>
            <S.Search>
                <S.Filters>
                    <S.TopicFilter>
                        <S.SearchTopic placeholder="토픽으로 검색" />
                        <S.SearchButton>
                            <IoSearch />
                        </S.SearchButton>
                    </S.TopicFilter>
                    <S.ToggleFilter>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.priority} list={priorityList} />
                    </S.ToggleFilter>
                    <S.ToggleFilter>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.manager} list={managerList} />
                    </S.ToggleFilter>
                    <S.CategoryToggleFilter>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.category} list={categoryList} />
                    </S.CategoryToggleFilter>
                    {showIssue && (
                        <S.StatusToggleFilter>
                            <components.ToggleButton btnType={utils.ISSUE_OPTIONS.status} list={statusList} />
                        </S.StatusToggleFilter>
                    )}
                </S.Filters>
                {showIssue && (
                    <S.CreateButton onClick={() => openIssueCreate()}>
                        <IoAdd />
                        <S.CreateText>생성</S.CreateText>
                    </S.CreateButton>
                )}
            </S.Search>
        </S.Header>
    )
}

const S = {
    Header: styled.div`
        display: flex;
        justify-content: center;
        flex-direction: column;
        height: 109px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 16px;
    `,
    Title: styled.div`
        display: flex;
        align-items: center;
        height: 34px;
    `,
    IssueTitle: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        cursor: pointer;
        color: ${({ showIssue, theme }) => (showIssue ? theme.color.black : theme.color.gray)};
    `,
    DivisionLine: styled.div`
        margin: 0 8px;
        background-color: ${({ theme }) => theme.color.gray};
        height: 18px;
        width: 1px;
    `,
    KanbanTitle: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        color: ${({ showIssue, theme }) => (!showIssue ? theme.color.black : theme.color.gray)};
        cursor: pointer;
    `,
    Search: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
        height: 43px;
    `,
    Filters: styled.div`
        display: flex;
        height: 43px;
        padding: 0;
        align-items: flex-end;
        justify-content: flex-start;
        &:last-child {
            margin-right: auto;
        }
    `,
    TopicFilter: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        max-height: 31px;
        max-width: 153px;
        padding: 8px;
        outline: solid 1px ${({ theme }) => theme.color.gray};
        border-radius: 4px;
        margin: 0 8px 0 0;
    `,
    SearchTopic: styled.input`
        border: none;
        height: 16px;
        width: 100%;
        outline: none;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        ::placeholder {
            color: ${({ theme }) => theme.color.gray};
        }
    `,
    SearchButton: styled.button`
        background-color: transparent;
        cursor: pointer;
        & svg {
            width: 20px;
            height: 20px;
            color: ${({ theme }) => theme.color.gray};
        }
    `,
    ToggleFilter: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 80px;
        max-height: 31px;
        margin: 0 8px;
    `,
    CategoryToggleFilter: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 115px;
        max-height: 31px;
        margin: 0 8px;
    `,
    StatusToggleFilter: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100px;
        max-height: 31px;
        margin: 0 8px;
    `,
    CreateButton: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 31px;
        margin: 0 8px;
        padding: 8px;
        border-radius: 8px;
        white-space: nowrap;
        border: 1px solid ${({ theme }) => theme.color.main};
        background-color: ${({ theme }) => theme.color.main};
        color: ${({ theme }) => theme.color.white};
        margin: 0 16px;
        transition-duration: 0.2s;

        &:hover {
            background-color: ${({ theme }) => theme.color.white};
            color: ${({ theme }) => theme.color.main};
            box-shadow: none;
            & svg {
                color: ${({ theme }) => theme.color.main};
            }
        }
        cursor: pointer;
        & svg {
            color: ${({ theme }) => theme.color.white};
            height: 16px;
            width: 16px;
        }
    `,
    CreateText: styled.div`
        font-size: ${({ theme }) => theme.fontsize.sub1};
    `,
}
export default TaskHeader
