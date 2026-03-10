# ────────────────────────────────────────────────
# Stage 1: Builder (downloads deps + compiles)
# ────────────────────────────────────────────────
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# 1. Copy only poms first → great cache layer
COPY pom.xml .
COPY */pom.xml ./

# Optional: if you have deeper nested modules
# COPY common/pom.xml common/
# COPY redis/pom.xml     redis/
# COPY api/pom.xml       api/

# 2. Download dependencies (cached until poms change)
RUN --mount=type=cache,target=/root/.m2/repository  mvn dependency:go-offline -B

# 3. Copy full source code
COPY . .

# 4. Build → install to local .m2 so modules can depend on each other
#    -DskipTests = faster, safer for Docker build
RUN --mount=type=cache,target=/root/.m2/repository  mvn clean package -DskipTests -B


# ────────────────────────────────────────────────
# Stage 2: Runtime image (very small)
# ────────────────────────────────────────────────
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copy ONLY the executable JAR from the module you actually run
# Adjust "api" / "redis" / "your-module-name" to match YOUR real module name
COPY --from=builder /build/target/*.jar          app.jar
# or
# COPY --from=builder /build/redis/target/*.jar      app.jar
# or (if you renamed during package or use standard naming)
# COPY --from=builder /build/*/target/*-exec.jar     app.jar
# or safest wildcard if only one runnable jar exists in all targets
# COPY --from=builder /build/*/target/*.jar          app.jar

# Optional: expose port your app uses
EXPOSE 8080

# Run (you can add JVM flags, Spring profiles, etc.)
ENTRYPOINT ["java", "-Duser.timezone=Europe/Kyiv", "-jar", "app.jar"]


