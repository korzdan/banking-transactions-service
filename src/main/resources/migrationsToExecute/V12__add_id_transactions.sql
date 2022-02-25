ALTER TABLE transactions
    DROP CONSTRAINT transactions_pk;

ALTER TABLE transactions
    ADD COLUMN id SERIAL PRIMARY KEY;