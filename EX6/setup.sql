CREATE DATABASE IF NOT EXISTS ticketdb;
USE ticketdb;
CREATE TABLE IF NOT EXISTS tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    event_name VARCHAR(50) NOT NULL,
    num_tickets INT NOT NULL,
    booking_date DATE NOT NULL
);
