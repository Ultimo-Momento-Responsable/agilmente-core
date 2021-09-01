INSERT INTO planning_state (name) VALUES ('Pendiente'), ('Vigente'), ('Terminada'), ('Cancelada');
INSERT INTO param (name, class_name, type, min_value, max_value) VALUES ('Cantidad de Figuras', 'FigureQuantity', 0, 3, 20);
INSERT INTO param (name, class_name, type, min_value, max_value) VALUES ('Tiempo Máximo', 'MaximumTime', 0, 1, -1);
INSERT INTO game (name) VALUES ('Encuentra al Repetido');
INSERT INTO game (name) VALUES ('Encuentra al Nuevo');
INSERT INTO cognitive_domain (name) VALUES ('Procesos Atencionales');
INSERT INTO game_param (game_id, param_id) VALUES (1, 1);
INSERT INTO game_param (game_id, param_id) VALUES (1, 2);
INSERT INTO game_param (game_id, param_id) VALUES (2, 1);
INSERT INTO game_param (game_id, param_id) VALUES (2, 2);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (2, 1);

INSERT INTO patient (born_date, city, description, first_name, last_name,is_logged) VALUES ('1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez',false);
INSERT INTO professional (first_name, last_name) VALUES ('Jorgelina', 'Cordero');