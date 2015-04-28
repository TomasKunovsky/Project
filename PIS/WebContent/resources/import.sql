
-- inserting data
-- admins
insert into user (LOGIN, DTYPE, EMAIL, FIRSTNAME, PASSWORD, SURNAME, TELEPHONENUMBER) values ('admin', 'Admin', 'admin@kurzy.com', 'Petr', 'admin', 'Horký', '624389573');

-- lector
INSERT INTO `user` (`LOGIN`, `DTYPE`, `EMAIL`, `FIRSTNAME`, `PASSWORD`, `SURNAME`, `TELEPHONENUMBER`, `QUALIFICATION`) VALUES ('lector', 'Lector', 'john@doe.com', 'John', 'lector', 'Doe', '495512344', 'Zaklinac hadov');

-- course
INSERT INTO `course` (`ID`, `DESCRIPTION`, `NAME`, `PRICE`, `LECTOR_LOGIN`) VALUES (1, 'Test popis', 'Test', 995, 'lector');

-- opencourse
INSERT INTO `pis_demo`.`open_course` (`ID`, `CAPACITY`, `ENDDATE`, `PLACE`, `STARTDATE`, `COURSE_ID`, `LECTOR_LOGIN`) VALUES (NULL, '20', '2015-04-07', 'Brno, Ceil 89', '2015-04-01', '1', 'lector');

commit;

