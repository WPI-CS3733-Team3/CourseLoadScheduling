-- get all user information for users who have a specified roleId

SELECT users.*
FROM users
WHERE id IN
(
    SELECT user_id
    FROM users_roles_links
    WHERE role_id = :roleId
)
ORDER BY users.id ASC;

