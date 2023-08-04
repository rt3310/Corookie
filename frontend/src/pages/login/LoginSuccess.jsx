import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import * as api from 'api'
import * as hooks from 'hooks'

const LoginSuccess = () => {
    const navigate = useNavigate()

    const params = new URLSearchParams(window.location.search)

    useEffect(() => {
        const auth = async () => {
            const token = {
                authToken: params.get('token'),
            }

            console.log(token.authToken)

            try {
                const res = await api.apis.auth(token)
                hooks.setAccessToken(res.data.token)
                hooks.setRefreshToken(res.date.refreshToken)
                navigate('/text/chat')
            } catch (err) {
                alert('로그인에 실패했습니다. 잠시 후 다시 시도해주세요')
            }
        }

        auth()
    }, [])

    return <></>
}

export default LoginSuccess
