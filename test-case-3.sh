#!/usr/bin/env bash

# Discount ABC exists that gives 10% off all items of type CLOTHES
echo 'Adding Discount: Discount ABC exists that gives 10% off all items of type CLOTHES'
curl -v -X 'POST' \
  'http://localhost:8080/api/discount' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"discountCode": "ABC",
	"percentage": 0.10,
	"discountType": "ITEM_TYPE",
	"itemType": "CLOTHES"
}'

echo ''
# Discount CDE exists that gives 15% off all items over $100
echo 'Adding Discount: Discount CDE exists that gives 15% off all items over $100'
curl -v -X 'POST' \
  'http://localhost:8080/api/discount' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"discountCode": "CDE",
	"percentage": 0.15,
	"discountType": "ITEM_COST",
	"itemCost": 100.0
}'

echo ''
# Calculating the best offer
echo 'Calculating the best offer'
curl -v -X 'POST' \
  'http://localhost:8080/api/discount/best' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "itemId": 123,
    "quantity": 1
  },
  {
    "itemId": 456,
    "quantity": 1
  }
]'

# Delete all discounts
curl -X 'DELETE' \
  'http://localhost:8080/api/discount/all' \
  -H 'accept: */*'