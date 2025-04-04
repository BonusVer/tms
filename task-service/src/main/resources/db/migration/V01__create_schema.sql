CREATE SCHEMA IF NOT EXISTS task_service;

CREATE TABLE task_service.t_user (
                                     id BIGSERIAL PRIMARY KEY,
                                     first_name VARCHAR(50) NOT NULL,
                                     last_name VARCHAR(50) NOT NULL,
                                     email VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE task_service.t_task (
                                     id BIGSERIAL PRIMARY KEY,
                                     title VARCHAR(50) NOT NULL CHECK (length(trim(title)) >= 3),
                                     details TEXT NOT NULL,
                                     priority VARCHAR(20),
                                     status VARCHAR(20),
                                     author_id BIGINT NOT NULL REFERENCES task_service.t_user(id),
                                     executor_id BIGINT NOT NULL REFERENCES task_service.t_user(id)
);
