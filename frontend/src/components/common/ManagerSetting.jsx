import React, { useEffect, useRef } from 'react'
import styled from 'styled-components'

import * as hooks from 'hooks'
import { FaCrown } from 'react-icons/fa'

const ManagerSetting = ({ managerTextRef }) => {
    const { manager, setManager, managerOpened, closeManager } = hooks.setManagerState()
    const { members } = hooks.memberState()
    const managerChange = member => {
        if (window.confirm(`관리자를 ${member}로 변경하시겠습니까?`)) {
            setManager(member)
        }
    }

    let managerRef = useRef(null)
    useEffect(() => {
        const handleOutside = e => {
            if (
                managerRef.current &&
                !managerRef.current.contains(e.target) &&
                !managerTextRef.current.contains(e.target)
            ) {
                closeManager()
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [managerRef])
    return (
        <S.Wrap ref={managerRef}>
            {managerOpened ? (
                <S.Container>
                    <S.Title>관리자 변경</S.Title>
                    <S.Line />
                    {members.map(member => {
                        return (
                            <S.Member key={member.id} className={member.name === manager ? 'manager' : null}>
                                <img src={member.img} alt={member.name} />
                                <S.Name>{member.name}</S.Name>
                                {member.name !== manager ? (
                                    <S.ManagerButton>
                                        <FaCrown onClick={() => managerChange(member.name)} />
                                    </S.ManagerButton>
                                ) : null}
                            </S.Member>
                        )
                    })}
                </S.Container>
            ) : null}
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        position: absolute;
        top: 127px;
        left: -10px;
        flex-direction: column;
        width: 180px;
        height: auto;
        background-color: ${({ theme }) => theme.color.white};
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
        &.manager div {
            color: ${({ theme }) => theme.color.gray};
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
            color: ${({ theme }) => theme.color.black};
            cursor: pointer;
            &:hover {
                color: ${({ theme }) => theme.color.main};
            }
        }
    `,
}

export default ManagerSetting
