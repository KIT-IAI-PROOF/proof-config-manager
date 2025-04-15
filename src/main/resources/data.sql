--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.4 (Homebrew)

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
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workflow
VALUES (NULL, 0, '2025-04-15 11:30:29.889', '2025-04-15 11:30:29.889', 'anonymous', 'Workflow',
        '48a521cf-131f-4d8a-a7f1-60cc52a189aa', 'Workflow', 'anonymous', 'Workflow');


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
VALUES (0, '2025-04-15 11:30:29.755', '2025-04-15 11:30:29.836', 'anonymous', 'test', 'test.py',
        '23d7a190-aa5e-470d-8cf4-2dfe41ae6153', 'test', 'anonymous', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-15 11:30:29.79', '2025-04-15 11:30:29.838', 'anonymous', 'test', 'test.py',
        'f6fe9676-3322-45e5-be5a-9bf2f4fc6317', 'test', 'anonymous', NULL);
INSERT INTO public.program
VALUES (0, '2025-04-15 11:30:29.819', '2025-04-15 11:30:29.839', 'anonymous', 'test', 'test.py',
        '928eeaa6-8666-43d5-9e7d-2a81c0e9994d', 'test', 'anonymous', NULL);


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.763', '2025-04-15 11:30:29.763', 'anonymous', 'test1',
        '75b7ee74-3a9c-454e-a089-4596c70eb50b', 'Attachment1', 'anonymous', '/proof/latest:test1', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.766', '2025-04-15 11:30:29.766', 'anonymous', 'test2',
        'fc53d2c5-edc8-4659-b39e-4eda3c2a577a', 'Attachment2', 'anonymous', '/proof/latest:test2', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.767', '2025-04-15 11:30:29.767', 'anonymous', 'test3',
        '0cda37a2-21b6-4fcf-a472-fb72db7174c4', 'Attachment3', 'anonymous', '/proof/latest:test3', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.792', '2025-04-15 11:30:29.792', 'anonymous', 'test1',
        '7fd27b60-2e25-4be9-a900-e53de0276d75', 'Attachment1', 'anonymous', '/proof/latest:test1', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.793', '2025-04-15 11:30:29.793', 'anonymous', 'test2',
        '6b2a1052-cf20-461f-a35e-3ff4dbdbb8c1', 'Attachment2', 'anonymous', '/proof/latest:test2', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.794', '2025-04-15 11:30:29.794', 'anonymous', 'test3',
        '73a71645-4a68-47b3-9623-d444fff58c6a', 'Attachment3', 'anonymous', '/proof/latest:test3', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.82', '2025-04-15 11:30:29.82', 'anonymous', 'test1',
        'd418a1d8-24c6-4c1b-895d-1e1647f04260', 'Attachment1', 'anonymous', '/proof/latest:test1', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.821', '2025-04-15 11:30:29.821', 'anonymous', 'test2',
        '7b7d9b2e-bc00-4792-bdb4-940e44e34df1', 'Attachment2', 'anonymous', '/proof/latest:test2', NULL);
INSERT INTO public.attachment
VALUES ('2025-04-15 11:30:29.822', '2025-04-15 11:30:29.822', 'anonymous', 'test3',
        '6bb5b643-4668-4f63-bd8a-9cacd75bc375', 'Attachment3', 'anonymous', '/proof/latest:test3', NULL);


--
-- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block
VALUES (NULL, NULL, NULL, NULL, NULL, '2025-04-15 11:30:29.891', '2025-04-15 11:30:29.891', NULL,
        'proof-standard:latest', 'anonymous', 'A template for Reader2025-04-15T11:30:29.886936',
        'a71183a3-7408-4ea8-9ab6-25faf54f0d83', 'Reader2025-04-15T11:30:29.886897', 'anonymous', NULL,
        'c183f9ab-2bbb-4abc-9a40-f241e2006d67', 'Reader', 'block', '48a521cf-131f-4d8a-a7f1-60cc52a189aa');
