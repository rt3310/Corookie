import React from 'react'
import styled from 'styled-components'

const LandingMain = () => {
    return (
        <S.Wrap>
            <S.Title>웹 개발 초심자를 위한 프로젝트 협업 툴</S.Title>
            <S.SubTitle>
                웹 프로젝트를 위해 필요한 기본적인 기능들을 모두 제공하여<nav></nav>
                처음으로 웹 개발을 진행하는 사람들도 원활한 협업을 할 수 있습니다.
            </S.SubTitle>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div``,

    Title: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 16px 8px 100px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.landingtitle};
    `,
    SubTitle: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
}

export default LandingMain
