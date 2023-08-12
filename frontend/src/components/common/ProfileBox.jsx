import React, { useRef, useEffect } from 'react'
import styled from 'styled-components'

import * as style from 'style'
import * as hooks from 'hooks'
import * as api from 'api'

import { IoPencil } from 'react-icons/io5'

const ProfileBox = () => {
    const { profileName, profileEdit, setName, openEdit, closeEdit } = hooks.profileState()
    const { id, name, email, setMe } = hooks.meState()
    const handleNameChange = e => setName(e.target.value)
    const nameKeyDown = e => {
        if (e.key === 'Enter') {
            closeEdit()
        }
    }
    const handleFileClick = e => {
        fileRef.current.click()
    }

    const handleFileChange = e => {
        console.log(e.target.files[0])
    }

    const fileRef = useRef()

    let nameRef = useRef()

    useEffect(() => {
        if (profileEdit) {
            nameRef.current.focus()
        }
    }, [profileEdit])

    useEffect(() => {
        api.apis.getMe().then(response => {
            setMe(response.data)
        })
    }, [])

    return (
        <S.Wrap>
            <S.Header>프로필</S.Header>
            <S.ImageBox onClick={handleFileClick}>
                <img src={require('images/profilebox.png').default} alt="프로필 이미지" />
                <S.FileUpload>
                    <input
                        ref={fileRef}
                        type="file"
                        accept="image/*"
                        onChange={handleFileChange}
                        style={{ display: 'none' }}
                    />
                    <IoPencil />
                </S.FileUpload>
            </S.ImageBox>
            <S.ContentBox>
                <S.MemberInfoBox>
                    <S.MemberNameContainer>
                        {!profileEdit ? (
                            <S.MemberName>{name}</S.MemberName>
                        ) : (
                            <S.MemberNameEdit
                                ref={nameRef}
                                onChange={handleNameChange}
                                onKeyDown={nameKeyDown}
                                type="text"
                                value={profileName}
                            />
                        )}
                        {!profileEdit && <S.EditName onClick={() => openEdit()}>편집</S.EditName>}
                    </S.MemberNameContainer>
                    <S.MemberEmail>{email ? email : '이메일이 없습니다'}</S.MemberEmail>
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
        width: 360px;
        min-width: 360px;
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
            bottom: -20px;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: ${({ theme }) => theme.color.lightgray};
        }
    `,

    ImageBox: styled.div`
        display: flex;
        width: 100%;
        margin: 16px 0;
        padding: 24px;
        position: relative;

        & img {
            width: 100%;
            height: 100%;
            border-radius: 8px;
        }

        & div {
            width: 0px;
            height: 0px;
            margin-right: 16px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        &:hover {
            & img {
                filter: brightness(50%);
            }
            & div {
                width: 100%;
                height: 100%;
                cursor: pointer;
            }
        }
    `,
    FileUpload: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        & svg {
            color: ${({ theme }) => theme.color.lightgray};
            width: 70px;
            height: 70px;
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
        /* margin: 0 16px; */
    `,
    MemberNameContainer: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
    `,
    MemberName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        margin: 0 8px 0 0;
        width: 100%;
    `,
    MemberNameEdit: styled.input`
        font-size: ${({ theme }) => theme.fontsize.title2};
        border: none;
        outline: none;
        width: 100%;
        margin: 0 8px 0 0;
        font-family: ${({ theme }) => theme.font.main};
    `,
    MemberEmail: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title3};
        margin: 0 8px 0 0;
        margin-top: 16px;
    `,
    EditName: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title3};
        color: ${({ theme }) => theme.color.main};
        cursor: pointer;
        white-space: nowrap;
    `,
}

export default ProfileBox