INSERT INTO public.block
VALUES (NULL, NULL, NULL, NULL, NULL, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892', NULL,
        'proof-standard:latest', 'anonymous', 'A template for Transformer2025-04-15T11:30:29.887364',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'Transformer2025-04-15T11:30:29.887354', 'anonymous', NULL,
        '24a41d25-1d95-4860-abcd-608ab8dd219d', 'Transformer', 'block', '48a521cf-131f-4d8a-a7f1-60cc52a189aa');
INSERT INTO public.block
VALUES (NULL, NULL, NULL, NULL, NULL, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894', NULL,
        'proof-standard:latest', 'anonymous', 'A template for Writer2025-04-15T11:30:29.887430',
        'baed331e-4cf8-4928-a611-0afaa04aef07', 'Writer2025-04-15T11:30:29.887426', 'anonymous', NULL,
        '13606b6a-5ebd-409d-9cb7-3a73674c37af', 'Writer', 'block', '48a521cf-131f-4d8a-a7f1-60cc52a189aa');


--
-- Data for Name: block_interface; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.template
VALUES (0, NULL, '2025-04-15 11:30:29.769', '2025-04-15 11:30:29.838', '#0D41E1', 'proof-standard:latest', 'anonymous',
        'A template for Reader', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', 'anonymous', 'Reader',
        '23d7a190-aa5e-470d-8cf4-2dfe41ae6153', 'block');
INSERT INTO public.template
VALUES (0, NULL, '2025-04-15 11:30:29.794', '2025-04-15 11:30:29.839', '#0C63E7', 'proof-standard:latest', 'anonymous',
        'A template for Transformer', '24a41d25-1d95-4860-abcd-608ab8dd219d', 'anonymous', 'Transformer',
        'f6fe9676-3322-45e5-be5a-9bf2f4fc6317', 'block');
INSERT INTO public.template
VALUES (0, NULL, '2025-04-15 11:30:29.822', '2025-04-15 11:30:29.839', '#0A85ED', 'proof-standard:latest', 'anonymous',
        'A template for Writer', '13606b6a-5ebd-409d-9cb7-3a73674c37af', 'anonymous', 'Writer',
        '928eeaa6-8666-43d5-9e7d-2a81c0e9994d', 'block');


--
-- Data for Name: block_io_definition; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: block_start_parameters; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.block_start_parameters
VALUES ('test', 'test', 'a71183a3-7408-4ea8-9ab6-25faf54f0d83');
INSERT INTO public.block_start_parameters
VALUES ('test', 'test', '7a4e78b2-1809-41a8-b2e7-a98afc072226');
INSERT INTO public.block_start_parameters
VALUES ('test', 'test', 'baed331e-4cf8-4928-a611-0afaa04aef07');


