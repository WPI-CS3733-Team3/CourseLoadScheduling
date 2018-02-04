CREATE TABLE faculty_type (
	id SERIAL PRIMARY KEY,
	type varchar(255) NOT NULL UNIQUE
);

CREATE TABLE account_type (
	id SERIAL PRIMARY KEY,
	account_type varchar(255) NOT NULL UNIQUE
);

CREATE TABLE faculty (
	id SERIAL PRIMARY KEY,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	faculty_type_id SERIAL NOT NULL REFERENCES faculty_type(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE faculty_history (
	id SERIAL PRIMARY KEY,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	faculty_type_id SERIAL NOT NULL REFERENCES faculty_type(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	account_type_id SERIAL NOT NULL REFERENCES account_type(id),
	faculty_id SERIAL NOT NULL REFERENCES faculty(id),
	encrypted_password varchar(255) NOT NULL UNIQUE,
	password_salt varchar(255) NOT NULL UNIQUE,
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE users_history (
	id SERIAL PRIMARY KEY,
	account_type_id SERIAL NOT NULL REFERENCES account_type(id),
	faculty_id SERIAL NOT NULL REFERENCES faculty(id),
	encrypted_password varchar(255) NOT NULL,
	password_salt varchar(255) NOT NULL,
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE end_time (
	id SERIAL PRIMARY KEY,
	time time NOT NULL UNIQUE
);

CREATE TABLE terms (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE start_time (
	id SERIAL PRIMARY KEY,
	time time NOT NULL UNIQUE
);

CREATE TABLE frequency (
	id SERIAL PRIMARY KEY,
	frequency varchar(255) NOT NULL UNIQUE
);

CREATE TABLE courses (
	id SERIAL PRIMARY KEY,
	title varchar(255) NOT NULL UNIQUE,
	number varchar(255) NOT NULL UNIQUE,
	frequency_id SERIAL NOT NULL REFERENCES frequency(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE courses_history (
	id SERIAL PRIMARY KEY,
	title varchar(255) NOT NULL,
	number varchar(255) NOT NULL,
	frequency_id SERIAL NOT NULL REFERENCES frequency(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);


CREATE TABLE section_type (
	id SERIAL PRIMARY KEY,
	type varchar(255) NOT NULL UNIQUE
);

CREATE TABLE days (
	id SERIAL PRIMARY KEY,
	day varchar(255) NOT NULL UNIQUE
);

CREATE TABLE course_load_type (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE sections (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
	crn int4 NOT NULL UNIQUE,
	terms_id SERIAL NOT NULL REFERENCES terms(id),
	section_type_id SERIAL NOT NULL REFERENCES section_type(id),
	days_id SERIAL NOT NULL REFERENCES days(id),
	courses_id SERIAL NOT NULL REFERENCES courses(id),
	start_id SERIAL NOT NULL REFERENCES start_time(id),
	end_id SERIAL NOT NULL REFERENCES end_time(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE sections_history (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
	crn int4 NOT NULL,
	terms_id SERIAL NOT NULL REFERENCES terms(id),
	section_type_id SERIAL NOT NULL REFERENCES section_type(id),
	days_id SERIAL NOT NULL REFERENCES days(id),
	courses_id SERIAL NOT NULL REFERENCES courses(id),
	start_id SERIAL NOT NULL REFERENCES start_time(id),
	end_id SERIAL NOT NULL REFERENCES end_time(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE schedule (
	id SERIAL PRIMARY KEY,
	faculty_id SERIAL NOT NULL REFERENCES faculty(id),
	sections_id SERIAL NOT NULL REFERENCES sections(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE schedule_history (
	id SERIAL PRIMARY KEY,
	faculty_id SERIAL NOT NULL REFERENCES faculty(id),
	sections_id SERIAL NOT NULL REFERENCES sections(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE request_status (
	id SERIAL PRIMARY KEY,
	status varchar(255) NOT NULL UNIQUE
);

CREATE TABLE requests (
	id SERIAL PRIMARY KEY,
	users_id SERIAL NOT NULL REFERENCES users(id),
	request_status_id SERIAL NOT NULL REFERENCES request_status(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE request_course (
	id SERIAL PRIMARY KEY,
	requests_id SERIAL NOT NULL REFERENCES requests(id),
	courses_id SERIAL NOT NULL REFERENCES courses(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE request_time (
	id SERIAL PRIMARY KEY,
	requests_id SERIAL NOT NULL REFERENCES requests(id),
	start_id SERIAL NOT NULL REFERENCES start_time(id),
	end_id SERIAL NOT NULL REFERENCES end_time(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE request_other (
	id SERIAL PRIMARY KEY,
	requests_id SERIAL NOT NULL REFERENCES requests(id),
	message varchar(255) NOT NULL,
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE request_term (
	id SERIAL PRIMARY KEY,
	requests_id SERIAL NOT NULL REFERENCES requests(id),
	terms_id SERIAL NOT NULL REFERENCES terms(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE course_load (
	id SERIAL PRIMARY KEY,
	course_load_type_id SERIAL NOT NULL references course_load_type(id),
	course_load_hours int4 NOT NULL,
	faculty_id SERIAL NOT NULL UNIQUE REFERENCES faculty(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

CREATE TABLE course_load_history (
	id SERIAL PRIMARY KEY,
	course_load_type_id SERIAL NOT NULL references course_load_type(id),
	course_load_hours int4 NOT NULL,
	faculty_id SERIAL NOT NULL REFERENCES faculty(id),
	created_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted bool NOT NULL DEFAULT(FALSE)
);

--This function updated the updated_at column
--in various tables to represent the current time.
CREATE FUNCTION update_timestamp() RETURNS TRIGGER AS
$BODY$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;

$BODY$ LANGUAGE plpgsql;

CREATE FUNCTION fake_delete_row() RETURNS TRIGGER AS $BODY$
DECLARE
 old_row RECORD;
BEGIN
 EXECUTE format('SELECT * FROM %I WHERE id=%s AND deleted IS TRUE', quote_ident(TG_TABLE_NAME), OLD.id) INTO old_row;
 EXECUTE format('UPDATE %I SET deleted=TRUE WHERE id=%s', quote_ident(TG_TABLE_NAME), OLD.id);
 RETURN old_row;
END;
$BODY$ LANGUAGE plpgsql;


--Functions that move data from a table to their respective history tables
--Function so the boolean value 'deleted' is updated
CREATE FUNCTION insert_faculty_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO faculty_history(first_name, last_name, email, faculty_type_id, created_at, updated_at, deleted)
VALUES(OLD.first_name, OLD.last_name, OLD.email, OLD.faculty_type_id, OLD.created_at, (CURRENT_TIMESTAMP), FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_courses_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO courses_history(title, number, frequency_id, created_at, updated_at, deleted)
VALUES(OLD.title, OLD.number, OLD.frequency_id, OLD.created_at, (CURRENT_TIMESTAMP), FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_sections_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO sections_history(name, crn, terms_id, section_type_id, days_id, courses_id, start_id, end_id, created_at, updated_at, deleted)
VALUES(OLD.name, OLD.crn, OLD.terms_id, OLD.section_type_id, OLD.days_id, OLD.courses_id, OLD.start_id, OLD.end_id, OLD.created_at, (CURRENT_TIMESTAMP), FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_schedule_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO schedule_history(faculty_id,sections_id,created_at,updated_at,deleted)
VALUES(OLD.faculty_id, OLD.sections_id, OLD.created_at, (CURRENT_TIMESTAMP), FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_course_load_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO course_load_schedule_history(course_load_type_id, course_load_hours,faculty_id,created_at, updated_at ,deleted)
VALUES(OLD.course_load_type_id, OLD.course_load_hours,OLD.faculty_id,OLD.created_at, (CURRENT_TIMESTAMP) , FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_users_history() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO users_history(account_type_id, faculty_id, encrypted_password, password_salt, created_at, updated_at, deleted)
VALUES(OLD.account_type_id, OLD.faculty_id, OLD.encrypted_password, OLD.password_salt, OLD.created_at, (CURRENT_TIMESTAMP), FALSE);
NEW.updated_at := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;


--DELETE FUNCTIONS
--called by delete trigger, inserts into history table.
CREATE FUNCTION insert_faculty_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO faculty_history(first_name, last_name, email, faculty_type_id, created_at, updated_at, deleted)
VALUES(OLD.first_name, OLD.last_name, OLD.email, OLD.faculty_type_id, OLD.created_at, (CURRENT_TIMESTAMP), TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_courses_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO courses_history(title, number, frequency_id, created_at, updated_at, deleted)
VALUES(OLD.title, OLD.number, OLD.frequency_id, OLD.created_at, (CURRENT_TIMESTAMP), TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_sections_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO sections_history(name, crn, terms_id, section_type_id, days_id, courses_id, start_id, end_id, created_at, updated_at, deleted)
VALUES(OLD.name, OLD.crn, OLD.terms_id, OLD.section_type_id, OLD.days_id, OLD.courses_id, OLD.start_id, OLD.end_id, OLD.created_at, (CURRENT_TIMESTAMP), TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_schedule_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO schedule_history(faculty_id,sections_id,created_at,updated_at,deleted)
VALUES(OLD.faculty_id, OLD.sections_id, OLD.created_at, (CURRENT_TIMESTAMP), TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_course_load_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO course_load_schedule_history(course_load_type_id, course_load_hours,faculty_id,created_at, updated_at ,deleted)
VALUES(OLD.course_load_type_id, OLD.course_load_hours,OLD.faculty_id,OLD.created_at, (CURRENT_TIMESTAMP) , TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE FUNCTION insert_users_history_delete() RETURNS TRIGGER AS
$BODY$
BEGIN INSERT INTO users_history(account_type_id, faculty_id, encrypted_password, password_salt, created_at, updated_at, deleted)
VALUES(OLD.account_type_id, OLD.faculty_id, OLD.encrypted_password, OLD.password_salt, OLD.created_at, (CURRENT_TIMESTAMP), TRUE);
RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;


--Triggers that copy data to their respecive history tables
--before being updated. Additionally, its "updated_at" table
--is changed to the CURRENT_TIMESTAMP
CREATE TRIGGER update_courses
BEFORE UPDATE ON courses
FOR EACH ROW EXECUTE PROCEDURE insert_courses_history();

CREATE TRIGGER update_course_load
BEFORE UPDATE ON course_load
FOR EACH ROW EXECUTE PROCEDURE insert_course_load_history();

CREATE TRIGGER update_sections
BEFORE UPDATE ON sections
FOR EACH ROW EXECUTE PROCEDURE insert_sections_history();

CREATE TRIGGER update_faculty
BEFORE UPDATE ON faculty
FOR EACH ROW EXECUTE PROCEDURE insert_faculty_history();

CREATE TRIGGER update_schedule
BEFORE UPDATE ON schedule
FOR EACH ROW EXECUTE PROCEDURE insert_schedule_history();

CREATE TRIGGER update_users
BEFORE UPDATE ON users
FOR EACH ROW EXECUTE PROCEDURE insert_users_history();



--Triggers for tables that have corresponding history tables
--makes a copy of rows to their history table before being deleted.

CREATE TRIGGER delete_courses
BEFORE DELETE ON courses
FOR EACH ROW EXECUTE PROCEDURE insert_courses_history_delete();

CREATE TRIGGER delete_course_load
BEFORE DELETE ON course_load
FOR EACH ROW EXECUTE PROCEDURE insert_course_load_history_delete();

CREATE TRIGGER delete_sections
BEFORE DELETE ON sections
FOR EACH ROW EXECUTE PROCEDURE insert_sections_history_delete();

CREATE TRIGGER delete_faculty
BEFORE DELETE ON faculty
FOR EACH ROW EXECUTE PROCEDURE insert_faculty_history_delete();

CREATE TRIGGER delete_schedule
BEFORE DELETE ON schedule
FOR EACH ROW EXECUTE PROCEDURE insert_schedule_history_delete();

CREATE TRIGGER delete_users
BEFORE DELETE ON users
FOR EACH ROW EXECUTE PROCEDURE insert_users_history_delete();


--Triggers for DELETE on various tables, so the boolean
--value 'deleted' is updated instead of the row being
--removed from the database:
CREATE TRIGGER request_time_delete
BEFORE DELETE ON request_time
FOR EACH ROW EXECUTE PROCEDURE fake_delete_row();

CREATE TRIGGER requests_delete
BEFORE DELETE ON requests
FOR EACH ROW EXECUTE PROCEDURE fake_delete_row();

CREATE TRIGGER request_course_delete
BEFORE DELETE ON request_course
FOR EACH ROW EXECUTE PROCEDURE fake_delete_row();

CREATE TRIGGER request_other_delete
BEFORE DELETE ON request_other
FOR EACH ROW EXECUTE PROCEDURE fake_delete_row();

CREATE TRIGGER request_term_delete
BEFORE DELETE ON request_term
FOR EACH ROW EXECUTE PROCEDURE fake_delete_row();







 