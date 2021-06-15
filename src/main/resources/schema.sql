/* SECUENCIA HIBERNATE */
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
	START WITH 1
	INCREMENT BY 1;

/* TABLAS */
DROP TABLE IF EXISTS hay_uno_repetido;
 
CREATE TABLE hay_uno_repetido (
	id INTEGER NOT NULL,
	name VARCHAR(128) NOT NULL,
	mistakes INTEGER,
	successes INTEGER,
	total_time NUMERIC,
	time_between_successes NUMERIC[],
	date_time TIMESTAMP,
	canceled BOOLEAN,
	PRIMARY KEY (id)
);
	
