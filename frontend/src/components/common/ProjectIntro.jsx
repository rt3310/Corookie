import React, { useState, useEffect, useRef } from 'react'
import styled from 'styled-components'

import { IoPeople, IoChevronForward, IoClose, IoRocket } from 'react-icons/io5'
import { AiOutlineCheckSquare } from 'react-icons/ai'

import * as hooks from 'hooks'
import * as components from 'components'
import * as api from 'api'

const ProjectIntro = () => {
    const { manager, managerOpened, openManager, closeManager } = hooks.setManagerState()
    const { members, setMembers, memberOpened, openMember, closeMember } = hooks.memberState()
    const { project, setProject } = hooks.projectState()
    const [flip, setFlip] = useState(false)
    const managerTextRef = useRef(null)
    const memberTextRef = useRef(null)

    const projects = [
        '프로젝트 1',
        '흐로젝트 2',
        '그로젝트 3',
        '느로젝트 4',
        '드로젝트 5',
        '르로젝트 6',
        '므로젝트 7',
        '브로젝트 8',
    ]

    let flippedRef = useRef(null)

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

    useEffect(() => {
        const handleOutside = e => {
            if (
                flippedRef.current &&
                !flippedRef.current.contains(e.target) &&
                !flippedRef.current.contains(e.target)
            ) {
                setFlip(false)
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [flippedRef])

    useEffect(() => {
        api.apis
            .getProjectMembers(project.id)
            .then(response => {
                console.log(project.id)
                console.log('멤버 불러오기 성공', response.data)
                setMembers(response.data)
            })
            .catch(error => {
                console.log('멤버 불러오기 실패', error)
            })
    }, [project])

    return (
        <S.Wrap>
            <S.RotateContainer className={flip ? 'card' : null} ref={flippedRef}>
                <S.Container className="cards">
                    <S.Title>
                        {project.name}
                        <IoChevronForward onClick={() => setFlip(!flip)} />
                    </S.Title>
                    <S.Description>{project.description}</S.Description>
                    <S.Line />
                    <S.MemberInfo>
                        <S.Manager>
                            <S.ManagerText ref={managerTextRef} onClick={e => clickManager(e)}>
                                관리자
                            </S.ManagerText>
                            <S.ManagerName>{project.managerName}</S.ManagerName>
                        </S.Manager>
                        <S.Members ref={memberTextRef} onClick={() => clickMember()}>
                            <IoPeople />
                            {members.length}
                        </S.Members>
                        <components.MemberSetting memberTextRef={memberTextRef} />
                    </S.MemberInfo>
                </S.Container>
                <S.ProjectsContainer className="cards">
                    <S.InnerContainer>
                        <S.Projects>
                            {projects.map((project, index) => {
                                return (
                                    <S.Project key={index}>
                                        <S.ProjectText>{project.charAt(0)}</S.ProjectText>
                                    </S.Project>
                                )
                            })}
                        </S.Projects>
                    </S.InnerContainer>
                </S.ProjectsContainer>
            </S.RotateContainer>
            <components.ManagerSetting managerTextRef={managerTextRef} />
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 216px;
        height: 150px;
        flex-direction: column;
        transition: transform 0.3s;
        transform: perspective(800px) rotateY(0deg);
        position: relative;
        z-index: 10000;
        & > .card {
            transform: rotateY(180deg);
        }
    `,
    RotateContainer: styled.div`
        display: flex;
        transition: all 0.5s;
        transform-style: preserve-3d;
        height: 100%;
        width: 100%;
        margin: 16px 0;
        position: relative;
        overflow: visible;
        & > .cards {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            backface-visibility: hidden;
        }
    `,
    Container: styled.div`
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: left;
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        box-shadow: ${({ theme }) => theme.shadow.card};
        padding: 24px;
        width: 100%;
        height: 100%;
    `,
    ProjectsContainer: styled.div`
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 8px;
        z-index: 5;
        transform: rotateY(180deg);
        padding: 8px;
        overflow-y: scroll;
        width: 100%;
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
    InnerContainer: styled.div`
        height: 134px;
        min-width: 100%;
        overflow: visible;
    `,
    Projects: styled.div`
        position: relative;
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 4px;
        z-index: 6;
    `,
    ProjectText: styled.div`
        display: flex;
        align-items: center;
        font-size: ${({ theme }) => theme.fontsize.title2};
        z-index: 7;
    `,
    Project: styled.div`
        display: flex;
        align-items: center;
        width: 64px;
        height: 64px;
        justify-content: center;
        font-size: ${({ theme }) => theme.fontsize.title1};
        line-height: 30px;
        cursor: pointer;
        background-color: ${({ theme }) => theme.color.gray};
        border-radius: 8px;
        position: relative;
        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
            & > div {
                height: auto;
                font-size: ${({ theme }) => theme.fontsize.content};
            }
        }
    `,
    Title: styled.div`
        display: flex;
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 0 16px 0;
        justify-content: space-between;
        align-items: center;
        & svg {
            width: 20px;
            height: 20px;
            color: ${({ theme }) => theme.color.gray};
            cursor: pointer;
            & :hover {
                color: ${({ theme }) => theme.color.main};
            }
        }
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
        margin-right: 4px;
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
