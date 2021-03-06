INSERT INTO planning_state (name) VALUES ('Pendiente'), ('Vigente'), ('Incompleta'), ('Cancelada'), ('Completada'), ('Completada y Terminada') ;

INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Nivel Máximo', 'MaxLevel', 0, 'Niveles','Cantidad de aciertos que debe lograr el paciente para que finalice el juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Tiempo Máximo', 'MaximumTime', 0, 'Segundos', 'Tiempo que debe transcurrir para que finalice el juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Tamaño Variable', 'VariableSize', 1, NULL, 'Las figuras varían en tamaño, pudiendo ser más grandes o pequeñas');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Distractores', 'Distractors', 1, NULL, 'Agrega aleatoriamente figuras que no corresponden al conjunto de figuras a utilizar');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Conjunto de Figuras', 'SpriteSet', 2, NULL, 'Conjunto de gráficos a utilizar en las figuras del juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Número de filas', 'NumberOfRows', 3, 'Filas', 'Cantidad de filas presentes en la grilla del juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Número de columnas', 'NumberOfColumns', 3, 'Columnas', 'Cantidad de columnas presentes en la grilla del juego');
INSERT INTO param (name, class_name, type, unit, contextual_help) VALUES ('Cantidad Máxima de Estímulos', 'FigureQuantity', 3, 'Estímulos', 'Cantidad máxima de estímulos presentes en el juego');

INSERT INTO game (name,description,param_description) VALUES ('Encuentra al Repetido', 'En este ejercicio, el paciente tiene que encontrar las figuras que se repiten para avanzar. A medida que va avanzando se le muestran nuevas figuras, aumentando progresivamente la dificultad.', 'Se puede cambiar la duración del ejercicio, la cantidad de estímulos se muestran en pantalla, y el conjunto de figuras que se utilizará durante el mismo.');
INSERT INTO game (name,description,param_description) VALUES ('Encuentra al Nuevo', 'En este ejercicio, el paciente tiene que prestar atención a las figuras que hay en pantalla. En cada nivel, las figuras se reordenan y se agrega una nueva. El paciente tiene que reconocer la nueva figura para avanzar.', 'Se puede cambiar la duración del ejercicio y el conjunto de figuras que se utilizará durante el mismo.');
INSERT INTO game (name,description,param_description) VALUES ('Memorilla', 'En este ejercicio, el paciente tiene que prestar atención y recordar las celdas resaltadas en una grilla, luego las mismas se ocultan y el jugador deberá presionar las celdas de la grilla donde se encontraban las resaltadas.', 'Se puede cambiar la cantidad de niveles, el tamaño de la grilla, y la cantidad de estímulos que se muestran en la grilla.');

INSERT INTO cognitive_domain (name) VALUES ('Atención');
INSERT INTO cognitive_domain (name) VALUES ('Memoria');
INSERT INTO cognitive_domain (name) VALUES ('Funciones ejecutivas');
INSERT INTO cognitive_domain (name) VALUES ('Visoespacial');
INSERT INTO cognitive_domain (name) VALUES ('Lenguaje');

INSERT INTO game_param (param_id, min_value, max_value) VALUES (1, 3, 20);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (1, 3, 17);
INSERT INTO game_param (param_id) VALUES (3);
INSERT INTO game_param (param_id) VALUES (4);
INSERT INTO game_param (param_id) VALUES (5);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (8, 3, 20);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (1, 3, 20);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (6, 3, 8);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (7, 3, 6);
INSERT INTO game_param (param_id, min_value, max_value) VALUES (8, 3, 15);

INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 1);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 3);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 4);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 5);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (1, 6);

INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 2);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 3);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (2, 5);

INSERT INTO game_game_param (game_id, game_param_id) VALUES (3, 7);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (3, 8);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (3, 9);
INSERT INTO game_game_param (game_id, game_param_id) VALUES (3, 10);

INSERT INTO param_type2content(name) VALUES ('Flores');
INSERT INTO param_type2content(name) VALUES ('Frutas');

INSERT INTO game_param_param_type2content(param_type2content_id, game_param_id) VALUES (1, 5);
INSERT INTO game_param_param_type2content(param_type2content_id, game_param_id) VALUES (2, 5);

INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (1, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (2, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (2, 2);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (3, 1);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (3, 2);
INSERT INTO game_cognitive_domain (game_id, cognitive_domain_id) VALUES (3, 4);

INSERT INTO patient (born_date, city, description, first_name, last_name, is_logged, is_enabled) VALUES ('1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez',false,true);
INSERT INTO professional (first_name, last_name, user_name, password) VALUES ('Jorgelina', 'Cordero', 'JCordero', '1234');
