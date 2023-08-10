import React from 'react'
import styled from 'styled-components'

const LandingIssueCal = () => {
    return (
        <S.Wrap>
            <S.Container>
                <S.Issue>
                    <S.Title>Issue Management</S.Title>
                    <S.SubTitle1>협업 툴에서 필수인 직관적인 이슈 관리</S.SubTitle1>
                    <S.SubTitle2>
                        프로젝트의 이슈들을 <nav></nav>팀원들과 공통으로 관리하고 한눈에 확인하며 <nav></nav>성공적인
                        프로젝트를 이끌어냅니다.
                    </S.SubTitle2>
                </S.Issue>
                <S.Schedule>
                    <S.Title>Schedule Management</S.Title>
                    <S.SubTitle1>일정을 공유할 수 있는 캘린더</S.SubTitle1>
                    <S.SubTitle2>
                        팀원들과 프로젝트와 관련된 일정들을 공유하고 <nav></nav>
                        쉽게 확인할 수 있습니다.
                    </S.SubTitle2>
                </S.Schedule>
            </S.Container>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div``,

    Title: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.black};
        margin: 16px;
        padding: 8px;
        text-align: left;
        font-weight: 700;
        font-size: ${({ theme }) => theme.fontsize.title1};
    `,
    Container: styled.div`
        display: flex;
        flex-direction: row;
        justify-content: space-around;
    `,
    Issue: styled.div`
        margin: 16px;
    `,
    Schedule: styled.div`
        margin: 16px;
    `,
    SubTitle1: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.main};
        margin: 8px;
        padding: 24px 8px;
        text-align: left;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
    SubTitle2: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.black};
        margin: 8px;
        padding: 24px 8px;
        text-align: left;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
}

export default LandingIssueCal
