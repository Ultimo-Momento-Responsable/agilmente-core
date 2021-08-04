INSERT INTO param (id, name, class_name, type) VALUES (hibernate_sequence.nextval,'Cantidad de Figuras', 'FigureQuantity',0);
INSERT INTO param (id, name, class_name, type) VALUES (hibernate_sequence.nextval,'Tiempo Máximo', 'MaximumTime',0);
INSERT INTO game (id, name) VALUES (hibernate_sequence.nextval, 'Encuentra al Repetido');
INSERT INTO game_param (game_id, param_id) VALUES (3, 1);
INSERT INTO game_param (game_id, param_id) VALUES (3, 2);
INSERT INTO cognitive_domain (id, name) VALUES (hibernate_sequence.nextval, 'Procesos Atencionales');
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 2);

INSERT INTO patient (id, born_date, city, description, first_name, last_name,is_logged) VALUES (hibernate_sequence.nextval, '1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez',false);
INSERT INTO professional (id, first_name, last_name) VALUES (hibernate_sequence.nextval, 'Jorgelina', 'Cordero');