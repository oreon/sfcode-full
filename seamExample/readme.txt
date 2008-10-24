Seam Software Manifest

* Eclipse 3.4 Ganymede
* JBoss Seam 2.0.2.SP1
* Apache Tomcat 6.0.16
* Maven 2.0.9
* JDK 5

Notes

1. With initial list of dependencies, I got this error:
   javax/el/ExpressionFactory violates loader constraints it looks
   like javax.el was included in the project, it was a transitive
   dependency of jboss.el, here are the results of "mvn dependency:tree"

   [INFO] |  +- org.jboss.el:jboss-el:jar:2.0.1.GA:compile
   [INFO] |  |  \- javax.el:el-api:jar:1.0:compile

   Make javax.el provided to fix the error:

    <dependency>
      <groupId>javax.el</groupId>
      <artifactId>el-api</artifactId>
      <version>1.0</version>
      <scope>provided</scope>
    </dependency>

2. To run integration tests with Maven and Seam, you'll need to make src/main/webapp a 
   test resources directory
   
3. Seam needs a seam.properties file in the classes directory to know this is a path
   it should scan.  Place seam.properties in both src/main/resources and src/test/resources
   
4. If you do not annotate a field with @In, you will need to create a public setXXX method
   for seam to call.  If you want a property to be read only, just create a getXXX method
   but no setXXX method. 
   
5. When editing the pages.xml file, you may need to add the pages.xsd file to Eclipses
   XML catalog.  Download the XSD (http://jboss.com/products/seam/pages-2.1.xsd), open
   Preferences->XML->XML Catalog, click Add... and browse to the file you just downloaded
   click OK and the XSD should now be in your Eclipse XSD catalog. 
   
6. See if there is a way to use @Startup from the components.xml file.

7. If you don't want to put an @Name annotation on an object, say in your model, you can create
   a "name" for you component with the <component> element in the components.xml file.

8. Methods marked with @Factory need to be public

9. When you put in an exception handler use the message element to give a user friendly messaage
   instead fo a stack trace.
   
10. In pages.xml, all view-id attributes must include a slash, like this view-id="/home.xhtml" 
    and NOT: view-id="home.xhtml"
    
11. Hibernate and import.sql, good link: http://www.jroller.com/eyallupu/entry/hibernate_s_hbm2ddl_tool

12. Good blog on Dates in HSQLDB: http://dotal.wordpress.com/2007/04/26/dates-in-hsqldb-and-oracle/

13. If a field is not outjected, you need to provide at least a getXXX method for it to be
    accessible by Seam
    
14. What do you do if you modify the data that is displayed in a list?  Raise an event via the Events 
    class and then add an observer to catch the event and refresh the transient data!
TODO

7/1/08 - built User and RegisterAction, need to:
   1. create register page and hook it into register action
   2. build hello.xhtml page
   
   3. Need to flush out User/Role objects and get authentication working
   
7/8/08 - bite the bullet
   1. Use Tomcat's Context.xml file and make the datasource for the persistence.xml flie
      a jndi name.
   2. In the unit test, we'll need to mock it out.
   
