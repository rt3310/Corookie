import { create } from 'zustand'

export const taskState = create(set => ({
    showIssue: true,
    openIssue: () => set(state => ({ showIssue: true })),
    openKanban: () => set(state => ({ showIssue: false })),
}))

export const priorityState = create(set => ({
    value: '중요도',
    setValue: input => set(state => ({ value: input })),
}))

export const managerState = create(set => ({
    value: '책임자',
    setValue: input => set(state => ({ value: input })),
}))

export const categoryState = create(set => ({
    value: '분류',
    setValue: input => set(state => ({ value: input })),
}))

export const statusState = create(set => ({
    value: '상태',
    setValue: input => set(state => ({ value: input })),
}))
