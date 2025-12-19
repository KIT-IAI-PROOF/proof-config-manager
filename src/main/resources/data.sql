--
-- PostgreSQL database dump
--

\restrict c0pkyG5taW3e4Eyi6KHElfOHkhSfaJyAncfDmyvi8DsHiJEXBbpaQKUCPyFyvOo

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date, default_step_size, duration, end_point, end_time, start_point, start_time) VALUES ('da06b2d1-53c6-4a0f-bf7b-a6d8ed7c2bff', 'test', '2025-12-19 11:44:42.018047+00', 'test', '2025-12-19 11:44:42.019719+00', 1, 1000, 1000, 1000, 0, 0);


--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date, communication_paradigm, description, label, simulation_strategy, step_based_config_id) VALUES ('6ecdb779-f1f2-437c-8797-ccb1ea63a606', 'test', '2025-12-19 11:43:09.704004+00', 'test', '2025-12-19 11:44:42.019521+00', 0, 'A simple test workflow that reads from a file and writes to another file', 'FileReader-to-FileWriter', 3, 'da06b2d1-53c6-4a0f-bf7b-a6d8ed7c2bff');


--
-- Data for Name: execution; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.execution (id, created_by, creation_date, last_modified_by, last_modified_date, description, interface_type, label, manual, override, process_env, simulation_strategy, started_at, status, stopped_at, workflow_id) VALUES ('b58233f1-cec1-4f55-b7fb-b8beea05c63c', 'test', '2025-12-19 11:47:42.782879+00', 'anonymous', '2025-12-19 11:51:26.581525+00', 'no description', NULL, 'Test Run 1', false, false, NULL, NULL, '2025-12-19T11:47:43.534203175Z', 9, NULL, '6ecdb779-f1f2-437c-8797-ccb1ea63a606');
INSERT INTO public.execution (id, created_by, creation_date, last_modified_by, last_modified_date, description, interface_type, label, manual, override, process_env, simulation_strategy, started_at, status, stopped_at, workflow_id) VALUES ('dab4e86e-3115-42e0-8934-4e9ae28ae607', 'test', '2025-12-19 11:51:40.59863+00', 'anonymous', '2025-12-19 11:51:41.018401+00', 'no description', NULL, 'Test Run 1', false, false, NULL, NULL, '2025-12-19T11:51:40.994081174Z', 4, NULL, '6ecdb779-f1f2-437c-8797-ccb1ea63a606');


