# ---------- Build ----------
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY settings.gradle build.gradle gradle/ ./

COPY obelix-common/ obelix-common/
COPY obelix-quarry-api/ obelix-quarry-api/
COPY obelix-basket-api/ obelix-basket-api/
COPY obelix-webshop/ obelix-webshop/

RUN chmod +x gradlew
RUN ./gradlew :obelix-webshop:bootJar -x test

# ---------- Runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/obelix-webshop/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]