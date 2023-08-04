import React from 'react'
import styled from 'styled-components'

const VideoBoard = () => {
    return (
        <S.Wrap>
            <S.VideoBox>
                <S.ProfileName>권현수</S.ProfileName>
            </S.VideoBox>
            <S.VideoBox>
                <S.ProfileName>박종서</S.ProfileName>
            </S.VideoBox>
            <S.VideoBox>
                <S.ProfileName>서원호</S.ProfileName>
            </S.VideoBox>
            <S.VideoBox>
                <S.ProfileName>신승수</S.ProfileName>
            </S.VideoBox>
            <S.VideoBox>
                <S.ProfileName>최효빈</S.ProfileName>
            </S.VideoBox>
            <S.VideoBox>
                <S.ProfileName>황상미</S.ProfileName>
            </S.VideoBox>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: repeat(2, 1fr);
        gap: 8px;
        background-color: ${({ theme }) => theme.color.background};
        margin: 8px 16px 24px 16px;
        height: 100%;
        max-height: calc(100% - 96px);
    `,

    ProfileName: styled.div`
        font-size: 13px;
        margin-top: auto;
        padding: 4px;
    `,
    VideoBox: styled.div`
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        padding: 8px;
    `,
}

export default VideoBoard
