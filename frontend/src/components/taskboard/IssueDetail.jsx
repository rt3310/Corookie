import React, { useState, useRef, useEffect } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import * as components from 'components'
import * as api from 'api'
import { IoTrashOutline, IoClose, IoPencil } from 'react-icons/io5'
import * as utils from 'utils'

const IssueDetail = ({ id }) => {
    const { closeIssueDetail } = hooks.issueDetailState()
    const { tasks, setTasks } = hooks.tasksState()
    const { project } = hooks.projectState()
    const { members } = hooks.memberState()
    const { value: priorityValue, setValue: setPriorityValue } = hooks.detailPriorityState()
    const { value: managerValue, setValue: setManagerValue } = hooks.detailManagerState()
    const { value: categoryValue, setValue: setCategoryValue } = hooks.detailCategoryState()

    const [task, setTask] = useState(null)

    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')
    // const [status, setStatus] = useState('')

    const priorityList = ['Highest', 'High', 'Normal', 'Low', 'Lowest']
    const categoryList = ['frontend', 'backend', 'design', 'development', 'product', 'other']

    const [editTitle, setEditTitle] = useState(false)
    const [editContent, setEditContent] = useState(false)

    let titleRef = useRef()
    let contentRef = useRef()

    useEffect(() => {
        setEditTitle(false)
        setEditContent(false)
        api.apis
            .getIssueDetail(project.id, id)
            .then(response => {
                console.log('task', response.data)
                setTask(response.data)
                setTitle(response.data.topic)
                setContent(response.data.description)
                setPriorityValue(response.data.priority)
                setManagerValue({ managerId: response.data.managerId, managerName: response.data.managerName })
                setCategoryValue(response.data.category)
            })
            .catch(error => {
                console.log('태스크 상세 실패', error)
            })
    }, [id])

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
    const titleKeyDown = async e => {
        if (e.key === 'Enter') {
            setEditTitle(false)
            const issueRes = await api.apis.changeIssueTitle(project.id, task.id, { topic: title })
            setTask(issueRes.data)
            setTitle(task.topic)
            const issuesRes = await api.apis.getIssueList(project.id)
            setTasks(issuesRes.data)
        }
    }
    const handleContentChange = e => setContent(e.target.value)
    const contentKeyDown = async e => {
        if (e.key === 'Enter') {
            setEditContent(false)
            const issueRes = await api.apis.changeIssueContent(project.id, task.id, { description: content })
            setTask(issueRes.data)
            setContent(task.description)
            const issuesRes = await api.apis.getIssueList(project.id)
            setTasks(issuesRes.data)
        }
    }

    const onRemove = async () => {
        if (window.confirm('정말 삭제하시겠습니까?')) {
            closeIssueDetail()
            const issueRes = await api.apis.deleteIssue(project.id, task.id)
            setTask(issueRes.data)
            const issuesRes = await api.apis.getIssueList(project.id)
            setTasks(issuesRes.data)

            // const updatedTasks = tasks.filter(task => task.id !== id)
            // api.apis
            //     .deleteIssue(project.id, task.id)
            //     .then(response => {
            //         console.log(response.data)
            //         setTasks(updatedTasks)
            //         alert('삭제되었습니다. ')
            //         closeIssueDetail()
            //     })
            //     .catch(error => {
            //         console.log(error)
            //     })
        }
    }
    const changeStatus = async status => {
        const issueRes = await api.apis.changeIssueStatus(project.id, id, { progress: status })
        setTask(issueRes.data)
        const issuesRes = await api.apis.getIssueList(project.id)
        setTasks(issuesRes.data)
    }

    // useEffect(() => {
    //     setPriorityValue(task.priority)
    //     setManagerValue(task.managerName)
    //     setCategoryValue(task.category)
    // }, [id])

    useEffect(() => {
        if (task && task.id) {
            console.log('중요도', priorityValue)
            const updatedTasks = tasks.map(task => (task.id === id ? { ...task, priority: `${priorityValue}` } : task))
            api.apis
                .changeIssuePriority(project.id, id, {
                    priority: priorityValue,
                })
                .then(response => {
                    console.log(response.data)
                    setTasks(updatedTasks)
                })
                .catch(error => console.log(error))
        }
    }, [priorityValue])

    useEffect(() => {
        if (task && task.id) {
            const updatedTasks = tasks.map(task => (task.id === id ? { ...task, manager: `${managerValue}` } : task))
            api.apis
                .changeIssueManager(project.id, id, { managerId: managerValue.managerId })
                .then(response => {
                    console.log(response.data)
                    setTasks(updatedTasks)
                })
                .catch(error => console.log(error))
        }
    }, [setManagerValue])

    useEffect(() => {
        if (task && task.id) {
            const updatedTasks = tasks.map(task => (task.id === id ? { ...task, category: `${categoryValue}` } : task))
            api.apis
                .changeIssueCategory(project.id, id, { category: categoryValue })
                .then(response => {
                    console.log(response.data)
                    setTasks(updatedTasks)
                })
                .catch(error => console.log(error))
        }
    }, [categoryValue])

    if (!task) {
        return
    }

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
                            <S.IssueTitleText>{task.topic}</S.IssueTitleText>
                        )}
                        {!editTitle && <IoPencil onClick={() => setEditTitle(true)} />}
                    </S.IssueTitle>
                    <S.Close onClick={() => closeIssueDetail()}>
                        <IoClose />
                    </S.Close>
                </S.Header>
                <S.Status>
                    <S.StatusBox onClick={() => changeStatus('todo')} status="todo" chosen={task.progress}>
                        To Do
                    </S.StatusBox>
                    <S.StatusBox onClick={() => changeStatus('inProgress')} status="inProgress" chosen={task.progress}>
                        In Progress
                    </S.StatusBox>
                    <S.StatusBox onClick={() => changeStatus('done')} status="done" chosen={task.progress}>
                        Done
                    </S.StatusBox>
                </S.Status>
                <S.Manager>
                    <S.Text>담당자</S.Text>
                    <S.ButtonContainer>
                        <components.ManagerToggleButton btnType={utils.ISSUE_OPTIONS.detailManager} list={members} />
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
                        <S.DescriptionBoxContent>{task.description}</S.DescriptionBoxContent>
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
        margin: 4px;
        cursor: pointer;
        color: ${({ theme }) => theme.color.white};
        font-size: ${({ theme }) => theme.fontsize.content};
        background-color: ${({ status, chosen, theme }) =>
            chosen !== status
                ? theme.color.gray
                : chosen === 'todo'
                ? theme.color.success
                : chosen === 'inProgress'
                ? theme.color.pending
                : theme.color.orange};
        border-radius: 8px;
        transition-duration: 0.2s;
        &:hover {
            background-color: ${({ status, theme }) =>
                status === 'todo'
                    ? theme.color.success
                    : status === 'inProgress'
                    ? theme.color.pending
                    : theme.color.orange};
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
