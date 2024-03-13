FROM eclipse-temurin:17-jdk-alpine as backend-build

WORKDIR /app

ENV S3_REGION=eu-central-1 \
    BUCKET=provedcode-backend \
    S3_ACCESS_KEY=AKIA5FTZDX7SRTYGK4PR \
    S3_SECRET_KEY=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 
#    DB_URL=129.151.222.203:5432/provedcode \
#    DB_LOGIN=provedcode \
#    DB_PASSWORD=FemQcpYd9MsmaSWW8LKY \
#    SPRING_PROFILES_ACTIVE=prod \
#    EMAIL_USER=proved.code.team@gmail.com \
#    EMAIL_PASSWORD=qqgtxxenjiesrrnn
COPY . .
RUN chmod 744 /app/mvnw \
    && cd /app \
    && ./mvnw clean package

FROM eclipse-temurin:17-jre

COPY --from=backend-build /app/target/*.jar /app/provedcode.jar

CMD ["java", "-jar", "/app/provedcode.jar", "--server.port=8080"]
