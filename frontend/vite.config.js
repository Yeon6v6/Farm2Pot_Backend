import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Axios가 요청할 때 사용할 모듈별 프록시 설정
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/user-api': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/user-api/, '/api'),
        configure: proxy => {
          proxy.on('error', (err, req, res) => {
            console.log(`❌ [Proxy Error][user-api]: ${err.message}`)
            res.writeHead(502, { 'Content-Type': 'application/json' })
            res.end(
              JSON.stringify({ error: 'User 서버에 연결할 수 없습니다.' })
            )
          })
        },
      },
      '/sub-api': {
        target: 'http://localhost:8083',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/sub-api/, '/api'),
        configure: proxy => {
          proxy.on('error', (err, req, res) => {
            console.log(`❌ [Proxy Error][sub-api]: ${err.message}`)
            res.writeHead(502, { 'Content-Type': 'application/json' })
            res.end(
              JSON.stringify({
                error: 'Subscription 서버에 연결할 수 없습니다.',
              })
            )
          })
        },
      },
      '/admin-api': {
        target: 'http://localhost:8084',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/admin-api/, '/api'),
        configure: proxy => {
          proxy.on('error', (err, req, res) => {
            console.log(`❌ [Proxy Error][admin-api]: ${err.message}`)
            res.writeHead(502, { 'Content-Type': 'application/json' })
            res.end(
              JSON.stringify({ error: 'Admin 서버에 연결할 수 없습니다.' })
            )
          })
        },
      },
    },
  },
})
