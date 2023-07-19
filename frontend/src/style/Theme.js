// 자주쓰이는 css
const common = {
    flexCenter: `
    display: flex;
    justify-contents: center;
    align-items: center;
    `,
    flexCenterColumn: `
        display: flex;
        flex-direction: column;
        justify-contents: center;
        align-items: center;
    `,
}

const color = {
    color1: '#001848',
    color2: '#02457A',
    color3: '#018ABE',
    color4: '#97CADB',
    color5: '#D6E8EE',
    pending: '#EA9A27',
    warning: '#E93636',
    success: '#008472',
    white: '#FFF',
    black: '#000',
    darkgray: '#333',
    background: '#F3F3F3',
    lightgray: '#EEE',
    gray: '#A0A0A0',

    warningFilter: 'invert(40%) sepia(73%) saturate(7218%) hue-rotate(348deg) brightness(100%) contrast(83%)',
}

const fontsize = {
    content: '14px',
    title1: '24px',
    title2: '18px',
    title3: '16px',
    sub1: '14px',
}

const lineheight = {
    content: '24px',
    title1: '24px',
    title2: '18px',
    title3: '16px',
    sub1: '14px',
}

const shadow = {
    card: `0px 3px 3px 0px rgba(0, 0, 0, 0.03);`,
}

const Theme = {
    common,
    color,
    shadow,
    fontsize,
    lineheight,
}

export default Theme
