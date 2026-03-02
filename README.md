# Library Management System

###### A minimal RESTful API for managing library records

## Overview

The Library Management System provides a lightweight RESTful API for digitizing library management tasks. It integrates
the management of books, authors, genres, and publishers—streamlining cataloging, tracking, and overall resource
maintenance.

## Architecture

This branch follows a **Layered Architecture (Horizontal Slicing)**.

The application is structured by technical responsibility:

- controller
- service
- repository
- entity
- mapper
- exception

Example structure:

```
com.netroutines.lms
├── controller
├── service
├── repository
├── entity
├── mapper
└── exception
```

This approach emphasizes:

- Clear separation of technical concerns
- Familiar structure for traditional enterprise applications
- Strong layering discipline

Business logic remains identical to the feature-based implementation
available in the `main` branch.

## Key Features

- **Author Management**: Register and manage authors.
- **Genre Classification**: Organize books by genre to improve discoverability.
- **Publisher Tracking**: Keep track of book publishers.
- **Book Cataloging**: Add and manage books, linking them to their respective authors, genres, and publishers for a
  rich, interconnected library database.

## Getting Started

To get started with the API:

1. **Add Authors**: Create author records.
2. **Define Genres**: Set up genres for book classification.
3. **Add Publishers**: Register publishers who have released the books.

Once the foundational data is in place, you can begin adding books and associating them with the relevant authors,
genres, and publishers.

## API Documentation

The API is documented using the OpenAPI specification (`openapi.yml`).

To explore and test the API:

- Use the [Swagger Editor](https://editor.swagger.io/) to import the OpenAPI spec and interact with the endpoints.
- Alternatively, use [Postman](https://www.postman.com/) or any API client.

---
