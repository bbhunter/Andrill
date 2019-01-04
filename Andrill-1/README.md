# Andrill 1

A simple mobile application connecting through HTTP to the back-end server.

## Penetration Test
The test should be considered as `black-box` pentest. Since the test is black-box, please `DO NOT` review the source code (either NodeJS back-end or Android's Java) which reveals the information. So, only the compiled APK and the remote server are the scope supposed to be tested.

## The Goal
The goal is capturing the traffic by some sort of tools, then extracting the administrator's note from the remote server. The APK discloses important information about the end-point.

## Instalation
Edit the `dockerfile` and put the server's IP address and the port number (the default value is 8081 which is published by the docker container, you don't have to change the value):

```
RUN sh ./Apk-Source/build-APK-here.sh [Server IP]:8081
```
Make the docker image, then run the new container with commands below:
```
> docker build -t Andrill_1 .
> docker run -d -p 8081:8081 --name Andrill_number_1 Andrill_1
```
Browse the server, download the APK, install it and begin the hacking.
