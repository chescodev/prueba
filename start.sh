#!/bin/bash

# Navigate to the backend directory
cd backend

# Install backend dependencies (adjust according to your dependency manager)
./mvnw clean install

# Navigate to the frontend directory
cd ../frontend

# Install frontend dependencies
npm install

# Go back to the main directory
cd ..

# Set your database connection properties (replace placeholders with actual values)
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/notesdb
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=1234

# Start the backend (make sure your database server is running)
cd backend

# Start the backend
./mvnw spring-boot:run &

# Go back to the main directory
cd ..

# Start the frontend
npm start
