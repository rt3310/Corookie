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
    main: '#286EF0',
    pending: '#FFC908',
    warning: '#FF1304',
    success: '#0DDB9D',
    white: '#FFF',
    black: '#000',
    darkgray: '#333',
    background: '#F3F3F3',
    lightgray: '#EEE',
    gray: '#A0A0A0',

    warningFilter: 'invert(16%) sepia(51%) saturate(7032%) hue-rotate(357deg) brightness(105%) contrast(108%)',
}

const fontsize = {
    content: '14px',
    logo: '27px',
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
