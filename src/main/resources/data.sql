--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.4 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow
VALUES (0, 0, '2025-04-16 15:13:00.418', '2025-04-16 15:13:19.516', 'test', 'Test Workflow',
        '7680d3fc-d59f-41db-8591-24583c38c58d', 'Test Workflow', 'test', NULL);


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
VALUES (0, '2025-04-16 15:12:55.281', '2025-04-16 15:13:19.516', 'anonymous', 'test', 'test.py',
        '717748dc-46fe-465e-a8cb-5460ea4d6faa', 'test', 'test', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-16 15:12:55.313', '2025-04-16 15:13:19.517', 'anonymous', 'test', 'test.py',
        '3f3eb578-75ff-4123-8bf9-50bc4b07835d', 'test', 'test', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-16 15:12:55.326', '2025-04-16 15:13:19.517', 'anonymous', 'test', 'test.py',
        '174df597-e0b1-453a-b56b-71fe7c259965', 'test', 'test', NULL);


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment
VALUES ('2025-04-16 15:12:55.441', '2025-04-16 15:12:55.441', 'anonymous', 'test1',
        '274e1c01-86e7-4794-a962-7d504387e252', 'Attachment1', 'anonymous', '/proof/latest:test1', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-16 15:12:55.442', '2025-04-16 15:12:55.442', 'anonymous', 'test2',
        '819c8a00-5787-402f-b4e0-ebf7cba9650e', 'Attachment2', 'anonymous', '/proof/latest:test2', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-16 15:12:55.443', '2025-04-16 15:12:55.443', 'anonymous', 'test3',
        '859f909a-5e5f-4d1f-877c-c036a9f4712a', 'Attachment3', 'anonymous', '/proof/latest:test3', NULL);


--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block
VALUES (0, NULL, NULL, 0, 20, '2025-04-16 15:13:09.305', '2025-04-16 15:13:09.357', '#0D41E1', 'proof-standard:latest',
        'test', 'A template for Reader', '399502df-aec0-4798-8c9c-b71b61ff9a20', NULL, 'test',
        '717748dc-46fe-465e-a8cb-5460ea4d6faa', '9e1452e7-7420-4037-8e21-bff56307d342', NULL, 'block',
        '7680d3fc-d59f-41db-8591-24583c38c58d');
INSERT INTO public.block
VALUES (0, NULL, NULL, 200, 0, '2025-04-16 15:13:09.324', '2025-04-16 15:13:09.358', '#0C63E7', 'proof-standard:latest',
        'test', 'A template for Transformer', '7ebb3b6e-a15c-4397-bcd3-a11437621e79', NULL, 'test',
        '3f3eb578-75ff-4123-8bf9-50bc4b07835d', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL, 'block',
        '7680d3fc-d59f-41db-8591-24583c38c58d');
INSERT INTO public.block
VALUES (0, NULL, NULL, 400, 20, '2025-04-16 15:13:09.346', '2025-04-16 15:13:09.358', '#0A85ED',
        'proof-standard:latest', 'test', 'A template for Writer', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d', NULL, 'test',
        '174df597-e0b1-453a-b56b-71fe7c259965', '47b64192-6967-411b-8636-eee6d761127a', NULL, 'block',
        '7680d3fc-d59f-41db-8591-24583c38c58d');


--
-- Data for Name: block_interface; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template
VALUES (0, NULL, '2025-04-16 15:12:55.349', '2025-04-16 15:12:55.405', '#0D41E1', 'proof-standard:latest', 'anonymous',
        'A template for Reader', '9e1452e7-7420-4037-8e21-bff56307d342', 'anonymous', 'Reader',
        '717748dc-46fe-465e-a8cb-5460ea4d6faa', 'block');
INSERT INTO public.template
VALUES (0, NULL, '2025-04-16 15:12:55.376', '2025-04-16 15:12:55.406', '#0C63E7', 'proof-standard:latest', 'anonymous',
        'A template for Transformer', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', 'anonymous', 'Transformer',
        '3f3eb578-75ff-4123-8bf9-50bc4b07835d', 'block');
INSERT INTO public.template
VALUES (0, NULL, '2025-04-16 15:12:55.397', '2025-04-16 15:12:55.406', '#0A85ED', 'proof-standard:latest', 'anonymous',
        'A template for Writer', '47b64192-6967-411b-8636-eee6d761127a', 'anonymous', 'Writer',
        '174df597-e0b1-453a-b56b-71fe7c259965', 'block');


