# ing-bank-study-case

# ING Bank Study Case
======================

## Overview
------------

This repository contains the solution to the ING Bank study case, which aims to [briefly describe the goal of the study case].

## Table of Contents
-----------------

* [Getting Started](#getting-started)
* [Project Structure](#project-structure)
* [Features](#features)
* [Requirements](#requirements)
* [Installation](#installation)
* [Usage](#usage)
* [Contributing](#contributing)
* [License](#license)

## Getting Started
---------------

To get started with the project, please follow these steps:

1. Clone the repository: `git clone https://github.com/artista-qrue/ing-bank-study-case
2. Install the dependencies: `npm install` or `yarn install`
3. Start the application: `npm start` or `yarn start`

## Project Structure
-----------------

The project is structured as follows:

* `src/`: contains the source code of the application
* `public/`: contains the static assets of the application
* `tests/`: contains the unit tests and integration tests of the application

## Features
------------

The application has the following features:

* [List the features of the application]

## Requirements
------------

The application requires the following dependencies:

* [List the dependencies of the application]

## Installation
------------

To install the dependencies, run the following command:

* `npm install` or `yarn install`

## Usage
-----

To start the application, run the following command:

* `npm start` or `yarn start`

## Contributing
------------

Contributions are welcome! To contribute to the project, please follow these steps:

1. Fork the repository: `git fork https://github.com/your-username/ing-bank-study-case.git`
2. Create a new branch: `git checkout -b your-branch-name`
3. Make changes and commit: `git commit -m "your commit message"`
4. Push changes: `git push origin your-branch-name`
5. Create a pull request: `https://github.com/your-username/ing-bank-study-case/pulls`

## License
-------

The project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
Login as user admin and call the get all endpoint. It should be allowed.

Login as user Bob and call the get all endpoint. It should be rejected with a status 403 Forbidden.

Restrict get all users to role ADMIN
Add the following annotation to the getAll method in the controller. Spring security will reject any user that does not an ADMIN role.

    @PreAuthorize("hasRole('ADMIN')")

Enable role based authorization
Add the following annotation to the WebSecurity class

@EnableGlobalMethodSecurity(prePostEnabled = true)

Create a Spring Boot server that

Has an admin user
Allows new non-admin users to sign up. This endpoint does not require a token
Allows users to login. This endpoint does not require a token but returns a token
Allows the user to get his/her information. Requires token
Allows the admin user to get all users. Requires token
Exercise the endpoints via curl and/or Postman.

firstly, this curl will create a token, and u can use this token other enpoints,We call this curl:
curl --location 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data '{
"username": "admin",
"password": "password"
}'

- Create Order: Create a new order for a given customer, asset, side, size and price
  Side can be BUY or SELL. Customer is a unique id for a customer

- curl --location 'http://localhost:8080/orders' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: ••••••' \
  --data '{
  "customerId": 1,
  "assetName": "AAPL",
  "side": "BUY",
  "size": 10,
  "price": 150
  }'

- List Orders: List orders for a given customer and date range. (you can add more filter
  if you want)
  curl --location 'http://localhost:8080/orders/1?startDate=2024-01-01T00%3A00%3A00&endDate=2024-12-31T23%3A59%3A59' \
  --header 'Authorization: ••••••'
- Delete Order: Cancel a pending order. Other status orders cannot be deleted
- curl --location --request DELETE 'http://localhost:8080/orders/1' \
  --header 'Authorization: ••••••'
- Deposit Money: Deposit TRY for a given customer and amount
- curl --location 'http://localhost:8080/customers/1/deposit' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: ••••••' \
  --data '1000'
- Withdraw Money: Withdraw TRY for a given customer amount and IBAN
- curl --location 'http://localhost:8080/customers/1/withdraw' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: ••••••' \
  --data '{"amount": 500, "iban": "TR000000000000000000000001"}'
- List Assets: List all assets for a given customer (you can add filters if you want)