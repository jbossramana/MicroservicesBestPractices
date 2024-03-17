

Success scenario:

post localhost:8082/inventory

{
"item":"books",
"quantity":200
}

post  localhost:8080/order

{
"item":"books",
"quantity":10,
"amount":1000,
"address":"chennai",
"paymentMode":"credit card"
}

Failure Scenario

post  localhost:8080/order

{
"item":"computer",
"quantity":10,
"amount":30000,
"address":"chennai",
"paymentMode":"credit card"
}



