import axios from 'axios'

const subscriptionClient = axios.create({
  baseURL: '/sub-api', // vite proxy prefix
  timeout: 5000, // 요청 제한 시간
  withCredentials: true, // 쿠키/인증 정보 포함 필요 시
})

export default subscriptionClient
