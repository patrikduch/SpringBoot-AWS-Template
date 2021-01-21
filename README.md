
## Dockerization

### Build image

docker build -f Dockerfile -t docker-springboot .


### Run image

docker run -p 8080:8080  docker-springboot