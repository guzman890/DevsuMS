INSERT INTO persons (name, gender, age, identification, address, phone)
VALUES
    ('Jose Lema', 'Male', 30, 'ID123456', 'Otavalo sn y principal', '098254785'),
    ('Marianela Montalvo', 'Female', 28, 'ID654321', 'Amazonas y NNUU', '097548965'),
    ('Juan Osorio', 'Male', 35, 'ID789012', '13 junio y Equinoccial', '098874587');

INSERT INTO clients (password, status, person_id)
VALUES
    ('1234', 'True', (SELECT id FROM persons WHERE name = 'Jose Lema')),
    ('5678', 'True', (SELECT id FROM persons WHERE name = 'Marianela Montalvo')),
    ('1245', 'True', (SELECT id FROM persons WHERE name = 'Juan Osorio'));