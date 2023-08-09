import React from 'react'
import styled from 'styled-components'

const LandingVideo = () => {
    return (
        <S.Wrap>
            <S.Title>Video Channel</S.Title>
            <S.SubTitle1>언제 어디서나 대면 소통할 수 있는 화상 채널</S.SubTitle1>
            <S.SubTitle2>
                WebRTC를 사용하여 팀원들과의 생생한 비대면 회의를 할 수 있습니다.<nav></nav>
                언제나 회의 내용을 놓치는 일이 없도록 획기적인 자동 회의록 기능도 제공합니다.
            </S.SubTitle2>
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
    SubTitle1: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 8px;
        padding: 24px 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
    SubTitle2: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.black};
        margin: 8px;
        padding: 24px 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
}

export default LandingVideo
