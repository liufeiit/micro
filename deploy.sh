#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp web/target/stats-web.war ~/dev/jboss-as-web-7.0.2.Final/standalone/deployments/
echo "deploy success!"

#nohup sh standalone.sh > nohup.out 2>&1 &
sh /home/lf/dev/jboss-as-web-7.0.2.Final/bin/standalone.sh

