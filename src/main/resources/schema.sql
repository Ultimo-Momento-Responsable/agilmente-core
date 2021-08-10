/* SECUENCIA HIBERNATE */
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
	START WITH 1
	INCREMENT BY 1;

/* TABLAS */
DROP TABLE IF EXISTS cognitive_domain;
CREATE TABLE cognitive_domain (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

DROP TABLE IF EXISTS figure_quantity;
CREATE TABLE figure_quantity (
	id SERIAL NOT NULL PRIMARY KEY,
	figure_quantity INTEGER NOT NULL
);

DROP TABLE IF EXISTS game;
CREATE TABLE game (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255)
);

DROP TABLE IF EXISTS hay_uno_repetido_result;
CREATE TABLE hay_uno_repetido_result (
	id SERIAL NOT NULL PRIMARY KEY,
	mistakes INTEGER,
	successes INTEGER,
	total_time NUMERIC,
	time_between_successes bytea,
	complete_datetime TIMESTAMP,
	canceled BOOLEAN
);

DROP TABLE IF EXISTS maximum_time;
CREATE TABLE maximum_time (
	id SERIAL NOT NULL PRIMARY KEY,
	maximum_time INTEGER NOT NULL
);

DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
	id SERIAL NOT NULL PRIMARY KEY,
	born_date TIMESTAMP,
	city VARCHAR(255),
	description VARCHAR(255),
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	is_logged BOOLEAN,
	login_code VARCHAR(255)
);

DROP TABLE IF EXISTS professional;
CREATE TABLE professional (
	id SERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255)
);

DROP TABLE IF EXISTS game_cognitive_domain;
CREATE TABLE game_cognitive_domain (
	game_id INTEGER NOT NULL,
	cognitive_domain_id INTEGER NOT NULL,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (cognitive_domain_id) REFERENCES cognitive_domain(id)
);

DROP TABLE IF EXISTS hay_uno_repetido_session;
CREATE TABLE hay_uno_repetido_session (
	id SERIAL NOT NULL PRIMARY KEY,
	figure_quantity_id INTEGER,
	game_id INTEGER NOT NULL,
	maximum_time_id INTEGER,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (figure_quantity_id) REFERENCES figure_quantity(id),
	FOREIGN KEY (maximum_time_id) REFERENCES maximum_time(id)
);

DROP TABLE IF EXISTS hay_uno_repetido_session_results;
CREATE TABLE hay_uno_repetido_session_results (
	hay_uno_repetido_session_id BIGINT NOT NULL,
	results_id BIGINT NOT NULL,
	FOREIGN KEY (hay_uno_repetido_session_id) REFERENCES hay_uno_repetido_session(id),
	FOREIGN KEY (results_id) REFERENCES hay_uno_repetido_result(id)
);

DROP TABLE IF EXISTS planning;
CREATE TABLE planning (
	id SERIAL NOT NULL PRIMARY KEY,
	creation_datetime TIMESTAMP,
	due_date TIMESTAMP,
	start_date TIMESTAMP,
	patient_id BIGINT,
	professional_id BIGINT,
	FOREIGN KEY (patient_id) REFERENCES patient(id),
	FOREIGN KEY (professional_id) REFERENCES professional(id)	
);

DROP TABLE IF EXISTS planning_detail;
CREATE TABLE planning_detail (
	id SERIAL NOT NULL PRIMARY KEY,
	max_number_of_sessions INTEGER,
	hay_uno_repetido_session_id BIGINT,
	planning_id BIGINT,
	FOREIGN KEY (hay_uno_repetido_session_id) REFERENCES hay_uno_repetido_session(id),
	FOREIGN KEY (planning_id) REFERENCES planning(id)	
);

DROP TABLE IF EXISTS param;
CREATE TABLE param (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	class_name VARCHAR(255),
	type INT,
	max_value INT,
	min_value INT
);

DROP TABLE IF EXISTS game_param;
CREATE TABLE game_param (
	game_id BIGINT NOT NULL,
	param_id BIGINT NOT NULL,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (param_id) REFERENCES param(id)
);