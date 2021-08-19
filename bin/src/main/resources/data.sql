INSERT INTO param (name, class_name, type) VALUES ('Cantidad de Figuras', 'FigureQuantity',0);
INSERT INTO param (name, class_name, type) VALUES ('Tiempo Máximo', 'MaximumTime',0);
INSERT INTO game (name) VALUES ('Encuentra al Repetido');
INSERT INTO cognitive_domain (name) VALUES ('Procesos Atencionales');
INSERT INTO game_param (game_id, param_id) VALUES (1, 1);
INSERT INTO game_param (game_id, param_id) VALUES (1, 2);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 1);

INSERT INTO patient (born_date, city, description, first_name, last_name,is_logged) VALUES ('1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez',false);
INSERT INTO professional (first_name, last_name) VALUES ('Jorgelina', 'Cordero');