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

# Start the backend (make sure your database server is running)
cd backend
./mvnw spring-boot:run &

# Go back to the main directory
cd ..

# Start the frontend
npm start
