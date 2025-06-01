# 🏥 Hospital Patient Management System

A Spring Boot REST API for managing hospital patients with full CRUD functionality and patient categorization by type.

## 🚀 Tech Stack
- **Java**, **Spring Boot**, **Spring Data JPA**  
- **PostgreSQL**  
- **Postman** (for API testing)

## 📌 Features
- RESTful API with full CRUD support  
- Search patients by gender  
- Bulk addition of patients  
- Auto-incremented patient IDs  
- Inheritance-based categorization: `AdultPatient` & `ChildPatient`  

## ⚙️ Challenges & Solutions
- **Null ID error**: Resolved using auto-increment strategy  
- **Bulk addition**: Implemented dedicated "bulk" endpoints  
- **ID sequencing issues**: Fixed using `ALTER SEQUENCE` for sync  

## 📬 API Usage
Use Postman to interact with endpoints:
- `POST /patients`
- `GET /patients/{id}`
- `GET /patients?gender=...`
- `POST /patients/bulk`
