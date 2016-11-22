#!/bin/bash
a=1
until [ ! $a -lt 11 ]
do
   echo $a
   a=`expr $a + 1`
   sleep 2;
done
pictolearn="java -jar -DlogPath=/usr/local/pictolearn /usr/local/pictolearn/target/docker-compose-pictolearn-1.0.0-SNAPSHOT.jar"
$pictolearn
