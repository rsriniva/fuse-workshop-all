STEP by STEP Procedure to deploy a Camel CDI example on Karaf using :
- Pax CDI 0.6.x,
- Weld 2,
- Apache Camel 2.12
- OSGI v5 (is required by Pax CDI)

1) Download and Install Apache Karaf - 3.x)
 - http://karaf.apache.org/index/community/download.html

OR
- https://repository.apache.org/content/repositories/snapshots/org/apache/karaf/apache-karaf/3.0.0-SNAPSHOT/

- Install Pax CDI Repo File & Features

Karaf 2.x instructions

addurl mvn:org.ops4j.pax.cdi/pax-cdi-features/0.6.0-SNAPSHOT/xml/features
features:install pax-war
features:install pax-cdi-1.1-web-weld

Karaf 3.x instructions

Weld

repo-add mvn:org.ops4j.pax.cdi/pax-cdi-features/0.6.0-SNAPSHOT/xml/features
feature:install pax-cdi-1.1-web-weld

OpenWebbeans

repo-add mvn:org.ops4j.pax.cdi/pax-cdi-features/0.6.0-SNAPSHOT/xml/features
feature:install pax-cdi-openwebbeans


- Install Camel Features Repo file & Features Required for CDI

Karaf 2.x instructions

addurl mvn:org.apache.camel.karaf/apache-camel/2.12.0/xml/features
features:install camel-core
install -s mvn:org.apache.deltaspike.cdictrl/deltaspike-cdictrl-api/0.5
install -s mvn:org.apache.deltaspike.core/deltaspike-core-api/0.5
install -s mvn:org.apache.camel/camel-cdi/2.12.0

Karaf 3.x instructions

Camel 2.12
repo-add mvn:org.apache.camel.karaf/apache-camel/2.12.0/xml/features
feature:install camel-core
install -s mvn:org.apache.deltaspike.core/deltaspike-core-api/0.5
install -s mvn:org.apache.camel/camel-cdi/2.12.0

Camel 2.13 including Provide-Capability
repo-add mvn:org.apache.camel.karaf/apache-camel/2.13-SNAPSHOT/xml/features
feature:install camel-core
install -s mvn:org.apache.deltaspike.core/deltaspike-core-api/0.5
install -s mvn:org.apache.camel/camel-cdi/2.13-SNAPSHOT

- Install Example

- Download Git Project : example source here :https://github.com/cmoulliard/fuse-workshop-all
- Build module routing/cdi-osgi : ‘mvn clean install’
-  Deploy it

install -s mvn:org.fusesource.workshop/cdi-osgi/1.0
