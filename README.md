# GenericUnion

How to start the GenericUnion application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/GenericUnion-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:22000`

Health Check
---

To see your applications health enter url `http://localhost:22001/healthcheck`

# APIs
GET localhost:22000/getGenericUnion/inputVariables/{sets}
..... This will give you the inputs required for {sets} .
Example : GET localhost:22000/getGenericUnion/inputVariables/3
Response : {
  "input": {
    "A": 0,
    "B": 0,
    "C": 0,
    "B intersection C": 0,
    "A intersection B intersection C": 0,
    "A intersection B": 0,
    "A intersection C": 0
  }
} 

You can put the output as the request Body in the POST call below, replacing each value appropriately.

POST localhost:22000/getGenericUnion/{sets}
Example : POST localhost:22000/getGenericUnion/inputVariables/3
Request Body : {
  "input": {
    "A": 4,
    "B": 5,
    "C": 7,
    "B intersection C": 2,
    "A intersection B intersection C":1,
    "A intersection B": 0,
    "A intersection C": 3
  }
}

Response: 12


