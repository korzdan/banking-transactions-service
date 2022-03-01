CREATE TABLE users_transactions(
    employee_id bigint,
    transaction_id bigint,
    FOREIGN KEY (employee_id) REFERENCES users(id),
    FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);
