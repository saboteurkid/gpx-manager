#!/bin/bash
#
# Startup script for Main
set -e
CONF=conf
JVM_HEAP=${JVM_HEAP:="-Xmx128m -Xms128m"}
NUM_PROCESSORS=${NUM_PROCESSORS:=4}
opts="${JVM_HEAP} \
        -XX:SurvivorRatio=8 \
        -XX:+UseParNewGC \
        -XX:+UseConcMarkSweepGC \
        -XX:+CMSParallelRemarkEnabled \
        -XX:ActiveProcessorCount=${NUM_PROCESSORS} \
        "
JVM_OPTS=${JVM_OPTS:=""}
if [ "x${JVM_OPTS}" = "x" ]; then
  JVM_OPTS=$opts
fi

exec java -jar $JVM_OPTS app.jar $@
