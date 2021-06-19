#!/bin/bash
echo argumentos: ${ARGS}
CMD="java -jar /app/apps.jar ${ARGS}";

exec ${CMD}
