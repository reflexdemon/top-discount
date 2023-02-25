# Top Discount

Create a Spring Boot service that exposes REST APIs that allow a user to find the best applicable discount given items purchased. Please provide tests along with it.

#### Requirements:

* The system stores discounts and determines the best discount for a given set of items
* An item has three properties: its id, its cost, and its type
* There are three types of discounts: by item type, by total cost of items, by count of a particular item
* The discount is applied via a percentage. For example, all items of type X are 15% off. Each discount has a corresponding Code
* Multiple discounts may apply but the system should select the best discount for the customer. Respond with the best discount code and with the total cost of the items after applying the discount.
* The system should expose an API to add a new discount
* The system should expose an API to remove an existing discount
* The system should expose an API to calculate the best discount for a given set of items

#### Examples:

GIVEN

* Discount ABC exists that gives 10% off all items of type CLOTHES
* Discount CDE exists that gives 15% off all items over $100

WHEN

User submits a request to calculate the best discount for a $50 shirt(id: 123, type: CLOTHES, cost: $50)

THEN

The system should response with discount ABC and a total cost of $45

GIVEN

* Discount ABC exists that gives 10% off all items of type CLOTHES
* Discount CDE exists that gives 15% off all items over $100
* Discount FGH exists that gives 20% off when purchasing 2 or more of shirts with id 123

WHEN

User submits a request to calculate the best discount for five $50 shirts(id: 123, type: CLOTHES, cost: $50)

THEN

The system should response with discount FGH and a total cost of $200

GIVEN

* Discount ABC exists that gives 10% off all items of type CLOTHES
* Discount CDE exists that gives 15% off all items over $100

WHEN

User submits a request to calculate the best discount for
* one $50 shirt(id: 123, type: CLOTHES, cost: $50)
* one $300 TV(id: 456, type: ELECTRONICS, cost: $300)

THEN

The system should response with discount CDE and a total cost of $305


## Demo

Prerequisites:
* JDK 17
* Terminal

Step 1: Clone the repository to your local using below command.
```shell
$ git clone git@github.com:reflexdemon/top-discount.git
$ cd top-discount
```
Step 2: Build, Compile and Test the application
```shell
$ ./gradlew clean test assemble
```
Sample Output
```text
> Task :test

  io.vpv.topdiscount.TopDiscountApplicationTests

    ✔ contextLoads()
    ✔ checkController()

  io.vpv.topdiscount.api.RestDiscountServiceTest

    ✔ deleteDiscountById()
    ✔ deleteAllDiscounts()
    ✔ createDiscount()
    ✔ getBestDiscountUseCase1()
    ✔ getBestDiscountUseCase2()
    ✔ getBestDiscountUseCase3()

  io.vpv.topdiscount.examples.ValidateWithFiveItems

    ✔ testDiscountAddition()

  io.vpv.topdiscount.examples.ValidateWithOneItem

    ✔ testDiscountAddition()

  io.vpv.topdiscount.examples.ValidateWithTwoItems

    ✔ testDiscountAddition()

  io.vpv.topdiscount.service.DiscountServiceTest

    ✔ testRunTimeExceptionForUpdateDiscount()
    ✔ testRunTimeExceptionForCreateDiscount()
    ✔ testEntityNotFound()

  io.vpv.topdiscount.service.ItemServiceTest

    ✔ getAllItems()
2023-02-25T00:19:42.036-05:00  INFO 89998 --- [extShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2023-02-25T00:19:42.037-05:00  INFO 89998 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2023-02-25T00:19:42.154-05:00  INFO 89998 --- [extShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
2023-02-25T00:19:42.154-05:00  INFO 89998 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
2023-02-25T00:19:42.156-05:00  INFO 89998 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown initiated...
2023-02-25T00:19:42.157-05:00  INFO 89998 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown completed.
2023-02-25T00:19:42.242-05:00  INFO 89998 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2023-02-25T00:19:42.255-05:00  INFO 89998 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2023-02-25T00:19:42.265-05:00  INFO 89998 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2023-02-25T00:19:42.265-05:00  INFO 89998 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
2023-02-25T00:19:42.266-05:00  WARN 89998 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 90121, SQLState: 90121
2023-02-25T00:19:42.266-05:00 ERROR 89998 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-214]
2023-02-25T00:19:42.267-05:00  WARN 89998 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 90121, SQLState: 90121
2023-02-25T00:19:42.267-05:00 ERROR 89998 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-214]
2023-02-25T00:19:42.267-05:00  WARN 89998 --- [ionShutdownHook] o.s.b.f.support.DisposableBeanAdapter    : Invocation of destroy method failed on bean with name 'entityManagerFactory': org.hibernate.exception.JDBCConnectionException: Unable to release JDBC Connection used for DDL execution
2023-02-25T00:19:42.268-05:00  INFO 89998 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown initiated...
2023-02-25T00:19:42.268-05:00  INFO 89998 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown completed.

  15 passing (5.8s)


BUILD SUCCESSFUL in 8s
9 actionable tasks: 9 executed

```
Step 3: Start the Spring Boot Server

