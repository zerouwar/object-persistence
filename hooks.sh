#!/usr/bin/env bash
git pull
kill `cat pid`
sbt stage
target/universal/stage/bin/object-persistence > object-persistence.log 2>&1 &
echo "$!" > pid