# Curso de Introducción a las tecnologías JEE

## Instalación

- [Git](https://git-scm.com/)
- [JDK](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Eclipse Installer](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2025-03/R/eclipse-inst-jre-win64.exe)
  - [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2025-03/R/eclipse-jee-2025-03-R-win32-x86_64.zip)
- Eclipse plugins
  - [OmniFish tools for Eclipse GlassFish](https://omnifish.ee/developers/glassfish-server/ide-plugins-for-glassfish/eclipse-ide/)
  - [Eclipse Krazo (MVC)](https://projects.eclipse.org/projects/ee4j.krazo)
- Web Services Clients
  - [SoapUI Open Source](https://www.soapui.org/downloads/soapui/)
  - [Postman](https://www.postman.com/downloads/)
- Docker (opcional pero recomendable)
  - [WSL 2 feature on Windows](https://learn.microsoft.com/es-es/windows/wsl/install)
  - [Docker Desktop](https://www.docker.com/get-started/)
  - Alternativas a Docker Desktop
    - [Podman](https://podman.io/docs/installation)
    - [Rancher Desktop](https://rancherdesktop.io/)
- Clientes de bases de datos (opcionales)
  - [HeidiSQL](https://www.heidisql.com/download.php)
  - [DBeaver Community](https://dbeaver.io/download/)
  - [MongoDB Compass](https://www.mongodb.com/try/download/compass)

## Servidores de aplicaciones

- [Java™ EE 8 SDK](https://www.oracle.com/java/technologies/javaee-8-sdk-downloads.html)
- [Eclipse GlassFish](https://glassfish.org/download.html)
- [Wildfly (antes JBoss)](https://www.wildfly.org/downloads/)

## Tutoriales

- [Java EE Tutorial (Legacy)](https://www.oracle.com/java/technologies/jee-tutorials.html)
- [Java EE 8 Tutorial](https://javaee.github.io/tutorial/)
- [First Cup of Java EE 8 Tutorial](https://javaee.github.io/firstcup/)
- [Jakarta EE Tutorial](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/index.html)

## Starters

- [Jakarta EE 8 Starter Boilerplate](https://github.com/hantsy/jakartaee8-starter-boilerplate)
- [Starter for Jakarta EE](https://start.jakarta.ee/)

## Base de datos de ejemplos

- [Página principal Sakila](https://dev.mysql.com/doc/sakila/en/)
- [Diagrama de la BD Sakila](http://trifulcas.com/wp-content/uploads/2018/03/sakila-er.png)

## Paquetes Java

- <https://downloads.mysql.com/archives/get/p/3/file/mysql-connector-java-5.1.49.zip>
- <https://sourceforge.net/projects/hibernate/files/hibernate-orm/5.6.5.Final/hibernate-release-5.6.5.Final.zip/download>

## Contenedores

### Agentes de Mensajería

#### Apache ActiveMQ o Artemis (JMS)

    docker run -d --name activemq -p 1883:1883 -p 5672:5672 -p 8161:8161 -p 61613:61613 -p 61614:61614 -p 61616:61616 jamarton/activemq

    docker run -d --name artemis -p 1883:1883 -p 5445:5445 -p 5672:5672 -p 8161:8161 -p 9404:9404 -p 61613:61613 -p 61616:61616 jamarton/artemis

### Bases de datos

#### MySQL

    docker run -d --name mysql-sakila -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 jamarton/mysql-sakila

#### MongoDB

    docker run -d --name mongodb -p 27017:27017 -v .:/externo jamarton/mongodb-contactos

#### Redis

    docker run -d --name redis -p 6379:6379 -p 6380:8001 -v .:/data redis/redis-stack:latest

### Comandos

    docker run --rm -it --name maven -v %cd%:/local -w /local -v maven-repository:/root/.m2 maven:3.8.6-eclipse-temurin-8 sh
