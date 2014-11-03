#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp manager/target/micro-manager.war D://software/jboss-as-web-7.0.1.Final/standalone/deployments
cp web/target/micro-web.war D://software/jboss-as-web-7.0.1.Final/standalone/deployments
echo "deploy success!"
