# Stage 1: Build and run Java 11 application
FROM openjdk:11-jdk as java_builder
WORKDIR /app
COPY jar/cgm-task-spring-maven.jar /app
EXPOSE 8080

# Stage 2: Build Node.js application
FROM node:14 as node_builder
WORKDIR /app2
RUN git clone https://github.com/mikestashevski/cgm-task-ui.git
WORKDIR /app2/cgm-task-ui
RUN npm install
RUN npm run build
EXPOSE 3000

# Stage 3: Combine both applications into a single image
FROM openjdk:11-jre
WORKDIR /app3
COPY --from=java_builder /app/cgm-task-spring-maven.jar /app3
COPY --from=node_builder /app2/cgm-task-ui/build /app3/cgm-task-ui/build

# Run both applications in the background
CMD ["sh", "-c", "java -jar cgm-task-spring-maven.jar & (cd /app3/cgm-task-ui/build && npx serve -s . -l 3000)"]
