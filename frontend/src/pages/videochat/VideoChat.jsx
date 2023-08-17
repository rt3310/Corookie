import React, { useState } from 'react'
import { useParams } from 'react-router'
import styled from 'styled-components'

import * as components from 'components'
import * as hooks from 'hooks'
import * as api from 'api'

import { IoExitOutline } from 'react-icons/io5'
import { useEffect } from 'react'
import axios from 'axios'

const VideoChat = () => {
    const { chatboxOpened } = hooks.chatBoxState()
    const { projectId, channelId } = useParams()
    const [videoChannel, setVideoChannel] = useState(null)
    const [selectedFile, setSelectedFile] = useState(null)
    const [selectedFileName, setSelectedFileName] = useState(null)
    const [uploadStatus, setUploadStatus] = useState('')
    const [summary, setSummary] = useState([])
    const [isSummaryVisible, setIsSummaryVisible] = useState(false)
    const [focusedSummary, setFocusedSummary] = useState(null)
    const [focusedSummaryUrl, setFocusedSummaryUrl] = useState(null)

    const setSummaryList = async () => {
        const summaryRes = await api.apis.getAnalysisList(channelId)
        console.log(summaryRes.data)
        setSummary(summaryRes.data)
    }

    const toggleVisibility = () => {
        if (!isSummaryVisible) {
            setIsSummaryVisible(!isSummaryVisible)
        }
    }

    useEffect(() => {
        const initChannel = async () => {
            const videoChannelRes = await api.apis.getVideoChannel(projectId, channelId)
            console.log(videoChannelRes.data)
            setVideoChannel(videoChannelRes.data)
        }
        setVideoChannel(null)
        setFocusedSummary(null)
        setFocusedSummaryUrl(null)
        setIsSummaryVisible(false)
        initChannel()
        setSummaryList()
    }, [projectId, channelId])

    if (!videoChannel) {
        return
    }

    const handleFileChange = event => {
        setSelectedFile(event.target.files[0])
        const idx = summary.length
        console.log(idx)
        setSelectedFileName(`${videoChannel.name} 요약 ${idx + 1}`)
    }

    const handleUpload = async () => {
        if (!selectedFile) {
            setUploadStatus('Please select a file.')
            return
        }

        const formData = new FormData()
        formData.append('file', selectedFile)
        formData.append('recordName', selectedFileName)

        const cookies = document.cookie // 쿠키 문자열 가져오기
        const cookieArray = cookies.split(';') // 세미콜론으로 구분된 쿠키 문자열을 배열로 분할
        let token = null
        // 각 쿠키를 순회하며 원하는 쿠키를 찾기
        for (const cookie of cookieArray) {
            const [name, value] = cookie.trim().split('=') // 쿠키 문자열을 이름과 값으로 분할
            if (name === 'Authorization') {
                token = value
                break // 원하는 쿠키를 찾았으면 루프 종료
            }
        }

        try {
            const response = await axios
                .post(`http://localhost:8080/api/v1/projects/1/video-channels/${channelId}/analysis`, formData, {
                    headers: {
                        Authorization: token,
                        'Content-Type': 'multipart/form-data',
                    },
                })
                .then(response => {
                    console.log(response.data)
                })
                .then(() => {
                    if (response.status === 200) {
                        setUploadStatus('File uploaded successfully.')
                        // Handle success scenario
                    } else {
                        setUploadStatus('Upload failed. Please try again.')
                        // Handle failure scenario
                    }
                })
        } catch (error) {
            setUploadStatus('An error occurred during upload.')
            console.log(error)
            // Handle error scenario
        } finally {
            setSummaryList()
        }
    }

    return (
        <S.Wrap>
            <S.Header>
                <S.Title>{videoChannel.name}</S.Title>
                <S.ExitButton>
                    <IoExitOutline />
                </S.ExitButton>
            </S.Header>
            <S.Container>
                <iframe
                    src={`http://localhost:4200/#/${videoChannel.sessionId}`} // local 시험할 때
                    // src={`http://i9a402.p.ssafy.io:8443/#/${videoChannel.sessionId}`} // 우리 서버에서
                    allow="camera;microphone;fullscreen;autoplay"
                    width="100%"
                    height="100%">
                    <p>사용 중인 브라우저는 iframe을 지원하지 않습니다.</p>
                </iframe>
            </S.Container>
            <S.TextBox>
                <div>
                    <input type="file" onChange={handleFileChange} />
                    <button onClick={handleUpload}>Upload</button>
                    <p>{uploadStatus}</p>
                </div>
            </S.TextBox>
            <S.TextBox>
                {summary.map((data, index) => (
                    <S.Recording
                        key={data.id}
                        onClick={() =>
                            api.apis.getAnalysisDetail(data.id).then(response => {
                                console.log(response.data)
                                toggleVisibility()
                                setFocusedSummary(response.data.summarizationText)
                                setFocusedSummaryUrl(response.data.s3URL)
                            })
                        }>
                        {index + 1}. {data.recordName}
                        {data.createdAt}
                    </S.Recording>
                ))}
            </S.TextBox>
            {isSummaryVisible && (
                <S.TextBox>
                    {focusedSummary} {focusedSummaryUrl}{' '}
                </S.TextBox>
            )}
        </S.Wrap>
    )
}

const S = {
    Recording: styled.div`
        margin: 0 16px;
    `,
    TextBox: styled.div`
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        height: 120px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 8px 16px;
        padding: 0 16px;
    `,
    Wrap: styled.div`
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
    `,
    Header: styled.div`
        display: flex;
        align-items: center;
        height: 64px;
        border-radius: 8px;
        background-color: ${({ theme }) => theme.color.white};
        box-shadow: ${({ theme }) => theme.shadow.card};
        margin: 16px;
        padding: 0 26px;
    `,
    Title: styled.div`
        font-size: ${({ theme }) => theme.fontsize.title2};
        display: flex;
        height: 64px;
        justify-content: space-between;
        align-items: center;
        position: relative;
    `,
    ExitButton: styled.div`
        margin: 0 0 0 auto;
        cursor: pointer;
        transition-duration: 0.2s;

        & svg {
            width: 24px;
            height: 24px;
        }

        &:hover {
            color: ${({ theme }) => theme.color.warning};
            transform: translateX(1px);
        }
    `,
    Container: styled.div`
        display: flex;
        height: 100%;
        max-height: calc(100vh - 152px);
        margin: 16px;
        border-radius: 8px;
    `,
}

export default VideoChat