--
-- Data for Name: block_io_definition; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: block_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block_start_parameters
VALUES ('path', 'test', '399502df-aec0-4798-8c9c-b71b61ff9a20');
INSERT INTO public.block_start_parameters
VALUES ('path', 'test', '7ebb3b6e-a15c-4397-bcd3-a11437621e79');
INSERT INTO public.block_start_parameters
VALUES ('path', 'test', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection
VALUES (NULL, '2025-04-16 15:13:09.355', '2025-04-16 15:13:09.358', 'bezier-catmull-rom', NULL, 'test',
        '12dd78db-b6c8-48af-89b3-26394354df46', '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test',
        '399502df-aec0-4798-8c9c-b71b61ff9a20', '399502df-aec0-4798-8c9c-b71b61ff9a20',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'block', '7680d3fc-d59f-41db-8591-24583c38c58d');
INSERT INTO public.connection
VALUES (NULL, '2025-04-16 15:13:09.356', '2025-04-16 15:13:09.358', 'bezier-catmull-rom', NULL, 'test',
        '55bb74f6-6961-48fa-ade8-25c18cce9931', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d', 'test',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d', 'block', '7680d3fc-d59f-41db-8591-24583c38c58d');


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
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.361', '2025-04-16 15:12:55.361', NULL, 'anonymous', 'output 1',
        '316796b8-f940-44c9-b1f7-5e7bff3e14db', 'o1', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.363', '2025-04-16 15:12:55.363', NULL, 'anonymous', 'output 2',
        '3e4bfc0b-9a71-4177-8527-b4ac3afaa054', 'o2', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.365', '2025-04-16 15:12:55.365', NULL, 'anonymous', 'output 3',
        '3f4ae518-6b2e-43e0-a1cc-71ef14aa62c1', 'o3', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.367', '2025-04-16 15:12:55.367', NULL, 'anonymous', 'output 4',
        '7b3fcf32-cd3a-4746-b21d-673b0ad5384e', 'o4', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.378', '2025-04-16 15:12:55.378', NULL, 'anonymous', 'output 1',
        'af33ed2c-a928-46fe-af38-89639ea42b07', 'o1', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.379', '2025-04-16 15:12:55.379', NULL, 'anonymous', 'output 2',
        '2ebeb71f-edb1-4fec-b717-da6de6eb67b4', 'o2', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.381', '2025-04-16 15:12:55.381', NULL, 'anonymous', 'output 3',
        '68ce0da1-5dac-40f0-9949-de6585c393ed', 'o3', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.382', '2025-04-16 15:12:55.382', NULL, 'anonymous', 'output 4',
        '73dbb5f4-045c-493e-9866-43eda338d16a', 'o4', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.384', '2025-04-16 15:12:55.384', NULL, 'anonymous', 'output 5',
        '5ed9ff6a-f312-424d-acba-e849757f52de', 'o5', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.385', '2025-04-16 15:12:55.385', NULL, 'anonymous', 'output 6',
        'c4dde749-5a71-4623-a292-559ea1ce5aaa', 'o6', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.398', '2025-04-16 15:12:55.398', NULL, 'anonymous', 'output 1',
        '63c95333-e20d-4c23-9c91-4abcb0442243', 'o1', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:12:55.399', '2025-04-16 15:12:55.399', NULL, 'anonymous', 'output 2',
        '7c46db4f-881a-4eb1-b60a-b81275e2d9d3', 'o2', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.308', '2025-04-16 15:13:09.308',
        '399502df-aec0-4798-8c9c-b71b61ff9a20', 'test', 'output 1', 'c1f62efd-e570-43cf-bb88-ec3a711de1fe', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.31', '2025-04-16 15:13:09.31', '399502df-aec0-4798-8c9c-b71b61ff9a20',
        'test', 'output 2', '25c5b14d-ce0d-44c9-9fab-a2b3fc5e6d1d', 'o2', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.312', '2025-04-16 15:13:09.312',
        '399502df-aec0-4798-8c9c-b71b61ff9a20', 'test', 'output 3', '521f7fff-d973-4dab-b7d4-42a46cab5be3', 'o3',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.313', '2025-04-16 15:13:09.313',
        '399502df-aec0-4798-8c9c-b71b61ff9a20', 'test', 'output 4', '0812be38-fc34-4adb-96b9-cec9434bb504', 'o4',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.326', '2025-04-16 15:13:09.326',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test', 'output 1', 'dfae3b42-cfed-4450-9bbb-5b86f510b7dd', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.327', '2025-04-16 15:13:09.327',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test', 'output 2', 'ef1ee054-6326-48ea-8f54-a0eaac1203dd', 'o2',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.328', '2025-04-16 15:13:09.328',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test', 'output 3', '13251a17-95e4-49df-944b-85462ea345c1', 'o3',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.33', '2025-04-16 15:13:09.33', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'output 4', '2b867c4a-40aa-4a17-a9e3-43bb54a9fd5c', 'o4', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.331', '2025-04-16 15:13:09.331',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test', 'output 5', 'b38962c9-d4ac-4c9a-8f9d-bbea00720659', 'o5',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.332', '2025-04-16 15:13:09.332',
        '7ebb3b6e-a15c-4397-bcd3-a11437621e79', 'test', 'output 6', '5ce50d2d-2f09-4040-8560-d0dac52e97c5', 'o6',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.346', '2025-04-16 15:13:09.346',
        'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d', 'test', 'output 1', '34c5f5d8-419e-41ab-8d30-bb530e183d0e', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-16 15:13:09.347', '2025-04-16 15:13:09.347',
        'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d', 'test', 'output 2', '08502278-75fe-41d2-a737-899c4701d768', 'o2',
        'test', NULL, NULL);


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.371', '2025-04-16 15:12:55.371', NULL, 'anonymous', 'input 1',
        'd86b25a5-f426-43e0-a686-7f5922ed9dd9', 'i1', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.373', '2025-04-16 15:12:55.373', NULL, 'anonymous', 'input 2',
        'f7dd0e43-3d6e-4c07-bd20-0f0f3c81dd04', 'i2', 'anonymous', '9e1452e7-7420-4037-8e21-bff56307d342', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.387', '2025-04-16 15:12:55.387', NULL, 'anonymous', 'input 1',
        '9fd42a10-f329-4b55-9623-73b247c18140', 'i1', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.388', '2025-04-16 15:12:55.388', NULL, 'anonymous', 'input 2',
        'c03e325b-1317-423a-aea1-13d638801bcb', 'i2', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.39', '2025-04-16 15:12:55.39', NULL, 'anonymous', 'input 3',
        'c9af8223-cb94-425b-847d-8937375dfd88', 'i3', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.392', '2025-04-16 15:12:55.392', NULL, 'anonymous', 'input 4',
        '1040dceb-6d79-474d-8d76-553b999c09da', 'i4', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.393', '2025-04-16 15:12:55.393', NULL, 'anonymous', 'input 5',
        '48a7ace8-abf8-496c-bbfd-21b39733408c', 'i5', 'anonymous', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.4', '2025-04-16 15:12:55.4', NULL, 'anonymous', 'input 1',
        '97f6be57-c43d-4801-a763-762ae866869f', 'i1', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.401', '2025-04-16 15:12:55.401', NULL, 'anonymous', 'input 2',
        'c5aa3c54-cef4-40eb-9b83-d8ddadfb2c76', 'i2', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.403', '2025-04-16 15:12:55.403', NULL, 'anonymous', 'input 3',
        '096bf1f6-d559-4420-99aa-3c3396751301', 'i3', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:12:55.403', '2025-04-16 15:12:55.403', NULL, 'anonymous', 'input 4',
        '86f8912d-017b-47d5-b4a7-41b15a36d487', 'i4', 'anonymous', '47b64192-6967-411b-8636-eee6d761127a', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.315', '2025-04-16 15:13:09.315', '399502df-aec0-4798-8c9c-b71b61ff9a20',
        'test', 'input 1', 'dd017a10-76c6-4566-a4e5-a260403aecdd', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.317', '2025-04-16 15:13:09.317', '399502df-aec0-4798-8c9c-b71b61ff9a20',
        'test', 'input 2', '8cd0293d-5cd4-447b-b698-74f805a38221', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.334', '2025-04-16 15:13:09.334', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'input 1', '2125b9f6-bd96-42ad-9057-c1785c0e482d', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.336', '2025-04-16 15:13:09.336', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'input 2', '6279a874-bb6a-49cb-a3dd-41537af723d8', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.337', '2025-04-16 15:13:09.337', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'input 3', '275dc1c2-5acf-4237-80b8-ae25f63923d9', 'i3', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.338', '2025-04-16 15:13:09.338', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'input 4', '41620367-8db0-402b-a211-45ac05654b59', 'i4', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.339', '2025-04-16 15:13:09.339', '7ebb3b6e-a15c-4397-bcd3-a11437621e79',
        'test', 'input 5', '6cf6935d-552c-448f-b9c5-2048d9551205', 'i5', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.348', '2025-04-16 15:13:09.348', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d',
        'test', 'input 1', '330e5a65-d25e-4713-81bb-5c74f4a543d1', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.349', '2025-04-16 15:13:09.349', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d',
        'test', 'input 2', 'f67dd341-212e-41b4-bd5f-cc18fa27ecbb', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.351', '2025-04-16 15:13:09.351', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d',
        'test', 'input 3', 'ff8adba0-e034-4fb4-ad3f-50d0eaec1367', 'i3', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-16 15:13:09.351', '2025-04-16 15:13:09.351', 'c2d74ffa-ef7e-4b07-ad02-6635c5e9912d',
        'test', 'input 4', '5fdec3ed-185c-48e8-8a79-a66f1288c096', 'i4', 'test', NULL, NULL);


--
-- Data for Name: parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: template_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template_start_parameters
VALUES ('path', 'test', '9e1452e7-7420-4037-8e21-bff56307d342');
INSERT INTO public.template_start_parameters
VALUES ('path', 'test', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816');
INSERT INTO public.template_start_parameters
VALUES ('path', 'test', '47b64192-6967-411b-8636-eee6d761127a');


--
-- PostgreSQL database dump complete
--

