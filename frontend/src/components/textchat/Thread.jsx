import React from 'react'
import styled from 'styled-components'

import { IoIosArrowForward } from 'react-icons/io'

const Thread = ({ openComment, setOpenComment }) => {
    return (
        <S.Wrap>
            <S.ChatBox>
                <S.ImageBox>
                    <img src={require('images/thread_profile.png').default} alt="스레드 이미지" />
                </S.ImageBox>
                <S.ContentBox>
                    <S.MemberInfoBox>
                        <S.MemberName>황상미</S.MemberName>
                        <S.CreatedTime>오전 11:12</S.CreatedTime>
                        <S.CommentButton onClick={() => setOpenComment(!openComment)} open={openComment}>
                            3개의 댓글 <IoIosArrowForward />
                        </S.CommentButton>
                    </S.MemberInfoBox>
                    <S.Text>나는 모든 걸 갖췄다. 재미. 세련미. 미. 그리고 황상미.</S.Text>
                </S.ContentBox>
            </S.ChatBox>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        border: 2px solid
            ${({ open, theme }) => {
                return open ? theme.color.main : theme.color.white
            }};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 24px;

        &:first-child {
            margin-top: 0;
        }

        &:last-child {
            margin-bottom: 0;
        }
    `,
    ChatBox: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
    `,
    ImageBox: styled.div`
        width: 40px;
        margin: 0 16px 0 0;

        & img {
            width: 40px;
            height: 40px;
        }
    `,
    ContentBox: styled.div`
        width: 100%;
    `,
    MemberInfoBox: styled.div`
        display: flex;
        align-items: flex-end;
        margin: 0 0 16px 0;
    `,
    MemberName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title3};
        margin: 0 8px 0 0;
    `,
    CreatedTime: styled.div`
        font-size: 13px;
        color: ${({ theme }) => theme.color.gray};
    `,
    CommentButton: styled.div`
        display: flex;
        align-items: center;
        margin: 0 0 0 auto;
        font-size: 13px;
        color: ${({ theme }) => theme.color.main};
        transition-duration: 0.2s;
        cursor: pointer;

        &:hover {
            color: ${({ theme }) => theme.color.warning};
        }

        & svg {
            width: 20px;
            height: 20px;
        }
    `,
    Text: styled.div``,
}

export default Thread
