# Currency Exchange

To run this program use:
`docker-compose up`

baseUrl = http://localhost:8080/api

Rest Api:

Get all available currencies that you can convert to each other:\
`/convert/available`

Get converted amount of money, you have to pass  amount, from and to variables:\
`/convert?amount=100&from=PLN&to=EUR`

Get currency rates of passed currency codes:\
`/currency-rate/?list=EUR&list=USD`

Get all records from database:\
`/calls`


### Example of full request
`http://localhost:8080/api/convert?amount=100&from=PLN&to=EUR`