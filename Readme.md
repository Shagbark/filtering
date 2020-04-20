#### This application is an implementation of the idea of creating data filters.

This is a pet-project for implementing the idea of creating data filters using Redis. 
This is my first project in Kotlin using Redis.

### The idea of creating filters
When I created API using filters in them, I was constantly discouraged with using POST requests 
for filtering data. After all, it's better to use GET request to receive and filter data.
One day, I came across an answer on StackOverflow with a proposal of this idea.  

### The algorithm of filtering data in API:
1. Front application sends a POST request to create a filter (i.e. instead of sending GET request 
to receive data by the filter, it sends to create the filter) 
2. Backend application saves JSON value of filter to the Redis (the redis key is the hash of JSON string(it's a simple 
Java hash, which was obtained by calling the ```java.lang.Object#hashCode()``` method))
3. After it, backend sends response with filled **Location** header and HTTP status 301.
4. Then, we can send the request to URL, which was indicated in **Location** header and we will
get data, which was request in the first request.

Stack:
* Kotlin
* Spring Boot
* Spring Data
* Gradle 
* Flyway
* Redis (and Spring Jedis library)