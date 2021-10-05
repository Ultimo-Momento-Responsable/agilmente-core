INSERT INTO planning_state (name) VALUES ('Pendiente'), ('Vigente'), ('Terminada'), ('Cancelada');

INSERT INTO param (name, class_name, type) VALUES ('Nivel Maximo', 'MaxLevel', 0);
INSERT INTO param (name, class_name, type) VALUES ('Tiempo Máximo', 'MaximumTime', 0);
INSERT INTO param (name, class_name, type) VALUES ('Tamaño Variable', 'VariableSize', 1);
INSERT INTO param (name, class_name, type) VALUES ('Distractores', 'Distractors', 1);
INSERT INTO param (name, class_name, type) VALUES ('Conjunto de Figuras', 'SpriteSet', 2);
INSERT INTO param (name, class_name, type) VALUES ('Cantidad Máxima de Figuras', 'FigureQuantity', 3);

INSERT INTO game (name) VALUES ('Encuentra al Repetido');
INSERT INTO game (name) VALUES ('Encuentra al Nuevo');

INSERT INTO cognitive_domain (name) VALUES ('Procesos Atencionales');
INSERT INTO cognitive_domain (name) VALUES ('Memoria');

INSERT INTO game_param (param_id, min_value, max_value) VALUES (1, 3, 20);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (2, 15, 120);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (1, 3, 17);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (2, 15, 60);
INSERT INTO game_param (param_id) VALUES (3);
INSERT INTO game_param (param_id) VALUES (4);
INSERT INTO game_param (param_id) VALUES (5);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (6, 3, 20);

INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 1);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 2);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 8);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 3);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 4);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 7);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 5);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 6);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 7);

INSERT INTO param_type2content(name) VALUES ('Flores');
INSERT INTO param_type2content(name) VALUES ('Frutas');

INSERT INTO game_param_param_type2content(param_type2content_id, game_param_id) VALUES (1, 7);
INSERT INTO game_param_param_type2content(param_type2content_id, game_param_id) VALUES (2, 7);

INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (2, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (2, 2);

INSERT INTO patient (born_date, city, description, first_name, last_name, is_logged, is_enabled) VALUES ('1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez',false,true);
INSERT INTO professional (first_name, last_name) VALUES ('Jorgelina', 'Cordero');