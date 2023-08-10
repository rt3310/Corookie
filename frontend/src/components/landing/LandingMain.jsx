import React from 'react'
import styled from 'styled-components'
import 'react-responsive-carousel/lib/styles/carousel.min.css'
import { Carousel } from 'react-responsive-carousel'
import * as components from 'components'

const LandingMain = () => {
    return (
        <S.Wrap>
            <S.Title>웹 개발 초심자를 위한 프로젝트 협업 툴</S.Title>
            <S.SubTitle>
                웹 개발 프로젝트에 필요한 기본적인 기능을 모두 제공하여<nav></nav>웹 개발이 처음인 사람도 원활한 협업을
                할 수 있습니다.
            </S.SubTitle>
            {/* <S.ProjectContainer>
                <components.ProjectList />
                11
            </S.ProjectContainer> */}

            <S.ProjectCarousel>
                <Carousel showThumbs={false} infiniteLoop autoPlay>
                    {[...Array(14)].map((_, index) => (
                        <S.ProjectBox key={index}>
                            <img src={require('images/thread_profile.png').default} alt={`스레드 이미지 ${index}`} />
                        </S.ProjectBox>
                    ))}
                </Carousel>
            </S.ProjectCarousel>
        </S.Wrap>
    )
}

const S = {
    Wrap: styled.div``,

    Title: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 16px 8px 60px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.landingtitle};
    `,
    SubTitle: styled.div`
        width: auto;
        color: ${({ theme }) => theme.color.white};
        margin: 16px 8px 32px 8px;
        padding: 8px;
        text-align: center;
        font-size: ${({ theme }) => theme.fontsize.sub1};
        line-height: 2.5;
    `,
    ProjectContainer: styled.div`
        border: 1px solid black;
    `,
    ProjectBox: styled.div`
        padding: 4px;
        width: 100px;
        height: 100px;
    `,
    ProjectCarousel: styled.div`
        width: 100%;
        height: 200px;
        margin: 24px;
        padding: 4px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .carousel {
            max-width: 1000px;
            width: 600px;
        }
        .slider-wrapper {
            width: 600px;
        }
    `,
}

export default LandingMain
