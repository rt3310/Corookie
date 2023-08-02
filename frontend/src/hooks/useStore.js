import { create } from 'zustand'

export const menuState = create(set => ({
    menu: '',
    openMenu: menu => set(state => ({ openedMenu: menu })),
}))

export const profileState = create(set => ({
    profileOpened: false,
    openProfile: () => set(state => ({ profileOpened: true })),
    closeProfile: () => set(state => ({ profileOpened: false })),
}))

export const commentState = create(set => ({
    commentOpened: false,
    openComment: () => set(state => ({ commentOpened: true })),
    closeComment: () => set(state => ({ commentOpened: false })),
}))

export const taskState = create(set => ({
    showIssue: true,
    openIssue: () => set(state => ({ showIssue: true })),
    openKanban: () => set(state => ({ showIssue: false })),
}))

export const priorityState = create(set => ({
    value: '중요도',
    setValue: input => set(state => ({ value: input })),
}))

export const detailPriorityState = create(set => ({
    value: '중요도',
    setValue: input => set(state => ({ value: input })),
}))

export const createPriorityState = create(set => ({
    value: '중요도',
    setValue: input => set(state => ({ value: input })),
}))

export const managerState = create(set => ({
    value: '책임자',
    setValue: input => set(state => ({ value: input })),
}))

export const detailManagerState = create(set => ({
    value: '책임자',
    setValue: input => set(state => ({ value: input })),
}))

export const createManagerState = create(set => ({
    value: '책임자',
    setValue: input => set(state => ({ value: input })),
}))

export const categoryState = create(set => ({
    value: '분류',
    setValue: input => set(state => ({ value: input })),
}))

export const detailCategoryState = create(set => ({
    value: '분류',
    setValue: input => set(state => ({ value: input })),
}))

export const createCategoryState = create(set => ({
    value: '분류',
    setValue: input => set(state => ({ value: input })),
}))

export const statusState = create(set => ({
    value: '상태',
    setValue: input => set(state => ({ value: input })),
}))

export const issueDetailState = create(set => ({
    issueDetailOpened: '-1',
    openIssueDetail: input => set(state => ({ issueDetailOpened: input })),
    closeIssueDetail: () => set(state => ({ issueDetailOpened: '-1' })),
}))

export const issueCreateState = create(set => ({
    issueCreateOpened: false,
    openIssueCreate: () => set(state => ({ issueCreateOpened: true })),
    closeIssueCreate: () => set(state => ({ issueCreateOpened: false })),
}))

export const dateState = create(set => ({
    monthStart: -1,
    monthEnd: -1,
    startDate: -1,
    endDate: -1,
    setMonthStart: input => set(state => ({ monthStart: input })),
    setMonthEnd: input => set(state => ({ monthEnd: input })),
    setStartDate: input => set(state => ({ startDate: input })),
    setEndDate: input => set(state => ({ endDate: input })),
}))

export const planRegisterState = create(set => ({
    planStartDate: null,
    planEndDate: null,
    setPlanStartDate: input => set(state => ({ planStartDate: input })),
    setPlanEndDate: input => set(state => ({ planEndDate: input })),
}))
