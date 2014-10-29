#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp web/target/micro-web.war D://software/jboss-as-web-7.0.1.Final/standalone/deployments
echo "deploy success!"

sh D:/software/jboss-as-web-7.0.1.Final/bin/standalone.bat
