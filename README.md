# netflow-stocks microservice

## What does is do?
 1. **Exposes stock quotes** data via REST service in JSON. Service loads stocks data from local DB or populates it 
 from 'Yahoo Finance' if requested stock is unknown. <br/> 
 2. **Updates stock quotes** data daily using scheduled process to collect data from 'Yahoo Finance'.
  
## How to use it?
Stock information can be retrieved using ```[HOST]:8080/stocks/[STOCK_SYMBOL]``` GET request.

## Running application & Configuration

### Running
Run ```mvn spring-boot:run``` maven command to start the application with in memory database. 
Default datasource (H2 in memory database) configuration can be overridden by external application properties file using ```-Dspring.config.location=file:[path_to_properties_file]```. 

### Remote debugging
Add property ```-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"```

### Proxy settings
Add properties ```-Dhttp.proxyHost=[HOST] -Dhttp.proxyPort=[PORT]```

### Externalizing storage
 * **Database creation scripts** can be found under ```/src/main/database``` folder in sources.  
 * **External configuration file** template providing datasource configuration example can be found in ```main/test/resources/config/netflow-stocks.properties.template```. 

### Dynamic class reloading
To enable use ```-Pspring-loaded``` profile when starting application from maven. 
When running application from sources, IDE recompiled classes will be automatically re-loaded by auto-configured 'SpringLoaded'. 

#TODO
 * add lookup to readme
 * DoS, XSS, DB
 * lookup statistics - number of requests and hit/failure rate