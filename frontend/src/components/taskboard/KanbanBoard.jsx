import React, { useState } from 'react'
import { DragDropContext, Draggable, Droppable } from 'react-beautiful-dnd'
import styled from 'styled-components'

const KanbanBoard = () => {
    const tasks = [
        { id: '1', content: 'First task' },
        { id: '2', content: 'Second task' },
        { id: '3', content: 'Third task' },
        { id: '4', content: 'Fourth task' },
        { id: '5', content: 'Fifth task' },
    ]

    const taskStatus = {
        toDo: {
            name: 'ToDo',
            items: tasks,
        },
        inProgress: {
            name: 'InProgress',
            items: [],
        },
        done: {
            name: 'Done',
            items: [],
        },
    }
    const [columns, setColumns] = useState(taskStatus)

    const onDragEnd = (result, columns, setColumns) => {
        if (!result.destination) return
        const { source, destination } = result

        if (source.droppableId !== destination.droppableId) {
            const sourceColumn = columns[source.droppableId]
            const destColumn = columns[destination.droppableId]
            const sourceItems = [...sourceColumn.items]
            const destItems = [...destColumn.items]
            const [removed] = sourceItems.splice(source.index, 1)
            destItems.splice(destination.index, 0, removed)
            setColumns({
                ...columns,
                [source.droppableId]: {
                    ...sourceColumn,
                    items: sourceItems,
                },
                [destination.droppableId]: {
                    ...destColumn,
                    items: destItems,
                },
            })
        } else {
            const column = columns[source.droppableId]
            const copiedItems = [...column.items]
            const [removed] = copiedItems.splice(source.index, 1)
            copiedItems.splice(destination.index, 0, removed)
            setColumns({
                ...columns,
                [source.droppableId]: {
                    ...column,
                    items: copiedItems,
                },
            })
        }
    }

    return (
        <div>
            <S.Wrap>
                <DragDropContext onDragEnd={result => onDragEnd(result, columns, setColumns)}>
                    {Object.entries(columns).map(([columnId, column], index) => {
                        return (
                            <S.Column key={columnId}>
                                {columnId === 'toDo' && <S.Todo>{column.name}</S.Todo>}
                                {columnId === 'inProgress' && <S.InProgress>{column.name}</S.InProgress>}
                                {columnId === 'done' && <S.Done>{column.name}</S.Done>}
                                <S.TaskBox>
                                    <Droppable droppableId={columnId} key={columnId}>
                                        {(provided, snapshot) => {
                                            return (
                                                <S.IssueContainer {...provided.droppableProps} ref={provided.innerRef}>
                                                    {column.items.map((item, index) => {
                                                        return (
                                                            <Draggable
                                                                key={item.id}
                                                                draggableId={item.id}
                                                                index={index}>
                                                                {(provided, snapshot) => {
                                                                    return (
                                                                        <S.IssueDrag
                                                                            ref={provided.innerRef}
                                                                            {...provided.draggableProps}
                                                                            {...provided.dragHandleProps}
                                                                            style={{
                                                                                backgroundColor: snapshot.isDragging
                                                                                    ? '#286EF0'
                                                                                    : 'white',
                                                                                ...provided.draggableProps.style,
                                                                            }}>
                                                                            {item.content}
                                                                        </S.IssueDrag>
                                                                    )
                                                                }}
                                                            </Draggable>
                                                        )
                                                    })}
                                                    {provided.placeholder}
                                                </S.IssueContainer>
                                            )
                                        }}
                                    </Droppable>
                                </S.TaskBox>
                            </S.Column>
                        )
                    })}
                </DragDropContext>
            </S.Wrap>
        </div>
    )
}

const S = {
    Column: styled.div`
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 0 12px;
        width: 100%;
    `,
    Wrap: styled.div`
        display: flex;
        align-items: center;
        justify-content: space-between;
        background-color: ${({ theme }) => theme.color.background};
        padding: 0 4px;
        height: 100%;
    `,
    TaskBox: styled.div`
        margin: 8px 0;
        width: 100%;
    `,
    IssueContainer: styled.div`
        width: 100%;
        border-radius: 8;
        min-height: 500px;
    `,
    IssueDrag: styled.div`
        user-select: 'none';
        padding: 16px;
        margin: 0 0 8px 0;
        min-height: 50px;
        border-radius: 8px;
    `,
    Todo: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.success};
        padding: 8px 16px;
        margin: 8px 0 4px 0;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
    `,
    InProgress: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.pending};
        padding: 8px 16px;
        margin: 8px 0 4px 0;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
    `,
    Done: styled.div`
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.orange};
        padding: 8px 16px;
        margin: 8px 0 4px 0;
        width: 100%;
        height: 32px;
        color: ${({ theme }) => theme.color.white};
    `,
}

export default KanbanBoard
