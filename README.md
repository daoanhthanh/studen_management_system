# Education Management System

**<h2>Overview</h2>**
- A group project for the SQA course in the Faculty of Information Technology at Hanoi University
- A Spring Boot Application as the backend for the Education Management System
- Deployed on Google App Engine, try it out!: https://sqa-2020.uc.r.appspot.com/
- Files & further documentation can be found at: https://drive.google.com/drive/u/0/folders/1w4oz-SEe2NDjrL_trPoYElY5eRljRboG

**<h2>Features</h2>**
**<h3>1) Architectural Feature</h3>**
- JWT Authentication
- CRUD with DTO and constraint validations on all domains
- Pagination and Sorting options when retrieving data
- Role-based Authorization: 3 roles: Admin, Teacher, Student
- OpenAPI Specification: on `localhost:8080` (after running the application)
- Database Password Hashing with BCrypt
- Docker Image
- One-click Deployment on Google App Engine
- Including **7** domains:
  - `Course`
  - `CourseRelease`
  - `Department`
  - `Enrollment`
  - `Student`
  - `Teacher`
  - `Timetable`

**<h3>2) Software Feature</h3>**

**<h4>Global Access Feature</h4>**
- Beautiful Landing Page
- Login, Logout

**<h4>Admin Role Feature</h4>**
- Courses Management
- Course Releases Management
- Departments Management
- Enrollments Management
- Students & their Timetable Management
- Teachers & their Timetable Management
- Change User password

**<h4>Student Role Feature</h4>**
- View Courses, Departments, Teachers
- View available Course Releases for own Department
- View own profile, Enrollments
- Enroll to a Course Release
- View own current Timetable
- Receive Timetable and Course suggestion
- Change own password

**<h2>Usage</h2>**
- **Requires** JAVA 8 SDK and MySQL 8 (version 8.0.23) to be installed
- Create a database named `ems` and then either:
  - Go to `src/main/resources/application-debug.properties` and change the following to:
    - `spring.datasource.username=YOUR_DB_USERNAME`
    - `spring.datasource.password=YOUR_DB_PASSWORD`
    - `spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/ems`
  - Or create a new mysql db account named `sqa-dev` with password `sqa@2021`
    https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql
- In the project root folder, run in terminal: `mvn springboot:run`
- Note: the first boot of the application will be very slow because it will insert ~2500 records in your database as the initial data for the application. Please wait until you receive
  _Data Initialization on First Server Boot Completed Successfully!_
- If you do not want any data to be initialized in your database, go to `src/main/java/hanu/edu/ems/InitData.java` and comment out the line (around line 134) containing `testData();` before running it for the first time.
- To reset the database, `drop database ems;` and `create database ems;`, `InitData` will be run again.

Cheers

**Other contributors:**

* [tangbamiinh](https://github.com/tangbamiinh)
* [Philip Ng](https://github.com/hwinng)
* [Đỗ Thị Ngân](https://github.com/DoNgan0902)
* [zondz](https://github.com/zondz)