--
-- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
--


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
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.776', '2025-04-15 11:30:29.776', NULL, 'anonymous', 'output 1',
        'c8c96394-8e9c-4296-83a8-067afb176d04', 'o1', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.778', '2025-04-15 11:30:29.778', NULL, 'anonymous', 'output 2',
        'e496bf6c-d567-48a1-a240-d60e0c434534', 'o2', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.78', '2025-04-15 11:30:29.78', NULL, 'anonymous', 'output 3',
        'fd22f2c5-2abc-44c0-8e94-94fd7e69ee0c', 'o3', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.782', '2025-04-15 11:30:29.782', NULL, 'anonymous', 'output 4',
        '46abcb20-bba7-44d3-85f2-744ef27d6b48', 'o4', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.8', '2025-04-15 11:30:29.8', NULL, 'anonymous', 'output 1',
        '137588fc-d731-4f86-956e-60e67acfe00f', 'o1', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.802', '2025-04-15 11:30:29.802', NULL, 'anonymous', 'output 2',
        'fa869286-8eff-4501-9720-d5f06116c75b', 'o2', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.803', '2025-04-15 11:30:29.803', NULL, 'anonymous', 'output 3',
        'cb655bb4-694a-4e7c-830c-2f762fb57417', 'o3', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.805', '2025-04-15 11:30:29.805', NULL, 'anonymous', 'output 4',
        'f5e80a27-8891-47ae-9974-36fa13682907', 'o4', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.806', '2025-04-15 11:30:29.806', NULL, 'anonymous', 'output 5',
        'ef609f7c-6246-4a1a-bdfb-0812874900cb', 'o5', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.808', '2025-04-15 11:30:29.808', NULL, 'anonymous', 'output 6',
        '109c5825-7c17-4e72-9cdc-d9e1a23715eb', 'o6', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.823', '2025-04-15 11:30:29.823', NULL, 'anonymous', 'output 1',
        '0d387f47-0f7a-4152-81e1-d43e881d6242', 'o1', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.824', '2025-04-15 11:30:29.824', NULL, 'anonymous', 'output 2',
        '64e7e2a3-8b2a-4134-83e3-06a57777feb2', 'o2', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892',
        'a71183a3-7408-4ea8-9ab6-25faf54f0d83', 'anonymous', 'output 1', '5338a8b5-d843-4f21-a242-904ccd967c26', 'o1',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892',
        'a71183a3-7408-4ea8-9ab6-25faf54f0d83', 'anonymous', 'output 2', '47ab65b3-c6d8-4891-8523-74b43c495d2f', 'o2',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892',
        'a71183a3-7408-4ea8-9ab6-25faf54f0d83', 'anonymous', 'output 3', '31d02ea0-cb18-44b3-a203-26f5bae99f77', 'o3',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892',
        'a71183a3-7408-4ea8-9ab6-25faf54f0d83', 'anonymous', 'output 4', '26910fbb-7dc5-4c23-9a30-0496cd214d7b', 'o4',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 1', '09bfdc3e-b99a-4f3c-9c0f-58d103d06007', 'o1',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 2', '7d344b7b-336b-468a-9e98-01524a1b900f', 'o2',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 3', 'faf73716-dacc-4263-a1ea-9b44fd58314c', 'o3',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 4', 'bb5271e9-99d0-42de-a834-6fe94d3b0bdf', 'o4',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 5', '73318db8-4494-4f96-b754-857aed0a501d', 'o5',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893',
        '7a4e78b2-1809-41a8-b2e7-a98afc072226', 'anonymous', 'output 6', '048f31ca-a68c-460e-af11-9d543318eb7f', 'o6',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894',
        'baed331e-4cf8-4928-a611-0afaa04aef07', 'anonymous', 'output 1', 'dad1af9b-0d23-4369-ad94-e0d566959e90', 'o1',
        'anonymous', NULL, NULL);
INSERT INTO public.input
VALUES (0, NULL, 2, NULL, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894',
        'baed331e-4cf8-4928-a611-0afaa04aef07', 'anonymous', 'output 2', '04e2a10b-0a4f-4def-bc94-8b52b1a6d20e', 'o2',
        'anonymous', NULL, NULL);


