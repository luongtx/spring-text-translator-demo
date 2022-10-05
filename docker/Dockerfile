FROM openjdk:8-jdk-alpine as builder
ARG WORKDIR=/home/microservice
ARG MICROSVC=${terminal.name}

WORKDIR ${WORKDIR}

COPY ${MICROSVC} ${MICROSVC}
RUN java -Dfile.encoding=Cp437 -Djarmode=layertools -jar ${MICROSVC} extract
#-------------------------------------------------------------

FROM openjdk:8-jdk-alpine as runner

ARG WORKDIR=/home/microservice

WORKDIR ${WORKDIR}

COPY  --from=builder ${WORKDIR}/dependencies/ ./
COPY  --from=builder ${WORKDIR}/spring-boot-loader/ ./
COPY  --from=builder ${WORKDIR}/snapshot-dependencies/ ./
COPY  --from=builder ${WORKDIR}/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
CMD [""]

ARG USER="nobody"