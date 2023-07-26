import React, { useEffect, useState, useRef } from 'react'
import styled from 'styled-components'
import * as hooks from 'hooks'

import { IoChevronUp, IoChevronDown } from 'react-icons/io5'

const ToggleButton = ({ defaultVal, list }) => {
    const [isActive, setIsActive] = useState(false)
    let searchRef = useRef(null)

    const changeIsActive = () => {
        setIsActive(!isActive)
        console.log(isActive)
    }

    useEffect(() => {
        const handleOutside = e => {
            if (searchRef.current && !searchRef.current.contains(e.target)) {
                setIsActive(false)
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [searchRef])

    const type = {
        priority: hooks.priorityState(),
        manager: hooks.managerState(),
        category: hooks.categoryState(),
        status: hooks.statusState(),
    }

    const { value, setValue } = type[defaultVal]

    useEffect(() => {
        console.log(value)
    }, [value])

    return (
        <S.Selector className={isActive ? 'active' : null}>
            <S.Label onClick={() => changeIsActive()}>
                {value}
                {isActive ? <IoChevronUp /> : <IoChevronDown />}
            </S.Label>
            <S.Options ref={searchRef}>
                {list.map((item, index) => (
                    <S.Option
                        key={index}
                        onClick={() => {
                            setValue(item)
                            changeIsActive()
                        }}>
                        {item}
                    </S.Option>
                ))}
            </S.Options>
        </S.Selector>
    )
}

const S = {
    Selector: styled.div`
        position: relative;
        display: flex;
        align-items: center;
        border: solid 1px ${({ theme }) => theme.color.gray};
        border-radius: 4px;
        height: 31px;
        width: 80px;
        background-color: ${({ isActive, theme }) => (!isActive ? theme.color.white : theme.color.main)};
        cursor: pointer;
        &.active ul {
            max-height: 500px;
        }
    `,
    Label: styled.button`
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 100%;
        cursor: pointer;
    `,
    Options: styled.ul`
        position: absolute;
        top: 31px;
        left: 0;
        width: inherit;
        background: #fff;
        color: #000;
        list-style-type: none;
        padding: 0;
        border-radius: 2px;
        overflow: auto;
        max-height: 0;
        transition: 0.3s;
        font-size: 10pt;
        z-index: 999;
    `,
    Option: styled.li`
        padding: 8px;
        cursor: pointer;
        white-space: nowrap;
        &:hover {
            background-color: ${({ theme }) => theme.color.main};
        }
    `,
}

export default ToggleButton
