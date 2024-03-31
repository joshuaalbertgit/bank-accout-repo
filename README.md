This is my personal learning project to stay up to date.
Thanks to Sean Campbell(from https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/?couponCode=KEEPLEARNING.

This project contains 4 modules, and it demonstrates CQRS & Event Sourcing design pattern.

The command query responsibility segregation (CQRS) pattern separates the data mutation, or the command part of a system, from the query part. 
You can use the CQRS pattern to separate updates and queries if they have different requirements for throughput, latency, or consistency.

prerequisite: Setup the required infrastructure 
1. My SQL DB
2. Mongo DB
3. Docker (Kafka & Zookeeper)
![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/08423ef5-966c-4eaa-aee2-bd400277eafd)


Setup your project in your IDE like this (you can clone this project from here - https://github.com/joshuaalbertgit/bank-accout-repo.git) 
![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/0ab9ed52-8fa5-47b6-b041-4ef73355783c)

Make sure, you have setup Java, Maven, in your IDE, and once you clode this project, 
Then open a terminal and run these commands: 
pwd - to check you are in the right folder
ls  - jsut to make sure you have all the modules are in there (along with pom.xml)
mvn clean install - to build the project, before you run the applications (Command and Query)

![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/134d7dad-53d2-40dd-98d9-5cd640dab71f)

Ocne the build goes well, you should be able to see the BUILD SUCCESS statement/info in the console.
![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/a5888ac4-12a6-4c73-85ce-c9b09ffccfca)