--
-- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.785', '2025-04-15 11:30:29.785', NULL, 'anonymous', 'input 1',
        'f612c70c-a6da-4120-bed2-c1f538fbff76', 'i1', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.788', '2025-04-15 11:30:29.788', NULL, 'anonymous', 'input 2',
        '41ed98c5-32fc-4758-9489-5f14c0c3d140', 'i2', 'anonymous', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.81', '2025-04-15 11:30:29.81', NULL, 'anonymous', 'input 1',
        'a309dae0-458e-45cf-b041-16cc83a1ac56', 'i1', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.812', '2025-04-15 11:30:29.812', NULL, 'anonymous', 'input 2',
        'b41c765a-afd4-47a0-bc00-f6b035a65a10', 'i2', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.814', '2025-04-15 11:30:29.814', NULL, 'anonymous', 'input 3',
        'bd158c74-000a-4b87-93f4-f6d968a49b39', 'i3', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.815', '2025-04-15 11:30:29.815', NULL, 'anonymous', 'input 4',
        'a7a0e089-9751-47f7-aa99-bbb4af77ab1c', 'i4', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.817', '2025-04-15 11:30:29.817', NULL, 'anonymous', 'input 5',
        '8c4f3440-ff22-4779-a120-14b898baf180', 'i5', 'anonymous', '24a41d25-1d95-4860-abcd-608ab8dd219d', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.826', '2025-04-15 11:30:29.826', NULL, 'anonymous', 'input 1',
        '0989bd7d-d83d-4b1f-9703-34cec1d8d80f', 'i1', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.827', '2025-04-15 11:30:29.827', NULL, 'anonymous', 'input 2',
        '51ccc267-83f2-4153-aa75-60716a4bd3f9', 'i2', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.829', '2025-04-15 11:30:29.829', NULL, 'anonymous', 'input 3',
        'b72065a5-a71e-4b30-8e05-95f3e61b0b04', 'i3', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.83', '2025-04-15 11:30:29.83', NULL, 'anonymous', 'input 4',
        '83e88e50-57a0-494c-9e70-8e77bccf6aeb', 'i4', 'anonymous', '13606b6a-5ebd-409d-9cb7-3a73674c37af', NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892', 'a71183a3-7408-4ea8-9ab6-25faf54f0d83',
        'anonymous', 'input 1', '7df3c597-b2f4-4388-b2aa-398d2f5ac90a', 'i1', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.892', '2025-04-15 11:30:29.892', 'a71183a3-7408-4ea8-9ab6-25faf54f0d83',
        'anonymous', 'input 2', '332a9c31-cf4a-4523-9149-ffa25d57ca24', 'i2', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893', '7a4e78b2-1809-41a8-b2e7-a98afc072226',
        'anonymous', 'input 1', '33830cde-b874-40d2-8862-c5c047a81bd0', 'i1', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893', '7a4e78b2-1809-41a8-b2e7-a98afc072226',
        'anonymous', 'input 2', 'a81b17fc-7f7d-4a9e-a29e-923781e73c0a', 'i2', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893', '7a4e78b2-1809-41a8-b2e7-a98afc072226',
        'anonymous', 'input 3', '6d9e174c-b561-406b-a107-b17b1f414543', 'i3', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893', '7a4e78b2-1809-41a8-b2e7-a98afc072226',
        'anonymous', 'input 4', '807da329-6aa6-4436-8648-1e764f90048f', 'i4', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.893', '2025-04-15 11:30:29.893', '7a4e78b2-1809-41a8-b2e7-a98afc072226',
        'anonymous', 'input 5', '6b4847e2-1091-4bee-aaae-2bcde6d508da', 'i5', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894', 'baed331e-4cf8-4928-a611-0afaa04aef07',
        'anonymous', 'input 1', 'ff973903-fb83-4452-8613-9644249ba4e8', 'i1', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894', 'baed331e-4cf8-4928-a611-0afaa04aef07',
        'anonymous', 'input 2', 'b37d0722-7d12-4ff3-87c3-aee112f0e2a7', 'i2', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894', 'baed331e-4cf8-4928-a611-0afaa04aef07',
        'anonymous', 'input 3', '066e6784-aa4f-4088-928c-49b607dd0e8d', 'i3', 'anonymous', NULL, NULL);
INSERT INTO public.output
VALUES (0, NULL, 2, 2, '2025-04-15 11:30:29.894', '2025-04-15 11:30:29.894', 'baed331e-4cf8-4928-a611-0afaa04aef07',
        'anonymous', 'input 4', '6a531786-2208-4349-ab7c-d54e102aca4d', 'i4', 'anonymous', NULL, NULL);


--
-- Data for Name: parameters; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
--


--
-- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.step_based_config
VALUES (0, 0, 0, '2025-04-15 11:30:29.896', 0, 0, '2025-04-15 11:30:29.896', 0, 'anonymous',
        '60c72570-66c0-4c78-8dc3-e8785d0e69f2', 'anonymous', '48a521cf-131f-4d8a-a7f1-60cc52a189aa');


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
VALUES ('path', 'test', 'c183f9ab-2bbb-4abc-9a40-f241e2006d67');
INSERT INTO public.template_start_parameters
VALUES ('path', 'test', '24a41d25-1d95-4860-abcd-608ab8dd219d');
INSERT INTO public.template_start_parameters
VALUES ('path', 'test', '13606b6a-5ebd-409d-9cb7-3a73674c37af');


--
-- PostgreSQL database dump complete
--

