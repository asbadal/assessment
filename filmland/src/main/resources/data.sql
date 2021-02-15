
-- insert customer
insert into customer (email, password) values('java@sogeti.com','$2a$10$0nCZnVb4wVL.Q7d.fkS9dOMooVEXOto/8LWunfMZPsFfzT/ellKF2');
insert into customer (email, password) values('kotlin@sogeti.com','$2a$10$0nCZnVb4wVL.Q7d.fkS9dOMooVEXOto/8LWunfMZPsFfzT/ellKF2');

-- insert category
insert into category (name, available_content, price, billable_after_days) values('Dutch Films',10,4,30);
insert into category (name, available_content, price, billable_after_days) values('Dutch Series',20,6,30);
insert into category (name, available_content, price, billable_after_days) values('International Films',5,8,30);
