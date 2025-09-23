# Node.js 20 이상으로 변경
FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

# mode 환경변수를 전달
ARG MODE=production
ENV VITE_MODE=${MODE}

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0", "--mode", "${VITE_MODE}"]