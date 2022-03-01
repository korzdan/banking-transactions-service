create table users_errors (
    employee_id int8,
    error_id int8 not null,
    primary key (error_id)
);

alter table if exists users_transactions drop constraint if exists UK_2hwhohq85gri6y93vq1a7p9dd;

alter table if exists users_transactions
    add constraint UK_2hwhohq85gri6y93vq1a7p9dd unique (transaction_id);

alter table if exists users_errors
    add constraint FK1l4eo65mp33cri0qj1barrau5 foreign key (employee_id) references users;

alter table if exists users_errors
    add constraint FKdaob0o9ud86xo6xxe7orghssc foreign key (error_id) references errors;