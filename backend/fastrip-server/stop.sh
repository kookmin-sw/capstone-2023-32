#!/bin/bash

if [ -f app.pid ]; then
  kill `cat app.pid`
  rm app.pid
  echo 'fastrip stopped.'
fi