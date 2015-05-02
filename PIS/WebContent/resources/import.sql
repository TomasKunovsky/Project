SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

INSERT INTO `user` (`LOGIN`, `DTYPE`, `EMAIL`, `FIRSTNAME`, `PASSWORD`, `SURNAME`, `TELEPHONENUMBER`, `QUALIFICATION`) VALUES
('admin', 'Admin', 'admin@kurzy.com', 'Petr', 'admin', 'Horký', '624389573', NULL),
('client1', 'Client', 'mrkvicka@client.com', 'Janko', 'client1', 'Mrkvicka', '724584123', NULL),
('client2', 'Client', 'maly@client.com', 'Jozef', 'client2', 'Maly', '757215789', NULL),
('client3', 'Client', 'hlina@client.com', 'Alfred', 'client3', 'Hlina', '755145487', NULL),
('lektor1', 'Lector', 'cerny@kurzy.com', 'Jan', 'lektor1', 'Èerný', '724399574', 'MS Word'),
('lektor2', 'Lector', 'maly@kurzy.com', 'Lukas', 'lektor2', 'Malý', '724390503', 'Power Point'),
('lektor3', 'Lector', 'karel@kurzy.cz', 'Karel', 'lektor3', 'Tichý', '784567125', 'Windows 7');

INSERT INTO `course` (`ID`, `DESCRIPTION`, `NAME`, `PRICE`, `LECTOR_LOGIN`) VALUES
(1, 'balík MS office', 'MS Word', 2000, 'lektor1'),
(2, 'seznámení s operaèním systémem', 'Windows 7', 2100, 'lektor3'),
(3, 'balík MS office', 'MS PowerPoint', 3000, 'lektor2');

INSERT INTO `open_course` (`ID`, `CAPACITY`, `ENDDATE`, `PLACE`, `STARTDATE`, `COURSE_ID`, `LECTOR_LOGIN`) VALUES
(1, 10, '2018-05-25', 'Brno', '2015-05-21', 2, 'lektor1'),
(2, 10, '2020-08-19', 'Praha', '2015-05-07', 2, 'lektor3'),
(3, 10, '2015-07-31', 'Olomouc', '2015-05-29', 2, 'lektor2'),
(4, 20, '2016-02-26', 'Brno', '2015-05-14', 1, 'lektor1');

ALTER TABLE `course`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;

ALTER TABLE `lesson`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `open_course`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;

ALTER TABLE `payment`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
