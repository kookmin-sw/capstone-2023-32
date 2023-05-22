#!/bin/bash

# Attempt to set JAVA_HOME , LANG

export JAVA_HOME=/usr
export LANG=en_US.UTF-8


nohup $JAVA_HOME/bin/java -jar ./target/fastrip-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 & echo $! > app.pid