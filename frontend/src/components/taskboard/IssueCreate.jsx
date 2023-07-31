import React, { useState, useRef, useEffect } from 'react'
import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'
const IssueCreate = () => {
    const { closeIssueCreate } = hooks.issueCreateState()
    const { value: priority, setValue: setPriority } = hooks.createPriorityState()
    const { value: manager, setValue: setManager } = hooks.createManagerState()
    const { value: category, setValue: setCategory } = hooks.createCategoryState()
    const [title, setTitle] = useState('')
    let createRef = useRef(null)

    useEffect(() => {}, [])

    useEffect(() => {
        setPriority('중요도')
        setManager('관리자')
        setCategory('분류')
        const handleOutside = e => {
            if (createRef.current && !createRef.current.contains(e.target)) {
                closeIssueCreate()
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [createRef, closeIssueCreate])

    const handleTitleChange = e => setTitle(e.target.value)

    const saveIssue = () => {
        if (title !== '' && priority !== '중요도' && manager !== '책임자' && category !== '분류') {
            closeIssueCreate()
        } else {
            alert('정보를 입력해주세요. ')
        }
    }

    const managerList = ['황상미', '최효빈', '신승수', '박종서', '서원호', '권현수']
    const priorityList = ['Highest', 'High', 'Normal', 'Low', 'Lowest']
    const categoryList = ['frontend', 'backend', 'design', 'development', 'product', 'other']
    return (
        <S.Container ref={createRef}>
            <S.Title value={title} onChange={handleTitleChange} placeholder="제목을 입력하세요" />
            <S.Properties>
                <components.ToggleButton defaultVal="createManager" list={managerList} />
                <components.ToggleButton defaultVal="createPriority" list={priorityList} />
                <components.ToggleButton defaultVal="createCategory" list={categoryList} />
            </S.Properties>
            <S.Save onClick={() => saveIssue()}>저장</S.Save>
        </S.Container>
    )
}

const S = {
    Container: styled.div`
        display: flex;
        justify-content: space-between;
        width: 100%;
        min-height: 48px;
        margin: 4px 0;
        padding: 8px 16px;
        border: solid 1px #dbdbdb;
        border-radius: 8px;
    `,
    Title: styled.input`
        width: 100%;
        border: none;
        outline: none;
    `,
    Properties: styled.div`
        display: flex;
        justify-content: space-between;
        min-width: 310px;
        & div {
            padding: 0px;
        }
    `,
    Save: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        width: auto;
        padding: 0 8px;
        margin: 0 8px;
        border-radius: 4px;
        white-space: nowrap;
        background-color: ${({ theme }) => theme.color.main};
        font-size: ${({ theme }) => theme.fontsize.sub1};
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
    `,
}

export default IssueCreate
