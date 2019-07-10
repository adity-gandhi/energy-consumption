# energy consumption

## energy consumption service
Service to get energy consumption

### Fetch from Github

```
> mkdir $HOME/service
> cd service
> git clone https://github.com/adity-gandhi/energy-consumption
> cd energy-consumption
```

### Docker Build (suggested)

````
> cd energy-consumption
> docker build -t ag/energy-consumption:latest .
````

### Run Docker Image

```
There are two port 8080 (service) and 8081 (monitoring)

> docker run --rm -p8080:8080 -p8081:8081 -it  ag/energy-consumption
```

#### Service Endpoints

```
> Swagger UI Endpoint -> localhost:8080/swagger-ui.html
> Conmsumption Service Endpoint -> localhost:8080/energyConsumption?product=<<productName>>
```

#### Monitoring Endpoints

```
> Info Endpoint -> localhost:8081/monitor/info
> Heath Endpoint -> localhost:8081/monitor/health
> Metrics Endpoint -> localhost:8081/monitor/metrics

> Business Metric for energy consumption --> localhost:8081/monitor/metrics/energyConsumption
```

#### Service Logs


.   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.3.RELEASE)

10-07-2019 12:04:08.940|main|INFO |com.ag.services.Application|logStarting|Starting Application on WKWIN5955364 with PID 18716 (C:\techPOCs\AWSSamples\energy-consumption\target\classes started by adigandh in C:\techPOCs\AWSSamples\energy-consumption)
10-07-2019 12:04:08.943|main|INFO |com.ag.services.Application|logStartupProfileInfo|The following profiles are active: prod
10-07-2019 12:04:11.223|main|INFO |o.s.b.w.e.tomcat.TomcatWebServer|initialize|Tomcat initialized with port(s): 8080 (http)
10-07-2019 12:04:11.243|main|INFO |o.a.coyote.http11.Http11NioProtocol|log|Initializing ProtocolHandler ["http-nio-8080"]
10-07-2019 12:04:11.259|main|INFO |o.a.catalina.core.StandardService|log|Starting service [Tomcat]
10-07-2019 12:04:11.259|main|INFO |o.a.catalina.core.StandardEngine|log|Starting Servlet engine: [Apache Tomcat/9.0.16]
10-07-2019 12:04:11.373|main|INFO |o.a.c.c.C.[Tomcat].[localhost].[/]|log|Initializing Spring embedded WebApplicationContext
10-07-2019 12:04:11.373|main|INFO |o.s.web.context.ContextLoader|prepareWebApplicationContext|Root WebApplicationContext: initialization completed in 2384 ms
10-07-2019 12:04:12.025|main|INFO |s.d.s.w.PropertySourcedRequestMappingHandlerMapping|initHandlerMethods|Mapped URL path [/v2/api-docs] onto method [public org.springframework.http.ResponseEntity<springfox.documentation.spring.web.json.Json> springfox.documentation.swagger2.web.Swagger2Controller.getDocumentation(java.lang.String,javax.servlet.http.HttpServletRequest)]
10-07-2019 12:04:12.170|main|INFO |o.s.s.c.ThreadPoolTaskExecutor|initialize|Initializing ExecutorService 'applicationTaskExecutor'
10-07-2019 12:04:12.810|main|INFO |o.s.b.w.e.tomcat.TomcatWebServer|initialize|Tomcat initialized with port(s): 8081 (http)
10-07-2019 12:04:12.811|main|INFO |o.a.coyote.http11.Http11NioProtocol|log|Initializing ProtocolHandler ["http-nio-8081"]
10-07-2019 12:04:12.812|main|INFO |o.a.catalina.core.StandardService|log|Starting service [Tomcat]
10-07-2019 12:04:12.812|main|INFO |o.a.catalina.core.StandardEngine|log|Starting Servlet engine: [Apache Tomcat/9.0.16]
10-07-2019 12:04:12.832|main|INFO |o.a.c.c.C.[Tomcat-1].[localhost].[/]|log|Initializing Spring embedded WebApplicationContext
10-07-2019 12:04:12.833|main|INFO |o.s.web.context.ContextLoader|prepareWebApplicationContext|Root WebApplicationContext: initialization completed in 131 ms
10-07-2019 12:04:12.868|main|INFO |o.s.b.a.e.web.EndpointLinksResolver|<init>|Exposing 3 endpoint(s) beneath base path '/monitor'
10-07-2019 12:04:12.936|main|INFO |o.a.coyote.http11.Http11NioProtocol|log|Starting ProtocolHandler ["http-nio-8081"]
10-07-2019 12:04:12.970|main|INFO |o.s.b.w.e.tomcat.TomcatWebServer|start|Tomcat started on port(s): 8081 (http) with context path ''
10-07-2019 12:04:12.980|main|INFO |s.d.s.w.p.DocumentationPluginsBootstrapper|start|Context refreshed
10-07-2019 12:04:13.017|main|INFO |s.d.s.w.p.DocumentationPluginsBootstrapper|start|Found 1 custom documentation plugin(s)
10-07-2019 12:04:13.054|main|INFO |s.d.s.w.s.ApiListingReferenceScanner|scan|Scanning for api listing references
10-07-2019 12:04:13.083|main|INFO |o.a.coyote.http11.Http11NioProtocol|log|Starting ProtocolHandler ["http-nio-8080"]
10-07-2019 12:04:13.091|main|INFO |o.s.b.w.e.tomcat.TomcatWebServer|start|Tomcat started on port(s): 8080 (http) with context path ''
10-07-2019 12:04:13.093|main|INFO |com.ag.services.Application|logStarted|Started Application in 4.601 seconds (JVM running for 4.963)
