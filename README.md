# 📝TaskHive – A Smart To-Do Web Application 🗒
- TaskHive is a web-based Task Manager(ToDo) Application built with **Java**, **Spring Boot**, **Spring MVC**, **Thymeleaf**, and **PostgreSQL**.
- It provides an intuitive and efficient way to create, manage, and track daily tasks. Designed with a clean Bootstrap-powered UI, it offers a seamless user experience for managing personal or professional productivity.
- It is a smart, lightweight, and efficient **task management system** designed to keep your work organized and boost productivity. It allows you to create, update, track, and complete tasks seamlessly. Whether you’re a student managing assignments, a professional tracking deadlines, or a team collaborating on projects, TaskHive provides a structured platform to improve productivity and focus.
<br>

# 🎯 Features

- 🔐 Registration/Login – Provided registration and login page for each user to register and login to application and perform their operation.

- ➕ Create Tasks – Add new tasks with title, description, and due date.

- 📝 Update Tasks – Edit existing tasks easily.

- ❌ Delete Tasks – Remove tasks when no longer needed.

- 📋 View All Tasks – Display a structured task list with status.

- ✅ Session Management – Manages session for the application.

- 🎨 Responsive UI – Designed with HTML, CSS, and Bootstrap for a modern look.

- 💾 Database Integration – Persistent storage with PostgreSQL.

-  🔓 Logout - Provided a clean logout button for user to logut.
<br>

# 🛠️ Tech Stacks

- Backend: Java, Spring Boot, Spring MVC

- Frontend: HTML5, CSS3, Bootstrap, Thymeleaf

- Database: PostgreSQL

- Build Tool: Maven



# 🗂️ Project Structure

```
TaskHive/
├── src/
│   ├── main/
│   │   ├── java/com/taskflow
│   │   │   ├── controller/    # Handles web requests
│   │   │   ├── model/         # Entity classes
│   │   │   ├── repository/    # JPA repositories
│   │   │   └── service/       # Business logic
│   │   └── resources/
│   │       ├── static/     # CSS, JS, Bootstrap
│   │       ├── templates/  # Thymeleaf HTML Pages   
│   │       └── application.properties
└── pom.xml
```


# ▶️ Getting Started

✅ Prerequisites

- Install Java 17+

- Install Maven/Gradle

- Install PostgreSQL



# ⚙️ Setup


## 1. Clone the repository:

git clone https://github.com/your-username/TaskHive.git
cd TaskHive


## 2. Configure PostgreSQL Database in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


## 3. Build and run the project:

mvn spring-boot:run


## 4. Access the app at:
👉 http://localhost:8080/




# 📱 Screens (Sample UI Flow)

- 🏠 Home Page – Task overview.

- ➕ Add Task Form – Create a new task.

- 📝 Edit Task Form – Update existing tasks.

- ✅ Task List – View all tasks in a structured table.



# 💡 Use Cases

- Personal to-do list management

- Team-based task tracking (extendable with user roles)

- Mini-project for Spring Boot + Thymeleaf learning

Hands-on practice with CRUD operations in PostgreSQL



# 🤝 Contribution

Contributions are welcome! 🎉 You can help by:

Adding user authentication (Spring Security)

Enhancing UI/UX with advanced Bootstrap

Integrating REST APIs for external usage

Adding notifications/reminders for tasks




# 🙌 Acknowledgments

Thanks to the Spring Boot & PostgreSQL community for their amazing tools that make building full-stack apps enjoyable!.
