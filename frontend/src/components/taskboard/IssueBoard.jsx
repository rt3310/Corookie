import React, { useEffect, useRef } from 'react'
import styled from 'styled-components'
import * as components from 'components'

import * as hooks from 'hooks'

const IssueBoard = () => {
    const { issueCreateOpened, closeIssueCreate } = hooks.issueCreateState()
    let createRef = useRef(null)

    useEffect(() => {
        const handleOutside = e => {
            if (createRef.current && !createRef.current.contains(e.target)) {
                closeIssueCreate()
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [createRef])

    return (
        <S.Container>
            <S.Wrap>
                {issueCreateOpened && <components.IssueCreate ref={createRef} />}
                <components.IssuePreview
                    id="1"
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    id="2"
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    id="3"
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    id="4"
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    id="5"
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
                <components.IssuePreview
                    title="사용자는 프로젝트를 생성한다. "
                    type="frontend"
                    manager="황상미"
                    priority="Normal"
                />
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
        padding: 16px 0;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
    `,
    Wrap: styled.div`
        padding: 0 16px;
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
