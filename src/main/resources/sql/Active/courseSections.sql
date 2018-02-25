--CS3733 Group 3
--SQL Scripts for Database
--Function: Gets all of the sections belonging to a course specified by
--the courseId variable, as well as information about the sections,
--such as days and section type.

SELECT sections.id AS section_id,
sections.terms_id AS terms_id,
sections.section_type_id AS section_type_id,
sections.days_id AS days_id,
sections.start_time_id AS start_time_id,
sections.end_time_id AS end_time_id,
sections.courses_id AS courses_id,
terms.name AS terms_name,
section_type.type AS section_type,
days.day AS days,
courses.number AS courses_number,
courses.title AS courses_title,
start_time.time AS start_time,
end_time.time AS end_time,
sections.name AS sections_name
FROM sections
LEFT JOIN terms ON sections.terms_id = terms.id
LEFT JOIN section_type ON section_type.id =  sections.section_type_id
LEFT JOIN days ON days.id = sections.days_id
LEFT JOIN courses ON courses.id = sections.courses_id
LEFT JOIN start_time ON start_time.id = sections.start_id
LEFT JOIN end_time ON end_time.id = sections.end_id
WHERE sections.courses_id = :courseId
ORDER BY sections_name DESC;
