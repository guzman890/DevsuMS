CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    person_id BIGINT NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES persons(id)
);