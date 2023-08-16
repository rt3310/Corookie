import React, { useEffect, useState, useRef } from 'react'
import styled from 'styled-components'

import * as utils from 'utils'
import { IoAdd } from 'react-icons/io5'
import { HexColorPicker } from 'react-colorful'

const PlanCategoryOptionToggle = ({ state, selected, setSelected }) => {
    const [isActive, setIsActive] = useState(false)
    const [addCategory, setAddCategory] = useState(false)
    const optionRef = useRef(null)
    const [categories, setCategories] = useState([])

    useEffect(() => {
        const handleOutside = e => {
            if (optionRef.current && !optionRef.current.contains(e.target)) {
                setIsActive(false)
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [optionRef])

    const [newOption, setNewOption] = useState('')
    const [newColor, setNewColor] = useState('#ffffff')
    let optionInput = useRef(null)
    let colorRef = useRef(null)
    const handleAddOption = e => setNewOption(e.target.value)
    const addOptionKeyDown = async e => {
        if (e.key === 'Enter') {
            const option = { content: newOption, color: newColor }
            setCategories([...categories, option])
            setAddCategory(false)
        }
    }

    useEffect(() => {
        if (addCategory) {
            optionInput.current.focus()
        }
        const handleOutside = e => {
            if (
                optionInput.current &&
                !optionInput.current.contains(e.target) &&
                colorRef.current &&
                !colorRef.current.contains(e.target)
            ) {
                setAddCategory(false)
                if (!optionRef.current.contains(e.target)) {
                    setIsActive(false)
                }
            }
        }
        document.addEventListener('mousedown', handleOutside)
        return () => {
            document.removeEventListener('mousedown', handleOutside)
        }
    }, [optionInput, addCategory, optionRef])

    const clickSelectedCategory = id => {
        setSelected(selected.filter(category => category.content !== id))
    }

    return (
        <S.PlanOptionBox>
            <S.PlanOptionLabel>{utils.PLAN_OPTIONS[state].label}</S.PlanOptionLabel>
            <S.Selector className={isActive ? 'active' : null}>
                <S.Label onClick={() => setIsActive(!isActive)}>
                    {selected.length === 0
                        ? '분류'
                        : selected.map((selectedCategory, index) => (
                              <S.SelectedCategory
                                  key={index}
                                  onClick={() => clickSelectedCategory(selectedCategory.content)}>
                                  {selectedCategory.content}
                              </S.SelectedCategory>
                          ))}
                </S.Label>
                {addCategory && (
                    <S.ColorPicker ref={colorRef}>
                        <HexColorPicker color={newColor} onChangeComplete={color => setNewColor(color.hex)} />
                    </S.ColorPicker>
                )}
                <S.Options ref={optionRef}>
                    {categories.map((option, index) => (
                        <S.Option
                            key={index}
                            onClick={() => {
                                setIsActive(false)
                                setSelected([...selected, option])
                            }}
                            color={option.color}>
                            {option.content}
                        </S.Option>
                    ))}

                    <S.AddOption>
                        {!addCategory && (
                            <S.Text onClick={() => setAddCategory(!addCategory)}>
                                <IoAdd /> 카테고리 추가
                            </S.Text>
                        )}
                        {addCategory && (
                            <S.AddOptionInput ref={optionInput}>
                                <S.NewOption
                                    value={newOption}
                                    onChange={handleAddOption}
                                    onKeyDown={addOptionKeyDown}
                                    placeholder="제목을 입력하세요"
                                />
                                <S.ColorPreview color={newColor} />
                            </S.AddOptionInput>
                        )}
                    </S.AddOption>
                </S.Options>
            </S.Selector>
        </S.PlanOptionBox>
    )
}

const S = {
    PlanOptionBox: styled.div`
        display: flex;
        align-items: center;
        margin: 10px 0;
        padding: 0 48px 0 14px;
    `,
    PlanOptionLabel: styled.div``,
    Selector: styled.div`
        position: relative;
        display: flex;
        border: solid 1px ${({ theme }) => theme.color.gray};
        border-radius: 8px;
        height: 32px;
        width: 230px;
        background-color: ${({ isActive, theme }) => (!isActive ? theme.color.white : theme.color.main)};
        margin: 0 0 0 auto;
        cursor: pointer;
        &.active ul {
            border: solid 1px ${({ theme }) => theme.color.gray};
            max-height: 500px;
        }
    `,
    Label: styled.button`
        display: flex;
        justify-content: flex-start;
        align-items: center;
        height: 100%;
        flex-grow: 1;
        padding: 0 16px;
        cursor: pointer;
        pointer-events: auto;
        border-radius: 8px;
        font-family: ${({ theme }) => theme.font.main};
        font-size: ${({ theme }) => theme.fontsize.sub1};
    `,
    SelectedCategory: styled.div`
        display: flex;
        padding: 0 4px;
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
        border-radius: 8px;
        overflow: auto;
        max-height: 0;
        transition: 0.3s;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        z-index: 9;

        &::-webkit-scrollbar {
            height: 0px;
            width: 4px;
        }
        &::-webkit-scrollbar-track {
            background: transparent;
        }
        &::-webkit-scrollbar-thumb {
            background: ${({ theme }) => theme.color.gray};
            border-radius: 45px;
        }
        &::-webkit-scrollbar-thumb:hover {
            background: ${({ theme }) => theme.color.gray};
        }
    `,
    Option: styled.li`
        padding: 8px;
        cursor: pointer;
        white-space: nowrap;
        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }

        &:first-child {
            border-radius: 8px 8px 0 0;
        }

        &:last-child {
            border-radius: 0 0 8px 8px;
        }
    `,
    AddOption: styled.li`
        padding: 8px;
        cursor: pointer;
        white-space: nowrap;
        &:hover {
            background-color: ${({ theme }) => theme.color.main};
            color: ${({ theme }) => theme.color.white};
        }
        overflow: visible;
    `,
    AddOptionInput: styled.div`
        display: flex;
        overflow: visible;
        justify-content: space-between;
    `,
    Text: styled.div`
        display: flex;
        align-items: center;
    `,
    NewOption: styled.input`
        margin: 0 8px 0 0;
        width: 100%;
        border: none;
        outline: none;
        font-family: 'Noto Sans KR', 'Pretendard', sans-serif;
        font-size: ${({ theme }) => theme.fontsize.content};
    `,
    ColorPreview: styled.div`
        width: 8px;
        height: 8px;
        background-color: ${props => props.color};
    `,
    ColorPicker: styled.div`
        position: absolute;
        top: 0;
        left: -100px;
        height: 100px;
        width: 100px;
        z-index: 10000;
        & .react-colorful {
            width: 100%;
            height: 100%;
        }
    `,
}

export default PlanCategoryOptionToggle
