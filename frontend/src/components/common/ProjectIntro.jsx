import React, { useEffect, useRef } from 'react'
import styled from 'styled-components'

import { IoPeople } from 'react-icons/io5'
import * as hooks from 'hooks'
import * as components from 'components'

const ProjectIntro = () => {
    const { manager, managerOpened, openManager, closeManager } = hooks.setManagerState()
    const { members, memberOpened, openMember, closeMember } = hooks.memberState()
    const managerTextRef = useRef(null)
    const memberTextRef = useRef(null)

    const clickManager = e => {
        if (managerOpened) {
            console.log('1')
            closeManager()
            return
        }
        console.log('2')
        openManager()
    }

    const clickMember = () => {
        if (memberOpened) {
            closeMember()
        } else {
            openMember()
        }
    }

    useEffect(() => {
        console.log(managerOpened)
    }, [managerOpened])

    return (
        <S.Wrap>
            <S.Container>
                <S.Title>Co - 공통 프로젝트</S.Title>
                <S.Description>웹 개발 초보자들을 위한 프로젝트 협업 툴</S.Description>
                <S.Line />
                <S.MemberInfo>
                    <S.Manager>
                        <S.ManagerText ref={managerTextRef} onClick={e => clickManager(e)}>
                            관리자
                        </S.ManagerText>
                        <S.ManagerName>{manager}</S.ManagerName>
                    </S.Manager>
                    <components.ManagerSetting managerTextRef={managerTextRef} />
                    <S.Members ref={memberTextRef} onClick={() => clickMember()}>
                        <IoPeople />
                        {members.length}
                    </S.Members>
                    <components.MemberSetting memberTextRef={memberTextRef} />
                </S.MemberInfo>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 216px;
        height: 150px;
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        box-shadow: ${({ theme }) => theme.shadow.card};
        padding: 24px;
        margin: 16px 0;
    `,
    Container: styled.div`
        display: flex;
        position: relative;
        flex-direction: column;
        width: 100%;
        height: 100%;
        justify-content: flex-start;
        align-items: left;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 0 16px 0;
    `,
    Description: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        line-height: ${({ theme }) => theme.lineheight.content};
        padding-bottom: 8px;
    `,
    Line: styled.div`
        margin: 0 8px;
        min-height: 1px;
        width: 206px;
        align-self: center;
        z-index: 100;
        background-color: ${({ theme }) => theme.color.lightgray};
    `,
    MemberInfo: styled.div`
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
    `,
    Manager: styled.div`
        display: flex;
        justify-content: space-between;
        width: 81px;
        height: auto;
        padding: 8px 0;
    `,
    ManagerText: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.main};
        white-space: nowrap;
        cursor: pointer;
    `,
    ManagerName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
        white-space: nowrap;
    `,
    Members: styled.div`
        display: flex;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.title3};
        color: ${({ theme }) => theme.color.gray};
        cursor: pointer;
        & svg {
            width: 20px;
            height: 20px;
            color: ${({ theme }) => theme.color.gray};
            margin-right: 4px;
        }
        &:hover {
            color: ${({ theme }) => theme.color.main};
            & svg {
                color: ${({ theme }) => theme.color.main};
            }
        }
    `,
}

export default ProjectIntro
