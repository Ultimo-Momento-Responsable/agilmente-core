/* SECUENCIA HIBERNATE */
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
	START WITH 1
	INCREMENT BY 1;

/* TABLAS */

DROP TABLE IF EXISTS cognitive_domain CASCADE;
CREATE TABLE cognitive_domain (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

DROP TABLE IF EXISTS planning_state CASCADE;
CREATE TABLE planning_state (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

DROP TABLE IF EXISTS max_level CASCADE;
CREATE TABLE max_level (
	id SERIAL NOT NULL PRIMARY KEY,
	max_level INTEGER NOT NULL
);

DROP TABLE IF EXISTS figure_quantity CASCADE;
CREATE TABLE figure_quantity (
	id SERIAL NOT NULL PRIMARY KEY,
	figure_quantity INTEGER NOT NULL
);

DROP TABLE IF EXISTS sprite_set CASCADE;
CREATE TABLE sprite_set (
	id SERIAL NOT NULL PRIMARY KEY,
	sprite_set INTEGER NOT NULL
);

DROP TABLE IF EXISTS variable_size CASCADE;
CREATE TABLE variable_size (
	id SERIAL NOT NULL PRIMARY KEY,
	variable_size BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS game CASCADE;
CREATE TABLE game (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	description VARCHAR(255),
	param_description VARCHAR(255)
);

DROP TABLE IF EXISTS hay_uno_repetido_result CASCADE;
CREATE TABLE hay_uno_repetido_result (
	id SERIAL NOT NULL PRIMARY KEY,
	mistakes INTEGER,
	successes INTEGER,
	total_time NUMERIC,
	time_between_successes BYTEA,
	complete_datetime TIMESTAMP,
	canceled BOOLEAN
);

DROP TABLE IF EXISTS maximum_time CASCADE;
CREATE TABLE maximum_time (
	id SERIAL NOT NULL PRIMARY KEY,
	maximum_time INTEGER NOT NULL
);

DROP TABLE IF EXISTS patient CASCADE;
CREATE TABLE patient (
	id SERIAL NOT NULL PRIMARY KEY,
	born_date TIMESTAMP,
	city VARCHAR(255),
	description VARCHAR(255),
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email VARCHAR(255),
	telephone VARCHAR(255),
	is_logged BOOLEAN,
	is_enabled BOOLEAN,
	login_code VARCHAR(255),
	join_date TIMESTAMP
);

DROP TABLE IF EXISTS professional CASCADE;
CREATE TABLE professional (
	id SERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	user_name VARCHAR(255),
	password VARCHAR(255),
	token VARCHAR(255),
	token_expiration TIMESTAMP
);

DROP TABLE IF EXISTS comment CASCADE;
CREATE TABLE comment (
	id SERIAL NOT NULL PRIMARY KEY,
	comment VARCHAR(3000),
	datetime TIMESTAMP,
	author_id BIGINT,
	edited BOOLEAN DEFAULT false,
	FOREIGN KEY (author_id) REFERENCES professional(id)
);

DROP TABLE IF EXISTS param CASCADE;
CREATE TABLE param (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	class_name VARCHAR(255),
	type INT,
	unit VARCHAR(255),
	contextual_help VARCHAR(255)
);

DROP TABLE IF EXISTS game_cognitive_domain CASCADE;
CREATE TABLE game_cognitive_domain (
	game_id INTEGER NOT NULL,
	cognitive_domain_id INTEGER NOT NULL,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (cognitive_domain_id) REFERENCES cognitive_domain(id)
);

DROP TABLE IF EXISTS hay_uno_repetido_session CASCADE; 
CREATE TABLE hay_uno_repetido_session (
	id SERIAL NOT NULL PRIMARY KEY,
	max_level_id INTEGER,
	game_id INTEGER NOT NULL,
	maximum_time_id INTEGER,
	sprite_set_id INTEGER,
	variable_size_id INTEGER,
	figure_quantity_id INTEGER,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (max_level_id) REFERENCES max_level(id),
	FOREIGN KEY (maximum_time_id) REFERENCES maximum_time(id),
	FOREIGN KEY (variable_size_id) REFERENCES variable_size(id),
	FOREIGN KEY (sprite_set_id) REFERENCES sprite_set(id),
	FOREIGN KEY (figure_quantity_id) REFERENCES figure_quantity(id)
);

DROP TABLE IF EXISTS hay_uno_repetido_session_results CASCADE;
CREATE TABLE hay_uno_repetido_session_results (
	hay_uno_repetido_session_id BIGINT NOT NULL,
	results_id BIGINT NOT NULL,
	FOREIGN KEY (hay_uno_repetido_session_id) REFERENCES hay_uno_repetido_session(id),
	FOREIGN KEY (results_id) REFERENCES hay_uno_repetido_result(id)
);

DROP TABLE IF EXISTS planning CASCADE;
CREATE TABLE planning (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	creation_datetime TIMESTAMP,
	due_date TIMESTAMP,
	start_date TIMESTAMP,
	patient_id BIGINT,
	professional_id BIGINT,
	planning_state_id BIGINT,
	FOREIGN KEY (patient_id) REFERENCES patient(id),
	FOREIGN KEY (professional_id) REFERENCES professional(id)	
);

DROP TABLE IF EXISTS planning_detail CASCADE;
CREATE TABLE planning_detail (
	id SERIAL NOT NULL PRIMARY KEY,
	max_number_of_sessions INTEGER,
	number_of_sessions INTEGER,
	hay_uno_repetido_session_id BIGINT,
	planning_id BIGINT,
	FOREIGN KEY (hay_uno_repetido_session_id) REFERENCES hay_uno_repetido_session(id),
	FOREIGN KEY (planning_id) REFERENCES planning(id)	
);

DROP TABLE IF EXISTS game_param CASCADE;
CREATE TABLE game_param (
	id SERIAL NOT NULL PRIMARY KEY,
	param_id BIGINT NOT NULL,
	min_value INT,
	max_value INT,
	FOREIGN KEY (param_id) REFERENCES param(id)
);

DROP TABLE IF EXISTS param_type2content CASCADE;
CREATE TABLE param_type2content (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

DROP TABLE IF EXISTS game_param_param_type2content CASCADE;
CREATE TABLE game_param_param_type2content (
	param_type2content_id BIGINT,
	game_param_id BIGINT,
	FOREIGN KEY (param_type2content_id) REFERENCES param_type2content(id),
	FOREIGN KEY (game_param_id) REFERENCES game_param(id)
);

DROP TABLE IF EXISTS game_game_param CASCADE;
CREATE TABLE game_game_param (
	game_id BIGINT,
	game_param_id BIGINT,
	FOREIGN KEY (game_id) REFERENCES game(id),
	FOREIGN KEY (game_param_id) REFERENCES game_param(id)
);