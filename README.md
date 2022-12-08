# coworking-space Project

This project consists of the backand for an application for members and administrators of a coworking space. An account can be created by registering with first name last name email and a password that has to be entered twice to avoid registrations with inadvertently wrong passwords. Once the authentication is done, the user has the role of a member who can create bookings. He can also view a list of his bookings and can also change or delete them. He can also change his password. An admin can view a list of all bookings and can ajust, delete, accept or decline them. He can also view all the users and manage them.

## Project setup

To setup the project you need to have a running docker container, a Dev Container in which the project runs and in which the Quarkus extention needs to be installed.

## Starting the project

To start the project enter the command: *Quarkus: Debug current Quarkus project* in the VSCode command palette. The application the runs on the localhost:8080 and the database on localhost:5050

## Loading the testdata

The testdata is defined in ch.zli.m223.service.TestDataService.java and will be loaded automatically by the start of the application

## Changes

As one of functional extended requirements I planned the following:
*As Member I want to be able to update my login data after registering so that I can apply any changes to my account*

While programming though, I considered it more sensible to make the member only able to update his password istead of all credentials like firstname, lastname etc.

## Additional notes by quarkus.io

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/coworking-space-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.