--CS3733 Group 3
--SQL Scripts for Database
--Function: List of all current faculty accounts with their
--names, emails, faculty type, and user type.

SELECT faculty.first_name AS faculty_first_name,
faculty.last_name AS faculty_last_name,
faculty.email AS faculty_email,
faculty.id AS faculty_id,
faculty_type.type AS faculty_type,
account_type.account_type AS account_type
FROM users
LEFT JOIN faculty ON users.faculty_id = faculty.id
LEFT JOIN faculty_type ON faculty.faculty_type_id = faculty_type.id
LEFT JOIN account_type ON users.account_type_id = account_type.id
WHERE faculty.deleted = false
ORDER BY faculty_last_name ASC;