drop schema if exists jpa;
create schema if not exists jpa;
use jpa;

create table if not exists city (
    id int not null auto_increment,
    name varchar(50) not null,
    description varchar(255) not null,
    primary key (id)
);

create table if not exists mc_donalds (
    id int not null auto_increment,
    address varchar(50) not null,
    phone_number varchar(50) not null,
    code varchar(50) not null,
    city_fk int not null,
    primary key (id),
    unique (code),
    foreign key (city_fk) references city(id)
);

insert into city
values
(null, "c_n1", "c_d1"),
(null, "c_n2", "c_d2");

insert into mc_donalds
values
(null, "m_a1", "m_pn1", "m_c1", 1),
(null, "m_a2", "m_pn2", "m_c2", 2),
(null, "m_a3", "m_pn3", "m_c3", 1),
(null, "m_a4", "m_pn4", "m_c4", 1),
(null, "m_a5", "m_pn5", "m_c5", 2);
