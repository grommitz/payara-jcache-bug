# payara-jcache-bug

Shows how trying to use a custom expiry policy class causes a ClassNotFoundException

To reproduce:

* download payara-micro.jar to current directory

* then:

        mvn clean install
        java -jar payara-micro-5.183.jar --deploy target/payara-jcache-bug-1.0-SNAPSHOT.war
    
* access the api:

        http://localhost:8080/jcachebug/rest/cache

In the log you should see the java.lang.ClassNotFoundException.    
