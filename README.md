# Queue Management System Backend

## Description
The **Queue Management System Backend** is built using **Spring Boot** and **PostgreSQL**. It allows employees to efficiently manage customer queues through a RESTful API, enabling smooth operations in environments like banks, service centers, or government offices.

---

## Features
- Employee login and counter assignment.
- Customer queue management (join, view, call next).
- Real-time queue updates and customer status tracking.
- Queue entry completion with email notification.
- Admin features to manage employees and counters.

---

## Technologies Used
- **Java** (Spring Boot)
- **PostgreSQL**
- **Maven**
- **RESTful APIs**
- **Spring Data JPA**
- **Email Notifications (JavaMailSender)**

---

## Dependencies
Include the following dependencies in your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.5.4</version>
    </dependency>

    <!-- Spring Boot Starter Mail (for Email Notifications) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <!-- Spring Boot DevTools (for live reload) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
````

---

## Folder Structure

```
queue-backend/
├── src/
│   └── main/
│       ├── java/
│       │   └── com.queueapp.queue_backend/
│       │       ├── config/                 # Configuration files  
│       │       ├── controller/             # REST Controllers  
│       │       ├── model/                  # Data Models  
│       │       ├── repository/             # Database Repositories  
│       │       └── service/                # Business Logic  
│       ├── resources/
│       │   ├── application.properties      # Spring Boot configuration  
│       │   ├── static/                     # Static assets  
│       │   └── templates/                  # HTML templates  
├── pom.xml                                # Maven dependencies  
└── README.md                              # Project documentation  
```

---

## Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/username/QueueMaster.git
   cd QueueMaster
   ```

2. **Configure the database:**
   Update the `application.properties` file:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/queue_db
   spring.datasource.username=postgres
   spring.datasource.password=gopika
   ```

3. **Build the project:**

   ```bash
   mvn clean install
   ```

---

## Running the Application

1. **Start the application:**

   ```bash
   mvn spring-boot:run
   ```

2. **Access the application:**
   Visit [http://localhost:8080/api/queue/](http://localhost:8080/api/queue/) to check the home route.

---

## API Endpoints

## 🧑‍💼 Employee Controller

| Endpoint                           | Method | Parameters        | Description                                         |
|------------------------------------|--------|-------------------|-----------------------------------------------------|
| `/api/employee/login`              | POST   | username, password| Employee login                                      |
| `/api/employee/queue-list`         | GET    | counterNumber     | Get list of queued customers for a counter         |
| `/api/employee/peek-next-customer`| GET    | counterNumber     | Peek at the next customer without dequeuing        |
| `/api/employee/next-customer`     | POST   | counterNumber     | Call and dequeue the next customer                 |

---

## 🎫 Queue Entry Controller

| Endpoint                             | Method | Parameters / Body         | Description                          |
|--------------------------------------|--------|----------------------------|--------------------------------------|
| `/api/queue/join-queue`              | POST   | `{ customer details }`     | Customer joins the queue             |
| `/api/queue/all`                     | GET    | None                       | Retrieve all queue entries           |
| `/api/queue/status/{tokenNumber}`    | GET    | Path Variable: tokenNumber | Get queue status by token            |
| `/api/queue/complete/{tokenNumber}`  | PUT    | Path Variable: tokenNumber | Mark the queue entry as completed    |


---

## Configuration

Update `application.properties` for database and server port:

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/queue_db
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 📦 Technologies Used

- **Backend**: Java, Spring Boot, PostgreSQL  
- **Frontend**: React.js  
- **Other Tools**: Postman, IntelliJ, Git, GitHub Actions

---

## Contributing

1. Fork the repository.
2. Create a feature branch:

   ```bash
   git checkout -b feature-branch
   ```
3. Commit your changes:

   ```bash
   git commit -m "Add new feature"
   ```
4. Push to the branch:

   ```bash
   git push origin feature-branch
   ```
5. Open a pull request.

---

## 🖼️ Output Screenshots

###  🔐 Home Page

![Employee Login](Home.png)

![Employee Login](assets/Home1.png)

![Employee Login](assets/Home2.png)

### 🔐 Employee Login Page

![Employee Login](assets/EmployeeLogin.png)

### 📋 Employee Dashboard

![Employee Dashboard](assets/EmployeeDashboard.png)

### 🧾 Customer Join Queue Page

![Join Queue](assets/Join-in-Queue.png)

### 📍 Customer Status View

![Customer Status](assets/CheckStatus.png)

---

## 📞 Contact

For questions, issues, or contributions:

- 📧 Email: 221124@iiitt.ac.in  
- 🐙 GitHub: [github.com/https://github.com/gopika04-saru](https://github.com/gopika04-saru)

- 📧 Email: 221152@iiitt.ac.in
- 🐙 GitHub: [github.com/https://github.com/Kalpana-1418](https://github.com/Kalpana-1418)

- 📧 Email: 221134@iiitt.ac.in
- 🐙 GitHub: [github.com/https://github.com/MiriyalaDeepti](https://github.com/MiriyalaDeepti)

---