--
-- Data for Name: applied_inputs; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.applied_inputs (execution_dao_id, value, id) VALUES ('b58233f1-cec1-4f55-b7fb-b8beea05c63c', 'input.txt', '8c14827e-8cd6-454c-a04f-552a188c4253');
INSERT INTO public.applied_inputs (execution_dao_id, value, id) VALUES ('b58233f1-cec1-4f55-b7fb-b8beea05c63c', 'output.txt', 'f664932f-78f2-48a8-aefa-289c53878c87');
INSERT INTO public.applied_inputs (execution_dao_id, value, id) VALUES ('dab4e86e-3115-42e0-8934-4e9ae28ae607', 'input.txt', '8c14827e-8cd6-454c-a04f-552a188c4253');
INSERT INTO public.applied_inputs (execution_dao_id, value, id) VALUES ('dab4e86e-3115-42e0-8934-4e9ae28ae607', 'output.txt', 'f664932f-78f2-48a8-aefa-289c53878c87');


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('id_attachment_file_reader', 'anonymous', '2025-12-19 10:37:54.516553+00', 'anonymous', '2025-12-19 10:37:54.557788+00', 'provide the workflow with lines from a given file for each SYNC', 'attachment_file_reader', 'file_reader.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('id_attachment_float_2_string', 'anonymous', '2025-12-19 10:37:59.663985+00', 'anonymous', '2025-12-19 10:37:59.709866+00', 'convert a float input to a string output without waiting for sync', 'attachment_float_2_string', 'float_2_string_converter.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('id_attachment_string_2_float', 'anonymous', '2025-12-19 10:38:04.437768+00', 'anonymous', '2025-12-19 10:38:04.482344+00', 'convert a string input to a float output without waiting for sync', 'attachment_string_2_float', 'string_2_float_converter.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('id_proc_time_sim_attachment', 'anonymous', '2025-12-19 10:38:09.405975+00', 'anonymous', '2025-12-19 10:38:09.451126+00', 'A simple block that simulates a long-lasting processing time.', 'proc_time_sim_attachment', 'proc_time_sim.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('id_attachment_tss_writer_4_values', 'anonymous', '2025-12-19 10:38:14.060124+00', 'anonymous', '2025-12-19 10:38:14.106894+00', 'write/post 4 values as time series data to the url of TSS', 'attachment_file_writer', 'tss_writer_4_values.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('attachment_file_writer', 'anonymous', '2025-04-23 12:59:49.855+00', 'anonymous', '2025-12-17 10:32:38.238484+00', 'Write data to a file', 'file_writer.py', '/tmp/proof/file_writer.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('attachment_file_line_provider', 'anonymous', '2025-04-23 12:59:46.722+00', 'anonymous', '2025-12-17 10:32:38.237847+00', 'Provide other blocks with one line per SYNC from a given file', 'file_line_provider.py', '/tmp/proof/file_line_provider.py');


--
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'anonymous', '2025-04-23 12:59:46.713+00', 'anonymous', '2025-12-17 10:32:38.237731+00', 'Provide other blocks with one line per SYNC from a given file', 'attachment_file_line_provider', 'prog_file_line_provider', 0, 'testFLP');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'anonymous', '2025-04-23 12:59:49.853+00', 'anonymous', '2025-12-17 10:32:38.238378+00', 'Write data to a file', 'attachment_file_writer', 'prog_file_writer', 0, 'testFLP');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('id_prog_file_reader', 'anonymous', '2025-12-19 10:37:54.516553+00', 'anonymous', '2025-12-19 10:37:54.557732+00', 'provide the workflow with lines from a given file for each SYNC', 'id_attachment_file_reader', 'prog_file_reader', 0, 'fileReader');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('id_prog_float_2_string', 'anonymous', '2025-12-19 10:37:59.663985+00', 'anonymous', '2025-12-19 10:37:59.709808+00', 'convert a float input to a string output without waiting for sync', 'id_attachment_float_2_string', 'prog_float_2_string', 0, 'float2string');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('id_prog_string_2_float', 'anonymous', '2025-12-19 10:38:04.437768+00', 'anonymous', '2025-12-19 10:38:04.482285+00', 'convert a float input to a string output without waiting for sync', 'id_attachment_string_2_float', 'prog_string_2_float', 0, 'float2string');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('id_proc_time_sim_program', 'anonymous', '2025-12-19 10:38:09.405975+00', 'anonymous', '2025-12-19 10:38:09.451069+00', 'A simple block that simulates a long-lasting processing time.', 'id_proc_time_sim_attachment', 'proc_time_sim_program', 0, 'procTimeSim');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('id_prog_tss_writer_4_values', 'anonymous', '2025-12-19 10:38:14.060124+00', 'anonymous', '2025-12-19 10:38:14.106844+00', 'post up to 4 values to the time series service TSS', 'id_attachment_tss_writer_4_values', 'prog_tss_writer_4_values', 0, 'tss');


--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x, y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id) VALUES ('d103cc06-8a5a-4297-a85d-b78907e0bf7d', 'test', '2025-12-19 11:46:35.180839+00', 'test', '2025-12-19 11:46:43.581896+00', 3, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'provide the workflow with lines from a given file for each SYNC', 1, 1, 'File Reader', 319, 306, false, NULL, 0, 'id_template_file_reader', 'File Reader', 'block', 'id_prog_file_reader', '6ecdb779-f1f2-437c-8797-ccb1ea63a606');
INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x, y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id) VALUES ('d47590e5-bb19-42f1-8a4d-aca9372c9889', 'test', '2025-12-19 11:46:35.194139+00', 'test', '2025-12-19 11:46:43.582151+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'Write data to a file', 2, 1, 'File Writer', 548, 306, true, NULL, 0, '4ad32c27-23c5-4e07-9e75-50a6549e82de', 'File Writer', 'block', '7554873a-813c-4e00-b8d3-be072f12bea8', '6ecdb779-f1f2-437c-8797-ccb1ea63a606');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id) VALUES ('dda3706d-81c0-4536-92cc-57bf84b2e220', 'test', '2025-12-19 11:46:35.199972+00', 'test', '2025-12-19 11:46:43.581628+00', 'bezier-catmull-rom', NULL, 'STRING', '7723bb02-c997-4a3a-b8f5-39b03e7e9d39', 'acf5b11e-31e9-4c07-93ce-620673d3f768', 'd103cc06-8a5a-4297-a85d-b78907e0bf7d', 'd47590e5-bb19-42f1-8a4d-aca9372c9889', 'block', '6ecdb779-f1f2-437c-8797-ccb1ea63a606');


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('id_template_float_2_string', 'anonymous', '2025-12-19 10:37:59.663985+00', 'anonymous', '2025-12-19 10:37:59.709632+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'float to string converter that does not wait for sync', 1, 'Float2String', false, NULL, 0, 'block', 'id_prog_float_2_string');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('id_template_string_2_float', 'anonymous', '2025-12-19 10:38:04.437768+00', 'anonymous', '2025-12-19 10:38:04.482104+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'string to float converter that does not wait for sync', 1, 'String2Float', false, NULL, 0, 'block', 'id_prog_string_2_float');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('id_ProcTimeSim', 'anonymous', '2025-12-19 10:38:09.405975+00', 'anonymous', '2025-12-19 10:38:09.450884+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0l', 'A simple block that simulates a long-lasting processing time.', 1, 'ProcTimeSim', false, NULL, 0, 'block', 'id_proc_time_sim_program');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('id_template_tss_writer_4_values', 'anonymous', '2025-12-19 10:38:14.060124+00', 'anonymous', '2025-12-19 10:38:14.106589+00', 3, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'Write up to 4 values to the Time Series Service', 1, 'TSS writer 4 Vals', false, NULL, 0, 'block', 'id_prog_tss_writer_4_values');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('id_template_file_reader', 'anonymous', '2025-12-19 10:37:54.516553+00', 'anonymous', '2025-12-19 10:37:54.557551+00', 3, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'provide the workflow with lines from a given file for each SYNC', 1, 'File Reader', false, NULL, 0, 'block', 'id_prog_file_reader');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('365d5380-54eb-4b40-a377-b4921d7370c6', 'anonymous', '2025-12-17 08:32:38.205558+00', 'anonymous', '2025-12-17 09:32:38.239927+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'Provide lines from a file', 1, 'File Provider', true, NULL, 0, 'block', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('4ad32c27-23c5-4e07-9e75-50a6549e82de', 'anonymous', '2025-12-17 08:32:38.224959+00', 'anonymous', '2025-12-17 09:32:38.240161+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'Write data to a file', 1, 'File Writer', true, NULL, 0, 'block', '7554873a-813c-4e00-b8d3-be072f12bea8');


--
-- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('a89eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-12-17 10:32:38.209819+00', 'anonymous', '2025-12-17 10:32:38.209819+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('v2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-12-17 10:32:38.212961+00', 'anonymous', '2025-12-17 10:32:38.212961+00', 3, 'Input: num_steps', 'num_steps', 'num_steps', 1, 1, NULL, false, '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('109cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-12-17 10:32:38.216065+00', 'anonymous', '2025-12-17 10:32:38.216065+00', 3, 'Input: ignore_first_line', 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('ed25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-12-17 10:32:38.226422+00', 'anonymous', '2025-12-17 10:32:38.226422+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('ra76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-12-17 10:32:38.227956+00', 'anonymous', '2025-12-17 10:32:38.227956+00', 3, 'Input: mode', 'mode', 'mode', 1, 0, NULL, false, '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('bad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-12-17 10:32:38.229281+00', 'anonymous', '2025-12-17 10:32:38.229281+00', 3, 'Input: waitForSync', 'waitForSync', 'waitForSync', 1, 0, NULL, false, '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('l71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-12-17 10:32:38.230939+00', 'anonymous', '2025-12-17 10:32:38.230939+00', 0, 'Input: data', 'data', 'data', 2, 0, NULL, false, '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('408a9092-defc-40be-ac4d-cc3aae085204', 'anonymous', '2025-12-19 10:37:54.557671+00', 'anonymous', '2025-12-19 10:37:54.557671+00', 3, NULL, 'file_name', 'file_name', 1, 0, NULL, true, 'id_template_file_reader', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('b4fd2093-9042-4d7c-b78d-608ce7a2c1cc', 'anonymous', '2025-12-19 10:37:54.557675+00', 'anonymous', '2025-12-19 10:37:54.557675+00', 3, NULL, 'num_steps', 'num_steps', 1, 1, NULL, false, 'id_template_file_reader', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('6af98fe6-16f0-4c58-bf35-571975d93c99', 'anonymous', '2025-12-19 10:37:54.557676+00', 'anonymous', '2025-12-19 10:37:54.557676+00', 3, NULL, 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, 'id_template_file_reader', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('999097db-ff04-4807-85d1-61e784681e02', 'anonymous', '2025-12-19 10:37:59.709749+00', 'anonymous', '2025-12-19 10:37:59.709749+00', 0, 'input as Float', 'float_input', 'float_input', 2, 2, NULL, false, 'id_template_float_2_string', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('ab7ce554-e1eb-4cb0-b1c8-4042f6e60740', 'anonymous', '2025-12-19 10:38:04.482218+00', 'anonymous', '2025-12-19 10:38:04.482218+00', 0, 'input as String', 'string_input', 'string_input', 2, 0, NULL, false, 'id_template_string_2_float', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('33fcb075-c53b-4131-86e6-86a974c4da58', 'anonymous', '2025-12-19 10:38:09.451004+00', 'anonymous', '2025-12-19 10:38:09.451004+00', 3, 'virtual processing time in seconds as Float, default is 1.0', 'proc_time', 'proc_time', 2, 2, NULL, false, 'id_ProcTimeSim', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('3289f7c3-8e86-4951-80e1-c4e3ba09d48d', 'anonymous', '2025-12-19 10:38:09.451008+00', 'anonymous', '2025-12-19 10:38:09.451008+00', 0, 'optional input as String', 'opt_input', 'opt_input', 2, 0, NULL, false, 'id_ProcTimeSim', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('49eb0a78-5594-4a34-8e75-3b446a065b7c', 'anonymous', '2025-12-19 10:38:14.106782+00', 'anonymous', '2025-12-19 10:38:14.106782+00', 3, NULL, 'tss_writer_url', 'tss_writer_url', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('956f6ff1-9a90-4067-8d16-f1d368503153', 'anonymous', '2025-12-19 10:38:14.106785+00', 'anonymous', '2025-12-19 10:38:14.106785+00', 3, NULL, 'tags', 'tags', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('9ae970ac-58fe-464c-985b-e2301da9b668', 'anonymous', '2025-12-19 10:38:14.106786+00', 'anonymous', '2025-12-19 10:38:14.106786+00', 3, NULL, 'valName_1', 'valName_1', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('f4538c24-7fc7-4e3e-b4ef-826c67cd7566', 'anonymous', '2025-12-19 10:38:14.106786+00', 'anonymous', '2025-12-19 10:38:14.106786+00', 3, NULL, 'valName_2', 'valName_2', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('12f33010-1f8d-498b-9bf4-26c569af1d02', 'anonymous', '2025-12-19 10:38:14.106787+00', 'anonymous', '2025-12-19 10:38:14.106787+00', 3, NULL, 'valName_3', 'valName_3', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('29961b3c-7482-4712-bb48-46e030cb6417', 'anonymous', '2025-12-19 10:38:14.106787+00', 'anonymous', '2025-12-19 10:38:14.106787+00', 3, NULL, 'valName_4', 'valName_4', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('7d2b7dc9-3b6b-4f76-8096-20545c7f248b', 'anonymous', '2025-12-19 10:38:14.106787+00', 'anonymous', '2025-12-19 10:38:14.106787+00', 3, NULL, 'waitForSync', 'waitForSync', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('0249b8d9-0988-4087-a432-cccc35cc0d5c', 'anonymous', '2025-12-19 10:38:14.106788+00', 'anonymous', '2025-12-19 10:38:14.106788+00', 3, NULL, 'timeUnit', 'timeUnit', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('3176cadf-284f-4b21-9397-66f50271b191', 'anonymous', '2025-12-19 10:38:14.106788+00', 'anonymous', '2025-12-19 10:38:14.106788+00', 3, NULL, 'resetInput', 'resetInput', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('7eca2724-0594-4356-b3ac-15428c09aab6', 'anonymous', '2025-12-19 10:38:14.106788+00', 'anonymous', '2025-12-19 10:38:14.106788+00', 0, NULL, 'value_1', 'value_1', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('44984652-0344-4ae5-b0c9-cc4211b18a13', 'anonymous', '2025-12-19 10:38:14.106788+00', 'anonymous', '2025-12-19 10:38:14.106788+00', 0, NULL, 'value_2', 'value_2', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('3c81f522-d123-4fb2-8ed4-b7516afc9022', 'anonymous', '2025-12-19 10:38:14.106789+00', 'anonymous', '2025-12-19 10:38:14.106789+00', 0, NULL, 'value_3', 'value_3', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('c8efcab5-d88b-46b6-84f5-4acf1165235b', 'anonymous', '2025-12-19 10:38:14.106789+00', 'anonymous', '2025-12-19 10:38:14.106789+00', 0, NULL, 'value_4', 'value_4', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('45e63dcf-629e-4a10-8e0c-7f7c992921e9', 'anonymous', '2025-12-19 10:38:14.106789+00', 'anonymous', '2025-12-19 10:38:14.106789+00', 3, NULL, 'writeCP', 'writeCP', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('8c14827e-8cd6-454c-a04f-552a188c4253', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019788+00', 3, NULL, 'file_name', 'file_name', 1, 0, NULL, true, NULL, 'd103cc06-8a5a-4297-a85d-b78907e0bf7d');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('64930138-84ee-49be-a9e5-6c580beaaf66', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019956+00', 3, NULL, 'num_steps', 'num_steps', 1, 1, NULL, false, NULL, 'd103cc06-8a5a-4297-a85d-b78907e0bf7d');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('f664932f-78f2-48a8-aefa-289c53878c87', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.018983+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, NULL, 'd47590e5-bb19-42f1-8a4d-aca9372c9889');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('8f4f07ec-0ceb-412a-8f4e-659ab72594cd', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019167+00', 3, 'Input: mode', 'mode', 'mode', 1, 0, NULL, false, NULL, 'd47590e5-bb19-42f1-8a4d-aca9372c9889');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('cc3f0257-03b6-46c1-b193-abff9ae76ad0', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019319+00', 3, 'Input: waitForSync', 'waitForSync', 'waitForSync', 1, 0, NULL, false, NULL, 'd47590e5-bb19-42f1-8a4d-aca9372c9889');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('7723bb02-c997-4a3a-b8f5-39b03e7e9d39', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019472+00', 0, 'Input: data', 'data', 'data', 2, 0, NULL, false, NULL, 'd47590e5-bb19-42f1-8a4d-aca9372c9889');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id, block_id) VALUES ('ca09d437-8cd4-4b2c-8403-e51bfcac398c', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.020121+00', 3, NULL, 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, NULL, 'd103cc06-8a5a-4297-a85d-b78907e0bf7d');


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('7000cc5d-637d-4c7f-b961-a9c9e797c50b', 'anonymous', '2025-12-17 10:32:38.220033+00', 'anonymous', '2025-12-17 10:32:38.220033+00', 0, 'Output: line', 'line', NULL, 2, 0, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('465d4648-6815-4aa6-b7cf-47b091e280d3', 'anonymous', '2025-12-19 10:37:54.55773+00', 'anonymous', '2025-12-19 10:37:54.55773+00', 0, NULL, 'line', 'line', 2, 0, NULL, 'id_template_file_reader', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('5e05ab77-7342-49d9-ba58-7034a69e425d', 'anonymous', '2025-12-19 10:37:59.709806+00', 'anonymous', '2025-12-19 10:37:59.709806+00', 0, 'output as String', 'string_output', 'string_output', 2, 0, NULL, 'id_template_float_2_string', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('53ad40d2-c66a-4572-b1db-dd7768608088', 'anonymous', '2025-12-19 10:38:04.482283+00', 'anonymous', '2025-12-19 10:38:04.482283+00', 0, 'output as Float', 'float_output', 'float_output', 2, 2, NULL, 'id_template_string_2_float', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('83b6e3ff-3236-4559-b4f9-5e6130efcd93', 'anonymous', '2025-12-19 10:38:09.451068+00', 'anonymous', '2025-12-19 10:38:09.451068+00', 0, 'optional output as String', 'opt_output', 'opt_output', 2, 0, NULL, 'id_ProcTimeSim', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id) VALUES ('acf5b11e-31e9-4c07-93ce-620673d3f768', NULL, NULL, 'anonymous', '2025-12-19 11:51:41.019635+00', 0, NULL, 'line', 'line', 2, 0, NULL, NULL, 'd103cc06-8a5a-4297-a85d-b78907e0bf7d');


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: program_attached; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'attachment_file_line_provider');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('id_prog_float_2_string', 'id_attachment_float_2_string');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('id_prog_string_2_float', 'id_attachment_string_2_float');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('id_proc_time_sim_program', 'id_proc_time_sim_attachment');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('id_prog_tss_writer_4_values', 'id_attachment_tss_writer_4_values');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('id_prog_file_reader', 'id_attachment_file_reader');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'attachment_file_writer');


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- PostgreSQL database dump complete
--

\unrestrict c0pkyG5taW3e4Eyi6KHElfOHkhSfaJyAncfDmyvi8DsHiJEXBbpaQKUCPyFyvOo

