# netflow-stocks microservice

## What does is do?
 1. **Exposes stock quotes** data via REST service in JSON. Service loads stocks data from local DB or populates it 
 from 'Yahoo Finance' if requested stock is unknown. <br/> 
 2. **Updates stock quotes** data daily using scheduled process to collect data from 'Yahoo Finance'.
  
## How to use it?
Stock information can be retrieved using ```[HOST]:8080/stocks/[STOCK_SYMBOL]``` GET request.

## Prerequisites
* **Backing database.** DB creations scripts can be found under ```/src/main/database``` folder in sources.  
* **External configuration file.** Environment specific properties, including data source configuration need to be provided 
in ```netflow-stocks.properties``` file in service home directory. Properties file template can be found in 
```main/test/resources/config/netflow-stocks.properties.template``` file. 

## Running application & Configuration
### Running
Run ```mvn spring-boot:run -Dnetflow.stocks.home=[path/to/home/directory]``` maven command <br/>
Sample: ```mvn spring-boot:run -Dnetflow.stocks.home=D:\projects\netflow\netflow-stocks```

### Remote debugging
Add property ```-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005```


