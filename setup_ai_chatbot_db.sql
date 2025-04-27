-- Create Database
CREATE DATABASE IF NOT EXISTS ai_chatbot_db;
USE ai_chatbot_db;

-- Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Events Table
CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    date_time DATETIME NOT NULL,
    location VARCHAR(255),
    available_seats INT NOT NULL
);

-- Event Registrations Table
CREATE TABLE event_registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    status ENUM('REGISTERED', 'CANCELLED') DEFAULT 'REGISTERED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

-- Admin Table
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert Default Admin
INSERT INTO admins (username, password) VALUES ('admin', 'admin123');

-- Insert Sample Users
INSERT INTO users (name, email, password) VALUES 
('Rahul Kumar', 'rahul@example.com', 'password123'),
('John Doe', 'john@example.com', 'securepass');

-- Insert Sample Events
INSERT INTO events (name, description, date_time, location, available_seats) VALUES 
('AI Conference 2025', 'A conference on AI and ML trends.', '2025-03-15 10:00:00', 'LPU Auditorium', 100),
('Tech Meetup', 'Networking event for developers.', '2025-04-20 14:30:00', 'Bangalore Tech Park', 50);

-- Insert Sample Registrations
INSERT INTO event_registrations (user_id, event_id, status) VALUES 
(1, 1, 'REGISTERED'),
(2, 2, 'REGISTERED');

-- Verify Data
SELECT * FROM users;
SELECT * FROM events;
SELECT * FROM event_registrations;
SELECT * FROM admins;
