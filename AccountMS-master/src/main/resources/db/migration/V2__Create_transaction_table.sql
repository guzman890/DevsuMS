CREATE TABLE transaction (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             date TIMESTAMP NOT NULL,
                             transaction_type VARCHAR(255) NOT NULL,
                             amount DOUBLE NOT NULL,
                             balance DOUBLE NOT NULL,
                             account_id BIGINT NOT NULL,
                             CONSTRAINT fk_account
                                 FOREIGN KEY (account_id)
                                     REFERENCES account(id)
);