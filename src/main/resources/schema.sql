/* SECUENCIA HIBERNATE 
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
	START WITH 1
	INCREMENT BY 1;

/* TABLAS 
DROP TABLE IF EXISTS hay_uno_repetido;
 
CREATE TABLE hay_uno_repetido (
	id BIGINT NOT NULL,
	name VARCHAR(128) NOT NULL,
	mistakes INTEGER,
	successes INTEGER,
	total_time NUMERIC,
	time_between_successes bytea,
	date_time TIMESTAMP,
	canceled BOOLEAN,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cognitive_domain;
CREATE TABLE cognitive_domain (
	id BIGINT NOT NULL,
	name VARCHAR(128) NOT NULL,
	PRIMARY KEY (id)
);
*/