--
-- PostgreSQL database dump
--

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.7 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date, communication_paradigm, description, label, simulation_strategy)
VALUES ('11f02ad7-08d0-4f92-b911-f70f969a6ba6', 'anonymous', '2025-12-17 10:09:30.89615+00', 'anonymous', '2025-12-17 10:09:30.958484+00', 0, 'FileReader2FileWriter', 'FileReader2FileWriter', 3)
ON CONFLICT DO NOTHING;


--
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'test', '2025-04-23 14:59:46.713+00', 'anonymous', '2025-12-17 10:09:30.961614+00', 'Provide other blocks with one line per SYNC from a given file',
        'attachment_file_line_provider', 'prog_file_line_provider', 0, 'testFLP');
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'test', '2025-04-23 14:59:49.853+00', 'anonymous', '2025-12-17 10:09:30.961759+00', 'Write data to a file', 'attachment_file_writer',
        'prog_file_writer', 0, 'testFLP');

--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
VALUES ('attachment_file_line_provider', 'anonymous', '2025-04-23 14:59:46.722+00', 'anonymous', '2025-12-17 10:09:30.960576+00', 'Provide other blocks with one line per SYNC from a given file',
        'file_line_provider.py', '/proof/latest:file_line_provider.py')
ON CONFLICT DO NOTHING;
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
VALUES ('attachment_file_writer', 'anonymous', '2025-04-23 14:59:49.855+00', 'anonymous', '2025-12-17 10:09:30.961843+00', 'Write data to a file', 'file_writer.py', '/proof/latest:file_writer.py')
ON CONFLICT DO NOTHING;

--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                          y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
VALUES ('bed2ac71-2575-462b-9b73-048e5f60388a', 'anonymous', '2025-12-17 10:09:30.921428+00', 'anonymous', '2025-12-17 10:09:30.962036+00', 0, '#0D41E1', 0,
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Provide lines from a file', 1, 1, 'File Provider', 0, 10, true, 0, 0, '365d5380-54eb-4b40-a377-b4921d7370c6',
        'File Provider', 'block', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');
INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                          y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
VALUES ('da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'anonymous', '2025-12-17 10:09:30.935617+00', 'anonymous', '2025-12-17 10:09:30.962365+00', 0, '#0D41E1', 0,
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'Write data to a file', 2, 1, 'File Writer', 200, 0, true, 0, 0, '4ad32c27-23c5-4e07-9e75-50a6549e82de',
        'File Writer', 'block', '7554873a-813c-4e00-b8d3-be072f12bea8', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
VALUES ('43179bda-c044-4ab9-9e68-fbfa8602a79e', 'anonymous', '2025-12-17 10:09:30.945735+00', 'anonymous', '2025-12-17 10:09:30.962561+00', 'bezier-catmull-rom', NULL, 'STRING',
        'f71ba36a-cb03-4353-adeb-a2d6985932e0', '7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'bed2ac71-2575-462b-9b73-048e5f60388a', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'block',
        '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('689eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-12-17 10:09:30.925834+00', 'anonymous', '2025-12-17 10:09:30.925834+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL,
        true, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('e2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-12-17 10:09:30.927924+00', 'anonymous', '2025-12-17 10:09:30.927924+00', 3, 'Input: num_steps', 'num_steps', 'num_steps', 1, 1, NULL,
        false, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('509cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-12-17 10:09:30.929906+00', 'anonymous', '2025-12-17 10:09:30.929906+00', 3, 'Input: ignore_first_line', 'ignore_first_line',
        'ignore_first_line', 1, 0, NULL, false, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('cd25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-12-17 10:09:30.937352+00', 'anonymous', '2025-12-17 10:09:30.937352+00', 3, 'Input: file_name', 'file_name', 'file_name', 1, 0, NULL,
        true, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('ca76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-12-17 10:09:30.939373+00', 'anonymous', '2025-12-17 10:09:30.939373+00', 3, 'Input: mode', 'mode', 'mode', 1, 0, NULL, false,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('1ad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-12-17 10:09:30.941135+00', 'anonymous', '2025-12-17 10:09:30.941135+00', 3, 'Input: waitForSync', 'waitForSync', 'waitForSync', 1, 0,
        NULL, false, 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, block_id,
                          template_id)
VALUES ('f71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-12-17 10:09:30.942624+00', 'anonymous', '2025-12-17 10:09:30.942624+00', 0, 'Input: data', 'data', 'data', 2, 0, NULL, false,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL);


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, block_id, template_id)
VALUES ('7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'anonymous', '2025-12-17 10:09:30.933546+00', 'anonymous', '2025-12-17 10:09:30.933546+00', 0, 'Output: line', 'line', NULL, 2, 0, NULL,
        'bed2ac71-2575-462b-9b73-048e5f60388a', NULL);


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: program_attached; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program_attached (program_id, attachment_id)
VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'attachment_file_line_provider');
INSERT INTO public.program_attached (program_id, attachment_id)
VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'attachment_file_line_provider');
INSERT INTO public.program_attached (program_id, attachment_id)
VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'attachment_file_writer');


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date, default_step_size, duration, end_point, end_time, start_point, start_time, workflow_id)
VALUES ('e4b90ac9-6d78-441b-9762-87af0114115f', 'anonymous', '2025-12-17 10:09:30.948422+00', 'anonymous', '2025-12-17 10:09:30.963286+00', 1, 500, 10, 1000, 1, 1,
        '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_size_definition (id, created_by, creation_date, last_modified_by, last_modified_date, default_size, end_point, start_point, step, step_based_configuration_id,
                                         step_size_definitions_key)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 'anonymous', '2025-12-17 10:09:30.951641+00', 'anonymous', '2025-12-17 10:09:30.963389+00', 1, 1, 0, '', 'e4b90ac9-6d78-441b-9762-87af0114115f', '1');
INSERT INTO public.step_size_definition (id, created_by, creation_date, last_modified_by, last_modified_date, default_size, end_point, start_point, step, step_based_configuration_id,
                                         step_size_definitions_key)
VALUES ('92a027a6-7a9d-488e-a167-75126072f455', 'anonymous', '2025-12-17 10:09:30.953175+00', 'anonymous', '2025-12-17 10:09:30.963641+00', 1, 1, 0, '', 'e4b90ac9-6d78-441b-9762-87af0114115f', '2');


--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 1, '2');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 1, '4');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 1, '5');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 1, '6');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('6e0e3843-8bf5-4249-af9d-3a0a7c1b8a8b', 1, '7');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('92a027a6-7a9d-488e-a167-75126072f455', 2, '01');
INSERT INTO public.stepsizes (step_size_definition_dao_id, value, id)
VALUES ('92a027a6-7a9d-488e-a167-75126072f455', 1, '2');


--
-- PostgreSQL database dump complete
--

