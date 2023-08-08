import React, { useState, useRef, useEffect } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import * as components from 'components'
import { IoTrashOutline, IoClose, IoPencil } from 'react-icons/io5'
import * as utils from 'utils'

const IssueDetail = ({ id }) => {
    const { closeIssueDetail } = hooks.issueDetailState()
    const { tasks, setTasks } = hooks.tasksState()
    const { value: priorityValue, setValue: setPriorityValue } = hooks.detailPriorityState()
    const { value: managerValue, setValue: setManagerValue } = hooks.detailManagerState()
    const { value: categoryValue, setValue: setCategoryValue } = hooks.detailCategoryState()

    const taskWithId = tasks.find(task => task.id === id)

    const managerList = ['황상미', '최효빈', '신승수', '박종서', '서원호', '권현수']
    const priorityList = ['Highest', 'High', 'Normal', 'Low', 'Lowest']
    const categoryList = ['frontend', 'backend', 'design', 'development', 'product', 'other']
    const [title, setTitle] = useState(taskWithId.title)
    const [content, setContent] = useState(taskWithId.content)
    const [priority, setPriority] = useState(taskWithId.priority)
    const [manager, setManager] = useState(taskWithId.manager)
    const [category, setCategory] = useState(taskWithId.type)
    const [editTitle, setEditTitle] = useState(false)
    const [editContent, setEditContent] = useState(false)

    const statusOfTask = taskWithId ? taskWithId.status : 'Not found'

    const [chosenState, setChosenState] = useState(statusOfTask)

    let titleRef = useRef()
    let contentRef = useRef()

    useEffect(() => {
        if (editTitle === true) {
            titleRef.current.focus()
        }
    }, [editTitle])

    useEffect(() => {
        if (editContent === true) {
            const focusContent = contentRef.current
            focusContent.selectionStart = focusContent.value.length
            focusContent.focus()
        }
    }, [editContent])

    const handleTitleChange = e => {
        setTitle(e.target.value)
    }
    const titleKeyDown = e => {
        if (e.key === 'Enter') {
            setEditTitle(false)
            const updatedTasks = tasks.map(task => (task.id === id ? { ...task, title } : task))
            setTasks(updatedTasks)
        }
    }
    const handleContentChange = e => setContent(e.target.value)
    const contentKeyDown = e => {
        if (e.key === 'Enter') {
            setEditContent(false)
            const updatedTasks = tasks.map(task => (task.id === id ? { ...task, content } : task))
            setTasks(updatedTasks)
        }
    }

    const onRemove = () => {
        if (window.confirm('정말 삭제하시겠습니까?')) {
            const updatedTasks = tasks.filter(task => task.id !== id)
            setTasks(updatedTasks)
            alert('삭제되었습니다. ')
            closeIssueDetail()
        }
    }
    const changeStatus = status => {
        const updatedTasks = tasks.map(task => (task.id === id ? { ...task, status } : task))
        setTasks(updatedTasks)
    }

    useEffect(() => {
        const taskWithId = tasks.find(task => task.id === id)
        const statusOfTask = taskWithId ? taskWithId.status : 'Not found'
        setChosenState(statusOfTask)
    }, [id, tasks])

    useEffect(() => {
        const taskWithId = tasks.find(task => task.id === id)
        setPriorityValue(taskWithId.priority)
        setManagerValue(taskWithId.manager)
        setCategoryValue(taskWithId.type)
    }, [id])

    useEffect(() => {
        const updatedTasks = tasks.map(task => (task.id === id ? { ...task, priority: `${priorityValue}` } : task))
        setTasks(updatedTasks)
    }, [priorityValue])

    useEffect(() => {
        const updatedTasks = tasks.map(task => (task.id === id ? { ...task, manager: `${managerValue}` } : task))
        setTasks(updatedTasks)
    }, [managerValue])

    useEffect(() => {
        const updatedTasks = tasks.map(task => (task.id === id ? { ...task, type: `${categoryValue}` } : task))
        setTasks(updatedTasks)
    }, [categoryValue])

    return (
        <S.Wrap>
            <S.Container>
                <S.Header>
                    <S.IssueTitle>
                        {editTitle ? (
                            <S.TitleEdit
                                ref={titleRef}
                                type="text"
                                value={title}
                                onChange={handleTitleChange}
                                onKeyDown={titleKeyDown}
                            />
                        ) : (
                            <S.IssueTitleText>{taskWithId.title}</S.IssueTitleText>
                        )}
                        {!editTitle && <IoPencil onClick={() => setEditTitle(true)} />}
                    </S.IssueTitle>
                    <S.Close onClick={() => closeIssueDetail()}>
                        <IoClose />
                    </S.Close>
                </S.Header>
                <S.Status>
                    <S.StatusBox onClick={() => changeStatus('toDo')} status="toDo" chosen={chosenState}>
                        ToDo
                    </S.StatusBox>
                    <S.StatusBox onClick={() => changeStatus('inProgress')} status="inProgress" chosen={chosenState}>
                        InProgress
                    </S.StatusBox>
                    <S.StatusBox onClick={() => changeStatus('done')} status="done" chosen={chosenState}>
                        Done
                    </S.StatusBox>
                </S.Status>
                <S.Manager>
                    <S.Text>담당자</S.Text>
                    <S.ButtonContainer>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.detailManager} list={managerList} />
                    </S.ButtonContainer>
                </S.Manager>
                <S.Priority>
                    <S.Text>중요도</S.Text>
                    <S.ButtonContainer>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.detailPriority} list={priorityList} />
                    </S.ButtonContainer>
                </S.Priority>
                <S.Category>
                    <S.Text>분류</S.Text>
                    <S.ButtonContainer>
                        <components.ToggleButton btnType={utils.ISSUE_OPTIONS.detailCategory} list={categoryList} />
                    </S.ButtonContainer>
                </S.Category>
                <S.DescriptionBox>
                    <S.DescriptionBoxHeader>
                        <S.Text>설명</S.Text>
                        {!editContent ? <IoPencil onClick={() => setEditContent(true)} /> : null}
                    </S.DescriptionBoxHeader>
                    <S.Line></S.Line>
                    {editContent ? (
                        <S.ContentEdit
                            ref={contentRef}
                            value={content}
                            onChange={handleContentChange}
                            onKeyDown={contentKeyDown}
                        />
                    ) : (
                        <S.DescriptionBoxContent>{taskWithId.content}</S.DescriptionBoxContent>
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
    ButtonContainer: styled.div`
        display: flex;
        justify-content: center;
        width: 100%;
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
        padding: 12px 8px 16px;
        width: 100%;
        & svg {
            width: 16px;
            height: 16px;
            cursor: pointer;
            transition: 0.2s;
            color: ${({ theme }) => theme.color.gray};
            &:hover {
                color: ${({ theme }) => theme.color.main};
            }
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
        margin-bottom: 16px;
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
                : chosen === 'toDo'
                ? theme.color.success
                : chosen === 'inProgress'
                ? theme.color.pending
                : theme.color.warning};
        border-radius: 8px;
        transition-duration: 0.2s;
        &:hover {
            background-color: ${({ status, theme }) =>
                status === 'toDo'
                    ? theme.color.success
                    : status === 'inProgress'
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
        white-space: nowrap;
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
            color: ${({ theme }) => theme.color.gray};
            transition: 0.2s;
            &:hover {
                color: ${({ theme }) => theme.color.main};
            }
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
