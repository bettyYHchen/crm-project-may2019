create database busyqacrm6;
use busyqacrm6;

// initialize the positions table
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_USER','TEAM_SALES');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_ADMIN','TEAM_SALES');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_PM','TEAM_SALES');

INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_USER','TEAM_ACCOUNTS');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_ADMIN','TEAM_ACCOUNTS');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_PM','TEAM_ACCOUNTS');

INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME)  VALUES('ROLE_USER','TEAM_ADMIN');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_ADMIN','TEAM_ADMIN');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_PM','TEAM_ADMIN');

INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_USER','TEAM_UNASSIGNED');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_PM','TEAM_UNASSIGNED');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_ADMIN','TEAM_UNASSIGNED');

INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_CLIENT','TEAM_LEAD');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_CLIENT','TEAM_STUDENT');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_CLIENT','TEAM_INTERN');
INSERT INTO POSITIONS (ROLE_NAME, TEAM_NAME) VALUES('ROLE_CLIENT','TEAM_ALUMNI');

// initialize the courses table 
INSERT INTO `busyqacrm6`.`courses` (`id`, `name`, `fee`) VALUES ('1', 'FULLSTACK_JAVA_DEVELOPER', '2500');
INSERT INTO `busyqacrm6`.`courses` (`id`, `name`, `fee`) VALUES ('2', 'AUTOMATION_TESTING', '2500');
INSERT INTO `busyqacrm6`.`courses` (`id`, `name`, `fee`) VALUES ('3', 'DATA_SCIENCE', '2500');
INSERT INTO `busyqacrm6`.`courses` (`id`, `name`, `fee`) VALUES ('4', 'SCRUM_MASTER', '2500');


// initialize the instructors table
INSERT INTO `busyqacrm6`.`instructors` (`id`, `email`, `first_name`, `last_name`) VALUES ('1', 'maryWhite@test.com', 'Mary', 'White');
INSERT INTO `busyqacrm6`.`instructors` (`id`, `email`, `first_name`, `last_name`) VALUES ('2', 'ritaJonna@test.com', 'Rita', 'Jonna');
INSERT INTO `busyqacrm6`.`instructors` (`id`, `email`, `first_name`, `last_name`) VALUES ('3', 'thomasGerman@test.com', 'Thomas', 'German');
INSERT INTO `busyqacrm6`.`instructors` (`id`, `email`, `first_name`, `last_name`) VALUES ('4', 'pamLongey@test.com', 'Pam', 'Longey');

// initialize the classes table
INSERT INTO `busyqacrm6`.`classes` (`id`, `name`, `course_id`, `instructor_id`, `is_finished`) VALUES ('14', 'SCRUM_MASTER WINTER 2019', '4', '2', b'1');
INSERT INTO `busyqacrm6`.`classes` (`id`, `name`, `course_id`, `instructor_id`, `is_finished`) VALUES ('6', 'DATA_SCIENCE WINTER 2019', '3', '1', b'1');
INSERT INTO `busyqacrm6`.`classes` (`id`, `name`, `course_id`, `instructor_id`, `is_finished`) VALUES ('7', 'FULLSTACK_JAVA_DEVELOPER WINTER 2019', '1', '3', b'1');
INSERT INTO `busyqacrm6`.`classes` (`id`, `name`, `course_id`, `instructor_id`, `is_finished`) VALUES ('8', 'AUTOMATION_TESTING WINTER 2019', '2', '2', b'1');

// initialize the users table (You can use these 3 users to test login and get access to admin, sales, and account)
INSERT INTO `busyqacrm6`.`users` (`id`, `email`, `name`, `password`, `status`, `status_as_of_day`, `username`, `type`) VALUES ('10', 'administrator@test.com', 'administrator', '$2a$10$HPbFvaSwWq3PcLMspSJwSe0gnW5IpPUg0tArP1sDN0JOMoJczny.O', 'YES', '2019-05-29T12:36:57.374079', 'administrator', '1');

INSERT INTO `busyqacrm6`.`users` (`id`, `email`, `name`, `password`, `status`, `status_as_of_day`, `username`, `type`) VALUES ('43', 'salesPerson@test.com', 'salesPerson', '$2a$10$Lksfr7lIUpbktlALNnGLsuWejEwXHAhrSlQuPqQLg63hYtpBucbSK', 'YES', '2019-05-29T17:40:48.023592', 'salesPerson', '1');

INSERT INTO `busyqacrm6`.`users` (`id`, `email`, `name`, `password`, `status`, `status_as_of_day`, `username`, `type`) VALUES ('44', 'accountPerson@test.com', 'accountPerson', '$2a$10$Paj1SQF.wz532aZVrr7LxeLsqcoO6okyRtz/SLXERrhFF0kEd2bBK', 'YES', '2019-05-29T17:41:37.406784', 'accountPerson', '1');

// initialize the user_position table (a table to reference roles of user when an user login)
INSERT INTO `busyqacrm6`.`user_position` (`user_id`, `position_id`) VALUES ('10', '8');
INSERT INTO `busyqacrm6`.`user_position` (`user_id`, `position_id`) VALUES ('43', '3');
INSERT INTO `busyqacrm6`.`user_position` (`user_id`, `position_id`) VALUES ('44', '4');




