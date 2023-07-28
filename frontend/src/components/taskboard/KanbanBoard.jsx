import React, { useState } from 'react'
import styled from 'styled-components'

const KanbanBoard = () => {
    const [lists, setLists] = useState({
        todo: [
            { id: 1, name: 'Task 1' },
            { id: 2, name: 'Task 2' },
        ],
        inProgress: [{ id: 3, name: 'Task 3' }],
        done: [],
    })

    const [draggedItem, setDraggedItem] = useState(null)
    const [draggedOverList, setDraggedOverList] = useState(null)

    const onDragStart = (event, listItem, sourceList) => {
        event.dataTransfer.effectAllowed = 'move'
        event.dataTransfer.setData('text/plain', listItem.id.toString())
        setDraggedItem(listItem)
        setDraggedOverList(sourceList)
    }

    const onDragOver = (event, targetList) => {
        event.preventDefault()
        setDraggedOverList(targetList)
    }

    const onDrop = event => {
        event.preventDefault()
        const itemId = parseInt(event.dataTransfer.getData('text'))
        const targetList = draggedOverList
        if (targetList && draggedItem && draggedOverList !== draggedItem) {
            const updatedSourceList = lists[draggedOverList].filter(item => item.id !== itemId)
            const targetIndex = lists[targetList].findIndex(item => item.id === itemId)
            const updatedTargetList = [...lists[targetList]]
            updatedTargetList.splice(targetIndex + 1, 0, draggedItem)
            setLists({
                ...lists,
                [draggedOverList]: updatedSourceList,
                [targetList]: updatedTargetList,
            })
            setDraggedItem(null)
            setDraggedOverList(null)
        }
    }

    return (
        <S.Wrap>
            <S.Todo onDragOver={e => onDragOver(e, 'todo')} onDrop={onDrop} draggable={false}>
                Todo
                <S.TaskBox>
                    <ul>
                        {lists.todo.map(item => (
                            <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'todo')}>
                                <p>{item.name}</p>
                            </li>
                        ))}
                    </ul>
                </S.TaskBox>
            </S.Todo>
            <S.InProgress onDragOver={e => onDragOver(e, 'inProgress')} onDrop={onDrop} draggable={false}>
                InProgress
                <S.TaskBox>
                    <ul>
                        {lists.inProgress.map(item => (
                            <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'inProgress')}>
                                <p>{item.name}</p>
                            </li>
                        ))}
                    </ul>
                </S.TaskBox>
            </S.InProgress>
            <S.Done onDragOver={e => onDragOver(e, 'done')} onDrop={onDrop} draggable={false}>
                Done
                <S.TaskBox>
                    <ul>
                        {lists.done.map(item => (
                            <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'done')}>
                                <p>{item.name}</p>
                            </li>
                        ))}
                    </ul>
                </S.TaskBox>
            </S.Done>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        display: flex;
        justify-content: space-between;
        background-color: ${({ theme }) => theme.color.background};
        padding: 0 4px;
    `,
    TaskBox: styled.div`
        display: flex;
        background-color: ${({ theme }) => theme.color.background};
        padding: 0 0;
        margin: 16px 0;
    `,
    Todo: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.success};
        padding: 8px 16px;
        margin: 8px 12px;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
        ul {
            list-style: none;
            padding: 0;
            width: 100%;
        }
        li {
            background-color: ${({ theme }) => theme.color.white};
            margin: 16px 0 16px 0;
            padding: 8px;
            border-radius: 4px;
            height: 120px;
            color: ${({ theme }) => theme.color.black};

            cursor: pointer;
        }
    `,
    InProgress: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.pending};
        padding: 8px 16px;
        margin: 8px 12px;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
        ul {
            list-style: none;
            padding: 0;
            width: 100%;
        }
        li {
            background-color: ${({ theme }) => theme.color.white};
            margin: 16px 0 16px 0;
            padding: 8px;
            border-radius: 4px;
            height: 120px;
            color: ${({ theme }) => theme.color.black};

            cursor: pointer;
        }
    `,
    Done: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.orange};
        padding: 8px 16px;
        margin: 8px 12px;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
        ul {
            list-style: none;
            padding: 0;
            width: 100%;
            height: 120px;
        }
        li {
            background-color: ${({ theme }) => theme.color.white};
            margin: 16px 0 16px 0;
            padding: 8px;
            border-radius: 4px;
            height: 120px;
            color: ${({ theme }) => theme.color.black};

            cursor: pointer;
        }
    `,
}

export default KanbanBoard
