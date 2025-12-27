# ims-gateway-service
Inventory Management System Spring Boot 3.2.1 Micro Services

Purpose:
Single entry point to backend APIs

Contains:

Spring Cloud Gateway

Route definitions

CORS

Token relay

Rate limiting

Centralized logging

Example Routes:
/api/inventory/** → inventory-service
/api/users/**     → user-service
/api/orders/**    → order-service

Why NOT inside ims-infra?
It is runtime application code
It has its own build, Docker image, lifecycle
It must scale independently

👉 Golden rule:



If it produces a Docker image → it gets its own repo.

