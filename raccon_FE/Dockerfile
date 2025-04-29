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

RUN mkdir -p /var/www/certbot/.well-known/acme-challenge
RUN chmod -R 755 /var/www/certbot/.well-known/acme-challenge/

RUN mkdir -p /etc/letsencrypt

FROM nginx:stable-alpine AS production-stage

RUN mkdir -p /usr/share/nginx /var/cache/nginx /var/run/nginx && \
    chown -R nginx:nginx /usr/share/nginx /var/cache/nginx /var/run/nginx && \
    chmod -R 755 /usr/share/nginx /var/cache/nginx /var/run/nginx

RUN mkdir -p /var/run/nginx && \
    chown -R nginx:nginx /var/run/nginx && \
    chmod -R 755 /var/run/nginx

COPY ./certs/fullchain.pem /etc/nginx/certs/fullchain.pem
COPY ./certs/privkey.pem /etc/nginx/certs/privkey.pem

RUN chmod 600 /etc/nginx/certs/fullchain.pem /etc/nginx/certs/privkey.pem
RUN chown nginx:nginx /etc/nginx/certs/fullchain.pem /etc/nginx/certs/privkey.pem

COPY ./nginx/nginx.conf /etc/nginx/nginx.conf


RUN mkdir -p /racconworld_data/img
RUN chmod g+s /racconworld_data/img
RUN chown -R nginx:nginx /racconworld_data/img && chmod -R 775 /racconworld_data/img

COPY --from=build-stage /app/dist /usr/share/nginx/html

USER nginx

EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]
