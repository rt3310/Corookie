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
    const [title, setTitle] = useState('사용자는 프로젝트를 생성한다. ')
    const [content, setContent] = useState(
        '사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.사용자는 프로젝트를 생성하고 생성하고 생성생성생성한다.',
    )
    const [editTitle, setEditTitle] = useState(false)
    const [editContent, setEditContent] = useState(false)
    const handleTitleChange = e => setTitle(e.target.value)
    const titleKeyDown = e => {
        if (e.key === 'Enter') {
            setEditTitle(false)
        }
    }
    const handleContentChange = e => setContent(e.target.value)
    const contentKeyDown = e => {
        if (e.key === 'Enter') {
            setEditContent(false)
        }
    }

    const onRemove = () => {
        if (window.confirm('정말 삭제하시겠습니까?')) {
            alert('삭제되었습니다. ')
            closeIssueDetail()
        }
    }
    return (
        <S.Wrap>
            <S.Container>
                <S.Header>
                    <S.IssueTitle>
                        {editTitle ? (
                            <S.TitleEdit
                                type="text"
                                value={title}
                                onChange={handleTitleChange}
                                onKeyDown={titleKeyDown}
                            />
                        ) : (
                            <S.IssueTitleText>{title}</S.IssueTitleText>
                        )}
                        {!editTitle && <IoPencil onClick={() => setEditTitle(true)} />}
                    </S.IssueTitle>
                    <S.Close onClick={() => closeIssueDetail()}>
                        <IoClose />
                    </S.Close>
                </S.Header>
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
                        {!editContent ? <IoPencil onClick={() => setEditContent(true)} /> : null}
                    </S.DescriptionBoxHeader>
                    <S.Line></S.Line>
                    {editContent ? (
                        <S.ContentEdit value={content} onChange={handleContentChange} onKeyDown={contentKeyDown} />
                    ) : (
                        <S.DescriptionBoxContent>{content}</S.DescriptionBoxContent>
                    )}
                </S.DescriptionBox>
                <S.Delete>
                    <IoTrashOutline onClick={() => onRemove()} />
                </S.Delete>
            </S.Container>
        </S.Wrap>
    )
}
const S = {
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        background-color: ${({ theme }) => theme.color.white};
        width: 320px;
        max-height: calc(100vh - 208px);
        padding: 24px 0 16px;
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
        margin-bottom: 20px;
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
        justify-content: flex-start;
        align-items: center;
        padding: 12px 8px;
        width: 100%;
        & svg {
            width: 16px;
            height: 16px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.black};
            margin-left: 8px;
        }
    `,
    TitleEdit: styled.input`
        flex: 1 1 0%;
        border: none;
        outline: none;
        width: auto;
        font-family: 'Noto Sans KR', 'Pretendard', sans-serif;
        font-size: ${({ theme }) => theme.fontsize.title3};
    `,
    IssueTitleText: styled.div`
        max-width: 225px;
        white-space: nowrap;
        font-size: ${({ theme }) => theme.fontsize.title3};
        overflow-x: auto;
        overflow-y: hidden;
        /* text-overflow: ellipsis; */
        padding: 4px 0;

        &::-webkit-scrollbar {
            height: 3px;
            width: 0px;
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
    Status: styled.div`
        display: flex;
        justify-content: space-between;
        margin-bottom: 8px;
        padding: 0 10px;
    `,
    StatusBox: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        width: 84px;
        height: 55px;
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
        &:hover {
            background-color: ${({ status, theme }) =>
                status === 'todo'
                    ? theme.color.success
                    : status === 'inprogress'
                    ? theme.color.pending
                    : theme.color.warning};
        }
    `,
    Manager: styled.div`
        display: flex;
        align-items: center;
        padding: 8px 16px;
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
        padding: 8px 16px;
        font-size: ${({ theme }) => theme.fontsize.content};
        & p {
            margin-right: 40px;
        }
    `,
    Category: styled.div`
        display: flex;
        align-items: center;
        padding: 8px 16px;
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
        max-height: calc(100vh - 540px);
    `,
    DescriptionBoxHeader: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        padding: 16px 8px;
        & svg {
            width: 16px;
            height: 16px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.black};
        }
    `,
    Line: styled.div`
        margin: 0 8px;
        height: 1px;
        background-color: ${({ theme }) => theme.color.gray};
    `,
    ContentEdit: styled.textarea`
        width: 100%;
        flex: 1 1 0%;
        border: none;
        outline: none;
        padding: 16px 8px;
        font-family: 'Noto Sans KR', 'Pretendard', sans-serif;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 20px;
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
    DescriptionBoxContent: styled.div`
        width: 100%;
        height: 100%;
        padding: 16px 8px;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        color: ${({ theme }) => theme.color.black};
        flex-grow: 1;
        overflow-y: auto;
        line-height: 20px;
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
    Delete: styled.div`
        display: flex;
        justify-content: flex-end;
        align-items: center;
        width: 100%;
        & svg {
            width: 20px;
            height: 20px;
            cursor: pointer;
            color: ${({ theme }) => theme.color.gray};
            &:hover {
                color: ${({ theme }) => theme.color.main};
            }
        }
    `,
}

export default IssueDetail
