FROM eclipse-temurin:17-jre AS runtime

ARG APP_NAME=sync-job
WORKDIR /app

COPY target/${APP_NAME}.jar app.jar

ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxRAMPercentage=75.0 -Dfile.encoding=UTF-8"
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8090

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
