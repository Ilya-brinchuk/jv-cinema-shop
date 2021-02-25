CINEMA SHOP
======
This project implements the online store model. It has authorization with two main roles: **User** and **Administrator**
+ Users can: login/sign in, view a list of all movies and movie session, buy  tickets for available sessions,
  view order history
+ Administrators can: login,  view  all customers by their email, add and delete movies, sessions, cinema halls

USED TECHNOLOGIES:
======
1. Java 11
2. Maven
3. Hibernate MySQL
4. Spring Core, MVC, Security
5. Apache Tomcat

TO START APP:
======
1. Configure Apache Tomcat
2. Install MySQL and MySQL Workbanch
3. Setup connection properties in **db.properties** file
* user: "your username"
* password: "your password"
* db.url=jdbc:mysql://localhost/*db_name*?serverTimezone=EET
4. Run application
5. The logs in as an administrator, in the login window enter:
* login: "admin@gmail.com"
* password: "2323"

AUTHOR:
======
[Ilya Brinchuk](https://github.com/Ilya-brinchuk)
