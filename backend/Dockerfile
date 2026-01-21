# Stage 1: Build all backend modules
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy Gradle wrapper and root Gradle files
COPY gradlew .
COPY gradle/ gradle/
COPY settings.gradle .
COPY build.gradle .

# Make gradlew executable
RUN chmod +x gradlew

# Copy all backend modules
COPY obelix-common/ obelix-common/
COPY obelix-quarry-api/ obelix-quarry-api/
COPY obelix-quarry-impl/ obelix-quarry-impl/
COPY obelix-basket-api/ obelix-basket-api/
COPY obelix-basket-impl/ obelix-basket-impl/
COPY obelix-webshop/ obelix-webshop/

# Build all modules (skip tests for faster build)
RUN ./gradlew build -x test

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy built jars from build stage
COPY --from=build /app/obelix-quarry-impl/build/libs/*.jar obelix-quarry-impl.jar
COPY --from=build /app/obelix-basket-impl/build/libs/*.jar obelix-basket-impl.jar
COPY --from=build /app/obelix-webshop/build/libs/*.jar obelix-webshop.jar

# Copy entrypoint script
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

# Expose all microservice ports
EXPOSE 8080 8081 8082

# Run all backend services
ENTRYPOINT ["./entrypoint.sh"]