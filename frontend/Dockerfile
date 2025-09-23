# Node.js 20 이상으로 변경
FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

# build (옵션: 도커 빌드 시 미리 번들링)
RUN npm run build

# 실행 (개발 서버가 아니라 실제 production build를 서비스)
CMD ["sh", "-c", "npm run dev -- --host 0.0.0.0 --mode ${VITE_MODE}"]