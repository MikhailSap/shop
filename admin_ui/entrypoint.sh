#!/usr/bin/env sh

#/usr/bin/java -XX:+UnlockExperimentalVMOptions \
#              -XX:+UseCGroupMemoryLimitForHeap \
#              -Xmx256m \
#              -Xss512k \
#              -XX MetaspaceSize=100m \
  /usr/bin/java -jar /bin/app.jar
