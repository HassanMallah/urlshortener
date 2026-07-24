--changeset hassan:001
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(20) NOT NULL,
                       google_id VARCHAR(255),
                       enabled BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP NOT NULL
);