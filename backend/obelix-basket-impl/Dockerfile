# ---------- Build ----------
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY settings.gradle build.gradle gradle/ ./

COPY obelix-common/ obelix-common/
COPY obelix-quarry-api/ obelix-quarry-api/
COPY obelix-basket-api/ obelix-basket-api/
COPY obelix-basket-impl/ obelix-basket-impl/

RUN chmod +x gradlew
RUN ./gradlew bootJar -x test

# ---------- Runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/obelix-basket-impl/build/libs/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]