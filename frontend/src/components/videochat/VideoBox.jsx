import React, { useRef, useEffect } from 'react'

import styled from 'styled-components'

const VideoBox = ({ streamManager }) => {
    const videoRef = useRef(null)

    useEffect(() => {
        if (streamManager && videoRef.current) {
            streamManager.addVideoElement(videoRef.current)
        }
    }, [streamManager])

    return <video autoPlay={true} ref={videoRef} />
}

const S = {
    ProfileName: styled.div`
        font-size: 13px;
        margin-top: auto;
        padding: 4px;
    `,
}

export default VideoBox
