import create from 'zustand'

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
