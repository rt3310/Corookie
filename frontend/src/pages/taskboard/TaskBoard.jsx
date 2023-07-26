import React from 'react'
import styled from 'styled-components'

import * as components from 'components'

const TaskBoard = () => {
    return (
        <S.Wrap>
            <components.TaskHeader />
        </S.Wrap>
    )
}
const S = {
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
    `,
}

export default TaskBoard
