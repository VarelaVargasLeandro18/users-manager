# USERS MANAGER API

## Endpoints

### User Creation

| Method | URL |
| :--: | :--: |
| **POST** | `{HOST}/users-manager/users` |

- **Body:**

```json
{
  "email": "varelavargasleandro@outlook.com.ar",
  "username": "lvarela",
  "password": "password",
  "firstname": "Leandro Gastón",
  "lastname": "Varela Vargas",
  "birthdate": "2001-06-18",
  "application": {
      "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002"
  }
}
```

- **Response:** **Status: 200**

```json
{
  "id": 13,
  "email": "varelavargasleandro@outlook.com.ar",
  "username": "lvarela",
  "firstname": "Leandro Gastón",
  "lastname": "Varela Vargas",
  "birthdate": "2001-06-18",
  "application": {
      "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002",
      "name": "USER-ADMINISTRATOR"
  }
}
```

### User Read

#### **All**

| Method | URL |
| :--: | :--: |
| **GET** | `{HOST}/users-manager/users` |

- **Response:** **Status: 200**

```json
{
  "content": [
      {
          "id": 13,
          "email": "varelavargasleandro@outlook.com.ar",
          "username": "lvarela",
          "firstname": "Leandro Gastón",
          "lastname": "Varela Vargas",
          "birthdate": "2001-06-18",
          "application": {
              "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002",
              "name": "USER-ADMINISTRATOR"
          }
      }
  ],
  "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "sort": {
          "sorted": false,
          "unsorted": true,
          "empty": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 1,
  "last": true,
  "first": true,
  "size": 10,
  "number": 0,
  "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
  },
  "numberOfElements": 1,
  "empty": false
}
```

#### **By Id**

| Method | URL |
| :--: | :--: |
| **GET** | `{HOST}/users-manager/users/{id}` |

- **Response:** **Status: 200**

```json
{
  "id": 13,
  "email": "varelavargasleandro@outlook.com.ar",
  "username": "lvarela",
  "firstname": "Leandro Gastón",
  "lastname": "Varela Vargas",
  "birthdate": "2001-06-18",
  "application": {
      "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002",
      "name": "USER-ADMINISTRATOR"
  }
}
```

### User Update

| Method | URL |
| :--:   |:--: |
| **PUT**| `{HOST}/users-manager/users` |

- **Body:**

```json
{
  "id": 13,
  "email": "varelavargasleandro@outlook.com.ar",
  "username": "lvarela",
  "password": "password",
  "firstname": "Leandro Gastón",
  "lastname": "Varela Vargas",
  "birthdate": "2001-06-18",
  "application": {
      "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002"
  }
}
```

- **Response:** **Status: 200**

```json
{
  "id": 13,
  "email": "varelavargasleandro@outlook.com.ar",
  "username": "lvarela",
  "firstname": "Leandro Gastón",
  "lastname": "Varela Vargas",
  "birthdate": "2001-06-18",
  "application": {
      "uuid": "173d3ca6-b1f9-11ef-92ee-0242ac110002",
      "name": "USER-ADMINISTRATOR"
  }
}
```

### User Delete

| Method | URL | Status Response |
| :--:   |:--: | :--: |
| **DELETE**| `{HOST}/users-manager/users/{id}` | **204** |

## Common Endpoints Errors

### NotFound

```json
{
  "message": "Entity not found.",
  "errorCode": 1000,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```

### EntityAlreadyExists

```json
{
  "message": "Entity already exists having...",
  "errorCode": 1001,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```

### UnexpectedError

```json
{
  "message": "An unexpected error has ocurred in the application.",
  "errorCode": 1002,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```

### AccessDenied

```json
{
  "message": "You don't have permission to access this resource.",
  "errorCode": 1003,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```

### Unauthorized

```json
{
  "message": "You are not authorized to access this resource.",
  "errorCode": 1004,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```

### ResourceNotFound

```json
{
  "message": "Resource not found.",
  "errorCode": 1005,
  "path": "/users-manager/users/",
  "timeStamp": "2024-12-18T18:06:13.16831022"
}
```
