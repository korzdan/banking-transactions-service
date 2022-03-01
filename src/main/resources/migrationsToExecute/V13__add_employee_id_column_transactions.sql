ALTER TABLE transactions
    ADD COLUMN employee_id BIGINT;

ALTER TABLE transactions
    ADD FOREIGN KEY (employee_id) REFERENCES users(id) ON DELETE CASCADE;