#!/usr/bin/env bash

# Discount ABC exists that gives 10% off all items of type CLOTHES
curl -X 'POST' \
  'http://localhost:8080/api/discount' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"discountCode": "ABC",
	"percentage": 0.10,
	"discountType": "ITEM_TYPE",
	"itemType": "CLOTHES"
}'

# Discount CDE exists that gives 15% off all items over $100
curl -X 'POST' \
  'http://localhost:8080/api/discount' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"discountCode": "CDE",
	"percentage": 0.15,
	"discountType": "ITEM_COST",
	"itemCost": 100.0
}'

# Discount FGH exists that gives 20% off when purchasing 2 or more of shirts with id 123
curl -X 'POST' \
  'http://localhost:8080/api/discount' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"discountCode": "FGH",
	"percentage": 0.20,
	"discountType": "ITEM_COUNT",
	"itemCount": 2
}'

# Posting for discount
curl -X 'POST' \
  'http://localhost:8080/api/discount/best' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "itemId": 123,
    "quantity": 5
  }
]'