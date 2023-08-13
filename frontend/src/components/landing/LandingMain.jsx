import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'

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
        const projectsRes = await api.apis.getProjects()
        setProjects(projectsRes.data)
        console.log(projectsRes.data)
    }

    const createProject = async () => {
        await api.apis.createProject(project)
        initProjects()
        setCreateFormOpened(false)
    }

    useEffect(() => {
        if (!accessToken) {
            return
        }
        initProjects()
    }, [])

    return (
        <S.Wrap>
            <S.Title>웹 개발 초심자를 위한 프로젝트 협업 툴</S.Title>
            <S.SubTitle>
                웹 개발 프로젝트에 필요한 기본적인 기능을 모두 제공하여<nav></nav>웹 개발이 처음인 사람도 원활한 협업을
                할 수 있습니다.
            </S.SubTitle>
            {/* <S.ProjectContainer>
                <components.ProjectList />
                11
            </S.ProjectContainer> */}
            {!createFormOpened && (
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
                                {project.name}
                                {/* <img src={require('images/thread_profile.png').default} alt={`스레드 이미지 ${index}`} /> */}
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
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        position: relative;
    `,

    Title: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 16px 8px 60px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.landingtitle};
    `,
    SubTitle: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 16px 8px 32px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
    ProjectContainer: styled.div`
        border: 1px solid black;
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
        background-color: ${({ theme }) => theme.color.orange};
        transition-duration: 0.2s;
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
        background-color: ${({ theme }) => theme.color.pending};
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
        background-color: ${({ theme }) => theme.color.success};
        font-size: ${({ theme }) => theme.fontsize.content};
    `,
    CreateCancelButton: styled.button`
        width: 80px;
        height: 30px;
        margin: 0 8px;
        border-radius: 4px;
        background-color: ${({ theme }) => theme.color.warning};
        font-size: ${({ theme }) => theme.fontsize.content};
    `,
}

export default LandingMain
