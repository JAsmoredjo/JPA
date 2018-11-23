drop schema if exists jpa;
create schema if not exists jpa;
use jpa;

create table if not exists city (
    id int not null auto_increment,
    name varchar(50) not null,
    description varchar(255) not null,
    primary key (id)
);

create table if not exists address (
    id int not null auto_increment,
    name varchar(50) not null,
    primary key (id)
);
create table if not exists employee (
    id int not null auto_increment,
    code varchar(50) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    dob date not null,
    gender varchar(1) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    primary key (id),
    unique (code),
    check (gender in ('m', 'v'))
);

create table if not exists mc_donalds (
    id int not null auto_increment,
    phone_number varchar(50) not null,
    code varchar(50) not null,
    city_fk int not null,
    address_fk int not null,
    primary key (id),
    unique (code),
    foreign key (city_fk) references city(id),
    foreign key (address_fk) references address(id)
);

create table if not exists mc_donalds_employee (
    mc_donalds_id int not null,
    employee_id int not null,
    log_date datetime default current_timestamp on update now(),
    primary key (mc_donalds_id, employee_id),
    foreign key (mc_donalds_id) references mc_donalds(id),
    foreign key (employee_id) references employee(id)
);

insert into city
values
(null, "c_n1", "c_d1"),
(null, "c_n2", "c_d2");

insert into address
values
(null, "a_n1"),
(null, "a_n2");

insert into employee
values
(null, "e_c1", "e_fn1", "e_ln1", "1990-1-1", "m", "e_pn1", "e_e1"),
(null, "e_c2", "e_fn2", "e_ln2", "1990-2-2", "v", "e_pn2", "e_e2");

insert into mc_donalds
values
(null, "m_pn1", "m_c1", 1, 1),
(null, "m_pn2", "m_c2", 2, 2);

insert into mc_donalds_employee
values
(1, 1, null),
(2, 2, null);
