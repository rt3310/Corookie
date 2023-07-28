import React from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import { IoReorderTwoSharp } from 'react-icons/io5'

const IssuePreview = ({ id, title, type, manager, priority }) => {
    const { openIssueDetail } = hooks.issueDetailState()
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
        openIssueDetail(id)
        closeProfile()
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
        <S.Wrap onClick={() => toggleIssueDetail(id)}>
            <S.Title>{title} </S.Title>
            <S.Description>
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
        width: 100%;
        min-height: 48px;
        margin: 4px 0;
        padding: 8px 16px;
        border: solid 1px #dbdbdb;
        border-radius: 8px;
        &:first-child {
            margin-top: 0;
        }
        &:last-child {
            margin-bottom: 0;
        }
    `,
    Title: styled.div`
        display: flex;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        color: ${({ theme }) => theme.color.black};
    `,
    Description: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
    `,
    Type: styled.div`
        margin: 0 8px;
        font-size: 13px;
        color: ${({ theme }) => theme.color.gray};
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
