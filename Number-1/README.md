# Number 1

A simple mobile application connecting through HTTP to the back-end server.

## Penetration Test
The test should be considered as a black-box pentest. Reviewing the source reveals the secret end-point, this is not the goal of this part, though. So, only the compiled APK and the remote server are the scope supposed to be tested.

## The Goal
The goal is capturing the traffic by some sort of tools, then extracting the administrator's note from the remote server. The APK discloses important information about the end-point.

## Instalation

Make the docker image, then run the new container with commands below:
```
> docker build -t Andrill_1 .
> docker run -d -p 8081:8081 --name Andrill_number_1 Andrill_1
```