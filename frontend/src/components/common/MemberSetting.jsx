import React, { useEffect, useRef } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import { FaCrown } from 'react-icons/fa'
import { IoClose, IoLink } from 'react-icons/io5'

const MemberSetting = ({ memberTextRef }) => {
    const { members, memberOpened, closeMember, removeMember } = hooks.memberState()
    const { manager } = hooks.setManagerState()
    const { linkActivated, activateLink, deactivateLink } = hooks.linkState()

    const copyLink = async text => {
        try {
            await navigator.clipboard.writeText(text)
            alert('초대코드가 복사되었습니다!')
        } catch (e) {
            alert('초대코드 복사에 실패했습니다')
        }
    }

    const handleRemoveMember = member => {
        if (window.confirm(`${member.name}님을 팀에서 퇴출시키겠습니까? `)) {
            removeMember(member.id)
        }
    }

    const handleLinkActivation = () => {
        if (linkActivated) {
            deactivateLink()
        } else {
            activateLink()
        }
    }

    useEffect(() => {
        console.log(linkActivated)
    }, [linkActivated])

    let memberRef = useRef(null)
    useEffect(() => {
        const handleOutside = e => {
            if (
                memberRef.current &&
                !memberRef.current.contains(e.target) &&
                !memberTextRef.current.contains(e.target)
            ) {
                closeMember()
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [memberRef])

    return (
        <S.Wrap ref={memberRef}>
            {memberOpened && (
                <S.Container>
                    <S.Title>멤버</S.Title>
                    <S.Line />
                    {members.map(member => {
                        return (
                            <S.Member key={member.id} className={member.name === manager ? 'manager' : null}>
                                <img src={member.img} alt={member.name} />
                                <S.Name>{member.name}</S.Name>
                                {member.name === manager ? (
                                    <S.ManagerButton>
                                        <FaCrown />
                                    </S.ManagerButton>
                                ) : (
                                    <S.RemoveButton>
                                        <IoClose onClick={() => handleRemoveMember(member)} />
                                    </S.RemoveButton>
                                )}
                            </S.Member>
                        )
                    })}
                    <S.Line />
                    <S.ActivateLink>
                        <S.Text>링크 활성화</S.Text>
                        <S.SlideButton active={linkActivated} onClick={handleLinkActivation}>
                            <S.InnerButton className={linkActivated ? 'active' : null} />
                        </S.SlideButton>
                    </S.ActivateLink>
                    {linkActivated && (
                        <S.CreateLink>
                            <S.Text>초대링크 복사</S.Text>
                            <IoLink onClick={() => copyLink('복사')} />
                        </S.CreateLink>
                    )}
                </S.Container>
            )}
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        position: absolute;
        top: 90px;
        left: 193px;
        z-index: 999;
        background-color: ${({ theme }) => theme.color.white};
        width: 180px;
        height: auto;
        border-radius: 8px;
        /* border: 1px solid ${({ theme }) => theme.color.lightgray}; */
        z-index: 100;
        box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.2);
        &.open div {
            height: auto;
        }
    `,
    Container: styled.div`
        display: flex;
        flex-direction: column;
        padding: 8px;
        width: 100%;
    `,
    Title: styled.div`
        padding: 8px;
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
    `,
    Line: styled.div`
        margin: 4px;
        height: 1px;
        width: 95%;
        align-self: center;
        background-color: ${({ theme }) => theme.color.lightgray};
    `,
    Member: styled.div`
        display: flex;
        align-items: center;
        padding: 8px 12px;
        & > img {
            width: 30px;
            height: 30px;
            margin-right: 16px;
        }
    `,
    Name: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
        white-space: nowrap;
    `,
    ManagerButton: styled.div`
        display: flex;
        width: 100%;
        justify-content: flex-end;
        & svg {
            width: 16px;
            height: 16px;
            color: ${({ theme }) => theme.color.main};
        }
    `,
    RemoveButton: styled.div`
        display: flex;
        width: 100%;
        justify-content: flex-end;
        & svg {
            width: 16px;
            height: 16px;
            color: ${({ theme }) => theme.color.black};
            cursor: pointer;
            &:hover {
                color: ${({ theme }) => theme.color.main};
            }
        }
    `,
    ActivateLink: styled.div`
        display: flex;
        width: 100%;
        height: auto;
        justify-content: space-between;
        padding: 8px 12px;
        &.actived div {
            justify-content: flex-end;
            background-color: ${({ theme }) => theme.color.lightgray};
        }
    `,
    Text: styled.div`
        font-size: ${({ theme }) => theme.fontsize.content};
        color: ${({ theme }) => theme.color.black};
        white-space: nowrap;
    `,
    SlideButton: styled.div`
        display: flex;
        width: 28px;
        height: 15px;
        align-items: center;
        border-radius: 8px;
        background-color: ${({ theme, active }) => (active ? theme.color.main : theme.color.gray)};
        padding: 2px;
        transition: background-color 0.5s;
        cursor: pointer;
    `,
    InnerButton: styled.div`
        width: 10px;
        height: 10px;
        background-color: ${({ theme }) => theme.color.white};
        border-radius: 100%;
        /* margin-left: 0px; */
        transition: all 0.1s linear;

        &.active {
            margin-left: 13px;
        }
    `,
    CreateLink: styled.div`
        display: flex;
        width: 100%;
        height: auto;
        justify-content: space-between;
        align-items: center;
        padding: 8px 12px;
        transition: 0.3s;
        & svg {
            width: 24px;
            height: 24px;
            color: ${({ theme }) => theme.color.main};
            cursor: pointer;
        }
    `,
}

export default MemberSetting
