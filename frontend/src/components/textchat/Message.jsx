import React from 'react'
import Prism from 'prismjs'

import styled from 'styled-components'
import 'prismjs/components/prism-java'
import '../../style/prism-material-light.css'
// import 'prismjs/themes/prism-twilight.css'
// import 'prismjs/themes/prism-okaidia.css'
// import 'prismjs/themes/prism-tomorrow.css'
// import 'prismjs/themes/prism-funky.css'
// import 'prismjs/themes/prism-coy.css'
// import 'prismjs/themes/prism-solarizedlight.css'
// import 'prismjs/themes/prism.css'

const Message = ({ isCode, text, language }) => {
    if (isCode) {
        const html = Prism.highlight(text, Prism.languages[language], language)
        return (
            <S.Wrap isCode={isCode}>
                <S.Code dangerouslySetInnerHTML={{ __html: html }} />
            </S.Wrap>
        )
    } else {
        return <div>{text}</div>
    }
}

const S = {
    Wrap: styled.div`
        display: flex;
        width: 100%;
        height: 100%;
        border-radius: 8px;
        border: 1px solid ${({ theme }) => theme.color.lightgray};
        background-color: #fafafa;
    `,
    Code: styled.pre`
        width: 100%;
        height: 100%;
        /* white-space: pre-wrap; */
        padding: 16px;
        border-radius: 8px;
        font-size: ${({ theme }) => theme.fontsize.sub1};

        & > code[class*='language-'] {
            height: 100%;
        }
    `,
}

export default Message
