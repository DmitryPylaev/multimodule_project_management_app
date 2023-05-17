create
database eduDigDes;

create table employee
(
    id              serial primary key,
    lastname        varchar(50) not null,
    name            varchar(50) not null,
    patronymic      varchar(50),
    position        varchar(50),
    account         varchar(50) unique,
    email           varchar(50),
    employee_status int         not null
);

insert into employee (lastname, name, patronymic, position, account, email, employee_status)
values ('Ivanov',
        'Petr',
        'Sergeevich',
        'Java engineer',
        'iva',
        'i@ma.ru',
        '1');

delete from employee where account = 'iva';

create table project
(
    id             serial primary key,
    code           varchar(50) not null unique,
    name           varchar(50) not null,
    description    varchar(255),
    project_status int         not null
);

create table project_assignment
(
    id             serial primary key,
    fk_project_id      int not null,
    fk_employee_id int not null,
    project_role   int not null,
    constraint fk_employee_id foreign key (fk_employee_id) references employee (id),
    constraint fk_project_id foreign key (fk_project_id) references project (id)
);

create table task
(
    id             serial primary key,
    name           varchar(50) not null,
    description    varchar(255),
    fk_employee_id int,
    estimate       int         not null check ( estimate > 0 ),
    deadline       varchar(50) not null,
    task_status    int         not null,
    fk_author_id   int         not null,
    create_date    date not null,
    change_date    date not null,
    constraint fk_employee_id foreign key (fk_employee_id) references employee (id),
    constraint fk_author_id foreign key (fk_author_id) references employee (id)
);

drop table task
