INSERT INTO planning_state (name) VALUES ('Pendiente'), ('Vigente'), ('Incompleta'), ('Cancelada'), ('Vigente con juegos libres'), ('Completada') ;

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

INSERT INTO patient (born_date, city, description, first_name, last_name, is_logged, is_enabled, medals, trophies) 
    VALUES ('1996-11-24 00:00:00', 'Villa María', null, 'Fake', 'Patient', false, false, 0, 0),
    ('1996-11-24 00:00:00', 'Villa María', null, 'Julián', 'Marquez', false, true, 0, 0);

INSERT INTO professional (first_name, last_name, user_name, password, email) VALUES 
	('Jorgelina', 'Cordero', 'JCordero', '$2a$10$bpxx9CYUVRZonSyBCwpmnujTyQgbsDaT/QRgEM5tXhVWkwPFgfU16', '');

INSERT INTO planning (name, creation_datetime, due_date, start_date, patient_id, professional_id, state_id, mgp) 
    VALUES ('Planificación de Julián Marquez', '2022-09-18 18:14:30.762', '2022-09-19 00:00:00', '2022-09-18 00:00:00', 1, 1, 6, 1250);

--
-- RESULTS 
--
INSERT INTO hay_uno_repetido_result (canceled, complete_datetime, mgp, mistakes, score, successes, time_between_successes, total_time)
    VALUES (false, '2022-09-19 18:16:06', 0, 0, 210, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:07', 2500, 0, 500, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:08', 0, 0, 650, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:09', 2500, 0, 1000, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:10', 0, 0, 1000, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:11', 2500, 0, 1550, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:12', 0, 0, 1100, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:13', 2500, 0, 2050, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:14', 0, 0, 1180, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634),
    (false, '2022-09-19 18:16:15', 2500, 0, 2100, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033f33e6403f56023f3f199e81', 8.634);

INSERT INTO encuentra_al_nuevo_result (canceled, complete_datetime, mgp, mistakes, score, successes, time_between_successes, total_time) 
    VALUES (false, '2022-09-19 18:15:52', 0, 0, 100, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:53', 2500, 0, 500, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:54', 0, 0, 470, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:55', 2500, 0, 710, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:56', 0, 0, 620, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:57', 2500, 0, 1000, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:58', 0, 0, 750, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:15:59', 2500, 0, 1400, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:16:00', 0, 0, 750, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482),
    (false, '2022-09-19 18:16:01', 2500, 0, 1750, 3, '\xaced0005757200025b460b9c818922e00c420200007870000000033fa8157f406602003fc966f1', 6.480482);

INSERT INTO memorilla_result (canceled, complete_datetime, mgp, mistakes_per_level, score, streak, successes_per_level, time_per_level, total_time) 
    VALUES (false, '2022-09-19 18:16:41', 0, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 33, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 2500, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 198, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 0, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 155, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 2500, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 545, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 0, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 266, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 2500, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 1030, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 0, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 360, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 2500, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 1487, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 0, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 600, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192),
    (false, '2022-09-19 18:16:41', 2500, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000000000000000000000', 2180, 3, '\xaced0005757200025b494dba602676eab2a5020000787000000003000000030000000300000003', '\xaced0005757200025b460b9c818922e00c42020000787000000003408994e83f63930040ac97c8', 10.58192);

--
-- SESSION PARAMS
--
INSERT INTO distractors (distractors)
    VALUES (false),
    (false),
    (true),
    (false),
    (true);

INSERT INTO figure_quantity (figure_quantity) 
    VALUES (3),
    (9),
    (12),
    (17),
    (20),
    (3),
    (5),
    (7),
    (9),
    (10);

INSERT INTO max_level (max_level) 
    VALUES (3),
    (7),
    (10),
    (14),
    (17),
    (3),
    (10),
    (15),
    (20),
    (20),
    (3),
    (5),
    (7),
    (8),
    (10);

INSERT INTO number_of_columns (number_of_columns) 
    VALUES (3),
    (5),
    (5),
    (6),
    (6);

INSERT INTO number_of_rows (number_of_rows)
    VALUES (3),
    (5),
    (6),
    (6),
    (8);

INSERT INTO sprite_set (sprite_set) 
    VALUES (2),
    (2),
    (2),
    (1),
    (1),
    (2),
    (2),
    (2),
    (1),
    (1);

INSERT INTO variable_size (variable_size) 
    VALUES (false),
    (false),
    (true),
    (false),
    (true),
    (false),
    (false),
    (false),
    (true),
    (true);

INSERT INTO figure_quantity (figure_quantity) 
    VALUES (3),
    (9),
    (12),
    (17),
    (20),
    (3),
    (5),
    (7),
    (9),
    (10);

--
-- SESSIONS
--
INSERT INTO hay_uno_repetido_session (max_level_id, game_id, maximum_time_id, sprite_set_id, variable_size_id, figure_quantity_id, distractors_id) 
    VALUES (6, 1, NULL, 6, 6, 1, 1),
    (7, 1, NULL, 7, 7, 2, 2),
    (8, 1, NULL, 8, 8, 3, 3),
    (9, 1, NULL, 9, 9, 4, 4),
    (10, 1, NULL, 10, 10, 5, 5);

INSERT INTO encuentra_al_nuevo_session (game_id, max_level_id, maximum_time_id, sprite_set_id, variable_size_id) 
    VALUES (2, 1, NULL, 1, 1),
    (2, 2, NULL, 2, 2),
    (2, 3, NULL, 3, 3),
    (2, 4, NULL, 4, 4),
    (2, 5, NULL, 5, 5);


INSERT INTO memorilla_session (figure_quantity_id, game_id, max_level_id, number_of_columns_id, number_of_rows_id) 
    VALUES (6, 3, 11, 1, 1),
    (7, 3, 12, 2, 2),
    (8, 3, 13, 3, 3),
    (9, 3, 14, 4, 4),
    (10, 3, 15, 5, 5);

--
-- SESSION - RESULTS RELATION
--
INSERT INTO hay_uno_repetido_session_results (hay_uno_repetido_session_id, results_id)
    VALUES (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10);

INSERT INTO encuentra_al_nuevo_session_results (encuentra_al_nuevo_session_id, results_id)
    VALUES (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10);

INSERT INTO memorilla_session_results (memorilla_session_id, results_id)
    VALUES (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10);

--
-- PLANNING DETAIL (SESSION - PLANNING RELATION)
--
INSERT INTO planning_detail (max_number_of_sessions, number_of_sessions, hay_uno_repetido_session_id, planning_id, difficulty, encuentra_al_nuevo_session_id, memorilla_session_id)
    VALUES (-1, -1, NULL, 1, 'MuyFacil', 1, NULL),
    (-1, -1, NULL, 1, 'Facil', 2, NULL),
    (-1, -1, NULL, 1, 'Intermedia', 3, NULL),
    (-1, -1, NULL, 1, 'Dificil', 4, NULL),
    (-1, -1, NULL, 1, 'MuyDificil', 5, NULL),
    (-1, -1, 1, 1, 'MuyFacil', NULL, NULL),
    (-1, -1, 2, 1, 'Facil', NULL, NULL),
    (-1, -1, 3, 1, 'Intermedia', NULL, NULL),
    (-1, -1, 4, 1, 'Dificil', NULL, NULL),
    (-1, -1, 5, 1, 'MuyDificil', NULL, NULL),
    (-1, -1, NULL, 1, 'MuyFacil', NULL, 1),
    (-1, -1, NULL, 1, 'Facil', NULL, 2),
    (-1, -1, NULL, 1, 'Intermedia', NULL, 3),
    (-1, -1, NULL, 1, 'Dificil', NULL, 4),
    (-1, -1, NULL, 1, 'MuyDificil', NULL, 5);
