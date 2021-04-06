## Restaurant Matcher Assessment

Per the assessment's requirements, this API is retrieves data from the provided
CSV's as the database and returns back the top matching restaurant results that most closely
matches the user's criteria.

### How To Run
1. Import the project into the IDE of choice
2. Run `mvn clean install` to install the dependencies for the project.
3. Start the application w/ RestaurantmatcherApplication as the main class.

### How To Test

Call the API with the following GET request: 

`localhost:8080/api/v1/getBestRestaurants`

Populate the Body with the search fields you'd like to use. Example:

`
{
"name": "Delicious",
"rating": 3,
"distance": 5,
"price": 20
}
`

#### Assumptions
 
* For the purpose of this project, assuming the numbered data is of type Integer
