import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import * as components from 'components'
import { BsPlus } from 'react-icons/bs'
import 'react-responsive-carousel/lib/styles/carousel.min.css'
import { Carousel } from 'react-responsive-carousel'

import * as hooks from 'hooks'
import * as api from 'api'

const LandingMain = () => {
    const navigate = useNavigate()
    const accessToken = hooks.getCookie('Authorization')
    const [projects, setProjects] = useState([])
    const [createFormOpened, setCreateFormOpened] = useState(false)
    const [project, setProject] = useState({
        name: '',
        description: '',
    })

    const initProjects = async () => {
        try {
            const projectsRes = await api.apis.getProjects()
            setProjects(projectsRes.data)
        } catch (e) {
            hooks.deleteCookie('Authorization')
            hooks.deleteCookie('Refresh')
        }
    }

    const createProject = async () => {
        try {
            await api.apis.createProject(project)
            initProjects()
            setCreateFormOpened(false)
        } catch (e) {}
    }

    useEffect(() => {
        if (!accessToken) {
            return
        }
        initProjects()
    }, [])

    return (
        <S.Wrap>
            <S.Logo>
                <img src={require('images/logo.png').default} alt={'로고'} />
            </S.Logo>
            {!accessToken && (
                <>
                    <S.Title>
                        웹 개발 초심자를 위한<nav></nav>프로젝트 협업 툴
                    </S.Title>
                    <S.SubTitle>
                        웹 개발 프로젝트에 필요한 기본적인 기능을 모두 제공하여<nav></nav>처음으로 웹 개발을 진행하는
                        사람도 원활한 협업을 할 수 있습니다.
                    </S.SubTitle>
                    <components.LandingStart />
                </>
            )}

            {accessToken && !createFormOpened && (
                <S.ProjectCarousel>
                    <Carousel
                        showStatus={false}
                        centerMode={true}
                        centerSlidePercentage={20}
                        stopOnHover={true}
                        showThumbs={false}
                        autoPlay>
                        <S.CreateProjectButton onClick={() => setCreateFormOpened(true)}>
                            <BsPlus />
                        </S.CreateProjectButton>
                        {projects.map((project, index) => (
                            <S.ProjectBox key={index} onClick={() => navigate('/project/' + project.id)}>
                                <S.ProjectName>{project.name}</S.ProjectName>
                                <S.ProjectDescription>{project.description}</S.ProjectDescription>
                            </S.ProjectBox>
                        ))}
                    </Carousel>
                </S.ProjectCarousel>
            )}
            {createFormOpened && (
                <S.ProjectCreateForm>
                    <S.ProjectNameBox>
                        <S.ProjectNameHeader>프로젝트 제목:</S.ProjectNameHeader>{' '}
                        <S.ProjectNameInput onChange={e => setProject({ ...project, name: e.target.value })} />
                    </S.ProjectNameBox>
                    <S.ProjectDescriptionBox>
                        <S.ProjectDescriptionHeader>설명:</S.ProjectDescriptionHeader>
                        <S.ProjectDescriptionInput
                            onChange={e => setProject({ ...project, description: e.target.value })}
                        />
                    </S.ProjectDescriptionBox>
                    <S.CreateAccess>
                        <S.CreateCancelButton onClick={() => setCreateFormOpened(false)}>취소</S.CreateCancelButton>
                        <S.CreateAcceptButton onClick={() => createProject()}>생성</S.CreateAcceptButton>
                    </S.CreateAccess>
                </S.ProjectCreateForm>
            )}
            {accessToken && (
                <S.Login
                    onClick={() => {
                        hooks.deleteCookie('Authorization')
                        hooks.deleteCookie('Refresh')
                    }}>
                    Logout
                </S.Login>
            )}
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        position: relative;
    `,

    Title: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.black};
        margin: 10px 8px 30px 8px;
        padding: 8px;
        text-align: center;
        line-height: 1.5;
        font-size: ${({ theme }) => theme.fontsize.landingtitle};
    `,
    SubTitle: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.black};
        margin: 16px 8px 32px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2;
    `,
    ProjectBox: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        flex: 0 0 auto;
        padding: 4px;
        width: 100px;
        height: 100px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.main};
        transition-duration: 0.2s;
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
    `,
    ProjectCarousel: styled.div`
        width: 100%;
        height: 200px;
        margin: 24px;
        padding: 4px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .carousel {
            max-width: 1000px;
            width: 600px;
        }
        .slider-wrapper {
            width: 600px;
        }
    `,
    CreateProjectButton: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        flex: 0 0 auto;
        padding: 4px;
        width: 100px;
        height: 100px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        transition-duration: 0.2s;
        cursor: pointer;

        & > svg {
            width: 80%;
            height: 80%;
        }
    `,
    ProjectCreateForm: styled.div`
        width: 100%;
        height: 200px;
        margin: 24px;
        padding: 4px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .carousel {
            max-width: 1000px;
            width: 600px;
        }
        .slider-wrapper {
            width: 600px;
        }
    `,
    ProjectNameBox: styled.div`
        width: 610px;
        display: flex;
        align-items: center;
    `,
    ProjectNameHeader: styled.div`
        width: 100px;
        margin: 0 auto 0 0;
    `,
    ProjectNameInput: styled.input`
        width: 500px;
        height: 40px;
        resize: none;
        border: none;
        outline: none;
        border-radius: 8px;
        margin: 8px 0;
        font-size: ${({ theme }) => theme.fontsize.title2};
        font-family: ${({ theme }) => theme.font.main};
        padding: 8px 16px;
        overflow-y: hidden;
    `,
    ProjectDescriptionBox: styled.div`
        width: 610px;
        display: flex;
        align-items: center;
    `,
    ProjectDescriptionHeader: styled.div`
        display: flex;
        align-items: flex-start;
        width: 100px;
        height: 100%;
        margin: 20px auto 0 0;
    `,
    ProjectDescriptionInput: styled.textarea`
        width: 500px;
        height: 80px;
        resize: none;
        border: none;
        outline: none;
        border-radius: 8px;
        margin: 8px 0;
        font-size: ${({ theme }) => theme.fontsize.content};
        font-family: ${({ theme }) => theme.font.main};
        padding: 8px 16px;
    `,
    CreateAccess: styled.div`
        display: flex;
        justify-content: flex-end;
        width: 610px;
    `,
    CreateAcceptButton: styled.button`
        width: 80px;
        height: 30px;
        margin: 0 8px;
        border-radius: 4px;
        background-color: ${({ theme }) => theme.color.main};
        font-size: ${({ theme }) => theme.fontsize.content};
        border-radius: 8px;
        border: 1px solid ${({ theme }) => theme.color.main};
        background-color: ${({ theme }) => theme.color.main};
        color: ${({ theme }) => theme.color.white};
        margin: auto 0 0 0;
        transition: all 0.2s linear;

        &:hover {
            background-color: ${({ theme }) => theme.color.white};
            color: ${({ theme }) => theme.color.main};
        }
    `,
    CreateCancelButton: styled.button`
        width: 80px;
        height: 30px;
        margin: 0 8px;
        border-radius: 4px;
        background-color: ${({ theme }) => theme.color.main};
        font-size: ${({ theme }) => theme.fontsize.content};
        border-radius: 8px;
        border: 1px solid ${({ theme }) => theme.color.main};
        background-color: ${({ theme }) => theme.color.main};
        color: ${({ theme }) => theme.color.white};
        margin: auto 0 0 0;
        transition: all 0.2s linear;

        &:hover {
            background-color: ${({ theme }) => theme.color.white};
            color: ${({ theme }) => theme.color.main};
        }
    `,
    Logo: styled.div`
        width: auto;
        /* height: auto; */
        padding: auto;
        text-align: center;
        margin: 10px 24px 8px 24px;
    `,
    Login: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        width: 180px;
        padding: 10px;
        color: ${({ theme }) => theme.color.main};

        border-radius: 8px;
        border: 1px solid ${({ theme }) => theme.color.main};
        background-color: ${({ theme }) => theme.color.main};
        color: ${({ theme }) => theme.color.white};
        margin: auto 0 0 0;
        transition: all 0.2s linear;

        &:hover {
            background-color: ${({ theme }) => theme.color.white};
            color: ${({ theme }) => theme.color.main};
        }
    `,
    ProjectName: styled.div``,
    ProjectDescription: styled.div``,
}

export default LandingMain
