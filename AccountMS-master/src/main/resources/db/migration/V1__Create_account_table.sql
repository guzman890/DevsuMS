CREATE TABLE account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_number VARCHAR(255) NOT NULL,
                         account_type VARCHAR(255) NOT NULL,
                         initial_balance DOUBLE NOT NULL,
                         status VARCHAR(255) NOT NULL,
                         client_id BIGINT NOT NULL
);