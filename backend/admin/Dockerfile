# 1단계: 빌드
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app/farm2Pot
# backend 전체 복사 (gradlew 포함)
COPY . .

# admin 모듈만 빌드
RUN ./gradlew :admin:bootJar --no-daemon

# 2단계: 런타임
FROM eclipse-temurin:21-jdk

WORKDIR /app/farm2Pot
COPY --from=builder /app/farm2Pot/admin/build/libs/*.jar farm2Pot-admin.jar

ENTRYPOINT ["java", "-jar", "farm2Pot-admin.jar"]
