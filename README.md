# DealController

The `DealController` class serves as the RESTFull controller for managing deals within the warehouse system.

## Controller Description

This controller is responsible for handling HTTP requests related to deals in the warehouse. It provides endpoints for adding new deals and retrieving all existing deals.

## Endpoints

### Save Deal

- **URL:** `/warehouse`
- **HTTP Method:** POST
- **Request Body:** JSON object representing the details of the deal to be saved.
- **Description:** This endpoint allows clients to save a new deal by sending a POST request with the deal details in the request body.

### Find All Deals

- **URL:** `/warehouse`
- **HTTP Method:** GET
- **Response Body:** JSON array containing a list of all deals.
- **Description:** This endpoint retrieves all existing deals from the warehouse system and returns them as a JSON array in the response body.



# DealService

The `DealService` class provides business logic for managing deals within the warehouse system.

## Service Description

This service class is responsible for managing deals in the warehouse. It contains methods for adding new deals and retrieving all existing deals.

## Constructor

The constructor of the `DealService` class initializes various components required for dealing with deals, including repositories, mappers, and validators.

## Methods

### Add Deal

The `addDeal` method adds a new deal to the warehouse system. It performs validation on deal details such as deal ID, ISO currency codes, timestamps, and amounts before saving the deal to the database.

### Find All Deals

The `findAll` method retrieves all existing deals from the warehouse system. It fetches deals from the database and converts them into request deal objects for further processing.


# ISOCodeValidator

The `ISOCodeValidator` class validates ISO currency codes used in deals within the warehouse system.

## Validator Description

This validator class ensures the validity of ISO currency codes used in deals. It checks whether the provided currency codes are valid ISO codes and throws exceptions if they are not.

## Methods

### Validate

The `validate` method checks the validity of the provided from and to currency ISO codes. It throws an `InvalidISOCodeException` if either of the currency codes is null or if they are not valid ISO codes.

Parameters:
- `fromCurrency`: The ISO currency code for the source currency.
- `toCurrency`: The ISO currency code for the target currency.

Exceptions:
- `InvalidISOCodeException`: Thrown if the from or to currency is null or if they are not valid ISO codes.


# DealIdValidator

The `DealIdValidator` class validates deal IDs within the warehouse system.

## Validator Description

This validator class ensures the uniqueness and validity of deal IDs used in the warehouse. It checks whether the provided deal ID is not zero and if it's already existing in the database. If the deal ID is not unique or zero, it throws an `InvalidIdException`.

## Constructor

The constructor of the `DealIdValidator` class initializes the `DealRepository` instance required for querying deal information from the database.

## Methods

### Validate

The `validate` method checks the validity and uniqueness of the provided deal ID. It throws an `InvalidIdException` if the deal ID is zero or if it already exists in the database.

Parameters:
- `dealId`: The ID of the deal to be validated.

Exceptions:
- `InvalidIdException`: Thrown if the deal ID is zero or if it already exists in the database.


## AmountValidator

The `AmountValidator` class is responsible for validating amounts, typically represented as `BigDecimal` objects. It ensures that the provided amount is not null and is greater than zero.

### Methods

#### `validate(BigDecimal amount)`

This method takes a `BigDecimal` parameter representing the amount to be validated. It first checks if the amount is null. If it is null, an `InvalidAmountException` with the message "Amount is required" is thrown. Next, it compares the amount with zero. If the amount is less than or equal to zero, another `InvalidAmountException` is thrown with the message "The amount is less than or equal to zero".

### Dependencies

- `java.math.BigDecimal`: Used for representing arbitrary-precision signed decimal numbers.
- `java.util.Objects`: Provides utility methods for operating on objects.
- `com.progressoft.warehouse.exception.InvalidAmountException`: Custom exception class for handling amount-related errors.

### Usage

To use the `AmountValidator`, simply instantiate an object of this class and call the `validate` method, passing the amount to be validated as a parameter.

Example:

```java
AmountValidator validator = new AmountValidator();
BigDecimal amount = new BigDecimal("100.00");
validator.validate(amount);
```

### Notes

- This class is marked with the `@Component` annotation, indicating that it is a Spring-managed component and can be automatically discovered and instantiated by the Spring framework.


## TimeStampValidator

The `TimeStampValidator` class is responsible for validating timestamp strings. It ensures that the provided timestamp is not null and is in a valid format.

### Methods

#### `validate(String timeStamp)`

This method takes a `String` parameter representing the timestamp to be validated. It first checks if the timestamp is null. If it is null, a `InvalidTimeStampException` with the message "Time stamp is required" is thrown. Next, it attempts to parse the timestamp using `LocalDateTime.parse()`. If the parsing fails due to an invalid format, a `InvalidTimeStampException` with the message "Invalid format" is thrown.

### Dependencies

- `com.progressoft.warehouse.exception.InvalidTimeStampException`: Custom exception class for handling timestamp-related errors.
- `java.time.LocalDateTime`: Represents a date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30.

### Usage

To use the `TimeStampValidator`, instantiate an object of this class and call the `validate` method, passing the timestamp string to be validated as a parameter.

Example:

```java
TimeStampValidator validator = new TimeStampValidator();
String timeStamp = "2024-06-07T10:15:30";
validator.validate(timeStamp);
```

### Notes

- This class is marked with the `@Component` annotation, indicating that it is a Spring-managed component and can be automatically discovered and instantiated by the Spring framework.

