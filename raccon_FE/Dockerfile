# 1단계: Vue 애플리케이션 빌드 (Node.js 환경에서)
FROM node:16 AS build-stage

# 작업 디렉터리 설정
WORKDIR /app

# package.json과 package-lock.json 파일 복사
COPY package*.json ./

# 의존성 설치
RUN npm install

# 소스 파일 복사
COPY . .

# Vue 애플리케이션 빌드 (build 명령어 실행)
RUN npm run build

# 2단계: Nginx로 빌드된 정적 파일을 서빙 (Nginx 환경)
FROM nginx:stable-alpine AS production-stage

# Nginx 설정 파일을 기본 설정과 덮어씌움 (Optional)
COPY ./nginx/nginx.conf /etc/nginx/nginx.conf

# 빌드된 Vue 파일을 Nginx의 기본 경로로 복사
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Nginx 포트 노출
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
