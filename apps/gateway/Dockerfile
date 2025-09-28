FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/gateway-*.jar app.jar

EXPOSE 9080

ENTRYPOINT ["java", "-jar", "app.jar"]


# GATEWAY
# 1단계: 빌드
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app/farm2pot
# backend 전체 복사 (gradlew 포함)
COPY . .

# gateway 모듈만 빌드
RUN ./gradlew :apps:gateway:bootJar --no-daemon

# 2단계: 런타임
FROM eclipse-temurin:21-jdk

WORKDIR /app/farm2pot
COPY --from=builder /app/farm2pot/apps/gateway/build/libs/*.jar farm2pot-gateway.jar

ENTRYPOINT ["java", "-jar", "farm2pot-gateway.jar"]
