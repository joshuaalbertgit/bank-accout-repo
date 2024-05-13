This is my personal learning project to stay up to date.

Thanks to Sean Campbell
(from https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/?couponCode=KEEPLEARNING)

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
   
Lastly, how to run the APIs:
1. openBankAccount - URL/endpoint - localhost:5001/api/v2/openBankAccount
   Input been validated now
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/27339bc7-3c8d-4022-8a02-014aa9e0cb1f)
   
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/5c1aa928-a7d7-4801-9e69-21c705e7f8c5)

   with the right input, it creates an account
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/d13e9932-26e7-45d9-843f-aee7d555a14c)

2. depositFunds - URL/endpoint - localhost:5001/api/v1/depositFunds/15aa0165-3d77-4650-a670-c605f5c1892a
   pass on the id created before, for the account creation
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/dc81d82b-c70f-4c80-a3fa-8ee1737aae65)
   
3. withdrawFunds -localhost:5001/api/v1/withdrawFunds/15aa0165-3d77-4650-a670-c605f5c1892a
   Pass on the same Id to withdraw funds
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/68b0aa06-3842-4c12-b261-a148358bd2ef)
   
4. deleteAccount - URL/endpoint - localhost:5001/api/v1/closeBankAccount/15aa0165-3d77-4650-a670-c605f5c1892a
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/e92c115d-b753-47b5-8583-5e20fcbf4821)

GET- APIs:
1. bankAccountLookup/byId - URL/endpoint - localhost:5002/api/v1/bankAccountLookup/byId/5d33b450-45b9-4b59-b34a-80fd1fcdcfd2
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/0645ef53-9d03-43d2-b79f-485c92af8203)

2. bankAccountLookup/byHolder - URL/endpoint - localhost:5002/api/v1/bankAccountLookup/byHolder/JJ Albert
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/250488c6-e555-451c-b93e-55ba7a7347e3)
   
3. bankAccountLookup/withBalance/LESS_THAN - URL/endpoint - localhost:5002/api/v1/bankAccountLookup/withBalance/LESS_THAN/1000
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/532c88d1-e70e-4261-b248-030dda012eab)

4. bankAccountLookup/withBalance/GREATER_THAN - URL/endpoint - localhost:5002/api/v1/bankAccountLookup/withBalance/GREATER_THAN/10000
   ![image](https://github.com/joshuaalbertgit/bank-accout-repo/assets/33743519/ef0606e3-ff55-4f96-a40d-47f9ccb31fbb)







