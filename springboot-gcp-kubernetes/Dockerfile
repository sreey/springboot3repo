FROM alpine:3.16.0 
RUN apk add --no-cache java-cacerts openjdk17-jdk
LABEL key="Sree Yakkanti"
COPY target/springboot-gcp-kubernetes-1.jar springboot-gcp-kubernetes.jar
ENTRYPOINT ["java","-jar","/springboot-gcp-kubernetes.jar"]