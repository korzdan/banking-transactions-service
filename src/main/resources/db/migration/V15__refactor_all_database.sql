ALTER TABLE errors
    RENAME COLUMN user_id TO employee_id;

ALTER TABLE files
    RENAME COLUMN user_id TO employee_id;

ALTER TABLE users_files
    RENAME COLUMN user_id TO employee_id;