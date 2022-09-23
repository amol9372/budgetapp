#!/bin/bash

cd /tmp/code-deploy_tasker
ls -altr
gunzip tasker.tar.gz
docker load -i tasker.tar
