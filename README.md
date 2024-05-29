# ToDo App Backend

## Project Overview

This project consists of two microservices: ToDo Service and User Service.

1. **ToDo Service**: Responsible for CRUD operations related to todo items.
2. **User Service**: Manages user data, authentication, and authorization.

## ToDo Service

### Database Schema

1. **Items Table**
   - id
   - title
   - user_id
   - item_details_id

2. **Items_Details Table**
   - id
   - description
   - created_at
   - priority
   - status

### Functionality

1. CRUD Operations for ToDo Items
2. API Documentation with Swagger
3. Exception Handling for Not Found Errors
4. Unit Testing with JUnit5
5. Backend Attribute Validation

### APIs Description

| API             | Body                  | Header                | Description                                |
|-----------------|-----------------------|-----------------------|--------------------------------------------|
| /add            | Item details          | JSON web token        | Add new todo item                          |
| /delete/{id}    |                       | JSON web token        | Delete an item by ID                       |
| /update/{id}    | Item details          | JSON web token        | Update an item by ID                       |
| /search/{id}    |                       | JSON web token        | Search an item by ID                       |

## User Service

### Database Schema

1. **User Table**
   - id
   - email
   - password
   - enabled

2. **Token Table**
   - id
   - OTP
   - expiration_time
   - user_id

3. **JWT Table**
   - id
   - token
   - user_id
   - created_at
   - expiration_date
   - token_type

### Functionality

1. User Authentication and Authorization
2. Token Management (Generation, Validation)
3. User CRUD Operations
4. Password Reset with OTP via Email
5. API Documentation with Swagger
6. Exception Handling for Not Found Errors
7. Unit Testing with JUnit5
8. Backend Attribute Validation

### APIs Description

##### Authentication Flow
| API                | Body                  | Header                | Description                                |
|--------------------|-----------------------|-----------------------|--------------------------------------------|
| /login             | username, password    |                       | Generate a new token for the user          |
| /register          | User details          |                       | Store user details with OTP generation     |
| /activate?username | OTP                   |                       | Activate user account with OTP             |
| /checkToken        | token                 |                       | Check token validity                       |
| /forgetPassword    | token                 |                       | Reset password with OTP via Email          |
| /changePassword    | User details, Token, OTP |                    | Change password with verified OTP          |
| /regenerateOtp?email |                    |                       | Generate a new OTP for the user            |

##### User Operations
| API                | Body                  | Header                | Description                                |
|--------------------|-----------------------|-----------------------|--------------------------------------------|
| /delete            | username, password    | JSON web token        | Delete the user account                    |
| /update            | User details          | JSON web token        | Update user details                        |

### Note
- Each API will call the /checkToken API in the UserService to validate the token's expiration and user existence.

## Deadline
2 Weeks (27-5-2024)
