import React from 'react'
import styled from 'styled-components'

import * as components from 'components'

const CalendarBoard = () => {
    return (
        <S.Wrap>
            <components.CalendarBoard />
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

export default CalendarBoard
