# wchallenge

WChallenge is a project about the consumption of the API https://jsonplaceholder.typicode.com, design in Spring Boot, H2 database and JDK 8.

# Rest API services

### All users

Method: Get

Url: http://localhost:8080/users

### All photos

Method: Get

Url: http://localhost:8080/photos

### All albums

Method: Get

Url: http://localhost:8080/albums

### Comments by name

Method: Get

Url: http://localhost:8080/comments/name/{name}

### Photos by user

Method: Get

Url: http://localhost:8080/photos/user/{userId}

### Assign read permissions to a user for a specific album

Method: Post

Url: http://localhost:8080/share_album

Body:

{
    "albumId": 1,
    "userId": 1,
    "permission": "READ"
}

### Assign write permissions to a user for a specific album

Method: Post

Url: http://localhost:8080/share_album

Body:

{
    "albumId": 1,
    "userId": 1,
    "permission": "WRITE"
}

### Change permissions to a user for a specific album

Method: Put

Url: http://localhost:8080/share_album

Body:

{
    "albumId": 1,
    "userId": 1,
    "permission": "WRITE"
}

Or

{
    "albumId": 1,
    "userId": 1,
    "permission": "READ"
}

### List of users with specific permissions to a specific album

Method: Get

Url: http://localhost:8080/share_album/{permission}/{userId}

Permissions: READ or WRITE
