# netflow-stocks microservice

## Running application & Configuration
Run **'mvn spring-boot:run -Dnetflow.stocks.home={path/to/home/directory}'** maven command <br/><br/>
Environment specific external configuration needs to be provided in **netflow-stocks.properties** file in home directory, 
example of properties file can be found in test resources. <br/><br/>
Example run command: **'mvn spring-boot:run -Dnetflow.stocks.home=D:\projects\netflow\netflow-stocks'**
 

## Remote debugging
Add property **-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"**
