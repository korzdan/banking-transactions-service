CREATE TABLE IF NOT EXISTS transactions (
    transaction_id uuid PRIMARY KEY NOT NULL UNIQUE,
    user_id uuid NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    amount BIGINT NOT NULL,
    currency VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);