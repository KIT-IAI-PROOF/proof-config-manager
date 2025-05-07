--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.4 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow
VALUES (0, 3, NULL, '2025-05-06 11:32:21.808', NULL, 'FileReader2FileWriter', '11f02ad7-08d0-4f92-b911-f70f969a6ba6',
        'FileReader2FileWriter', 'anonymous');


--
-- Data for Name: execution; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: applied_inputs; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.program
VALUES (0, '2025-04-23 14:59:46.713', '2025-05-06 11:32:21.81', 'anonymous',
        'Provide other blocks with one line per SYNC from a given file', 'attachment_file_line_provider',
        '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'prog_file_line_provider', 'anonymous', 'testFLP');
INSERT INTO public.program
VALUES (0, '2025-04-23 14:59:49.853', '2025-05-06 11:32:21.81', 'anonymous', 'Write data to a file',
        'attachment_file_writer', '7554873a-813c-4e00-b8d3-be072f12bea8', 'prog_file_writer', 'anonymous', 'test');


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment
VALUES ('2025-04-23 14:59:49.855', '2025-04-23 14:59:49.855', 'anonymous', 'Write data to a file',
        'attachment_file_writer', 'file_writer.py', 'anonymous', '/proof/latest:file_writer.py',
        '7554873a-813c-4e00-b8d3-be072f12bea8');
INSERT INTO public.attachment
VALUES ('2025-04-23 14:59:46.722', '2025-04-23 14:59:46.722', 'anonymous',
        'Provide other blocks with one line per SYNC from a given file', 'attachment_file_line_provider',
        'file_line_provider.py', 'anonymous', '/proof/latest:file_line_provider.py',
        '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29');


--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block
VALUES (0, 0, 0, 0, 10, '2025-04-23 15:03:25.743', '2025-05-06 11:00:50.941', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'test', 'Provide lines from a file',
        'bed2ac71-2575-462b-9b73-048e5f60388a', 'File Provider', 'anonymous', '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29',
        '365d5380-54eb-4b40-a377-b4921d7370c6', 'File Provider', 'block', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');
INSERT INTO public.block
VALUES (0, 1, 0, 200, 0, '2025-04-23 15:03:25.76', '2025-05-06 11:00:50.941', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'test', 'Write data to a file',
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'File Writer', 'anonymous', '7554873a-813c-4e00-b8d3-be072f12bea8',
        '4ad32c27-23c5-4e07-9e75-50a6549e82de',
        'File Writer', 'block', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: block_interface; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:46.725', '2025-04-23 14:59:46.725', 'anonymous', NULL,
        '9fc4b6ff-ffe7-445c-852e-0832379e099a', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (NULL, '2025-04-23 14:59:46.727', '2025-04-23 14:59:46.727', 'anonymous', NULL,
        '206bfabb-281d-4848-b170-4e7a6ccbd2ef', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:46.727', '2025-04-23 14:59:46.727', 'anonymous', NULL,
        'e11ffb72-1e76-4f55-98ae-c2745808eeb9', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:46.727', '2025-04-23 14:59:46.727', 'anonymous', NULL,
        'aa7ebdc6-bb8f-4b7b-9261-d59bbf767b9e', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:46.727', '2025-04-23 14:59:46.727', 'anonymous', NULL,
        'a49aa529-67c1-4ecf-80ef-39f7607a9a5c', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:49.855', '2025-04-23 14:59:49.855', 'anonymous', NULL,
        'ffb5f64e-c5b6-4110-af74-cab7366b9ee8', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:49.856', '2025-04-23 14:59:49.856', 'anonymous', NULL,
        '5df0565e-e203-474a-877d-5ff46022a00b', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:49.856', '2025-04-23 14:59:49.856', 'anonymous', NULL,
        '946f1932-d5c0-4f85-bd7d-aff2c49fa425', NULL, 'anonymous');
INSERT INTO public.block_interface
VALUES (1, '2025-04-23 14:59:49.856', '2025-04-23 14:59:49.856', 'anonymous', NULL,
        'c8a77e24-ec15-4077-91d5-1fe04222444a', NULL, 'anonymous');


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template
VALUES (0, NULL, '2025-04-23 14:59:46.724', '2025-04-23 14:59:46.745', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'anonymous',
        'Provide lines from a file', '365d5380-54eb-4b40-a377-b4921d7370c6', 'anonymous', 'File Provider',
        '08d4fc61-b9d5-4bd8-9f69-19bff1f33e29', 'block');
INSERT INTO public.template
VALUES (0, NULL, '2025-04-23 14:59:49.855', '2025-04-23 14:59:49.864', '#0D41E1',
        'iai-artifactory.iai.kit.edu/docker-webis/proof-2/proof-standard:latest', 'anonymous', 'Write data to a file',
        '4ad32c27-23c5-4e07-9e75-50a6549e82de', 'anonymous', 'File Writer', '7554873a-813c-4e00-b8d3-be072f12bea8',
        'block');


--
-- Data for Name: block_io_definition; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block_io_definition
VALUES (1, '2025-04-23 14:59:46.727', '2025-04-23 15:03:25.771', 'bed2ac71-2575-462b-9b73-048e5f60388a', 'anonymous',
        NULL, 'a5ff6ec4-5103-47fc-9f74-e850b0f30ab8', '9fc4b6ff-ffe7-445c-852e-0832379e099a', NULL, 'test', NULL, NULL);
INSERT INTO public.block_io_definition
VALUES (2, '2025-04-23 14:59:46.727', '2025-04-23 15:03:25.771', 'bed2ac71-2575-462b-9b73-048e5f60388a', 'anonymous',
        NULL, '7fb56fac-1cd7-42b5-be2d-17c53e0b8dc7', '206bfabb-281d-4848-b170-4e7a6ccbd2ef', NULL, 'test',
        'e11ffb72-1e76-4f55-98ae-c2745808eeb9', NULL);
INSERT INTO public.block_io_definition
VALUES (3, '2025-04-23 14:59:46.728', '2025-04-23 15:03:25.771', 'bed2ac71-2575-462b-9b73-048e5f60388a', 'anonymous',
        NULL, 'eb4b3034-7813-4b8a-8c02-723cd2685ed5', 'aa7ebdc6-bb8f-4b7b-9261-d59bbf767b9e', NULL, 'test',
        'a49aa529-67c1-4ecf-80ef-39f7607a9a5c', NULL);
INSERT INTO public.block_io_definition
VALUES (1, '2025-04-23 14:59:49.855', '2025-04-23 15:03:25.772', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'anonymous',
        NULL, '5c62e5e0-b27e-4eca-a32b-346ca77e03a3', 'ffb5f64e-c5b6-4110-af74-cab7366b9ee8', NULL, 'test', NULL, NULL);
INSERT INTO public.block_io_definition
VALUES (2, '2025-04-23 14:59:49.856', '2025-04-23 15:03:25.772', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'anonymous',
        NULL, '141e9a8c-0050-4dbc-9346-79b8c7c2dd1b', '5df0565e-e203-474a-877d-5ff46022a00b', NULL, 'test', NULL, NULL);
INSERT INTO public.block_io_definition
VALUES (3, '2025-04-23 14:59:49.856', '2025-04-23 15:03:25.772', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'anonymous',
        NULL, 'c6c42470-ec49-4607-9654-ff661204de15', '946f1932-d5c0-4f85-bd7d-aff2c49fa425', NULL, 'test',
        'c8a77e24-ec15-4077-91d5-1fe04222444a', NULL);


--
-- Data for Name: block_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection
VALUES (NULL, '2025-04-23 15:03:25.769', '2025-04-23 15:03:25.772', 'bezier-catmull-rom', NULL, 'test',
        'e0ed360f-9038-4926-a3a1-5cfee9834c9f', 'b3d6434d-dd6d-49ca-ab1c-742c0bcdab3c', 'test',
        '189208ed-769f-42b3-9778-2f38141702c8', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'da35e3b1-aaea-4ba3-bd27-34c1323dd2af', 'block', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: event_life_cycle; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: dependencies; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.input
VALUES (3, 0, 1, true, 0, '2025-04-23 14:59:46.731', '2025-04-23 14:59:46.731', NULL, 'anonymous', 'Input: file_name',
        '689eef18-1189-4538-9019-7d7a646d9af4', 'file_name', 'anonymous', '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 1, '2025-04-23 14:59:46.733', '2025-04-23 14:59:46.733', NULL, 'anonymous', 'Input: num_steps',
        'e2063aa3-e694-4593-8f54-d322b977971e', 'num_steps', 'anonymous', '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input
VALUES (3, 1, 1, false, 0, '2025-04-23 14:59:46.735', '2025-04-23 14:59:46.735', NULL, 'anonymous',
        'Input: ignore_first_line', '509cea6d-dc33-4e2a-af9d-b8b0475d8c46', 'ignore_first_line', 'anonymous',
        '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.input
VALUES (3, 0, 1, true, 0, '2025-04-23 14:59:49.859', '2025-04-23 14:59:49.859', NULL, 'anonymous', 'Input: file_name',
        'cd25d323-9544-4236-91a5-963e68dc3620', 'file_name', 'anonymous', '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 0, '2025-04-23 14:59:49.86', '2025-04-23 14:59:49.86', NULL, 'anonymous', 'Input: mode',
        'ca76bf1a-6f3a-4754-87d8-924c8a9ac1d0', 'mode', 'anonymous', '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 0, '2025-04-23 14:59:49.862', '2025-04-23 14:59:49.862', NULL, 'anonymous',
        'Input: waitForSync', '1ad1aea4-e255-4324-9809-6923f537ec23', 'waitForSync', 'anonymous',
        '4ad32c27-23c5-4e07-9e75-50a6549e82de',
        NULL);
INSERT INTO public.input
VALUES (0, 1, 2, false, 0, '2025-04-23 14:59:49.863', '2025-04-23 14:59:49.863', NULL, 'anonymous', 'Input: data',
        'f71ba36a-cb03-4353-adeb-a2d6985932e0', 'data', 'anonymous', '4ad32c27-23c5-4e07-9e75-50a6549e82de', NULL);
INSERT INTO public.input
VALUES (3, 0, 1, true, 0, '2025-04-23 15:03:25.748', '2025-04-23 15:03:25.748', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'test', 'Input: file_name', 'e264da1e-f5dc-4c41-8f38-214d07ca6240', 'file_name', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (3, 1, 1, false, 0, '2025-04-23 15:03:25.752', '2025-04-23 15:03:25.752', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'test', 'Input: ignore_first_line', '562d0d6f-762f-452f-b6eb-d6565dc35b84', 'ignore_first_line', 'test', NULL,
        NULL);
INSERT INTO public.input
VALUES (3, 0, 1, true, 0, '2025-04-23 15:03:25.762', '2025-04-23 15:03:25.762', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af',
        'test', 'Input: file_name', 'a5567f6a-6ddc-4a93-9801-3261dfcb580b', 'file_name', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 0, '2025-04-23 15:03:25.764', '2025-04-23 15:03:25.764', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af',
        'test', 'Input: mode', 'c21585d0-7202-49c8-abcc-75012cd7de39', 'mode', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 0, '2025-04-23 15:03:25.765', '2025-04-23 15:03:25.765', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af',
        'test', 'Input: waitForSync', '2b12a3e7-4b3b-43e2-bc28-6ba96b6d61c0', 'waitForSync', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, 1, 2, false, 0, '2025-04-23 15:03:25.766', '2025-04-23 15:03:25.766', 'da35e3b1-aaea-4ba3-bd27-34c1323dd2af',
        'test', 'Input: data', 'b3d6434d-dd6d-49ca-ab1c-742c0bcdab3c', 'data', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (3, 0, 1, false, 2, '2025-04-23 15:03:25.75', '2025-05-06 10:49:13.07', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'test', 'Input: num_steps', 'd3de0d44-d55b-4e18-b16b-e2afe0474ad3', 'num_steps', 'anonymous', NULL, NULL);


--
-- Data for Name: mapping; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.mapping
VALUES ('num_steps', 'num_steps', '9fc4b6ff-ffe7-445c-852e-0832379e099a');
INSERT INTO public.mapping
VALUES ('ignore_first_line', 'ignore_first_line', '9fc4b6ff-ffe7-445c-852e-0832379e099a');
INSERT INTO public.mapping
VALUES ('file_name', 'file_name', '9fc4b6ff-ffe7-445c-852e-0832379e099a');
INSERT INTO public.mapping
VALUES ('line', 'line', 'e11ffb72-1e76-4f55-98ae-c2745808eeb9');
INSERT INTO public.mapping
VALUES ('mode', 'mode', 'ffb5f64e-c5b6-4110-af74-cab7366b9ee8');
INSERT INTO public.mapping
VALUES ('file_name', 'file_name', 'ffb5f64e-c5b6-4110-af74-cab7366b9ee8');
INSERT INTO public.mapping
VALUES ('waitForSync', 'waitForSync', 'ffb5f64e-c5b6-4110-af74-cab7366b9ee8');
INSERT INTO public.mapping
VALUES ('data', 'data', '5df0565e-e203-474a-877d-5ff46022a00b');


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output
VALUES (0, 1, 2, 0, '2025-04-23 14:59:46.738', '2025-04-23 14:59:46.738', NULL, 'anonymous', 'Output: line',
        '7000cc5d-637d-4c7f-b961-a9c9e797c50b', 'line', 'anonymous', '365d5380-54eb-4b40-a377-b4921d7370c6', NULL);
INSERT INTO public.output
VALUES (0, 1, 2, 0, '2025-04-23 15:03:25.753', '2025-04-23 15:03:25.753', 'bed2ac71-2575-462b-9b73-048e5f60388a',
        'test', 'Output: line', '189208ed-769f-42b3-9778-2f38141702c8', 'line', 'test', NULL, NULL);


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config
VALUES (1, 10, 1, '2025-05-06 10:49:13.057', 500, 1000, '2025-05-06 11:32:21.804', 0, 'anonymous',
        'e4b90ac9-6d78-441b-9762-87af0114115f', 'anonymous', '11f02ad7-08d0-4f92-b911-f70f969a6ba6');


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_size_definition
VALUES (1, 10, 2, '2025-05-06 10:49:13.061', '2025-05-06 11:32:21.812', 'anonymous',
        'a387198d-8220-4f8f-afcc-57e2c4365f1a', 'anonymous', '0', 'e4b90ac9-6d78-441b-9762-87af0114115f');


--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.stepsizes
VALUES (2, '2', 'a387198d-8220-4f8f-afcc-57e2c4365f1a');
INSERT INTO public.stepsizes
VALUES (1, '4', 'a387198d-8220-4f8f-afcc-57e2c4365f1a');
INSERT INTO public.stepsizes
VALUES (2, '5', 'a387198d-8220-4f8f-afcc-57e2c4365f1a');
INSERT INTO public.stepsizes
VALUES (1, '7', 'a387198d-8220-4f8f-afcc-57e2c4365f1a');


--
-- Data for Name: template_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- PostgreSQL database dump complete
--

