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

    getMember: memberId => instance.get(`/api/v1/members/${memberId}`),
    getMe: () => instance.get(`api/v1/members/me`),
}
