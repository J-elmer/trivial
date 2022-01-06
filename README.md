**Trivial**
Spring boot application with Vue JS for a simple quiz webapp. The user is presented 5 questions of random difficulty and random categories from OpenTrivia (*https://opentdb.com/9) through a Vue build front end using Bootstrap. The SpringBoot Applicatino actually retrieves the questions and present these to the user and checks the given answers.

**Installation**

*Using Docker*
Clone the repository and in the main directory run:

```
make up
```
If make commands are not supported, run:
```
docker-compose up -d
```

*Installing locally*
To run locally, first start the Spring boot application. To do this, either run the application through an IDE or run the generated jar file in the main folder (trivia_application-0.0.1-SNAPSHOT.jar) with the following command:
```
java -jar trivia_application-0.0.1-SNAPSHOT.jar
```
Then navigate to the 'app' folder and run:
```
npm run serve
```

**Using the app**
After the installation, navigate to http://localhost:8081 to try your hand at the questions.
