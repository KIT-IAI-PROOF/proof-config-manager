--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.4 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date,
                             communication_paradigm, description, label, simulation_strategy)
VALUES ('11f02ad7-08d0-4f92-b911-f70f969a6ba6', 'test', '2025-05-06 11:32:21', 'test', '2025-05-06 11:32:21', 0,
        'FileReader2FileWriter', 'FileReader2FileWriter', 3)
ON CONFLICT DO NOTHING;

--
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description,
                            entry_point, label, runtime, tag)
VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'test', '2025-04-23 14:59:46.713', 'anonymous',
        '2025-05-06 11:32:21.81',
        'Provide other blocks with one line per SYNC from a given file', 'attachment_file_line_provider',
        'prog_file_line_provider', 0, 'testFLP')
ON CONFLICT DO NOTHING;
INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description,
                            entry_point, label, runtime, tag)
VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'test', '2025-04-23 14:59:49.853', 'anonymous',
        '2025-05-06 11:32:21.81',
        'Write data to a file', 'attachment_file_writer',
        'prog_file_writer', 0, 'testFLP')
ON CONFLICT DO NOTHING;


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label,
                               path)
VALUES ('attachment_file_writer', 'anonymous', '2025-04-23 14:59:49.855', 'anonymous', '2025-04-23 14:59:49.855',
        'Write data to a file',
        'file_writer.py', '/proof/latest:file_writer.py')
ON CONFLICT DO NOTHING;
INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label,
                               path)
VALUES ('attachment_file_line_provider', 'anonymous', '2025-04-23 14:59:46.722', 'anonymous', '2025-04-23 14:59:46.722',
        'Provide other blocks with one line per SYNC from a given file',
        'file_line_provider.py', '/proof/latest:file_line_provider.py'
       )
ON CONFLICT DO NOTHING;

