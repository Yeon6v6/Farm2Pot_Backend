import axios from 'axios'

/**
 * 공통 Axios Wrapper
 * client: axios instance
 * name: 서버 이름 (User, Admin, Subscription)
 */
class ApiClient {
  constructor(baseURL, name) {
    this.client = axios.create({ baseURL, timeout: 5000 })
    this.name = name
  }

  async get(path, config) {
    try {
      const res = await this.client.get(path, config)
      return res.data
    } catch (err) {
      // 서버가 응답하지 않거나 502, 500 등도 잡아서 메시지 반환
      if (err.response?.data?.error) {
        throw new Error(`${this.name} 서버 오류: ${err.response.data.error}`)
      }
      throw new Error(`${this.name} 서버 요청 실패`)
    }
  }

  async post(path, data, config) {
    try {
      const res = await this.client.post(path, data, config)
      return res.data
    } catch (err) {
      if (err.response?.data?.error) {
        throw new Error(`${this.name} 서버 오류: ${err.response.data.error}`)
      }
      throw new Error(`${this.name} 서버 요청 실패`)
    }
  }

  // 필요하면 put, delete 등도 추가 가능
}

// 각 서버별 인스턴스
export const userApi = new ApiClient('/user-api', 'User')
export const subscriptionApi = new ApiClient('/sub-api', 'Subscription')
export const adminApi = new ApiClient('/admin-api', 'Admin')
