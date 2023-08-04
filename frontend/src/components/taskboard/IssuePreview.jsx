import React, { useEffect } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import { IoReorderTwoSharp } from 'react-icons/io5'

const IssuePreview = ({ id, title, type, manager, priority, status }) => {
    const { issueDetailOpened, openIssueDetail, closeIssueDetail } = hooks.issueDetailState()
    const { closeProfile } = hooks.profileState()
    const renderPriority = priority => {
        switch (priority) {
            case 'Normal':
                return <IoReorderTwoSharp />
            default:
                return null
        }
    }

    const toggleIssueDetail = id => {
        if (issueDetailOpened === id) {
            closeIssueDetail()
        } else {
            openIssueDetail(id)
            closeProfile()
        }
    }

    const renderProfile = manager => {
        switch (manager) {
            case '황상미':
                return <img src={require('images/thread_profile.png').default} alt="프로필 이미지" />
            default:
                return null
        }
    }
    return (
        <S.Wrap onClick={() => toggleIssueDetail(id)} id={id} issueDetailOpened={issueDetailOpened}>
            {title}
            <S.Description>
                <S.Status status={status} />
                <S.Type>{type}</S.Type>
                <S.Profile>{renderProfile(manager)}</S.Profile>
                <S.Priority>{renderPriority(priority)}</S.Priority>
            </S.Description>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        min-height: 48px;
        margin: 8px 0;
        padding: 8px 16px;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        background-color: ${({ theme }) => theme.color.white};
        border: 2px solid
            ${({ theme, id, issueDetailOpened }) => (id === issueDetailOpened ? theme.color.main : theme.color.white)};
        border-radius: 8px;
        &:first-child {
            margin-top: 0;
            &:hover {
                transform: translateY(0);
                box-shadow: 1px 1px 5px 1px ${({ theme }) => theme.color.middlegray};
            }
        }
        &:last-child {
            margin-bottom: 0;
        }
        transition-duration: 0.2s;
        cursor: pointer;
        &:hover {
            transform: translateY(-3px);
            box-shadow: 1px 1px 5px 1px ${({ theme }) => theme.color.middlegray};
        }
    `,
    Status: styled.div`
        padding: 4px;
        width: 2px;
        height: 2px;
        border-radius: 100%;
        background-color: ${({ theme, status }) => {
            switch (status) {
                case 'toDo':
                    return theme.color.success
                case 'inProgress':
                    return theme.color.pending
                default:
                    return theme.color.warning
            }
        }};
    `,
    Description: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
    `,
    Type: styled.div`
        margin: 0 8px;
        font-size: 13px;
        color: ${({ theme }) => theme.color.middlegray};
    `,
    Profile: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0 8px;
        & img {
            width: 30px;
            height: 30px;
        }
    `,
    Priority: styled.div`
        display: flex;
        width: 20px;
        margin: 0 8px;
        & svg {
            width: 20px;
            height: 20px;
            color: ${({ theme }) => theme.color.pending};
        }
    `,
}

export default IssuePreview
