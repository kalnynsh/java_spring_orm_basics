CREATE TABLE avatars(
    id BIGSERIAL,
    photo_url VARCHAR(8000),
    PRIMARY KEY (id)
);

CREATE TABLE courses(
    id BIGSERIAL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE students(
	id BIGSERIAL,
    name VARCHAR(255),
    avatar_id BIGINT REFERENCES avatars(id),
    PRIMARY KEY (id)
);

CREATE TABLE emails(
	id BIGSERIAL,
	student_id BIGINT REFERENCES students(id) ON DELETE CASCADE,
    email VARCHAR(255),    
    PRIMARY KEY (id)
);

CREATE TABLE student_courses(
	student_id BIGINT REFERENCES students(id) ON DELETE CASCADE,
	course_id BIGINT REFERENCES courses(id),
	PRIMARY KEY (student_id, course_id)
);