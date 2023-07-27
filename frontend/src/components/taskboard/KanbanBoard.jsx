import React, { useState } from 'react'
import styled from 'styled-components'

const KanbanBoard = () => {
    const [lists, setLists] = useState({
        todo: [
            { id: 1, name: 'Task 1' },
            { id: 2, name: 'Task 2' },
            { id: 3, name: 'Task 3' },
        ],
        inProgress: [],
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
            setDraggedItem(null) // Clear dragged item after drop
            setDraggedOverList(null) // Clear dragged over list after drop
        }
    }

    return (
        <S.Wrap>
            <S.Todo onDragOver={e => onDragOver(e, 'todo')} onDrop={onDrop}>
                Todo
                <ul>
                    {lists.todo.map(item => (
                        <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'todo')}>
                            <p>{item.name}</p>
                        </li>
                    ))}
                </ul>
            </S.Todo>
            <S.InProgress onDragOver={e => onDragOver(e, 'inProgress')} onDrop={onDrop}>
                InProgress
                <ul>
                    {lists.inProgress.map(item => (
                        <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'inProgress')}>
                            <p>{item.name}</p>
                        </li>
                    ))}
                </ul>
            </S.InProgress>
            <S.Done onDragOver={e => onDragOver(e, 'done')} onDrop={onDrop}>
                Done
                <ul>
                    {lists.done.map(item => (
                        <li key={item.id} draggable onDragStart={e => onDragStart(e, item, 'done')}>
                            <p>{item.name}</p>
                        </li>
                    ))}
                </ul>
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
    Todo: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.success};
        padding: 8px 16px;
        margin: 8px 12px;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
        cursor: pointer;
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
    `,
}

export default KanbanBoard
