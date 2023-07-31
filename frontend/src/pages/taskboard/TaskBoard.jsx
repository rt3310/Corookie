import React, { useEffect } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import * as components from 'components'

const TaskBoard = () => {
    const { setValue: setPriorityValue } = hooks.priorityState()
    const { setValue: setManagerValue } = hooks.managerState()
    const { setValue: setCategoryValue } = hooks.categoryState()
    const { setValue: setStatusValue } = hooks.statusState()
    const { showIssue } = hooks.taskState()
    const { issueDetailOpened, closeIssueDetail } = hooks.issueDetailState()

    useEffect(() => {
        setPriorityValue('중요도')
        setManagerValue('책임자')
        setCategoryValue('분류')
        setStatusValue('상태')
        closeIssueDetail()
    }, [])

    useEffect(() => {
        setPriorityValue('중요도')
        setManagerValue('책임자')
        setCategoryValue('분류')
        setStatusValue('상태')
    }, [showIssue])

    return (
        <S.Wrap>
            <components.TaskHeader />
            <S.IssueContainer>
                {showIssue && <components.IssueBoard />}
                {issueDetailOpened !== '-1' && <components.IssueDetail id={issueDetailOpened} />}
            </S.IssueContainer>
        </S.Wrap>
    )
}
const S = {
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
    `,
    IssueContainer: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
    `,
}

export default TaskBoard