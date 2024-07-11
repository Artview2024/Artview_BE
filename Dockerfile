# open jdk 17 버전의 환경을 구성
FROM openjdk:17-jdk

#gradle로 빌드했을 때 jar 파일이 생성되는 경로 작성
ARG JAR_FILE=./build/libs/Artview-0.0.1-SNAPSHOT.jar

# JAR_FILE을 app.jar(==컨테이너 내부 jar 파일 경)로 복사
COPY ${JAR_FILE} app.jar

# 운영 및 개발에서 사용되는 환경 설정을 분리, 컨테이너를 싱행하면 자동으로 jar 파일을 행하도록 함
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "/app.jar" ]