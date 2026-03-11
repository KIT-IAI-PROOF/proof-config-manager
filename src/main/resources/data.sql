DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM public.workflow WHERE id = 'FileReader2FileWriter') THEN
        --
        -- PostgreSQL database dump
        --

        -- Dumped from database version 17.6
        -- Dumped by pg_dump version 17.6

        --
        -- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date, default_step_size, duration, end_point, end_time, start_point, start_time)
        VALUES ('ee527d0c-8428-4f5d-ab55-e883a1cbde2b', 'proof', '2026-02-06 11:24:39.858655+00', 'proof', '2026-02-06 11:24:50.398361+00', 1, 2000, 10, 1000, 0, 0)
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date, communication_paradigm, description, label, simulation_strategy, step_based_config_id)
        VALUES ('FileReader2FileWriter', 'proof', '2026-02-06 11:23:09.547596+00', 'proof', '2026-02-06 11:24:50.398471+00', 0,
                'A simple test workflow that reads from a file and writes to another file', 'FileReader-to-FileWriter', 3, 'ee527d0c-8428-4f5d-ab55-e883a1cbde2b')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: execution; Type: TABLE DATA; Schema: public; Owner: admin
        --


        --
        -- Data for Name: applied_inputs; Type: TABLE DATA; Schema: public; Owner: admin
        --


        --
        -- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_file_reader', NULL, '2026-02-06 11:22:03.833729+00', NULL, NULL, 'provide the workflow with lines from a given file for each SYNC', 'attachment_file_reader',
                'id_attachment_file_reader/file_reader.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_file_writer', NULL, '2026-02-06 11:22:16.466608+00', NULL, NULL, 'Write data from an input line to a file', 'attachment_file_writer', 'id_attachment_file_writer/file_writer.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_float_2_string', NULL, '2026-02-06 11:22:21.695596+00', NULL, NULL, 'convert a float input to a string output without waiting for sync', 'attachment_float_2_string',
                'id_attachment_float_2_string/float_2_string_converter.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_string_2_float', NULL, '2026-02-06 11:22:28.492579+00', NULL, NULL, 'convert a string input to a float output without waiting for sync', 'attachment_string_2_float',
                'id_attachment_string_2_float/string_2_float_converter.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_proc_time_sim', NULL, '2026-02-06 11:22:33.170691+00', NULL, NULL, 'A simple block that simulates a long-lasting processing time.', 'attachment_proc_time_sim',
                'id_attachment_proc_time_sim/proc_time_sim.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('id_attachment_tss_writer_4_values', NULL, '2026-02-06 11:22:39.454108+00', NULL, NULL, 'write/post 4 values as time series data to the url of TSS', 'attachment_tss_writer_4_values',
                'id_attachment_tss_writer_4_values/tss_writer_4_values.py')
        ON CONFLICT DO NOTHING;

        --
        -- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_prog_file_reader', NULL, '2026-02-06 11:22:03.833729+00', NULL, NULL, 'provide the workflow with lines from a given file for each SYNC', 'id_attachment_file_reader', 'prog_file_reader', 0,
                'fileReader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_prog_file_writer', NULL, '2026-02-06 11:22:16.466608+00', NULL, NULL, 'Write data from an input line to a file', 'id_attachment_file_writer', 'prog_file_writer', 0, 'fileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_prog_float_2_string', NULL, '2026-02-06 11:22:21.695596+00', NULL, NULL, 'convert a float input to a string output without waiting for sync', 'id_attachment_float_2_string',
                'prog_float_2_string', 0, 'float2string')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_prog_string_2_float', NULL, '2026-02-06 11:22:28.492579+00', NULL, NULL, 'convert a string input to a float output without waiting for sync', 'id_attachment_string_2_float',
                'prog_string_2_float', 0, 'string2float')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_proc_time_sim_program', NULL, '2026-02-06 11:22:33.170691+00', NULL, NULL, 'A simple block that simulates a long-lasting processing time.', 'id_attachment_proc_time_sim',
                'proc_time_sim_program', 0, 'procTimeSim')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('id_prog_tss_writer_4_values', NULL, '2026-02-06 11:22:39.454108+00', NULL, NULL, 'post up to 4 values to the time series service TSS', 'id_attachment_tss_writer_4_values',
                'prog_tss_writer_4_values', 0, 'tss')
        ON CONFLICT DO NOTHING;

        --
        -- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
        VALUES ('Block_FileReader', 'proof', '2026-02-06 11:24:50.376483+00', 'proof', '2026-02-06 11:24:50.398608+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'provide the workflow with lines from a given file for each SYNC', 1, 1, 'File Reader', 204, 227, true, NULL, 0, 'id_template_file_reader', 'File Reader', 'block', 'id_prog_file_reader',
                'FileReader2FileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
        VALUES ('Block_FileWriter', 'proof', '2026-02-06 11:24:50.388598+00', 'proof', '2026-02-06 11:24:50.398969+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'Write data from an input line to a file', 2, 1, 'File Writer', 443, 227, false, NULL, 0, 'id_template_file_writer', 'File Writer', 'block', 'id_prog_file_writer',
                'FileReader2FileWriter')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('81815e0f-cb92-457a-99a7-463c07b74272', 'proof', '2026-02-06 11:24:50.397354+00', 'proof', '2026-02-06 11:24:50.399151+00', 'bezier-catmull-rom', NULL, 'STRING',
                '884c806c-e0f0-451a-a833-8b557e38f5b7', 'bdf9d634-b50d-46f2-b40a-0218be59e8e3', 'Block_FileReader', 'Block_FileWriter', 'block',
                'FileReader2FileWriter')
        ON CONFLICT DO NOTHING;

        --
        -- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_template_file_reader', 'proof', '2026-02-06 11:22:03.871892+00', 'proof', '2026-02-06 11:22:03.871895+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'provide the workflow with lines from a given file for each SYNC', 1, 'File Reader', true, NULL, 0, 'block', 'id_prog_file_reader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_template_file_writer', 'proof', '2026-02-06 11:22:16.504007+00', 'proof', '2026-02-06 11:22:16.504011+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'Write data from an input line to a file', 1, 'File Writer', false, NULL, 0, 'block', 'id_prog_file_writer')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_template_float_2_string', 'proof', '2026-02-06 11:22:21.7428+00', 'proof', '2026-02-06 11:22:21.742803+00', 0, '#37e142', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'float to string converter that does not wait for sync', 1, 'Float2String', false, NULL, 2, 'block', 'id_prog_float_2_string')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_template_string_2_float', 'proof', '2026-02-06 11:22:28.531754+00', 'proof', '2026-02-06 11:22:28.531759+00', 0, '#37e142', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'string to float converter that does not wait for sync', 1, 'String2Float', false, NULL, 2, 'block', 'id_prog_string_2_float')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_ProcTimeSim', 'proof', '2026-02-06 11:22:33.209293+00', 'proof', '2026-02-06 11:22:33.209296+00', 0, '#009000', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0',
                'A simple block that simulates a long-lasting processing time.', 1, 'ProcTimeSim', false, NULL, 2, 'block', 'id_proc_time_sim_program')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, type, program_id)
        VALUES ('id_template_tss_writer_4_values', 'proof', '2026-02-06 11:22:39.498172+00', 'proof', '2026-02-06 11:22:39.498175+00', 0, '#009000', 0,
                'ghcr.io/kit-iai-proof/proof-worker-python:1.0.0', 'Write up to 4 values to the Time Series Service', 1, 'TSS writer 4 Vals', false, NULL, 0, 'block', 'id_prog_tss_writer_4_values')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('097ebd19-4b21-48b4-8960-755dd9c5438a', NULL, NULL, NULL, NULL, 3, 'Path to the input file to read lines from', 'file_name', 'file_name', 1, 0, NULL, true, 'id_template_file_reader', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('30d2cb63-b919-4831-9bc2-cc884164fb43', NULL, NULL, NULL, NULL, 3, 'Number of steps to perform reading one line from the input file. Set to -1 to read the entire file.', 'num_steps',
                'num_steps', 1, 1, NULL, false, 'id_template_file_reader', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('d51460ad-2d78-4636-81b2-5607989645e1', NULL, NULL, NULL, NULL, 3, 'Whether to ignore the first line of the input file (e.g., header line)', 'ignore_first_line', 'ignore_first_line', 1, 0,
                NULL, false, 'id_template_file_reader', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('e0702a54-a31a-4da1-ab7d-6da33fa6cd97', NULL, NULL, NULL, NULL, 3, 'Path to the output file where data will be written', 'file name', 'file_name', 1, 0, NULL, true, 'id_template_file_writer',
                NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('5a24ab4d-7b0d-4d83-b987-e591c3dbb400', NULL, NULL, NULL, NULL, 3, 'append mode (a) or overwrite mode (w)', 'mode', 'mode', 1, 0, NULL, false, 'id_template_file_writer', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('53f6c3b3-3a0a-49c5-8817-5ba9ee410c9c', NULL, NULL, NULL, NULL, 3, 'Whether to add an additional Carriage Return and Line Feed after each write (true/false)', 'additionalCRLF',
                'additionalCRLF', 1, 0, NULL, false, 'id_template_file_writer', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('e2a72ee2-84cb-4145-a024-d58a2998d0ff', NULL, NULL, NULL, NULL, 0, 'data to be written to a file', 'data', 'data', 2, 0, NULL, false, 'id_template_file_writer', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('f6c64d69-7f2c-4741-bb7a-3159c9ee2a5e', NULL, NULL, NULL, NULL, 0, 'input as Float', 'in', 'float_input', 2, 2, NULL, false, 'id_template_float_2_string', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('0cfb96b5-9935-4560-bbab-d25246eee29f', NULL, NULL, NULL, NULL, 0, 'input as String', 'in', 'string_input', 2, 0, NULL, false, 'id_template_string_2_float', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('74fe2de3-08a6-4ba5-826a-3b1bde66a55c', NULL, NULL, NULL, NULL, 3, 'virtual processing time in seconds as Float, default is 1.0', 'proc_time', 'proc_time', 1, 2, NULL, false, 'id_ProcTimeSim',
                NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('a6202eb8-61e4-40fc-a10b-e6b9a1a5093d', NULL, NULL, NULL, NULL, 0, 'optional input as String', 'opt_in', 'opt_input', 2, 0, NULL, NULL, 'id_ProcTimeSim', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('fcce40d7-a0b6-4b47-bf3b-aea4a7040026', NULL, NULL, NULL, NULL, 3, NULL, 'tss_writer_url', 'tss_writer_url', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('aa543813-e5e9-4634-9e16-5d25930e5b98', NULL, NULL, NULL, NULL, 3, NULL, 'tags', 'tags', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('8afb4c7a-24b1-4fbb-93f4-fcef486ec8c5', NULL, NULL, NULL, NULL, 3, NULL, 'valName_1', 'valName_1', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('35094594-1508-47e4-9beb-50b448057d23', NULL, NULL, NULL, NULL, 3, NULL, 'valName_2', 'valName_2', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('e23866bb-8978-4f78-a2bb-fe7fd85b5f61', NULL, NULL, NULL, NULL, 3, NULL, 'valName_3', 'valName_3', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('fc4f3e99-39f1-4418-9512-9bb94129506a', NULL, NULL, NULL, NULL, 3, NULL, 'valName_4', 'valName_4', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('46aa1410-ef82-4359-a589-60d7b0654f98', NULL, NULL, NULL, NULL, 3, NULL, 'timeUnit', 'timeUnit', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('93ee881a-b9d7-42a2-9c1d-e4fbe9dec433', NULL, NULL, NULL, NULL, 3, NULL, 'resetInput', 'resetInput', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('ce895663-9723-4b76-810c-962a0acba70f', NULL, NULL, NULL, NULL, 0, NULL, 'value_1', 'value_1', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('26eb8463-f91e-476e-85fd-01d70822944f', NULL, NULL, NULL, NULL, 0, NULL, 'value_2', 'value_2', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('f72a8fc0-8a7e-4522-bfe4-de6068297927', NULL, NULL, NULL, NULL, 0, NULL, 'value_3', 'value_3', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('3a3c03fa-07f3-4c8f-b945-9dd71bb84d08', NULL, NULL, NULL, NULL, 0, NULL, 'value_4', 'value_4', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('2f72b49e-65bc-4933-8550-a8be06b6a419', NULL, NULL, NULL, NULL, 3, NULL, 'writeCP', 'writeCP', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('077bd354-4124-4192-8b43-7e25004c9586', 'proof', '2026-02-06 11:24:50.382421+00', 'proof', '2026-02-06 11:24:50.382421+00', 3, 'Path to the input file to read lines from', 'file_name',
                'file_name', 1, 0, NULL, true, NULL, 'Block_FileReader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('def99dfe-9fd7-4bdf-9503-1d7e404be719', 'proof', '2026-02-06 11:24:50.384013+00', 'proof', '2026-02-06 11:24:50.384013+00', 3,
                'Number of steps to perform reading one line from the input file. Set to -1 to read the entire file.', 'num_steps', 'num_steps', 1, 1, NULL, false, NULL,
                'Block_FileReader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('3b800d66-0beb-401b-8c3e-9eeac4676c11', 'proof', '2026-02-06 11:24:50.385161+00', 'proof', '2026-02-06 11:24:50.385161+00', 3,
                'Whether to ignore the first line of the input file (e.g., header line)', 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, false, NULL, 'Block_FileReader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('e6a7406b-79c7-498c-aace-914f87b1bc59', 'proof', '2026-02-06 11:24:50.389663+00', 'proof', '2026-02-06 11:24:50.389663+00', 3, 'Path to the output file where data will be written', 'file name',
                'file_name', 1, 0, NULL, true, NULL, 'Block_FileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('39c8bb64-0b29-481e-a40d-64a0912bcdda', 'proof', '2026-02-06 11:24:50.390487+00', 'proof', '2026-02-06 11:24:50.390487+00', 3, 'append mode (a) or overwrite mode (w)', 'mode', 'mode', 1, 0,
                NULL, false, NULL, 'Block_FileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('6df9f0aa-e5f6-4b3c-befe-8e560d817fac', 'proof', '2026-02-06 11:24:50.390953+00', 'proof', '2026-02-06 11:24:50.390953+00', 3,
                'Whether to add an additional Carriage Return and Line Feed after each write (true/false)', 'additionalCRLF', 'additionalCRLF', 1, 0, NULL, false, NULL, 'Block_FileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required, template_id,
                                block_id)
        VALUES ('884c806c-e0f0-451a-a833-8b557e38f5b7', 'proof', '2026-02-06 11:24:50.391234+00', 'proof', '2026-02-06 11:24:50.391234+00', 0, 'data to be written to a file', 'data', 'data', 2, 0, NULL, false,
                NULL, 'Block_FileWriter')
        ON CONFLICT DO NOTHING;

        --
        -- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('dca148ee-4f34-454e-b449-553e16171d82', NULL, NULL, NULL, NULL, 0, 'Line read from the input file at each SYNC', 'line', 'line', 2, 0, NULL, 'id_template_file_reader', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('0c6ecf49-716d-4398-ab19-0172cd55c53c', NULL, NULL, NULL, NULL, 0, 'output as String', 'out', 'string_output', 2, 0, NULL, 'id_template_float_2_string', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('a9067304-ed12-4094-9272-79f44f3c3627', NULL, NULL, NULL, NULL, 0, 'output as Float', 'out', 'float_output', 2, 2, NULL, 'id_template_string_2_float', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('6c72d75d-ec65-47c3-8c1e-3343e85e71d3', NULL, NULL, NULL, NULL, 0, 'optional output as String', 'opt_out', 'opt_output', 2, 0, NULL, 'id_ProcTimeSim', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('bdf9d634-b50d-46f2-b40a-0218be59e8e3', 'proof', '2026-02-06 11:24:50.386218+00', 'proof', '2026-02-06 11:24:50.386218+00', 0, 'Line read from the input file at each SYNC', 'line', 'line', 2, 0,
                NULL, NULL, 'Block_FileReader')
        ON CONFLICT DO NOTHING;
        
        --
        -- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
        --


        --
        -- Data for Name: program_attached; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_prog_float_2_string', 'id_attachment_float_2_string')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_prog_string_2_float', 'id_attachment_string_2_float')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_proc_time_sim_program', 'id_attachment_proc_time_sim')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_prog_tss_writer_4_values', 'id_attachment_tss_writer_4_values')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_prog_file_reader', 'id_attachment_file_reader')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('id_prog_file_writer', 'id_attachment_file_writer')
        ON CONFLICT DO NOTHING;

        --
        -- Data for Name: step_size_definition; Type: TABLE DATA; Schema: public; Owner: admin
        --


        --
        -- Data for Name: stepsizes; Type: TABLE DATA; Schema: public; Owner: admin
        --


        --
        -- PostgreSQL database dump complete
        --
END IF;

IF NOT EXISTS (SELECT 1 FROM public.workflow WHERE id = 'FileReader2FileWriter') THEN

END IF;
END $$;;