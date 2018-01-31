--CS3733 Group 3
--SQL Scripts for Database
--Function: Collects all faculty members who are teaching the specified course.

SELECT courses.title AS courses_title,
courses.number AS courses_number,
faculty.first_name AS faculty_first_name,
faculty.last_name AS faculty_last_name
FROM schedule
LEFT JOIN faculty ON schedule.faculty_id = faculty.id
LEFT JOIN sections ON schedule.sections_id = sections.id
LEFT JOIN courses ON courses.id =  sections.courses_id
WHERE sections.courses_id = :courseId
ORDER BY faculty_last_name ASC;
