import React, { useEffect, useRef } from 'react'
import styled from 'styled-components'
import * as components from 'components'

import * as hooks from 'hooks'

const IssueBoard = () => {
    const { issueCreateOpened } = hooks.issueCreateState()
    const { tasks } = hooks.tasksState()

    return (
        <S.Container>
            <S.Wrap>
                {issueCreateOpened && <components.IssueCreate />}
                {tasks.map((task, idx) => {
                    return (
                        <components.IssuePreview
                            key={idx}
                            id={task.id}
                            title={task.title}
                            type={task.type}
                            manager={task.manager}
                            priority={task.priority}
                            status={task.status}
                        />
                    )
                })}
            </S.Wrap>
        </S.Container>
    )
}

const S = {
    Container: styled.div`
        display: flex;
        flex-direction: column;
        height: 100%;
        width: auto;
        flex-grow: 1;
        max-height: calc(100vh - 208px);
        margin: 0 16px 16px;
        border-radius: 8px;
        /* background-color: ${({ theme }) => theme.color.white}; */
    `,
    Wrap: styled.div`
        /* padding: 0 16px; */
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

export default IssueBoard
