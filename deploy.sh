#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp web/target/micro-web.war $JBOSS_HOME/standalone/deployments/
cp manager/target/micro-manager.war JBOSS_HOME/standalone/deployments/
echo "deploy success!"

#nohup sh standalone.sh > nohup.out 2>&1 &
sh $JBOSS_HOME/bin/standalone.sh

