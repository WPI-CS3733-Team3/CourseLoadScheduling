--CS3733 Group 3
--SQL Scripts for Database
--Function: Collects course information for one course, including
--course name and number, as well as all sections.

SELECT DISTINCT courses.title AS courses_title,
courses.number AS courses_number,
terms.name AS terms_name,
frequency.frequency AS frequency,
sections.name AS sections_name
FROM courses
LEFT JOIN sections ON sections.courses_id = courses.id
LEFT JOIN terms ON sections.terms_id = terms.id
LEFT JOIN frequency ON courses.frequency_id = frequency.id
WHERE courses.id = :courseId
ORDER BY sections_name DESC;
