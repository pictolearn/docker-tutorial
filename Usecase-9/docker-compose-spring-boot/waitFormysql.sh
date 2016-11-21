#!/bin/bash
a=1
until [ ! $a -lt 10 ]
do
   echo $a
   a=`expr $a + 1`
   sleep 2;
done
`java -jar -DlogPath=/usr/local/pictolearn /usr/local/pictolearn/target/docker-compose-pictolearn-1.0.0-SNAPSHOT.jar`

