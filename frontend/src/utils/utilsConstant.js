export const URL = {
    LOGIN: {
        LOGIN: '/login',
    },
    HOME: {
        MAIN: '/',
    },
    CHAT: {
        TEXT: '/chat/text',
    },
    TASK: {
        BOARD: '/task/board',
    },
    PLAN: {
        CALENDER: '/plan/calendar',
    },
}

export const ISSUE_OPTIONS = {
    priority: 'priority',
    manager: 'manager',
    category: 'category',
    status: 'status',
    createManager: 'createManager',
    createPriority: 'createPriority',
    createCategory: 'createCategory',
    detailManager: 'detailManager',
    detailPriority: 'detailPriority',
    detailCategory: 'detailCategory',
}

export const PLAN_OPTIONS = {
    date: {
        label: '날짜',
        placeholder: '날짜 선택',
        options: [
            '날짜 선택',
            '2020년 1월 1일',
            '2020년 1월 2일',
            '2020년 1월 3일',
            '2020년 1월 4일',
            '2020년 1월 5일',
        ],
    },
    member: {
        label: '참여자',
        placeholder: '참여자 선택',
        options: ['참여자 선택', '홍길동', '홍길동'],
    },
    category: {
        label: '분류',
        placeholder: '분류 선택',
        options: ['분류 선택', '일반', '일반'],
    },
}

export const MAX_WIDTH = '1920px'
