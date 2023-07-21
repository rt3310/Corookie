import React from 'react'
import styled from 'styled-components'

const ProjectIntro = () => {
    return (
        <S.Wrap>
            <S.Container>
                <S.Title>Co - 공통 프로젝트</S.Title>
                <S.Description>웹 개발 초보자들을 위한 프로젝트 협업 툴</S.Description>
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
        padding: 26px;
        margin: 16px 0;
    `,
    Container: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
        justify-content: center;
        align-items: center;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 0 34px 0;
        /* line-height: ${({ theme }) => theme.lineheight.title1}; */
    `,
    Description: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        /* line-height: ${({ theme }) => theme.lineheight.content}; */
    `,
}

export default ProjectIntro
