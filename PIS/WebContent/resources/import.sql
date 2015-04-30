
-- inserting data
-- admins
insert into user (LOGIN, DTYPE, EMAIL, FIRSTNAME, PASSWORD, SURNAME, TELEPHONENUMBER) values ('admin', 'Admin', 'admin@kurzy.com', 'Petr', 'admin', 'Horký', '624389573');

-- lector
INSERT INTO `user` (`LOGIN`, `DTYPE`, `EMAIL`, `FIRSTNAME`, `PASSWORD`, `SURNAME`, `TELEPHONENUMBER`, `QUALIFICATION`) VALUES ('lector', 'Lector', 'john@doe.com', 'John', 'lector', 'Doe', '495512344', 'Zaklinac hadov');

-- users
INSERT INTO `user` (`LOGIN`, `DTYPE`, `EMAIL`, `FIRSTNAME`, `PASSWORD`, `SURNAME`, `TELEPHONENUMBER`, `QUALIFICATION`) VALUES
('client1', 'Client', 'client1@client.com', 'Janko', 'client1', 'Mrkvicka', '123465789', NULL),
('client2', 'Client', 'client2@client.com', 'Jozef', 'client2', 'Maly', '123465789', NULL),
('client3', 'Client', 'client3@client.com', 'Alfred', 'client3', 'Hlina', '123465777', NULL);

-- course
INSERT INTO `course` (`ID`, `DESCRIPTION`, `NAME`, `PRICE`, `LECTOR_LOGIN`) VALUES (1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vel mauris felis. Etiam orci sapien, viverra ut tellus a, lacinia maximus ante. ', 'Lorem ipsum dolor sit amet', 995, 'lector');

-- opencourse
INSERT INTO `pis_demo`.`open_course` (`ID`, `CAPACITY`, `ENDDATE`, `PLACE`, `STARTDATE`, `COURSE_ID`, `LECTOR_LOGIN`) VALUES (NULL, '20', '2015-04-07', 'Brno, Ceil 89', '2015-04-01', '1', 'lector');

commit;

