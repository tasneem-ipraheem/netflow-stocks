# netflow-stocks microservice

## What does is do?
 1. **Exposes stock quotes** data via REST service in JSON. Service loads stocks data from local DB or populates it 
 from 'Yahoo Finance' if requested stock is unknown. <br/> 
 2. **Updates stock quotes** data every 15 minutes using scheduled process to collect data from 'Yahoo Finance'.
 3. **Collects usage statistics** and exposes collected data in JSON format.
  
## How to use it?
REST API is exposed in ```http://<HOST>/swagger/index.html```

## Running application & Configuration

### Running
Run ```mvn spring-boot:run``` maven command to start the application with in memory database. 
Default datasource (H2 in memory database) configuration can be overridden by external application properties file using ```-Dspring.config.location=file:<path_to_properties_file>```. 

### Remote debugging
Add property ```-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"```

### Proxy settings
Add properties ```-Dhttp.proxyHost=<HOST> -Dhttp.proxyPort=<PORT>```

### Externalizing storage
 * **Database creation scripts** can be found under ```/src/main/database``` folder in sources.  
 * **External configuration file** template providing datasource configuration example can be found in ```main/test/resources/config/netflow-stocks.properties.template```. 

### Dynamic class reloading
To enable use ```-Pspring-loaded``` profile when starting application from maven. 
When running application from sources, IDE recompiled classes will be automatically re-loaded by auto-configured 'SpringLoaded'. 

## Monitoring, Statistics & Logging

### Logging
Logs can be found in ```<NETFLOW_APP_DIRECTORY>/netflow-stocks.log``` and logging level customised using ```application.properties```.

### Monitoring & Statistics
Spring Boot built-in Actuator monitoring can be accessed by URL: 
```http://<HOST>/manage/[autoconfig|beans|configprops|dump|env|health|info|metrics|mappings|shutdown|trace|jolokia]```. <br/>

#Security
Monitoring and statistics URLs are protected by default ```admin/secret``` credentials that can be overridden by 
 ```netflow.users.admin.*``` properties in application.properties. To logout use ```http://<HOST>/logout``` URL. <br/> 
To prevent XSS and SQL injections, all user input (i.e. TICKER symbols, lookup queries) is encoded. <br/><br/>
