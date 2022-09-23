FROM adoptopenjdk/openjdk11:latest
WORKDIR /app
ARG JAR_FILE=target/budgetapp-1.0.jar
ADD ${JAR_FILE} .
ENTRYPOINT ["java","-jar","budgetapp-1.0.jar"]