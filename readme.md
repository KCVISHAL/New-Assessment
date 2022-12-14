### Description
This API has a single endpoint which will calculate the points earned based on the transaction amount. The Transaction details are fetched from csv file which is under resources folder.

## Initial Setup
- Run the TestApplication.java which will initiate the spring boot application(assuming running Java-11)

## Unit Test
- To run the test suit you can TestApplicationTests, I have written all possible test cases which include the edge cases especially with the amount $100.

## Endpoints

- #### GetPoints:
`GET: localhost:8080/getPoints`

## Sample Data needed in the CSV file that is being read from API which is under resources folder

| transactionId |        timeStamp        | amount |
|---------------|:-----------------------:|-------:|
| 1             | 2022-10-01T16:34:26.666 |    100 |
| 2             | 2022-11-01T16:34:26.666 |    120 |
| 3             | 2022-11-05T16:34:26.666 |     10 |
| 4             | 2022-12-08T16:34:26.666 | 111.67 |


##  Testing

All Postman collection are included in postmanCollection folder
