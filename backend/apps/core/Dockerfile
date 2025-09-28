# Gradle 빌드용 JDK
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# 루트 gradle 파일과 소스 복사
COPY . .

# core 모듈만 빌드 (멀티모듈 루트 backend 기준)
RUN ./gradlew :apps:core:bootJar --no-daemon

# 실행용 JDK
FROM eclipse-temurin:21-jdk

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=builder /app/apps/core/build/libs/*.jar farm2pot-core.jar

# 실행
ENTRYPOINT ["java", "-jar", "/app/farm2pot-core.jar"]