INSERT INTO public.program_attached (program_id, attachment_id)
VALUES ('7554873a-813c-4e00-b8d3-be072f12bea8', 'attachment_file_writer')
ON CONFLICT DO NOTHING;
INSERT INTO public.program_attached (program_id, attachment_id)
VALUES ('08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'attachment_file_line_provider')
ON CONFLICT DO NOTHING;



--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block (id, communication_paradigm, index, status, x, y, creation_date,
                          last_modified_date, color, container_image, created_by, description, label, last_modified_by,
                          program_id, template_id, template_name, type, block_type, interface_type, workflow_id, shutdown_relevant, sync_strategy)
VALUES ('bed2ac71-2575-462b-9b73-048e5f60388a', 0, 1, 0, 0, 10, '2025-04-23 15:03:25.743',
        '2025-05-06 11:00:50.941', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'test', 'Provide lines from a file',
        'File Provider', 'anonymous', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29',
        '365d5380-54eb-4b40-a377-b4921d7370c6', 'File Provider', 'block', 0, 1, '11f02ad7-08d0-4f92-b911-f70f969a6ba6', true, 0)
ON CONFLICT DO NOTHING;
INSERT INTO public.block (id, communication_paradigm, index, status, x, y, creation_date,
                          last_modified_date, color, container_image, created_by, description, label, last_modified_by,
                          program_id, template_id, template_name, type, block_type, interface_type, workflow_id, shutdown_relevant, sync_strategy)
VALUES ('da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 0, 2, 0, 200, 0, '2025-04-23 15:03:25.76',
        '2025-05-06 11:00:50.941', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'test', 'Write data to a file',
        'File Writer', 'anonymous', '7554873a-813c-4e00-b8d3-be072f12bea8',
        '4ad32c27-23c5-4e07-9e75-50a6549e82de',
        'File Writer', 'block', 0, 1, '11f02ad7-08d0-4f92-b911-f70f969a6ba6', true, 0)
ON CONFLICT DO NOTHING;


--
-- Data for Name: block_interface; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template (id, communication_paradigm, status, creation_date, last_modified_date, color,
                             container_image, created_by, description, last_modified_by, name, program_id, type, block_type,
                             interface_type, shutdown_relevant, sync_strategy)
VALUES ('365d5380-54eb-4b40-a377-b4921d7370c6', 0, NULL, '2025-04-23 14:59:46.724', '2025-04-23 14:59:46.745',
        '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'anonymous',
        'Provide lines from a file', 'anonymous', 'File Provider',
        '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'block', 0, 1, true, 0)
ON CONFLICT DO NOTHING;
INSERT INTO public.template (id, communication_paradigm, status, creation_date, last_modified_date, color,
                             container_image, created_by, description, last_modified_by, name, program_id, type, block_type,
                             interface_type, shutdown_relevant, sync_strategy)
VALUES ('4ad32c27-23c5-4e07-9e75-50a6549e82de', 0, NULL, '2025-04-23 14:59:49.855', '2025-04-23 14:59:49.864',
        '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'anonymous', 'Write data to a file',
        'anonymous', 'File Writer', '7554873a-813c-4e00-b8d3-be072f12bea8',
        'block', 0, 1, true, 0)
ON CONFLICT DO NOTHING;


--
-- Data for Name: block_io_definition; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: block_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated,
                               connection_type, input, output, source, target, type, workflow_id)
VALUES ('43179bda-c044-4ab9-9e68-fbfa8602a79e', 'test', '2025-07-15 12:19:15.906', 'test', '2025-07-15 12:19:15.92',
        'bezier-catmull-rom', NULL, NULL, 'f71ba36a-cb03-4353-adeb-a2d6985932e0',
        '7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'block', '11f02ad7-08d0-4f92-b911-f70f969a6ba6')
ON CONFLICT DO NOTHING;


--
-- Data for Name: event_life_cycle; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: dependencies; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
--
INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('689eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: file_name', 'file_name', 1, 0, NULL, 'file_name', true,
        'bed2ac71-2575-462b-9b73-048e5f60388a', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('e2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: num_steps', 'num_steps', 1, 1, NULL, 'num_steps', false,
        'bed2ac71-2575-462b-9b73-048e5f60388a', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('509cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: ignore_first_line', 'ignore_first_line', 1, 0, NULL, 'ignore_first_line',
        false, 'bed2ac71-2575-462b-9b73-048e5f60388a', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('cd25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: file_name', 'file_name', 1, 0, NULL, 'file_name', true,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('ca76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: mode', 'mode', 1, 0, NULL, 'mode', false,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('1ad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: waitForSync', 'waitForSync', 1, 0, NULL, 'waitForSync', false,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('f71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 0, 'Input: data', 'data', 2, 0, NULL, 'data', false,
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', NULL)
ON CONFLICT DO NOTHING;


---

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('a89eef18-1189-4538-9019-7d7a646d9af4', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: file_name', 'file_name', 1, 0, NULL, 'file_name', true,
        NULL, '365d5380-54eb-4b40-a377-b4921d7370c6')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('v2063aa3-e694-4593-8f54-d322b977971e', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: num_steps', 'num_steps', 1, 1, NULL, 'num_steps', false,
        NULL, '365d5380-54eb-4b40-a377-b4921d7370c6')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('109cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: ignore_first_line', 'ignore_first_line', 1, 0, NULL, 'ignore_first_line',
        false, NULL, '365d5380-54eb-4b40-a377-b4921d7370c6')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('ed25d323-9544-4236-91a5-963e68dc3620', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: file_name', 'file_name', 1, 0, NULL, 'file_name', true,
        NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('ra76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: mode', 'mode', 1, 0, NULL, 'mode', false,
        NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('bad1aea4-e255-4324-9809-6923f537ec23', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 3, 'Input: waitForSync', 'waitForSync', 1, 0, NULL, 'waitForSync', false,
        NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de')
ON CONFLICT DO NOTHING;

INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                          description, label, phase, type, unit, model_var_name, required, block_id, template_id)
VALUES ('l71ba36a-cb03-4353-adeb-a2d6985932e0', 'anonymous', '2025-04-23 14:59:46.731', 'anonymous',
        '2025-04-23 14:59:46.731', 0, 'Input: data', 'data', 2, 0, NULL, 'data', false,
        NULL, '4ad32c27-23c5-4e07-9e75-50a6549e82de')
ON CONFLICT DO NOTHING;



--
-- Data for Name: applied_inputs; Type: TABLE DATA; Schema: public; Owner: admin
--


INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('e2063aa3-e694-4593-8f54-d322b977971e', '10', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;
INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('ca76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'w', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;
INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('509cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'True', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;
INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('1ad1aea4-e255-4324-9809-6923f537ec23', 'True', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;
INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('cd25d323-9544-4236-91a5-963e68dc3620', '/tmp/ebbes', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;
INSERT INTO public.applied_inputs (id, value, execution_dao_id)
VALUES ('689eef18-1189-4538-9019-7d7a646d9af4', '/tmp/csvTest.csv', '58caf8ce-6783-46e7-a671-d827f1b0958d')
ON CONFLICT DO NOTHING;

--
-- Data for Name: mapping; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                           description, label, phase, type, unit, block_id, template_id)
VALUES ('7000cc5d-637d-4c7f-b961-a9c9e797c50b', 'anonymous', '2025-04-23 14:59:46.738', 'anonymous',
        '2025-04-23 14:59:46.738', 0, 'Output: line', 'line', 2, 0, NULL, NULL,
        '365d5380-54eb-4b40-a377-b4921d7370c6')
ON CONFLICT DO NOTHING;

INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type,
                           description, label, phase, type, unit, block_id, template_id)
VALUES ('7100cc5d-637d-4c7f-b961-a9c9e797c50c', 'anonymous', '2025-04-23 14:59:46.738', 'anonymous',
        '2025-04-23 14:59:46.738', 0, 'Output: line', 'line', 2, 0, NULL, 'bed2ac71-2575-462b-9b73-048e5f60388a',
        NULL)
ON CONFLICT DO NOTHING;


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date,
                                      default_step_size, duration, start_point, end_point, end_time, start_time,
                                      workflow_id)
VALUES ('e4b90ac9-6d78-441b-9762-87af0114115f', 'anonymous', '2025-05-06 11:32:21.804', 'anonymous',
        '2025-05-06 11:32:21.804', 1, 500, 1, 10, 1000, 1, '11f02ad7-08d0-4f92-b911-f70f969a6ba6')
ON CONFLICT DO NOTHING;


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_size_definition (id, created_by, creation_date, last_modified_by, last_modified_date,
                                         default_size, start_point, end_point, step, step_based_configuration_id)
VALUES ('a387198d-8220-4f8f-afcc-57e2c4365f1a', 'anonymous', '2025-05-06 10:49:13.061', 'anonymous',
        '2025-05-06 11:32:21.812', 1, 2, 10, '0', 'e4b90ac9-6d78-441b-9762-87af0114115f')
ON CONFLICT DO NOTHING;


--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.stepsizes (id, value, step_size_definition_dao_id)
VALUES ('2', 2, 'a387198d-8220-4f8f-afcc-57e2c4365f1a')
ON CONFLICT DO NOTHING;
INSERT INTO public.stepsizes (id, value, step_size_definition_dao_id)
VALUES ('4', 1, 'a387198d-8220-4f8f-afcc-57e2c4365f1a')
ON CONFLICT DO NOTHING;
INSERT INTO public.stepsizes (id, value, step_size_definition_dao_id)
VALUES ('5', 2, 'a387198d-8220-4f8f-afcc-57e2c4365f1a')
ON CONFLICT DO NOTHING;
INSERT INTO public.stepsizes (id, value, step_size_definition_dao_id)
VALUES ('7', 1, 'a387198d-8220-4f8f-afcc-57e2c4365f1a')
ON CONFLICT DO NOTHING;


--
-- Data for Name: template_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- PostgreSQL database dump complete
--

