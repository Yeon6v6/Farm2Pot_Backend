# USER
# 1단계: 빌드
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app/farm2pot
# backend 전체 복사 (gradlew 포함)
COPY . .

# user 모듈만 빌드
RUN ./gradlew :apps:user:bootJar --no-daemon

# 2단계: 런타임
FROM eclipse-temurin:21-jdk

WORKDIR /app/farm2pot
COPY --from=builder /app/farm2pot/apps/user/build/libs/*.jar farm2pot-user.jar

ENTRYPOINT ["java", "-jar", "farm2pot-user.jar"]
