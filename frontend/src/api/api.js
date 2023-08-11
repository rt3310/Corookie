import axios from 'axios'
import * as utils from 'utils'
import * as hooks from 'hooks'

export const instance = axios.create({
    baseURL: utils.API_BASE_URL,
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Accept: 'application/json',
    },
})

instance.interceptors.request.use(
    function (config) {
        const accessToken = hooks.getCookie('Authorization')
        config.headers['Authorization'] = accessToken
        return config
    },
    function (error) {
        return Promise.reject(error)
    },
)

export const apis = {
    auth: token => instance.post('/api/v1/auth', token),

    createProject: data => instance.post('/api/v1/projects', data),
    getProjects: () => instance.get('/api/v1/projects'),
    getProject: projectId => instance.get(`/api/v1/projects/${projectId}`),

    getProjectMembers: projectId => instance.get(`/api/v1/projects/${projectId}/projectmembers`),

    getTextChannels: projectId => instance.get(`/api/v1/projects/${projectId}/text-channels`),

    getIssueList: projectId => instance.get(`/api/v1/projects/${projectId}/issues`),
    createIssue: (projectId, data) => instance.post(`/api/v1/projects/${projectId}/issues`, data),
    deleteIssue: (projectId, issueId) => instance.delete(`/api/v1/projects/${projectId}/issues/${issueId}`),
    getIssueDetail: (projectId, issueId) => instance.get(`/api/v1/projects/${projectId}/issues/${issueId}`),
    changeIssueTitle: (projectId, issueId, topic) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/topic`, topic),
    changeIssueContent: (projectId, issueId, description) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/description`, description),
    changeIssueStatus: (projectId, issueId, progress) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/progress`, progress),
    changeIssuePriority: (projectId, issueId, priority) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/priority`, priority),
    changeIssueManager: (projectId, issueId, managerId) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/manager`, managerId),
    changeIssueCategory: (projectId, issueId, category) =>
        instance.put(`/api/v1/projects/${projectId}/issues/${issueId}/category`, category),
    filterIssue: (projectId, type, condition) =>
        instance.get(`/api/v1/projects/${projectId}/issues/filter?type=${type}&condition=${condition}`),

    // instance.put 'https://naver.com?issue=123'
    getProjectMembers: projectId => instance.get(`/api/v1/projects/${projectId}/projectmembers`),
    getMember: memberId => instance.get(`/api/v1/members/${memberId}`),
    getMe: () => instance.get(`api/v1/members/me`),
}
