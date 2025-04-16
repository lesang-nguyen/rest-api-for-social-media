# Social Media Restful API

## Setup DOCKER for Local
1. Download Docker.
2. Run Script to create DB
```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle
```
Explain the command line
```
docker run --detach //docker command line to run
--env MYSQL_ROOT_PASSWORD=dummypassword  // create root password
--env MYSQL_USER=social-media-user  // create root user
--env MYSQL_PASSWORD=dummypassword // password to user id
--env MYSQL_DATABASE=social-media-database  // name of database
--name mysql //name of container
--publish 3306:3306  // post of db
mysql:8-oracle // name, version of this mySQL image
```
