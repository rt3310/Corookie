import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import * as components from 'components'
import { BsPlus } from 'react-icons/bs'
import 'react-responsive-carousel/lib/styles/carousel.min.css'
import { Carousel } from 'react-responsive-carousel'

import * as hooks from 'hooks'
import * as api from 'api'

const ProjectChoose = () => {
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
            <S.Logo>
                <img src={require('images/logo.png').default} alt={'로고'} />
            </S.Logo>

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
    Logo: styled.div`
        width: auto;
        /* height: auto; */
        padding: auto;
        text-align: center;
        margin: 180px 24px 8px 24px;
    `,
}

export default ProjectChoose
