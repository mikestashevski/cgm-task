# Live Endpoint Documentation

See the following links for (very basic) endpoint documentation:

- [API Docs](http://localhost:8080/v2/api-docs)
- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

# Database Schema

See the schema at:
jdbc:mariadb://3.248.200.162:3306/cgm_test
password: PassW0rd123!

The database schema for this application consists of three main entities:

1. `Doctor`: Represents a doctor with attributes such as `id`, `name`, `surname`, `username`, and `passwordHash`. It has
   a one-to-many relationship with `Patient`.

2. `Patient`: Represents a patient with attributes such as `id`, `name`, `surname`, `dateOfBirth`,
   and `socialSecurityNumber`.

3. `Visit`: Represents a visit with attributes such
   as `id`, `doctorId`, `patientId`, `startTime`, `endTime`, `reason`, `type`, and `familyHistory`. It has a many-to-one
   relationship with both `Doctor` and `Patient`.

There are indexes in place to optimize search and query performance. Primary keys are used to uniquely identify each
record in the tables.

# API Documentation

This API documentation provides information on the available endpoints for the Patient and Visit Controllers, as well as
descriptions of the `PatientDTO` and `VisitDTO` objects.

**Note**: The `authorization_token` used in the headers should be in the format `username:passwordhash`. Password hash
is sha256 of the id (eg johndoe:sha256(1)).

## PatientDTO

The `PatientDTO` object contains the following fields:

- `id` (Long): The unique identifier of the patient.
- `firstName` (String): The first name of the patient.
- `lastName` (String): The last name of the patient.
- `dateOfBirth` (Long): The date of birth of the patient (Unix timestamp).

## VisitDTO

The `VisitDTO` object contains the following fields:

- `id` (Long): The unique identifier of the visit.
- `patientId` (Long): The unique identifier of the associated patient.
- `doctorId` (Long): The unique identifier of the associated doctor.
- `startTime` (Long): The start time of the visit (Unix timestamp).
- `endTime` (Long): The end time of the visit (Unix timestamp).
- `reason` (String): The reason for the visit.
- `type` (String): The type of the visit.
- `familyHistory` (String): The patient's family history.

## Patient Controller

### Get all patients

- **Method**: `GET`
- **Endpoint**: `/api/patients`
- **Headers**: `Authorization: <authorization_token>`
- **Response**: List of `PatientDTO` objects

### Create a patient

- **Method**: `POST`
- **Endpoint**: `/api/patients`
- **Headers**: `Authorization: <authorization_token>`
- **Request body**: `PatientDTO` object

## Visit Controller

### Get visits by patient ID

- **Method**: `GET`
- **Endpoint**: `/api/patients/{patientId}/visits`
- **Path variables**: `{patientId}` - patient's ID
- **Headers**: `Authorization: <authorization_token>`
- **Response**: List of `VisitDTO` objects

### Create a visit

- **Method**: `POST`
- **Endpoint**: `/api/patients/{patientId}/visits`
- **Path variables**: `{patientId}` - patient's ID
- **Headers**: `Authorization: <authorization_token>`
- **Request body**: `VisitDTO` object
- **Response**: `VisitDTO` object

### Update a visit

- **Method**: `PUT`
- **Endpoint**: `/api/patients/{patientId}/visits/{visitId}`
- **Path variables**: `{patientId}` - patient's ID, `{visitId}` - visit's ID
- **Headers**: `Authorization: <authorization_token>`
- **Request body**: `VisitDTO` object
- **Response**: `VisitDTO` object

# Frontend Application

There is a separate frontend application developed using Node.js and React, which interacts with the API endpoints
provided by this backend service. The frontend application can be found in another repository at:

[https://github.com/mikestashevski/cgm-task-ui/tree/staszewski](https://github.com/mikestashevski/cgm-task-ui/tree/staszewski)

# Docker Setup

The Dockerfile here is designed to create an image with both the Java 11 application and the Node.js frontend
application. The Dockerfile is composed of three stages:

1. **Stage 1**: Build and run the Java 11 application. The JAR file is copied into the Docker image and the app is
   exposed on port 8080.
2. **Stage 2**: Build the Node.js application. The repository is cloned, dependencies are installed, and the app is
   built. The app is exposed on port 3000.
3. **Stage 3**: Combine both applications into a single image. Both the Java and Node.js applications are copied into
   the final image, which is based on the OpenJDK 11 JRE.

## Building the Docker Image

To build the Docker image, follow these steps:

1. Navigate to the project directory containing the Dockerfile.
2. Run the following command to build the Docker image:

docker build -t cgm-task-image .

**Note**: Due to time constraints, this Dockerfile setup has not been thoroughly tested. It is possible that you may
encounter issues during the build process or when running the image. If you experience any problems, please review the
Dockerfile and make any necessary adjustments.