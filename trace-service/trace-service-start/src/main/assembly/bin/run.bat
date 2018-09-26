cd ..
java -Xms1g -Xmx1g -XX:PermSize=64M -XX:+UseConcMarkSweepGC -classpath conf -jar lib\trace-service-start-1.0.0.jar