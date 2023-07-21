import { keyframes } from 'styled-components'

export const leftSlide = keyframes`
    0% {
        margin-left: 600px;
        margin-right: -600px;
        opacity: 0;
    }
    100% {
        margin-left: 0;
        margin-right: 0;
        opacity: 1;
    }
`
