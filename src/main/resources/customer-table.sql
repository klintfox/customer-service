-- Script to create the 'customer' table for the customer-service microservice
-- Each microservice must have its own database as per the Challenge.txt

CREATE TABLE customer (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(16) NOT NULL
);

-- Index for faster lookups by document
CREATE UNIQUE INDEX idx_customer_document ON customer(document);

