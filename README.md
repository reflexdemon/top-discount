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
