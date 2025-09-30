# 1단계: 빌드 (Builder Stage)
FROM eclipse-temurin:21-jdk AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 및 설정 파일 복사 (캐싱 레이어 1)
# 루트의 gradlew, settings.gradle, build.gradle 복사
COPY gradlew settings.gradle build.gradle /app/
COPY gradle /app/gradle

# gradlew 실행 권한 부여
RUN chmod +x ./gradlew
RUN sed -i 's/\r$//' gradlew

# 종속성 다운로드 및 캐싱 실행
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
# 소스 코드가 변경될 때만 해당 레이어와 이후 레이어가 재실행
COPY . /app
RUN sed -i 's/\r$//' gradlew

# 최종 JAR 빌드
RUN ./gradlew :apps:gateway:bootJar --no-daemon

# ----------------------------------------------------------------------------------

# 2단계: 런타임 (Runtime Stage)
FROM eclipse-temurin:21-jdk

# 최종 JAR 파일 복사 준비
WORKDIR /app/farm2pot

# 빌더 스테이지에서 생성된 JAR 파일 복사
COPY --from=builder /app/apps/gateway/build/libs/*.jar farm2pot-gateway.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "farm2pot-gateway.jar"]