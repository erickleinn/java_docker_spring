-----------------PROJECT SETUP INSTRUCTIONS-----------------


ROOT DIRECTORY: dev-test/test

1- run "mvn clean install" on the project root directory, to build the project;
2- run "docker-compose up" on the project root directory, to create the database instance;
3- run the project trough the IDE on the AppplicationRunner.java class;

Please use Postman or swagger UI to call the REST APIs.
Once the application is up and running, access swagger ui through the following link: http://localhost:8080/swagger-ui/#/
There's also a postman collection with all the APIs and examples. You can import the collection on postman and run the APIs or call them through swagger ui.