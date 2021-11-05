select * from Tasks

insert into Tasks values (101, 'description 1', 0 , '1 First TASK TITLE')
insert into Tasks values (102, 'description 2', 0, '2 TASK TITLE')
insert into Tasks values (103, 'description 3', 0, '3 TASK TITLE')
insert into SUB_TASKS values (21, 'SUB ', 0,   'SUB 1 Title', 102)
insert into SUB_TASKS values (22, 'SUB ', 2,   'SUB 2 Title', 101)
insert into SUB_TASKS values (23, 'SUB ', 2,   'SUB 2 Title', 101)
insert into SUB_TASKS values (24, 'SUB ', 1,   'SUB 2 Title', 103)