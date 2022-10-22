# spring-boot-redis-example
Spring Boot Application with Redis Cache Example

## Introduction
As of right now, we are aware that typical users need a quicker response time and improved programme performance. 
What if we make an unnecessary query request to the database in this situation? 
(When we are fetching the same data again and again in the different calls). What if, instead of doing this, <b><u>we stored the data that does not change frequently somewhere where it could be retrieved more quickly than a database?</u></b> As a result, <b><u>we can store the data in memory, and memory retrieval is quicker than database retrieval.</b></u>

In essence, it is known as 

    Storing the frequently used DB data into memory so that 
    we may load data straight from memory to increase speed 
    and reaction time.

## Functionalities
- We can Add a new User inside the Database.
- We can retrieve the single user details.
- We can fetch users list with any number of users on a single page.
- Implementation of caching on fetching of users list so that fetching can be faster than usual.

## API Endpoints
1. <u><i>/add-user</i></u>: In this POST request, we can add a new user with the following informations.
        
        {
            "firstName": "example",
            "lastName" : "finish",
            "email"    : "example123@gmail.com",
            "mobile"   : "8888888888"
        }

2. <u><i>/users/{userId}</i></u>: In this GET Request, you need to pass the valid userId to fetch the User.
3. <u><i>/users/{userId}</i></u>: In this DELETE Request, you can delete the User with <userId> which exist in the DB, and it will be flushed from Cache.
4. <u><i>/users?page=<></i></u>: In this GET Request, you can get 1000 Users on each page, and you need to pass valid page number.  
5. <u><i>/users/{userId}</i></u>: In this PUT Request, you can update the user details which exist with user Id <userId> in the DB.

        {
            "firstName": "example",
            "lastName" : "finish",
            "email"    : "example123@gmail.com",
            "mobile"   : "8888888888"
        }
6. <u><i>/query-users?queryEmail=<>&limit=<></i></u>: In this GET Request, you can fetch the users whose email contains <i><u>queryEmail</u></i> character, and per page <i><u>limit</u></i> can be send in queries.

## Points
- <b>UserDao</b> is separate layer used for <b><i>caching</i></b>.

