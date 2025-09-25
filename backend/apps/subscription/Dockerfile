# SUBSCRIPTION
# 1단계: 빌드
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app/farm2Pot
# backend 전체 복사 (gradlew 포함)
COPY . .

# subscription 모듈만 빌드
RUN ./gradlew :apps:subscription:bootJar --no-daemon

# 2단계: 런타임
FROM eclipse-temurin:21-jdk

WORKDIR /app/farm2Pot
COPY --from=builder /app/farm2Pot/apps/subscription/build/libs/*.jar farm2Pot-sub.jar

ENTRYPOINT ["java", "-jar", "farm2Pot-sub.jar"]
