#!/bin/bash

echo "Killing all active user-service containers"

containers=$(docker ps | grep "user-service" | awk '{print$1}')
if [ -n "$containers" ]
then
   docker kill $containers
   docker rm $containers
fi

#if [ -n "$(docker ps -a -q)" ]
#then
#   docker rm $(docker ps -a -q)
#fi

#docker kill $(docker ps -q)

#docker rm $(docker ps -a -q)

