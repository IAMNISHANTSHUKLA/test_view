# Microservices-Based E-commerce Platform

## Project Overview

This project is a microservices-based e-commerce platform designed to showcase the implementation of modern cloud-native technologies such as Docker and Kubernetes. The platform follows a modular architecture where each service (e.g., product listing, order management, and caching) is independently deployed, scaled, and managed.

### Key Services:

- **Order Management Service**: Handles the lifecycle of orders, including placement, tracking, and fulfillment. It relies on Redis for caching and PostgreSQL for order persistence.
- **Product Management Service**: Manages the product catalog, including listing, searching, and updating products. The service uses PostgreSQL as the primary database and MongoDB to simulate elastic-search for enhanced text-based searching.

The project is containerized using Docker, and Kubernetes is used for orchestration, scaling, and service management. It leverages Kubernetes YAML files for deploying the individual services, managing environment variables, and configuring databases.

For detailed explanations of the overall architecture and live demonstrations, please refer to the video [here](https://www.youtube.com/watch?v=6mrKaLNbZ0E).

---

## Steps to Run Locally Using Minikube and kubectl

### Prerequisites

- **Minikube**: Ensure that Minikube is installed and running on your local machine.
- **kubectl**: Install `kubectl`, the command-line tool to interact with Kubernetes clusters.
- **Docker**: Ensure that Docker is installed and running on your local machine.
- Clone the repository to your local machine.

### Steps to Run Locally

1. **Start Minikube**:
   Start a Minikube cluster by running the following command:

   ```bash
   minikube start --cpus 4 --memory 6000
   ```
2. **Verify Minikube Setup**:
   Verify that Minikube is running properly by checking the status:

   ```bash
   minikube status
   ```
3. **Deploy Services**:
   The `deploy.sh` script contains the necessary `kubectl` commands to deploy all the services using the provided YAML files. To execute the deployment, simply run:

   ```bash
   ./deploy.sh
   ```

   This will apply the following configurations:

   - **Redis Cache**: Deployed using `order-cache-redis.yml`, which configures Redis to notify for keyspace events.
   - **PostgreSQL for Orders**: Configured in `order-db-postgresql.yml` for storing order data.
   - **Order Management**: Deployed using `order-management-service.yml`.
   - **Product Catalog**: Managed by two databases—PostgreSQL (`product-db-main-postgresql.yml`) and MongoDB (`product-db-es-mongodb.yml`). The product listing service is deployed using `product-listing-service.yml`.
4. **Check Deployments**:
   After running the deployment script, verify that all services have been deployed successfully by listing the Kubernetes pods:

   ```bash
   kubectl get pods
   ```
5. **Access the Services**:
   Depending on the configuration, you may need to expose the services externally. You can expose a service using Minikube:

   ```bash
    minikube service <service-name>
   ```

   For example, to access the Order Management service:

   ```bash
   minikube service order-management-service
   ```

   Expose order-management-service and product-listing-service to test them locally.

---

By following the steps outlined above, you should be able to successfully run the e-commerce platform on your local Minikube Kubernetes cluster.
