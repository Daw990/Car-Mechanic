# Car-Mechanic

Polish website with backend in spring boot, spring security and communication with the frontend via thymeleaf where user can add his cars to account and
make an appointments

## Use Cases

1. Unregistered user:
   - Can see main page and price list without an appointment
   - Can register to site
  
2. Registered
   - With role USER:
     - Can add car to his account
     - Can make visit
     - Can see his user-panel
     - Can see his cars and edit or delete it
     - Can see history of his visits
    
   - With role ADMIN (USER can get it by DB developer)
     - Can do all what USER can do
     - Additional can edit price list
     - Can see all visits in future
     
## Getting Started
   
   1. Get (java jdk 8) [https://www.oracle.com/pl/java/technologies/javase/javase-jdk8-downloads.html]
   
   1. Get source code for example by git Bash: 
      ```
      git clone https://github.com/Daw990/Car-Mechanic.git
      ```
   2. Get MySql with Workbench
   
      - [Link for download](https://dev.mysql.com/downloads/windows/installer/8.0.html)
      - [youtube how to get started](https://www.youtube.com/watch?v=u96rVINbAUI)
      - Open workbench and connect to local database. Create new database: file/New Query Tab
      ```
      CREATE SCHEMA `savingdatacar` ;
      ```
         
      - Open project in IDE and go to:
      
      `src/main/resources/application.properties` use your username and password to Database (without '*').
      
      ```
      spring.datasource.username=*my DB username*
      
      spring.datasource.password=*my DB password*
      ```
         
       - Run code in IDE. Hibernate will automatticaly make all needed tables.
       
   3. Go to Workbench and make roles (right click mouse on table: role -> select Rows) and add roles:
      
      ```
      insert into role values (1, 'USER');
      insert into role values (2, 'ADMIN');
      ```
      
   4. Add repair list to database (right click mouse on table: repair -> Table Data Import Wizard), File patch: "project patch"/sql/priceList.csv
   
   5. Register (disable firewall before Registration)
   
      - To activate account you need to use activation link sended to your email (check spam)
      
      - To get ADMIN privileges you need to add role 'ADMIN' to the your newly registered user  
      
      `insert into user_roles values (firstParam, secondParam);`
      
      firstParam = id of user who will get ADMIN role
      
      secondParam = 2 - id of 'ADMIN' in role table
      
  
