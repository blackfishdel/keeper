#!/bin/bash -x

#docker
#	mysql
#	web


#启动数据库
docker run --name mysql -e MYSQL_ROOT_PASSWORD='Del!@#$5679921' -p 3306:3306 -d mysql:5.5
