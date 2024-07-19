# Spring Boot Expert: JPA, RESTFul API, Security, JWT and more
![](https://img.shields.io/badge/status-progress-blue)

<p align="center">
<img src=".github/logo.png">
</p>

A complete course of Spring Boot from [Udemy](https://www.udemy.com/course/spring-boot-expert).

Creator: Dougllas Sousa

## History of commands

```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash

# Install several Java version
sdk list java
sdk install java 8.0.412-amzn

# Set default Java
sdk default java 8.0.412-amzn

# Install IntelliJ IDEA
wget https://download-cdn.jetbrains.com/idea/ideaIC-2023.3.7.tar.gz
# Follow Install-Linux-tar.txt
# Click on Options Menu > Create Desktop Entry...

# Install Postman
```

## Endpoints with HTTPie

```bash
# Create new client
echo '{"name": "John", "cpf": "12345678909"}' | http POST :8080/api/clients/

# List clients
http GET :8080/api/clients/

# Create new products
echo '{"description": "Water", "unitPrice": "5"}' | http POST :8080/api/products/
echo '{"description": "Wine", "unitPrice": "42.25"}' | http POST :8080/api/products/

# List products
http GET :8080/api/products/

# Create Purchase order
echo '{ "client": 1, "total": 101, "items": [ {"product": 2, "amount": 2}, {"product": 3, "amount": 10} ] }' | \
http POST :8080/api/orders/

# Get purchase order
http GET :8080/api/orders/6

# Patch order status
echo '{"name": "John", "cpf": "12345678909"}' | http POST :8080/api/clients/
echo '{"description": "Water", "unitPrice": "5"}' | http POST :8080/api/products/
echo '{ "client": 2, "total": 101, "items": [ { "product": 3, "amount": 2 } ] }' | \
http POST :8080/api/orders/
http PATCH :8080/api/orders/5 newStatus=CANCELLED

# Basic auth
http -a fulano:123 GET :8080/api/clients/

# Create new user
http POST :8080/api/users login=Administrator password=123 admin=true
http -a Administrator:123 GET :8080/api/clients/

# Using JWT
http POST :8080/api/users/auth login=Administrator password=123

# Create client using JWT
JWT=PutHereYourJwtToken
echo '{"name": "John", "cpf": "12345678909"}' | http -A bearer -a $JWT POST :8080/api/clients/
http -A bearer -a $JWT GET :8080/api/clients/
```

## Using MySQL with Docker

```bash
# Start containter with MySQL server on 3306
docker compose up -d

# Testing using MySQL

## Create admin user
http POST :8080/api/users login=admin password=admin admin=true

## Login admin
http POST :8080/api/users/auth login=admin password=admin

## Create and list clients (Using token in every request)
echo '{"name": "John", "cpf": "12345678909"}' | http -A bearer -a $JWT POST :8080/api/clients/
http -A bearer -a $JWT GET :8080/api/clients/

## Create and list products
echo '{"description": "Water", "unitPrice": "5"}' | http -A bearer -a $JWT POST :8080/api/products/
echo '{"description": "Wine", "unitPrice": "42.25"}' | http -A bearer -a $JWT POST :8080/api/products/
http -A bearer -a $JWT GET :8080/api/products/

# Create and list orders
echo '{ "client": 1, "total": 101, "items": [ {"product": 1, "amount": 2}, {"product": 2, "amount": 10} ] }' | \
http -A bearer -a $JWT POST :8080/api/orders/
http -A bearer -a $JWT GET :8080/api/orders/1
```

## Build aplication

```bash
# Install maven
sdk list maven
sdk install maven 3.3.9
sdk default maven 3.3.9
mvn -v

# Build and run JAR
mvn clean package
java -jar target/using_data_jpa-1.0-SNAPSHOT.jar

# Build WAR
mvn clean package

# Using profiles
mvn clean package -P development
mvn clean package -P production
```

# Extras

```bash
# Using Java 11
sdk install java 11.0.23-amzn
```
