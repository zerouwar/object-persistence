#!/usr/bin/env bash
git pull
PID=`cat pid`
(sleep 1; kill ${PID}) &
wait ${PID}
sbt stage
target/universal/stage/bin/object-persistence > object-persistence.log 2>&1 &
echo "$!" > pid