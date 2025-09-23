
# 도커 컨테이너로 모두 실행
docker-compose up --build

# 로컬에서 개발 시 DB만 컨테이너 실행해서 연결.
docker-composse up mysql

## TODO
1. docker-compose에 실행 명령어에 환경변수 추가해서 실행하여 application.yml을 dev / prod 로 분리해야 함.
> 현재 DB설치를 docker에서 하기때문에 연결하려는 host가 달라서 연결 실패. (pc에서 붙을 때는 local / docker에서 서버까지 실행 시 mysql )
