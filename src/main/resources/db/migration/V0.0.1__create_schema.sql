create type currency as enum ('usd', 'eur', 'rub');

create table jobs (
    id serial primary key,
    vacancy_name varchar not null,
    company_name varchar not null,
    salary int,
    currency currency not null
);