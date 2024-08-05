INSERT INTO account
    (account_number, account_type, initial_balance, status, client_id)
VALUES
    ('478758', 'Ahorros', 2000, TRUE, 1),
    ('225487', 'Corriente', 100, TRUE, 2),
    ('495878', 'Ahorros', 0, TRUE, 3),
    ('496825', 'Ahorros', 540, TRUE, 2);

INSERT INTO transaction
    (account_id, transaction_type, amount, balance, date)
VALUES
    ((SELECT id FROM account WHERE account_number = '478758'), 'Retiro', 575, 1425, NOW()),
    ((SELECT id FROM account WHERE account_number = '225487'), 'Depósito', 600, 700, NOW()),
    ((SELECT id FROM account WHERE account_number = '495878'), 'Depósito', 150, 150, NOW()),
    ((SELECT id FROM account WHERE account_number = '496825'), 'Retiro', 540, 0, NOW());