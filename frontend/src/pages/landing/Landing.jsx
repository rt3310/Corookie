import React from 'react'
import styled from 'styled-components'
import * as components from 'components'

const Landing = () => {
    return (
        <S.Wrap>
            <components.FullPageScroll>
                <S.Box>
                    <components.LandingHeader />
                    <S.Section1>
                        <components.LandingMain />
                    </S.Section1>
                </S.Box>
                <S.Box>
                    <S.Section2>
                        <components.LandingText />
                    </S.Section2>
                </S.Box>
                <S.Box>
                    <S.Section3>
                        <components.LandingVideo />
                    </S.Section3>
                </S.Box>
                <S.Box>
                    <S.Section4>
                        <S.Section4>
                            <components.LandingIssueCal />
                        </S.Section4>
                    </S.Section4>
                </S.Box>
                <S.Box>
                    <S.Section5>
                        <components.LandingFooter />
                    </S.Section5>
                </S.Box>
            </components.FullPageScroll>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div`
        width: 100%;
        height: 100%;
    `,
    Box: styled.div`
        position: relative;
        top: 0;
        height: 100vh;
        width: 100%;
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
    `,
    Section1: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        width: 100%;
        background-color: ${({ theme }) => theme.color.main};
    `,
    Section2: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        width: 100%;
        background-color: ${({ theme }) => theme.color.main};
    `,
    Section3: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        width: 100%;
        background-color: ${({ theme }) => theme.color.main};
    `,
    Section4: styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        width: 100%;
        background-color: ${({ theme }) => theme.color.main};
    `,
    Section5: styled.div`
        height: 100vh;
        width: 100%;
        background-color: ${({ theme }) => theme.color.main};
    `,
}

export default Landing
