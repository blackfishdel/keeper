#!/bin/bash -x

#docker
#	mysql
#	web


#启动数据库
docker run --name mysql -e MYSQL_ROOT_PASSWORD='Del!@#$5679921' -p 3306:3306 -d mysql:5.5


docker run --name redis -p 6379:6379 -d redis:3.2-alpine redis-server --appendonly yes

config set requirepass Del!@#$5679921