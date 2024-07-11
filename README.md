# Spring Boot Expert: JPA, RESTFul API, Security, JWT and more
![](https://img.shields.io/badge/status-progress-blue)

<p align="center">
<img src=".github/logo.png">
</p>

A complete course of Spring Boot.

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
```
