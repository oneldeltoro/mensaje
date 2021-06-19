# mensaje

- Escribir mensaje

  java -jar .\mensajes-1.0-SNAPSHOT-jar-with-dependencies.jar --msg jskdfkjsdfkjb --logToFileParam true  --logToConsoleParam true --logToDatabaseParam false --logMessageParam true --logWarningParam true --logErrorParam false --driver postgress --host localhost --port 1234 --dbname hdfj --username dfg --password fdjk


  
Los argumentos para lanzar comando java, es la siguiente:

```yaml
--msg 
--logToFileParam 
--logToConsoleParam 
--logToDatabaseParam 
--logMessageParam 
--logWarningParam 
--logErrorParam 
--driver 
--host 
--port 
--dbname
--username 
--password fdjk 

```

-Docker

docker run -e ARGS="--msg jskdfkjsdfkjb --logToFileParam true  --logToConsoleParam true --logToDatabaseParam false --logMessageParam true --logWarningParam true --logErrorParam false --driver postgress --host localhost --port 1234 --dbname hdfj --username dfg --password fdjk" quay.io/onel_deltoro/mensaje

