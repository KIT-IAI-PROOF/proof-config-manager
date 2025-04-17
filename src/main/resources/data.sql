--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.4 (Homebrew)

--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow
VALUES (4, 0, '2025-04-17 12:08:42.451', '2025-04-17 12:09:25.658', 'test', 'Test Workflow',
        '6fee3e22-9dc2-4020-8e3c-87a34b1590fe', 'Test Workflow', 'test', NULL);


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
VALUES (0, '2025-04-16 15:12:55.281', '2025-04-17 12:09:25.659', 'anonymous', 'test', 'test.py',
        '717748dc-46fe-465e-a8cb-5460ea4d6faa', 'test', 'test', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-16 15:12:55.313', '2025-04-17 12:09:25.659', 'anonymous', 'test', 'test.py',
        '3f3eb578-75ff-4123-8bf9-50bc4b07835d', 'test', 'test', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-16 15:12:55.326', '2025-04-17 12:09:25.659', 'anonymous', 'test', 'test.py',
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
VALUES (0, NULL, NULL, 0, 20, '2025-04-17 12:09:01.257', '2025-04-17 12:09:01.388', '#0D41E1', 'proof-standard:latest',
        'test', 'A template for Reader', 'c220b7fb-a858-4db7-ac8f-91f5de707515', NULL, 'test',
        '717748dc-46fe-465e-a8cb-5460ea4d6faa', '9e1452e7-7420-4037-8e21-bff56307d342', NULL, 'block',
        '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');
INSERT INTO public.block
VALUES (0, NULL, NULL, 200, 0, '2025-04-17 12:09:01.303', '2025-04-17 12:09:01.39', '#0C63E7', 'proof-standard:latest',
        'test', 'A template for Transformer', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2', NULL, 'test',
        '3f3eb578-75ff-4123-8bf9-50bc4b07835d', 'b0e7e91b-8066-48c5-b4c6-b5d44a203816', NULL, 'block',
        '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');
INSERT INTO public.block
VALUES (0, NULL, NULL, 400, 20, '2025-04-17 12:09:01.351', '2025-04-17 12:09:01.391', '#0A85ED',
        'proof-standard:latest', 'test', 'A template for Writer', 'd4694a40-e81c-49fc-9d9f-fff6649fe619', NULL, 'test',
        '174df597-e0b1-453a-b56b-71fe7c259965', '47b64192-6967-411b-8636-eee6d761127a', NULL, 'block',
        '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');


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
VALUES ('path', 'test', 'c220b7fb-a858-4db7-ac8f-91f5de707515');
INSERT INTO public.block_start_parameters
VALUES ('path', 'test', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2');
INSERT INTO public.block_start_parameters
VALUES ('path', 'test', 'd4694a40-e81c-49fc-9d9f-fff6649fe619');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.connection
VALUES (NULL, '2025-04-17 12:09:01.372', '2025-04-17 12:09:01.391', 'bezier-catmull-rom', NULL, 'test',
        '35ddbdae-177a-49d9-b324-b732300b25d7', 'dd3214ea-e417-4729-84e0-b1de9afd8561', 'test',
        '65ba3030-cf4b-4855-8e0f-843faceff91f', 'c220b7fb-a858-4db7-ac8f-91f5de707515',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'block', '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');
INSERT INTO public.connection
VALUES (NULL, '2025-04-17 12:09:01.374', '2025-04-17 12:09:01.391', 'bezier-catmull-rom', NULL, 'test',
        'e7f6913f-e14c-40d5-8e69-aa3a4884f659', 'a626344d-e0d3-47d3-b89d-ca97eba4ee49', 'test',
        '8fdf29fa-9468-4f67-9b71-b807a4e3f644', 'c220b7fb-a858-4db7-ac8f-91f5de707515',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'block', '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');
INSERT INTO public.connection
VALUES (NULL, '2025-04-17 12:09:01.376', '2025-04-17 12:09:01.392', 'bezier-catmull-rom', NULL, 'test',
        '6b3a0339-0575-4fdb-b8d5-97239b4f4399', 'c3b304b1-a09c-4886-b0d7-e4acfd61f66e', 'test',
        '92b597e3-f7f0-4685-b25d-907230dee0ff', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'd4694a40-e81c-49fc-9d9f-fff6649fe619', 'block', '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');
INSERT INTO public.connection
VALUES (NULL, '2025-04-17 12:09:01.378', '2025-04-17 12:09:01.392', 'bezier-catmull-rom', NULL, 'test',
        '4975556f-196c-4043-ba81-c33a3023e5f5', 'bd9c4d4d-26d3-4cb0-972f-26d9cf3b93fc', 'test',
        'b9651230-8bcb-4098-9a89-7e3cf725d713', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'd4694a40-e81c-49fc-9d9f-fff6649fe619', 'block', '6fee3e22-9dc2-4020-8e3c-87a34b1590fe');


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
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.267', '2025-04-17 12:09:01.267',
        'c220b7fb-a858-4db7-ac8f-91f5de707515', 'test', 'output 1', 'a50b8e1c-2ef5-4125-b60e-c651c8735f79', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.271', '2025-04-17 12:09:01.271',
        'c220b7fb-a858-4db7-ac8f-91f5de707515', 'test', 'output 2', 'e9cb44f6-8602-45a3-b239-bff0e55d9458', 'o2',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.274', '2025-04-17 12:09:01.274',
        'c220b7fb-a858-4db7-ac8f-91f5de707515', 'test', 'output 3', '60d95f12-5df5-44a5-899d-e9660a26b735', 'o3',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.278', '2025-04-17 12:09:01.278',
        'c220b7fb-a858-4db7-ac8f-91f5de707515', 'test', 'output 4', 'a8d4e992-7e33-4928-88f0-52a1c21384da', 'o4',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.307', '2025-04-17 12:09:01.307',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'test', 'output 1', 'af82741a-43f2-403b-97b1-73e1ff2e3bc1', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.31', '2025-04-17 12:09:01.31', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'output 2', '1a00f03b-c209-4391-9dd1-29374b043ed4', 'o2', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.312', '2025-04-17 12:09:01.312',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'test', 'output 3', 'a626344d-e0d3-47d3-b89d-ca97eba4ee49', 'o3',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.315', '2025-04-17 12:09:01.315',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'test', 'output 4', 'dd3214ea-e417-4729-84e0-b1de9afd8561', 'o4',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.317', '2025-04-17 12:09:01.317',
        'fd1577fd-3f12-4c03-8f84-2211eb16eda2', 'test', 'output 5', 'a24d5d17-4ebf-4969-96d5-6efd7b445f2a', 'o5',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.32', '2025-04-17 12:09:01.32', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'output 6', '9833fc6f-6141-41ac-8144-7f91eaf8b9a3', 'o6', 'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.354', '2025-04-17 12:09:01.354',
        'd4694a40-e81c-49fc-9d9f-fff6649fe619', 'test', 'output 1', 'c3b304b1-a09c-4886-b0d7-e4acfd61f66e', 'o1',
        'test', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-17 12:09:01.355', '2025-04-17 12:09:01.355',
        'd4694a40-e81c-49fc-9d9f-fff6649fe619', 'test', 'output 2', 'bd9c4d4d-26d3-4cb0-972f-26d9cf3b93fc', 'o2',
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
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.286', '2025-04-17 12:09:01.286', 'c220b7fb-a858-4db7-ac8f-91f5de707515',
        'test', 'input 1', '8fdf29fa-9468-4f67-9b71-b807a4e3f644', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.289', '2025-04-17 12:09:01.289', 'c220b7fb-a858-4db7-ac8f-91f5de707515',
        'test', 'input 2', '65ba3030-cf4b-4855-8e0f-843faceff91f', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.326', '2025-04-17 12:09:01.326', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'input 1', '92b597e3-f7f0-4685-b25d-907230dee0ff', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.329', '2025-04-17 12:09:01.329', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'input 2', 'b9651230-8bcb-4098-9a89-7e3cf725d713', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.333', '2025-04-17 12:09:01.333', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'input 3', '50d718af-7003-464f-b5e4-d99d9da1e7d8', 'i3', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.336', '2025-04-17 12:09:01.336', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'input 4', '81195478-17cb-43c9-b5dd-390d88f78c46', 'i4', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.34', '2025-04-17 12:09:01.34', 'fd1577fd-3f12-4c03-8f84-2211eb16eda2',
        'test', 'input 5', '5c59131e-8aba-408b-83ff-2b888db2fa69', 'i5', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.358', '2025-04-17 12:09:01.358', 'd4694a40-e81c-49fc-9d9f-fff6649fe619',
        'test', 'input 1', '735dffec-809d-49b5-a889-6b71c89b5863', 'i1', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.36', '2025-04-17 12:09:01.36', 'd4694a40-e81c-49fc-9d9f-fff6649fe619',
        'test', 'input 2', 'ec34f46f-cbf0-4045-9def-228e8ddb38a0', 'i2', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.363', '2025-04-17 12:09:01.363', 'd4694a40-e81c-49fc-9d9f-fff6649fe619',
        'test', 'input 3', '1408a6b4-1a63-4fb0-9be9-7db10addf2af', 'i3', 'test', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-17 12:09:01.364', '2025-04-17 12:09:01.364', 'd4694a40-e81c-49fc-9d9f-fff6649fe619',
        'test', 'input 4', '5115ee16-2385-4c0d-9f9f-0c47187e8d5c', 'i4', 'test', NULL, NULL);


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

