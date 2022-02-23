create table if not exists files (
    id serial not null unique,
    name varchar(255),
    user_id bigint not null,
    primary key (id)
);

create table if not exists users_files (
    user_id bigint not null,
    file_id bigint not null
);

alter table if exists files add constraint FKfctvcspbr3k260meioyvusd41 foreign key (user_id) references users;
alter table if exists users_files drop constraint if exists UK_lusfhtdww5c2h7e5fm6tng633;
alter table if exists users_files add constraint UK_lusfhtdww5c2h7e5fm6tng633 unique (file_id);
alter table if exists users_files add constraint FK3g76s2qlrs1a7sf86ob94dtdd foreign key (file_id) references files;
alter table if exists users_files add constraint FKd4qp5joaiwfhnvh0y1uol5huf foreign key (user_id) references users;