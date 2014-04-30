sawsdl-springws-sample
======================

Spring Web Services sample application aims to show, how to use Semantic Annotations for WSDL (SAWSDL).

Requirements:
=====
1. Java HotSpot 1.6+
2. Apache Maven 3.0+
3. Access to internet

How to use:
=====
1. Extract archive: tar xzf sawsdl-springws-sample.tar.gz

Run Web Application (server)
==
2. Go to Web application project directory: cd sawsdl-springws-sample/spring-webservices-webapp
3. Run Web application with semantic web-service: mvn clean install && mvn jetty:run

Run Jax-WS client
==
2. Go to Web application project directory: cd sawsdl-springws-sample/spring-webservices-client
3. Run Jax-WS client: mvn clean install && mvn exec:exec

Run SAWSDL (Semantic Annotations for WSDL) integration test based on embedded Jetty HTTP server
==
2. Go to Web application project directory: cd sawsdl-springws-sample/spring-webservices-webapp
3. Run integration test: mvn clean test
