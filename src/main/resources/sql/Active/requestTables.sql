--CS3733 Group 3
--SQL Scripts for Database
--Function: Joins all of the request tables, containing information for
--any request specifications, and returns all of the requests made.

SELECT
requests.id AS requests_id,
requests.users_id AS requests_user_id,
faculty.first_name AS faculty_first_name,
faculty.last_name AS faculty_last_name,
terms.name AS terms_name,
terms.id AS terms_id,
start_time.id AS start_time_id,
start_time.time AS start_time,
end_time.id AS end_time_id,
end_time.time AS end_time,
courses.id AS courses_id,
courses.title AS courses_title,
courses.number AS courses_number,
request_other.message AS request_other_message,
request_status.id AS request_status_id,
request_status.status AS request_status
FROM requests 
LEFT JOIN request_course ON requests.id = request_course.requests_id
LEFT JOIN courses ON courses.id = request_course.courses_id
LEFT JOIN request_time ON requests.id = request_time.requests_id
LEFT JOIN start_time ON start_time.id = request_time.start_id
LEFT JOIN end_time ON end_time.id = request_time.end_id
LEFT JOIN request_term ON requests.id = request_term.requests_id
LEFT JOIN terms ON terms.id = request_term.terms_id
LEFT JOIN request_other ON requests.id = request_other.requests_id
LEFT JOIN request_status ON requests.request_status_id = request_status.id
LEFT JOIN users ON requests.users_id = users.id
LEFT JOIN faculty ON users.faculty_id = faculty.id
ORDER BY requests_id ASC;