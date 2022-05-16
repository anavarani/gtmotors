# The application

The application should be based on the existing functionality of the Motors.co.uk mobile app.
For scope of this assignment, we would like you to focus on:

1. The search screen
2. Ability to search by make, model, and year
3. Display the search results on the screen

## Acceptance Criteria

-   The user should be able to search by:
		- make
		- model
		- year
-   The user should be able to scroll through results on the screen

## Architecture
The application will be implemented using:
- Single activity architecture: to make use of the navigation component
- MVVM: using ViewModel to manage UI-related data conscious about lifecycle

## Backend
Server: https://mcuapi.mocklab.io/

GET: **/search**
**make**: String
**model**: String
**year**: String

Response:
```json
{
  "searchResults": [
    {
      "id": "221843",
      "name": "Nissan Juke",
      "title": "Nice car for sale",
      "make": "Nissan",
      "model": "Juke",
      "year": "2020",
      "price": "£212.00"
    },
    {
      "id": "397286",
      "name": "Nissan Juke",
      "title": "Used car in mint condition",
      "make": "Nissan",
      "model": "Juke",
      "year": "2020",
      "price": "£201.00"
    },
    {
      "id": "390251",
      "name": "Nissan Juke",
      "title": "Low mileage car for sale",
      "make": "Nissan",
      "model": "Juke",
      "year": "2020",
      "price": "£307.00"
    },
    {
      "id": "012565",
      "name": "Nissan Juke",
      "title": "Car for sale with unique specs",
      "make": "Nissan",
      "model": "Juke",
      "year": "2020",
      "price": "£389.00"
    }
  ]
}
```