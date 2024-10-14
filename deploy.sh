kubectl apply -f order-management-env-config.yml
kubectl apply -f product-env-config.yml
kubectl apply -f order-cache-redis.yml
kubectl apply -f order-db-postgresql.yml
kubectl apply -f product-db-es-mongodb.yml
kubectl apply -f product-db-main-postgresql.yml
kubectl apply -f order-management-service.yml
kubectl apply -f product-listing-service.yml