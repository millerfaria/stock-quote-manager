# Build solution
mvnw clean install -DskipTestsm

# Docker build
docker build . -t stock-quote-manager

# Docker run containers

docker container run --name stock-manager-container -p 8080:8080 -d lucasvilela/stock-manager

docker container run --name mysql-container-database -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -d mysql:8


docker container run --name stock-quote-manager-container -p 8081:8081  --link mysql-container-database:mysql --link stock-manager-container:lucasvilela/stock-manager -d stock-quote-manager


