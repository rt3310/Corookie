import React, { useEffect } from 'react'
import styled from 'styled-components'

import * as api from 'api'
import * as hooks from 'hooks'

const Main = () => {
    const { setProject } = hooks.projectState()
    useEffect(() => {
        const data = {
            name: '공통 프로젝트',
            // description: '웹 개발 초보자들을 위한 프로젝트 협업 툴',
            description: 'd',
        }

        api.apis
            .createProject(data.name, data.description)
            .then(response => {
                console.log('프로젝트 생성 성공', response.data)
                setProject(response.data)
            })
            .catch(error => {
                console.log('프로젝트 생성 실패', error)
            })
    }, [])

    return <div></div>
}

const S = {
    Wrap: styled.div`
        width: 100%;
        height: 100%;
    `,
}

export default Main
