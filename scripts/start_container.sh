#!/bin/bash

echo "Killing all active user-service containers"

containers=$(docker ps | grep "trackerapp" | awk '{print$1}')
if [ -n "$containers" ]
then
   docker kill $containers
   docker rm $containers
fi

aws --version

echo "Pulling image and starting container"

#aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 319897880590.dkr.ecr.ap-south-1.amazonaws.com
docker login -u AWS -p $(aws ecr get-login-password --region ap-south-1) 667631227859.dkr.ecr.us-east-1.amazonaws.com
docker pull 667631227859.dkr.ecr.us-east-1.amazonaws.com/budgetapp
#docker tag 319897880590.dkr.ecr.ap-south-1.amazonaws.com/user-service user-service
docker run -it --env-file /home/ec2-user/docker/env_files/budgetapp.env -p 8081:8081 -d 667631227859.dkr.ecr.us-east-1.amazonaws.com/budgetapp