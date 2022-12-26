# RolesAPI

Implementation of the "Back-end developer exercise" requested by e-Core.

### Main task
Create a new Roles service that enhances the Users and Teams services, by giving us the concept of team roles and the ability to associate them with team members.

## How to run the API

### Docker

I have created a docker image of the API implementation. To run it, just run the command below:

```
docker run -it --rm --name=ecore-roles-api -p 8080:8080 emanuelrodrigues/ecore-roles-api 
```
![image](https://user-images.githubusercontent.com/1282312/209581938-ea0b6819-2220-4fc9-b62f-22d0706c87a0.png)

[Docker image page in Docker hub](https://hub.docker.com/repository/docker/emanuelrodrigues/ecore-roles-api)

After started, the application will be available at: http://localhost:8080

![image](https://user-images.githubusercontent.com/1282312/209581953-f69832ad-7693-40b6-a580-09dca1cd2a83.png)


### Development

The API was developed wit Spring Boot, version 3.0.1 and for this reason it depends on the java version 17. All other dependencies are managed by maven.

To run the application in development evironment, you just need to follow this steps:

1) Clone the repository inside your workspace.
2) Import the project in your IDE
3) Run the main class: br.com.emanuel.rolesapi.RolesapiApplication

The application will be available at: http://localhost:8080

## How to use the API

### Roles

This is the structure from a Role:
- id: Numeric identifier from Role. Is automatically assigned by the API.
- name: Name of the role. Mandatory to be filled.

The RESTful service "/roles" was implemented based on the level 2 the Richadson maturity model, having the following methods:

| HTTP Verb| Address | Description |
| --------- | -------- | --------- |
| GET | /roles | List all roles stored |
| GET | /roles/{id} | Return the role related to the id |
| POST | /roles | Store the received role, returning it  |
| UPDATE / PATCH | /roles/{id} | Update the role attributes (today, only the name) |
| DELETE | /roles/{id} | Remove the role from API database |

### Role Membership

The rest service "/membership" have the following methods:

| HTTP Verb| Address | Description |
| --------- | -------- | --------- |
| POST | /membership | Assign a role to a team member. The json parameters are: roleName, userId, teamId |
| GET | /membership | Look up a role for a membership. A membership is defined by a user id and a team id. The json parameters are: userId, teamId. Return null if no membership is found.|
| GET | /membership/{roleName} | Look up memberships for a role.|



