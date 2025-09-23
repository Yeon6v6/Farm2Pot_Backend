import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'

export default ({ mode }) => {
  const env = loadEnv(mode, process.cwd(), 'VITE_')

  return defineConfig({
    plugins: [react()],
    server: {
      host: env.VITE_FRONTEND_HOST || '0.0.0.0',
      port: Number(env.VITE_FRONTEND_PORT) || 5173,
      strictPort: true,
      hmr: {
        host: 'localhost', // 개발용 핫 리로드
      },
      proxy: {
        '/user-api': {
          target: env.VITE_USER_API,
          changeOrigin: true,
          rewrite: path => path.replace(/^\/user-api/, '/api'),
          configure: proxy => {
            proxy.on('error', (err, req, res) => {
              console.log(`❌ [Proxy Error][user-api]: ${err.message}`)
              res.writeHead(502, { 'Content-Type': 'application/json' })
              res.end(JSON.stringify({ error: 'User 서버에 연결할 수 없습니다.' }))
            })
          },
        },
        '/sub-api': {
          target: env.VITE_SUB_API,
          changeOrigin: true,
          rewrite: path => path.replace(/^\/sub-api/, '/api'),
          configure: proxy => {
            proxy.on('error', (err, req, res) => {
              console.log(`❌ [Proxy Error][sub-api]: ${err.message}`)
              res.writeHead(502, { 'Content-Type': 'application/json' })
              res.end(JSON.stringify({ error: 'Subscription 서버에 연결할 수 없습니다.' }))
            })
          },
        },
        '/admin-api': {
          target: env.VITE_ADMIN_API,
          changeOrigin: true,
          rewrite: path => path.replace(/^\/admin-api/, '/api'),
          configure: proxy => {
            proxy.on('error', (err, req, res) => {
              console.log(`❌ [Proxy Error][admin-api]: ${err.message}`)
              res.writeHead(502, { 'Content-Type': 'application/json' })
              res.end(JSON.stringify({ error: 'Admin 서버에 연결할 수 없습니다.' }))
            })
          },
        },
      },
    },
  })
}
