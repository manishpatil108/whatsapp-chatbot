# WhatsApp Chatbot Backend Simulation
### Java 17 + Spring Boot 3.2

A REST API backend that simulates a WhatsApp chatbot webhook.  
Accepts incoming message payloads, logs them, and returns predefined replies.  
Includes a WhatsApp-style frontend UI served from the same application.

---

## Features

- `POST /webhook` — receives simulated WhatsApp messages and returns chatbot replies
- `GET /webhook` — health check endpoint
- Predefined replies: `Hi` → Hello, `Bye` → Goodbye, `Help`, `Thanks`
- Full request validation with structured JSON error responses
- WhatsApp-style dark theme frontend (no extra server needed)
- Docker-ready for deployment on Render / AWS

---

## Tech Stack

| Layer       | Technology          |
|-------------|---------------------|
| Language    | Java 17             |
| Framework   | Spring Boot 3.2     |
| Build Tool  | Maven               |
| Logging     | SLF4J + Logback     |
| Frontend    | HTML / CSS / JS     |
| Deployment  | Docker / AWS / Render |

---

## Running Locally

```bash
# 1. Clone
git clone https://github.com/YOUR_USERNAME/whatsapp-chatbot.git
cd whatsapp-chatbot

# 2. Run
mvn spring-boot:run

# 3. Open browser
http://localhost:8080
```

---

## API Testing (curl)

```bash
# POST - Send a message
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+919876543210", "body": "Hi", "timestamp": "2024-04-23T10:00:00Z"}'

# GET - Health check
curl http://localhost:8080/webhook
```

---

## Supported Keywords

| Input    | Bot Reply                                      |
|----------|------------------------------------------------|
| Hi       | Hello! How can I assist you today?             |
| Hello    | Hello! How can I assist you today?             |
| Bye      | Goodbye! Have a wonderful day.                 |
| Goodbye  | Goodbye! Have a wonderful day.                 |
| Help     | Sure! You can say: Hi, Hello, Bye...           |
| Thanks   | You're welcome! Is there anything else...      |
| *other*  | I'm sorry, I didn't understand that...         |

> All keywords are case-insensitive.

---

## Run Tests

```bash
mvn test
```

---

## Deploy on AWS Elastic Beanstalk

```bash
# Build JAR
mvn clean package -DskipTests

# Upload target/whatsapp-chatbot-0.0.1-SNAPSHOT.jar
# to AWS Elastic Beanstalk → Java 17 platform
```

## Deploy on Render (Free)

1. Push to GitHub
2. New Web Service → connect repo
3. Environment: Docker, Port: 8080
4. Deploy

---

## Project Structure

```
whatsapp-chatbot/
├── src/main/java/com/jarurat/chatbot/
│   ├── WhatsappChatbotApplication.java
│   ├── controller/
│   │   ├── WebhookController.java
│   │   └── GlobalExceptionHandler.java
│   ├── model/
│   │   ├── IncomingMessage.java
│   │   └── OutgoingMessage.java
│   └── service/
│       └── ChatbotService.java
├── src/main/resources/
│   ├── application.properties
│   └── static/index.html
├── src/test/
│   └── ChatbotServiceTest.java
├── Dockerfile
├── .gitignore
└── pom.xml
```

---

Built for Jarurat Care Java Developer Internship Assignment.
