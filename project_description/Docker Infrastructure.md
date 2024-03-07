The task was to build the following infrastructure on the server using Docker, with frontend and backend services running in containers.
![[docker infrastucture 1.jpg]]
The following steps needed to be performed:
1. Select and deploy a server
2. Install Docker
3. Describe Dockerfile for the backend application
4. Describe Dockerfile for the frontend application
5. Describe Docker Compose
6. Verify the application's functionality

# Select and Deploy a Server
Before installing Docker, it's necessary to determine the resources that will be used by the application containers. Considering the experience with building the frontend application, it required approximately 2 GB of RAM. Therefore, either a local host or a dedicated server on a cloud provider can be used, but at a minimum with 1 VCPU, 2 GB of RAM, and 8 GB of disk space with Ubuntu. I chose to deploy on a dedicated server on a cloud provider. It could be AWS, GCP, Azure, and others.

# Installing Docker
When installing Open Source applications, use official websites where the [installation](https://docs.docker.com/engine/install/ubuntu/) process is described in detail.
```
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```
If IPv6 is enabled on the server, it is recommended to temporarily disable it for correct operation when editing the IPv4 address file:


`sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1  sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1  sudo sysctl -w net.ipv6.conf.lo.disable_ipv6=1`

To check if Docker is running, use the command to show the currently running containers:
```
sudo docker ps -a
```
# Creating Dockerfile for the backend application
Initially, it was necessary to write a Dockerfile for the container image, where the source code is built along with environment variables and deploy the backend application. There was a question about which builder image to use for Java in the container. `eclipse-temurin` was used instead of `open-jdk` because the latter is outdated.
![[Pasted image 20240208175258.png]]
It is also important to know the type of processor architecture of the server where Docker is installed. For example, many images do not work on arm64 because the architecture is relatively new at the moment. Most images from Docker Hub are designed for the amd64 architecture. Images based on the Alpine distribution have a relatively small size, making them convenient to use.
![[Pasted image 20240208213455.png]]
During the execution of the Dockerfile, git was installed because it is not included by default in the Alpine distribution. Then the backend application source code is cloned, the ```dev``` branch is selected, environment variables are set, and the application is built.

The Dockerfile uses the Multi-Stage Builds approach, which means that a temporary environment, ```eclipse-temurin:17-jdk-alpine```, was used for building the Java application. This environment is intended solely for build tools and source code *(Java Development Kit - a set of tools for developing Java applications, including the Java compiler, standard Java libraries, examples, documentation, various utilities, and the Java runtime environment)*.

After the build is complete, its files are moved to another environment, ```eclipse-temurin:17-jre```, which can only run the compiled Java application *(Java Runtime Environment - a minimal implementation of the Java Virtual Machine required to run Java applications)* but cannot build them, occupying less memory space. The build files from the previous ```backend-build``` environment are copied into it using ```COPY --from=backend-build /app/backend/target/*.jar /app/provedcode.jar```. Once the build process in the ```backend-build``` or ```eclipse-temurin:17-jre``` environment is finished, it is cleaned up.

Therefore, if the image were built without switching to the ```FROM eclipse-temurin:17-jre``` environment, it would weigh around 550 MB, whereas with this method, it weighs approximately 330 MB.
```
FROM eclipse-temurin:17-jdk-alpine as backend-build

WORKDIR /app

RUN apk add --no-cache git

RUN git clone https://github.com/ProvedCode/backend.git \
    && cd backend \
    && git switch dev

ENV S3_REGION=eu-central-1 \
    BUCKET=provedcode-backend \
    S3_ACCESS_KEY=AKIA5FTZDX7SRTYGK4PR \
    S3_SECRET_KEY=rh3tNnZZbqAdLpTkKda5oP9kua/pyzr0OOVuh/3e \
    DB_URL=129.151.222.203:5432/provedcode \
    DB_LOGIN=provedcode \
    DB_PASSWORD=FemQcpYd9MsmaSWW8LKY \
    SPRING_PROFILES_ACTIVE=prod \
    EMAIL_USER=proved.code.team@gmail.com \
    EMAIL_PASSWORD=qqgtxxenjiesrrnn

RUN chmod 744 /app/backend/mvnw \
    && cd /app/backend \
    && ./mvnw clean package

FROM eclipse-temurin:17-jre

COPY --from=backend-build /app/backend/target/*.jar /app/provedcode.jar

CMD ["java", "-jar", "/app/provedcode.jar", "--server.port=8080"]
```
# Creating Dockerfile for the frontend application
Here, the Multi-Stage Builds approach was also used, ```node:21-alpine``` or ```frontend-build``` - for building with Node.js, and ```nginx:latest``` - to store the build files in the directory for serving the application with Nginx. As a result, the container image size is about 200 MB.
In addition to git, curl is installed here because there was a need to obtain the server address to set the backend application variable address, as well as to adjust the frontend application file. Then, using a regular expression, the incorrect IP value is replaced.
```
FROM node:21-alpine as frontend-build

WORKDIR /app

RUN apk add --no-cache git \
    && apk add --no-cache curl

RUN git clone https://github.com/ProvedCode/frontend.git

WORKDIR /app/frontend

RUN git switch dev \
    && ip=$(curl -s https://ifconfig.me) \
    && export BACKEND_URL=http://$(echo $ip)/:8080 \
    && escaped_ip=$(echo $ip | sed 's/\./\\\./g') \
    && sed -i "s/18\\.194\\.159\\.42:8081/$escaped_ip:8080/g" src/services/api-services.js

RUN npm install \
    && npm run build

FROM nginx:latest

COPY --from=frontend-build /app/frontend/build/ /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
```
# Working with Docker Compose
To work with the application, a `docker-compose.yml` file is described, in which services are described - the backend application and the frontend application. Each service is described as previously described Dockerfile and each one is located in its own subdirectory. That is, the `docker-compose.yml` file should be located in the the above  directory. In case the application in the container becomes inactive, the container will be restarted, which is performed by the setting `restart: always`. In docker-compose, you can set environment variables in a separate file through `env_file:`, making the file more readable. For each container service, you need to specify port forwarding, otherwise the containers will not be able to be accessed over the internet. At the end, the `depends_on` setting is set, where it is specified that the frontend depends on the backend service. Therefore, the backend will be launched earlier and only then the frontend.
```
version: '3.1'

services:
  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    restart: always
    container_name: java-backend
    env_file:
      - ./backend/.env
    ports:
      - 8080:8080

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    restart: always
    container_name: node-frontend
    ports:
      - 80:80
    depends_on:
      - backend
```
The ```env_file:``` itself in ```./backend``` looks like this:
```
S3_REGION=eu-central-1
BUCKET=provedcode-backend
S3_ACCESS_KEY=AKIA5FTZDX7SRTYGK4PR
S3_SECRET_KEY=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

DB_LOGIN=provedcode
DB_PASSWORD=FemQcpYd9MsmaSWW8LKY
DB_URL=129.151.222.203:5432/provedcode

SPRING_PROFILES_ACTIVE=prod

EMAIL_USER=proved.code.team@gmail.com
EMAIL_PASSWORD=qqgtxxenjiesrrnn
```
After launching all containers, it is necessary to check the website's functionality. Since the containers are running on the same server, requests to retrieve the list of users and for registration should be directed to the same server on port 8080. Setting an avatar in the user account on the website should work if AWS S3 and the database are configured correctly.
![[Pasted image 20240208183258.png]]