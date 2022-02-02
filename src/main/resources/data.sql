INSERT INTO planning_state (name) VALUES ('Pendiente'), ('Vigente'), ('Terminada'), ('Cancelada');

INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Nivel Máximo', 'MaxLevel', 0, 'Niveles','Cantidad de aciertos que debe lograr el paciente para que finalice el juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Tiempo Máximo', 'MaximumTime', 0, 'Segundos', 'Tiempo que debe transcurrir para que finalice el juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Tamaño Variable', 'VariableSize', 1, NULL, 'Las figuras varían en tamaño, pudiendo ser más grandes o pequeñas');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Distractores', 'Distractors', 1, NULL, 'Agrega aleatoriamente figuras que no corresponden al conjunto de figuras a utilizar');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Conjunto de Figuras', 'SpriteSet', 2, NULL, 'Conjunto de gráficos a utilizar en las figuras del juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Cantidad Máxima de Figuras', 'FigureQuantity', 3, 'Figuras', 'Cantidad de figuras máximas presentes en el juego');
INSERT INTO game (name,description,param_description) VALUES ('Encuentra al Repetido', 'En este ejercicio, el paciente tiene que encontrar las figuras que se repiten para avanzar. A medida que va avanzando se le muestran nuevas figuras, aumentando progresivamente la dificultad.', 'Se puede cambiar la duración del ejercicio, la cantidad de estímulos se muestran en pantalla, y el conjunto de figuras que se utilizará durante el mismo.');
INSERT INTO game (name,description,param_description) VALUES ('Encuentra al Nuevo', 'En este ejercicio, el paciente tiene que prestar atención a las figuras que hay en pantalla. En cada nivel, las figuras se reordenan y se agrega una nueva. El paciente tiene que reconocer la nueva figura para avanzar.', 'Se puede cambiar la duración del ejercicio y el conjunto de figuras que se utilizará durante el mismo.');

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
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 5);
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
INSERT INTO professional (first_name, last_name, user_name, password) VALUES ('Jorgelina', 'Cordero', 'JCordero', '1234');