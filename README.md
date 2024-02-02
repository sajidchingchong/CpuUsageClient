# Pre-requisite
Active MQ should be up and running

Check the following: https://activemq.apache.org/version-5-getting-started.html

# Build
`mvn install`

# Run
`java -jar target/CpuUsageClient-1.0-jar-with-dependencies.jar {arg1} {arg2} {arg3}`

where 

> arg1 = A unique alphanumeric client identifier. It should start with a letter. The characters beyond length 8 will be trimmed.
>
> arg2 = A number specifying max cpu mock load. It should be between 1 and 100 or will be defaulted to 100.
>
> arg3 = 'r' to specify random value cpu usage or an sin wave style based usage algorithm will be used.

> Example: `java -jar target/CpuUsage-1.0-jar-with-dependencies Test 50 r`
