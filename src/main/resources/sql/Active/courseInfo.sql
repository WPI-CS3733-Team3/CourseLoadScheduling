--CS3733 Group 3
--SQL Scripts for Database
--Function: Collects course information for all courses, including
--course name and number, as well as frequency.

SELECT DISTINCT courses.title AS courses_title,
courses.number AS courses_number,
frequency.frequency AS frequency,
courses.frequency_id AS frequency_id,
courses.id AS courses_id
FROM courses
--LEFT JOIN sections ON sections.courses_id = courses.id
--LEFT JOIN terms ON sections.terms_id = terms.id
LEFT JOIN frequency ON courses.frequency_id = frequency.id
ORDER BY courses_title DESC;
