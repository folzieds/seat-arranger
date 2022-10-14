-- inserting default user to the table
insert into m_appuser
(username, firstname, lastname, password, email, is_active)
values
('superuser', 'phos', 'admin', '$2a$10$dqX8Jog/uSD0yvBRXEa1eOdvcXkhPAfy8l4Mx6Vb6aSp4hH5Mryi2','superuser@phos.com', 1);

-- Add default roles
insert into m_role
(name, description)
values
('USER', 'just a basic user'),
('ADMIN', 'backend user');

-- Add admin role for default user
insert into m_appuser_role
    (appuser_id, role_id)
values
(1,2);