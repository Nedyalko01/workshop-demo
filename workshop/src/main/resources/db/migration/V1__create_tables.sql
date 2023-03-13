CREATE SEQUENCE IF NOT EXISTS employee_id_seq;
CREATE SEQUENCE IF NOT EXISTS task_id_seq;

CREATE TABLE IF NOT EXISTS employees (
    id bigint DEFAULT nextval('employee_id_seq') PRIMARY KEY,
    full_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    phone_number varchar(255),
    date_of_birth date,
    monthly_salary numeric(15, 2)
    );

CREATE TABLE IF NOT EXISTS tasks (
    id bigint DEFAULT nextval('task_id_seq') PRIMARY KEY,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    assignee_id bigint REFERENCES employees(id) ON DELETE CASCADE,
    due_date date
    );