# Exam Portal

The Exam Portal, a Full Stack application built with Spring Boot and React JS, serves as a comprehensive solution for universities to manage online examinations for students. The system consists of three main modules: Admin, Teacher, and Student.

## Features

There are 2 types of profiles that are available in Exam Portal

1. Admin – to be used by the Professor
2. Student

- **Admin Module:**
  - Create Courses
  - Modify Courses
  - Delete Courses
  - Create Quizzes
  - Modify Quizzes
  - Delete Quizzes
  - Enable Tab Switch Prevention
  - Create Questions
  - View Student-Wise Test Results

- **Student Module:**
  - Attempt Quizzes.
  - Review Quizzes.

## Getting Started

Follow the steps below to set up and run the Online Exam Portal locally.

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Running the Application

1. Clone the repository:

   git clone //
   cd online-exam-portal


2. Start the application using Docker Compose:

   docker-compose up


3. Access the application:
  - Spring Boot Backend: http://localhost:8080
  - React Frontend: http://localhost:3000

4. Stop the application:

   docker-compose down

## Contributing

Contributions are welcome! Please fork the repository and create a pull request for any enhancements or bug fixes.


**Developed by [NikKnez](https://github.com/NikKnez)**
