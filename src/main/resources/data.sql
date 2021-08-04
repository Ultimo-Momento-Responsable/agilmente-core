INSERT INTO game (id, name) VALUES (hibernate_sequence.nextval, 'HayUnoRepetido');
INSERT INTO cognitive_domain (id, name) VALUES (hibernate_sequence.nextval, 'Procesos Atencionales');
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 2);

INSERT INTO patient (id, born_date, city, description, first_name, last_name) VALUES (hibernate_sequence.nextval, '1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez');
INSERT INTO professional (id, first_name, last_name) VALUES (hibernate_sequence.nextval, 'Jorgelina', 'Cordero');