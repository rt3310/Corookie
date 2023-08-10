import React from 'react'
import styled from 'styled-components'

const LandingText = () => {
    return (
        <S.Wrap>
            <S.Title>Text Channel</S.Title>
            <S.SubTitle1>기발한 아이디어를 공유하고 효과적인 논의를 위한 스레드 채팅</S.SubTitle1>
            <S.SubTitle2>
                스레드를 열어 관련된 내용에 대해 논의할 수 있는 구조적인 채팅은
                <nav></nav>협업에 특화되어 있습니다.
            </S.SubTitle2>
            <S.ImageContent>
                <S.Image src={require('images/chatting.png').default} alt="채팅" />
            </S.ImageContent>
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
        color: ${({ theme }) => theme.color.main};
        margin: 8px;
        padding: 24px 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.title3};
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
    ImageContent: styled.div`
        width: 300px;
        height: 200px;
    `,
    Image: styled.img`
        width: 100%;
        height: 100%;
        object-fit: cover;
    `,
}

export default LandingText
