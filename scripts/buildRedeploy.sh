#!/bin/sh

echo "************ UNDEPLOYING *******************"
/opt/devel/as/glassfish3/glassfish/bin/asadmin undeploy jeedemo
echo "************ BUILDING **********************"
mvn package
echo "************ DEPLOYING *********************"
/opt/devel/as/glassfish3/glassfish/bin/asadmin deploy target/jeedemo.war
