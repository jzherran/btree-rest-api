# BTree-rest-api

Rest api for manage Binary tree objects, this endpoint as 4 endpoints exposed in a springboot
 application.
 
# Installation
1. Clone the repository and take in account you need to use Java 8+.
2. Run from terminal inside the project folder this command: ``./gradlew clean build``
3. Remember you need to have configured in your environment JAVA_HOME.
4. Run after build this command ``java -jar build/libs/btree-rest-api-0.0.1-SNAPSHOT.jar``
5. Or you can choose use gradle for running the project with this command ``./gradlew bootRun``

# Examples Requests
The URL base of the API is ``http://localhost:8080``. 

1. Get all B-Trees stored:

```json
GET /api/v1/b-tree/

cache-control: no-cache
Postman-Token: 0ce50783-3ad0-4f41-924a-3ed0d8eb8ddd
User-Agent: PostmanRuntime/7.6.0
Accept: */*
Host: localhost:8080
cookie: JSESSIONID=05E6D14E283660D0FB4EA651484EC7FF
accept-encoding: gzip, deflate

HTTP/1.1 200
status: 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Aug 2019 03:19:24 GMT

Response :: [{"id":1,"root":{"value":67,"left":{"value":39,"left":{"value":28,"right":{"value":29
}},"right":{"value":44}},"right":{"value":76,"left":{"value":85,"left":{"value":83},"right":{"value":87}},"right":{"value":74}}}}]
```

2. Get lowest common ancestor with identifier of B-Tree stored:

````json
GET /api/v1/b-tree/lowestCommonAncestor/1

cache-control: no-cache
Postman-Token: 8efd5d7d-fc5a-449c-99a9-3e0371f516a1
User-Agent: PostmanRuntime/7.6.0
Accept: */*
Host: localhost:8080
cookie: JSESSIONID=05E6D14E283660D0FB4EA651484EC7FF
accept-encoding: gzip, deflate

HTTP/1.1 200
status: 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Aug 2019 03:20:25 GMT

Response :: 85
````

3. Get lowest common ancestor with B-Tree received in the body:

````json
GET /api/v1/b-tree/lowestCommonAncestor

Content-Type: application/json
cache-control: no-cache
Postman-Token: 10678501-41d2-4439-8ca9-2e3a63222f1a
User-Agent: PostmanRuntime/7.6.0
Accept: */*
Host: localhost:8080
cookie: JSESSIONID=05E6D14E283660D0FB4EA651484EC7FF
accept-encoding: gzip, deflate
content-length: 573
{ "root": { "value": 67, "left": { "value": 39, "left": { "value": 28, "right": { "value": 29 } }, "right": { "value": 44 } }, "right": { "value": 76, "right": { "value": 74 }, "left": { "value": 85, "left": { "value": 83 }, "right": { "value": 87 } } } } }

HTTP/1.1 200
status: 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Aug 2019 03:23:48 GMT

Response :: 67
````

4. Create a new B-Tree:

````json
POST /api/v1/b-tree/
Content-Type: application/json
cache-control: no-cache
Postman-Token: a0c09c2a-a516-4cef-93d5-e9a259d8c273
User-Agent: PostmanRuntime/7.6.0
Accept: */*
Host: localhost:8080
cookie: JSESSIONID=05E6D14E283660D0FB4EA651484EC7FF
accept-encoding: gzip, deflate
content-length: 573
{ "root": { "value": 67, "left": { "value": 39, "left": { "value": 28, "right": { "value": 29 } }, "right": { "value": 44 } }, "right": { "value": 76, "right": { "value": 74 }, "left": { "value": 85, "left": { "value": 83 }, "right": { "value": 87 } } } } }

HTTP/1.1 200
status: 200
Content-Length: 0
Date: Sun, 04 Aug 2019 03:19:21 GMT
````