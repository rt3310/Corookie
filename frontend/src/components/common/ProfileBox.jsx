import React from 'react'
import styled from 'styled-components'

import * as style from 'style'

const ProfileBox = () => {
    return (
        <S.Wrap>
            <S.Header>프로필</S.Header>
            <S.ImageBox>
                <img src={require('images/profilebox.png').default} alt="프로필 이미지" />
            </S.ImageBox>
            <S.ContentBox>
                <S.MemberInfoBox>
                    <S.MemberNameContainer>
                        <S.MemberName>황상미</S.MemberName>
                        <S.EditName>편집</S.EditName>
                    </S.MemberNameContainer>
                    <S.MemberEmail>ssafy@email.com</S.MemberEmail>
                </S.MemberInfoBox>
            </S.ContentBox>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 400px;
        min-width: 400px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
        /* animation: ${style.leftSlide} 0.4s linear; */
    `,
    Header: styled.div`
        display: flex;
        position: relative;
        align-items: center;
        white-space: nowrap;
        width: 100%;
        height: fit-content;
        margin: 24px 0 0 0;
        font-size: ${({ theme }) => theme.fontsize.title2};
        color: ${({ theme }) => theme.color.black};

        &::after {
            content: '';
            position: absolute;
            bottom: -20px; /* 선의 위치를 조정하세요 (더 낮게 내리려면 값을 늘립니다) */
            left: 0;
            width: 100%;
            height: 1px;
            background-color: ${({ theme }) => theme.color.lightgray}; /* 선의 색상을 설정합니다. */
        }
    `,

    ImageBox: styled.div`
        display: flex;
        width: 240px;
        height: 240px;
        margin: 16px 0;
        padding: 24px;

        & img {
            width: 100%;
            height: 100%;
        }
    `,
    ContentBox: styled.div`
        width: 100%;
        padding: 0 24px;
    `,
    MemberInfoBox: styled.div`
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin: 0 16px;
    `,
    MemberNameContainer: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        margin: 0 0 8px 0;
    `,
    MemberName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 8px 0 0;
    `,
    MemberEmail: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title3};
        margin: 0 8px 0 0;
        margin-top: 16px;
    `,
    EditName: styled.div`
        font-size: 14px;
        color: ${({ theme }) => theme.color.main};
    `,
}

export default ProfileBox
