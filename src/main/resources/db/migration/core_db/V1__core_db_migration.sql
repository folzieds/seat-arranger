
-- inserting default user to the table
insert into m_appuser
(username, firstname, lastname, password, email, is_active)
values
('superuser', 'phos', 'admin', '$2a$10$dqX8Jog/uSD0yvBRXEa1eOdvcXkhPAfy8l4Mx6Vb6aSp4hH5Mryi2','superuser@phos.com', 1);

-- role table
CREATE TABLE m_role (
      id BIGINT NOT NULL AUTO_INCREMENT,
      name varchar(100) NOT NULL,
      description varchar(500),
      PRIMARY KEY (`id`),
      UNIQUE KEY `unq_name` (`name`)
);

-- Add default roles
insert into m_role
(name, description)
values
('USER', 'just a basic user'),
('ADMIN', 'backend user');

CREATE TABLE `m_appuser_role`(
     `id` BIGINT NOT NULL AUTO_INCREMENT,
     `appuser_id` BIGINT DEFAULT NULL,
     `role_id` BIGINT DEFAULT NULL,
     PRIMARY KEY (`id`),
     KEY `FK1E37728B93C6C1B6` (`appuser_id`),
     KEY `FK1E37728B783C5C25` (`role_id`),
     CONSTRAINT `FK1E37728B783C5C25` FOREIGN KEY (`role_id`) REFERENCES `m_role` (`id`),
     CONSTRAINT `FK1E37728B93C6C1B6` FOREIGN KEY (`appuser_id`) REFERENCES `m_appuser` (`id`)
);

-- Add admin role for default user
insert into m_appuser_role
    (appuser_id, role_id)
values
(1,2);