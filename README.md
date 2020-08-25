# Car-Mechanic

Polish website with backend in spring boot, spring security and communication with the frontend via thymeleaf 

Application have two ROLES:

- USER
- ADMIN

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

   1. Get source code for example by git Bash: 
      ```
      git clone https://github.com/Daw990/Car-Mechanic.git
      ```
   2. Get MySql with Workbench and make new DB with name 'savingdatacar' 
      
      in `src/main/resources/application.properties` use your username and password to DB
      
      ```
      spring.datasource.username=*my DB username*
      
      spring.datasource.password=*my DB password*
      ```
      
       - with custom DB name just change in first line in file src/main/resources/application.properties
         `spring.datasource.url=jdbc:mysql://localhost:3306/savingdatacar?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin`
         
         savingdatacar to your name of DB.
         
       - Hibernate will automatticaly make all needed tables
       
   3. Make Roles
      
      - in table 'role' need to make roles
      ```
      insert into role values (1, 'USER');
      insert into role values (2, 'ADMIN');
      ```
   
   3. Register (disable firewall before Registration)
   
      - To activate account you need to use activation link sended to your email (check spam)
      
      - To get ADMIN privileges you need to add role 'ADMIN' to the newly created user  
      
      `insert into user_roles values (firstParam, secondParam);`
      
      firstParam- id of user who will get ADMIN role
      
      secondParam = 2 - id of 'ADMIN' in role table
      
  
