#!/bin/bash

echo "Killing all active user-service containers"

containers=$(docker ps | grep "budgetapp" | awk '{print$1}')
if [ -n "$containers" ]
then
   docker kill $containers
   docker rm $containers
fi

aws --version

echo "Pulling image and starting container"

docker login -u AWS -p $(aws ecr get-login-password --region us-east-1) 667631227859.dkr.ecr.us-east-1.amazonaws.com
docker pull 667631227859.dkr.ecr.us-east-1.amazonaws.com/budgetapp
docker run -it --env-file /home/ec2-user/docker/env_files/budgetapp.env -p 8082:8082 -d 667631227859.dkr.ecr.us-east-1.amazonaws.com/budgetapp