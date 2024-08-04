# ME_BUILDOUT_XMEME_JAVA
**link:- https://www.crio.do/learn/portfolio/yogeshrathee7400/ME_BUILDOUT_XMEME_JAVA/**

This project serves as a template for initializing and running Spring Boot projects. It includes a variety of tools and configurations to help you get started quickly.

## What's Included
1. Gradle file created from start.spring.io
2. Plugins for Spotbugs, Checkstyle, and Jacoco included
3. Dependencies for MongoDB, MySQL, and Redis
4. Dockerfile to start the Mongo server and run the Spring Boot application within

## Project Overview

### Features
- **User Authentication**: Secure login and registration functionality.
- **Meme Sharing**: Users can share memes with the community.
- **Meme Feed**: A feed displaying all the memes shared by users.
- **CRUD Operations**: Users can create, read, update, and delete memes.
- **Database Integration**: MongoDB is used to store user and meme data.

## Usage

### Building the Repository
To build the repository:

1. Run the build:
    ```sh
    ./gradlew build test
    ```
2. Create the executable jar:
    ```sh
    ./gradlew bootjar
    ```

The jar will be located inside the build directories.

This file can be used as a template for initializing and running spring projects.

What's included: 
1. Gradle file created from start.spring.io
2. Plugins for Spotbugs, Checkstyle and Jacoco included
3. Other dependencies like Mongo, MySql and redis.
4. Dockerfile to start mongo server and run the spring boot application within.

Usage - 

1. To build the repository - 

From the repository root, 

1. run `./gradlew build test`run the build
2. run `./gradlew bootjar` to create executable jar. The jar will be located inside build directories.

To run inside docker container, use below commands

To build docker image, use the command below - `docker build -t your_tag_name  .`

To run the generated container, use this command - `docker run -p8080:8080 your_tag_name`. This will run the server on 8080 port.. You can change the ports as per your needs. 


License - 
While this repository is licensed under APACHE 2.0 license, It is mandatory for users to share the readme.md and License file along with the changes they do in the contents.
