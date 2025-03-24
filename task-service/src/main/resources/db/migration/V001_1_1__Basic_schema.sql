create schema if not exists task_service;

create type priority as ENUM ('HIGH', 'MEDIUM', 'LOW');
create type status as ENUM ('WAITING', 'IN', 'completed')

create table task_service.t_task
(
    id serial primary key,
    c_title varchar(50) not null check ( length(trim(c_title)) >= 3),
    c_details varchar(2000) not null,
    c_priority priority,
    c_status status,
    c_id_author int not null references task_service.t_user(id),
    c_id_executor int not null references task_service.t_user(id)
);

create table task_service.t_user
(
    id serial primary key,
    c_first_name varchar(50),
    c_last_name varchar(50),
    c_email varchar(50) not null, unique
);