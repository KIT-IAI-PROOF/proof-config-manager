--
-- PostgreSQL database dump
--

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.7 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date, communication_paradigm, description, label, simulation_strategy) VALUES ('11f02ad7-08d0-4f92-b911-f70f969a6ba6', 'anonymous', '2025-12-17 10:32:38.13094+00', 'anonymous', '2025-12-17 10:32:38.23622+00', 0, 'FileReader2FileWriter', 'FileReader2FileWriter', 3);


--
-- Data for Name: execution; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: applied_inputs; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('attachment_file_line_provider', 'anonymous', '2025-04-23 12:59:46.722+00', 'anonymous', '2025-12-17 10:32:38.237847+00', 'Provide other blocks with one line per SYNC from a given file', 'file_line_provider.py', '/proof/latest:file_line_provider.py');
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path) VALUES ('attachment_file_writer', 'anonymous', '2025-04-23 12:59:49.855+00', 'anonymous', '2025-12-17 10:32:38.238484+00', 'Write data to a file', 'file_writer.py', '/proof/latest:file_writer.py');


--
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'test', '2025-04-23 12:59:46.713+00', 'anonymous', '2025-12-17 10:32:38.237731+00', 'Provide other blocks with one line per SYNC from a given file', 'attachment_file_line_provider', 'prog_file_line_provider', 0, 'testFLP');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag) VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'test', '2025-04-23 12:59:49.853+00', 'anonymous', '2025-12-17 10:32:38.238378+00', 'Write data to a file', 'attachment_file_writer', 'prog_file_writer', 0, 'testFLP');


--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x, y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id) VALUES ('bed2ac71-2575-462b-9b73-048e5f60388a', 'anonymous', '2025-12-17 10:32:38.150792+00', 'anonymous', '2025-12-17 10:32:38.237996+00', 0, '#0D41E1', 0, 'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Provide lines from a file', 1, 1, 'File Provider', 0, 10, true, 0, 0, '365d5380-54eb-4b40-a377-b4921d7370c6', 'File Provider', 'block', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');
INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x, y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id) VALUES ('da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'anonymous', '2025-12-17 10:32:38.173622+00', 'anonymous', '2025-12-17 10:32:38.238597+00', 0, '#0D41E1', 0, 'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Write data to a file', 2, 1, 'File Writer', 200, 0, true, 0, 0, '4ad32c27-23c5-4e07-9e75-50a6549e82de', 'File Writer', 'block', '7554873a-813c-4e00-b8d3-be072f12bea8', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id) VALUES ('43179bda-c044-4ab9-9e68-fbfa8602a79e', 'anonymous', '2025-12-17 10:32:38.18917+00', 'anonymous', '2025-12-17 10:32:38.238951+00', 'bezier-catmull-rom', NULL, 'STRING', 'f71ba36a-cb03-4353-adeb-a2d6985932e0', '7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'bed2ac71-2575-462b-9b73-048e5f60388a', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'block', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('365d5380-54eb-4b40-a377-b4921d7370c6', 'anonymous', '2025-12-17 10:32:38.205558+00', 'anonymous', '2025-12-17 10:32:38.239927+00', 0, '#0D41E1', 0, 'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Provide lines from a file', 1, 'File Provider', true, NULL, 0, 'block', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29');
INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, type, program_id) VALUES ('4ad32c27-23c5-4e07-9e75-50a6549e82de', 'anonymous', '2025-12-17 10:32:38.224959+00', 'anonymous', '2025-12-17 10:32:38.240161+00', 0, '#0D41E1', 0, 'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Write data to a file', 1, 'File Writer', true, NULL, 0, 'block', '7554873a-813c-4e00-b8d3-be072f12bea8');


--
-- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('689eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-12-17 10:32:38.15648+00', 'anonymous', '2025-12-17 10:32:38.15648+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('e2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-12-17 10:32:38.159338+00', 'anonymous', '2025-12-17 10:32:38.159338+00', 3, 'Input: num_steps', 'num_steps', 'num_steps', 1, 1, NULL, false, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('509cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-12-17 10:32:38.162328+00', 'anonymous', '2025-12-17 10:32:38.162328+00', 3, 'Input: ignore_first_line', 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('cd25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-12-17 10:32:38.175866+00', 'anonymous', '2025-12-17 10:32:38.175866+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('ca76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-12-17 10:32:38.178344+00', 'anonymous', '2025-12-17 10:32:38.178344+00', 3, 'Input: mode', 'mode', 'mode', 1, 0, NULL, false, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('1ad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-12-17 10:32:38.180682+00', 'anonymous', '2025-12-17 10:32:38.180682+00', 3, 'Input: waitForSync', 'waitForSync', 'waitForSync', 1, 0, NULL, false, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('f71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-12-17 10:32:38.1826+00', 'anonymous', '2025-12-17 10:32:38.1826+00', 0, 'Input: data', 'data', 'data', 2, 0, NULL, false, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('a89eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-12-17 10:32:38.209819+00', 'anonymous', '2025-12-17 10:32:38.209819+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('v2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-12-17 10:32:38.212961+00', 'anonymous', '2025-12-17 10:32:38.212961+00', 3, 'Input: num_steps', 'num_steps', 'num_steps', 1, 1, NULL, false, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('109cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-12-17 10:32:38.216065+00', 'anonymous', '2025-12-17 10:32:38.216065+00', 3, 'Input: ignore_first_line', 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('ed25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-12-17 10:32:38.226422+00', 'anonymous', '2025-12-17 10:32:38.226422+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL, true, NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('ra76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-12-17 10:32:38.227956+00', 'anonymous', '2025-12-17 10:32:38.227956+00', 3, 'Input: mode', 'mode', 'mode', 1, 0, NULL, false, NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('bad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-12-17 10:32:38.229281+00', 'anonymous', '2025-12-17 10:32:38.229281+00', 3, 'Input: waitForSync', 'waitForSync', 'waitForSync', 1, 0, NULL, false, NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de');
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id, template_id) VALUES ('l71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-12-17 10:32:38.230939+00', 'anonymous', '2025-12-17 10:32:38.230939+00', 0, 'Input: data', 'data', 'data', 2, 0, NULL, false, NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de');


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, block_id, template_id) VALUES ('7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'anonymous', '2025-12-17 10:32:38.167706+00', 'anonymous', '2025-12-17 10:32:38.167706+00', 0, 'Output: line', 'line', NULL, 2, 0, NULL, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, block_id, template_id) VALUES ('7000cc5d-637d-4c7f-b961-a9c9e797c50b', 'anonymous', '2025-12-17 10:32:38.220033+00', 'anonymous', '2025-12-17 10:32:38.220033+00', 0, 'Output: line', 'line', NULL, 2, 0, NULL, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6');


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: program_attached; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'attachment_file_line_provider');
INSERT INTO public.program_attached (program_id, attachment_id) VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'attachment_file_writer');


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date, default_step_size, duration, end_point, end_time, start_point, start_time, workflow_id) VALUES ('e4b90ac9-6d78-441b-9762-87af0114115f', 'anonymous', '2025-12-17 10:32:38.193114+00', 'anonymous', '2025-12-17 10:32:38.239789+00', 1, 500, 10, 1000, 1, 1, '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--



--
-- PostgreSQL database dump complete
--