```shell
$ java -jar build/libs/top-discount-0.0.1-SNAPSHOT.jar
```
Note: You should see `Started TopDiscountApplication` on your screen to confirm that the server has started successfully

Step 4: Access Swagger UI on your browser  to start Manual Testing. URL: `http://localhost:8080/swagger-ui/index.html`

Step 5: Run API integration tests using CLI (If you are using Mac/Linux)
```shell
$ ./test-case-1.sh
Adding Discount: Discount ABC exists that gives 10% off all items of type CLOTHES
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 101
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:24:48 GMT
<
* Connection #0 to host localhost left intact
{"id":1,"discountCode":"ABC","percentage":0.1,"discountType":"ITEM_TYPE","itemType":"CLOTHES","itemCount":null,"itemCost":null}
Adding Discount: Discount CDE exists that gives 15% off all items over $100
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 97
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:24:48 GMT
<
* Connection #0 to host localhost left intact
{"id":2,"discountCode":"CDE","percentage":0.15,"discountType":"ITEM_COST","itemType":null,"itemCount":null,"itemCost":100.0}
Calculating the best offer
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount/best HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 48
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:24:48 GMT
<
* Connection #0 to host localhost left intact
{"discountCode":"ABC","totalCost":45.0}
```
```shell
$ ./test-case-2.sh
Adding Discount: Discount ABC exists that gives 10% off all items of type CLOTHES
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 101
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:25:47 GMT
<
* Connection #0 to host localhost left intact
{"id":3,"discountCode":"ABC","percentage":0.1,"discountType":"ITEM_TYPE","itemType":"CLOTHES","itemCount":null,"itemCost":null}
Adding Discount: Discount CDE exists that gives 15% off all items over $100
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 97
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:25:47 GMT
<
* Connection #0 to host localhost left intact
{"id":4,"discountCode":"CDE","percentage":0.15,"discountType":"ITEM_COST","itemType":null,"itemCount":null,"itemCost":100.0}
Adding Discount: Discount CDE exists that gives 15% off all items over $100
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 99
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:25:47 GMT
<
* Connection #0 to host localhost left intact
{"id":5,"discountCode":"FGH","percentage":0.2,"discountType":"ITEM_COUNT","itemType":null,"itemCount":2,"itemCost":null}
Calculating the best offer
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount/best HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 48
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:25:47 GMT
<
* Connection #0 to host localhost left intact
{"discountCode":"FGH","totalCost":200.0}
```

```shell
$ ./test-case-3.sh
Adding Discount: Discount ABC exists that gives 10% off all items of type CLOTHES
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 101
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:26:13 GMT
<
* Connection #0 to host localhost left intact
{"id":6,"discountCode":"ABC","percentage":0.1,"discountType":"ITEM_TYPE","itemType":"CLOTHES","itemCount":null,"itemCost":null}
Adding Discount: Discount CDE exists that gives 15% off all items over $100
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 97
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:26:13 GMT
<
* Connection #0 to host localhost left intact
{"id":7,"discountCode":"CDE","percentage":0.15,"discountType":"ITEM_COST","itemType":null,"itemCount":null,"itemCost":100.0}
Calculating the best offer
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/discount/best HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.86.0
> accept: application/json
> Content-Type: application/json
> Content-Length: 94
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sat, 25 Feb 2023 05:26:13 GMT
<
* Connection #0 to host localhost left intact
{"discountCode":"CDE","totalCost":305.0}
```
