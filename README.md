

# SpringBoot AWS Template

Basic simplified template for using SpringBoot platform on the Elastic BeanStalk (AWS cloud provider). EBStalk must
be configurared as Docker service. Application is fully dockerizated, so it's easy to transit from AWS to another Cloud
provider or personal VPS.


## Dockerization

### Build image

docker build -f Dockerfile -t docker-springboot .


### Run image

docker run -p 8080:8080  docker-springboot


## Database

