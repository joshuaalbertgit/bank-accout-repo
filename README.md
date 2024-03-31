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

Make sure, you have setup Java, Maven, Docker (running) in your IDE, and once you clode this project, 
![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/71579419-6344-4b86-89aa-a50c0bd47c52)

Then open a terminal and run these commands: 
1. pwd - to check you are in the right folder
2. ls  - jsut to make sure you have all the modules are in there (along with pom.xml)
3. mvn clean install - to build the project, before you run the applications (Command and Query)

![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/134d7dad-53d2-40dd-98d9-5cd640dab71f)

Ocne the build goes well, you should be able to see the BUILD SUCCESS statement/info in the console.
![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/a5888ac4-12a6-4c73-85ce-c9b09ffccfca)

Last two steps
1. Run Command Application
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/0315c269-daa5-491e-b552-58ddaf91f089)
   You can confirm, that the Command Application is running successfully by seeing this statement "**Tomcat started on port 5001**" and "**Started CommandApplication**" in the console.
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/d4086e73-b7f5-4629-91be-381d8c92ce7f)


3. Run Query Application
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/6dc4cf94-7bf2-4e51-83c8-aebc74654f9a)
   You will notice "**Tomcat started** on port 5002" in the console.
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/f47397e7-0272-4144-b34c-5e78865f8273)
   Plus, you can confirm, that the Query Application is running successfully by seeing this statement "**Started QueryApplication**" in the console.
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/3364e378-2848-4e10-94a3-3118cbc0eb40)
   Lastly, you will notice, these 4 "**KafkaMessageListenerContainer**" for
   1. AccountOpenedEvent
   2. FundsDepositedEvent
   3. FundsWithdrawnEvent
   4. AccountClosedEvent
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/0aeeeeac-dfb8-4958-b665-4139a78ba301)
   


