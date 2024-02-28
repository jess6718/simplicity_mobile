#!/bin/bash

api='https://virtserver.swaggerhub.com/UCSANTOS/Simplicity/1.0.0/'
if [ $# = 1 ]; then
  if [ "$1" = 'local' ]; then
    api='http://192.168.1.100:3030/'
  fi
fi

