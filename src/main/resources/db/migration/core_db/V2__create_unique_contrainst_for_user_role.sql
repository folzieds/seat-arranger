-- Create Unique constraint for app user and role.
-- Means one user can have one type of role per time
-- Hence user A can be a User and an admin but not a user and a user.

ALTER TABLE m_appuser_role
ADD CONSTRAINT m_appuser_role_unique UNIQUE (appuser_id, role_id);