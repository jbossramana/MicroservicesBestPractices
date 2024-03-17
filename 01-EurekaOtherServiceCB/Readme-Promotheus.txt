pom.xml
--------

<dependency>
<groupId>io.micrometer</groupId>
<artifactId>micrometer-registry-prometheus</artifactId>
</dependency>


application.yml
---------------

resilience4j.circuitbreaker:
  configs:
    default:
      slidingWindowType: COUNT_BASED
      slidingWindowSize: 100
      permittedNumberOfCallsInHalfOpenState: 10
      waitDurationInOpenState: 10
      failureRateThreshold: 60
      registerHealthIndicator: true
      
     
management:
  endpoints:
    web:
      exposure:
        include:
        - "*"
    
  endpoint:
    health:
      show-details: always

prometheus.yml
--------------

scrape_configs:
 - job_name: 'spring-actuator'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
    - targets: ['localhost:8081']


