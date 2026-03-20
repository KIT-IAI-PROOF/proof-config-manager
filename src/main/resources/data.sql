DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM public.workflow WHERE id = 'demo_FileReader2FileWriter') THEN
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
            VALUES ('demo_FileReader2FileWriter', 'proof', '2026-02-06 11:23:09.547596+00', 'proof', '2026-02-06 11:24:50.398471+00', 0,
                    'A simple test workflow that reads from a file and writes to another file', 'DEMO_FileReader-to-FileWriter', 3, 'ee527d0c-8428-4f5d-ab55-e883a1cbde2b')
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
            VALUES ('id_attachment_file_writer', NULL, '2026-02-06 11:22:16.466608+00', NULL, NULL, 'Write data from an input line to a file', 'attachment_file_writer',
                    'id_attachment_file_writer/file_writer.py')
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
            VALUES ('id_attachment_tss_writer_4_values', NULL, '2026-02-06 11:22:39.454108+00', NULL, NULL, 'write/post 4 values as time series data to the url of TSS',
                    'attachment_tss_writer_4_values',
                    'id_attachment_tss_writer_4_values/tss_writer_4_values.py')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
            VALUES ('id_attachment_MQTT_Subscriber', 'proof', '2026-03-19 16:40:27.353033+00', 'proof', '2026-03-19 16:40:27.353033+00', 'The attachment subscribing a mosquitto MQTT broker for PROOF', 'attachment_MQTT_Subscriber', 'id_attachment_MQTT_Subscriber/MQTT_Subscriber.py')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
            VALUES ('id_attachment_MQTT_Publisher', 'proof', '2026-03-19 16:41:13.914311+00', 'proof', '2026-03-19 16:41:13.914311+00', 'This module publishes data to a mosquitto MQTT broker for PROOF.', 'attachment_MQTT_Publisher', 'id_attachment_MQTT_Publisher/MQTT_Publisher.py')
            ON CONFLICT DO NOTHING;

            --
            -- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
            --

            INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
            VALUES ('id_prog_file_reader', NULL, '2026-02-06 11:22:03.833729+00', NULL, NULL, 'provide the workflow with lines from a given file for each SYNC', 'id_attachment_file_reader',
                    'prog_file_reader', 0,
                    'fileReader')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
            VALUES ('id_prog_file_writer', NULL, '2026-02-06 11:22:16.466608+00', NULL, NULL, 'Write data from an input line to a file', 'id_attachment_file_writer', 'prog_file_writer', 0,
                    'fileWriter')
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
            INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
            VALUES ('id_prog_MQTT_Subscriber', 'proof', '2026-03-19 16:40:27.350261+00', 'proof', '2026-03-19 16:40:27.356248+00', 'The program subscribing a mosquitto MQTT broker for PROOF.', 'id_attachment_MQTT_Subscriber', 'prog_MQTT_Subscriber', 0, 'MQTT_Subscriber')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
            VALUES ('id_prog_MQTT_Publisher', 'proof', '2026-03-19 16:41:13.913449+00', 'proof', '2026-03-19 16:41:13.915987+00', 'This module publishes data to a mosquitto MQTT broker for PROOF.', 'id_attachment_MQTT_Publisher', 'prog_MQTT_Publisher', 0, 'MQTT_Publisher')
            ON CONFLICT DO NOTHING;

            --
            -- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
            --

        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description, index,
                                interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
        VALUES ('Block_FileReader', 'proof', '2026-02-06 11:24:50.376483+00', 'proof', '2026-02-06 11:24:50.398608+00', 0, '#009000', '#ffffff', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'provide the workflow with lines from a given file for each SYNC', 1, 1, 'File Reader', 204, 227, true, NULL, 0, 'id_template_file_reader', 'File Reader', 'block', 'id_prog_file_reader',
                'demo_FileReader2FileWriter')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description, index,
                                interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, type, program_id, workflow_id)
        VALUES ('Block_FileWriter', 'proof', '2026-02-06 11:24:50.388598+00', 'proof', '2026-02-06 11:24:50.398969+00', 0, '#009000', '#ffffff', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Write data from an input line to a file', 2, 1, 'File Writer', 443, 227, false, NULL, 0, 'id_template_file_writer', 'File Writer', 'block', 'id_prog_file_writer',
                'demo_FileReader2FileWriter')
        ON CONFLICT DO NOTHING;


            --
            -- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
            --

            INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
            VALUES ('56cdf04a-4ff3-471d-87dd-bfad7a9579c1', 'proof', '2026-03-20 08:19:13.062123+00', 'proof', '2026-03-20 08:19:13.063865+00', 'bezier-catmull-rom', NULL, 'STRING',
                    'c1277f43-2813-4611-b3f3-d740e562fe20', 'bdf9d634-b50d-46f2-b40a-0218be59e8e3', 'Block_FileReader', 'Block_FileWriter', 'block',
                    'demo_FileReader2FileWriter')
            ON CONFLICT DO NOTHING;

            --
            -- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
            --

            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_template_file_reader', 'proof', '2026-02-06 11:22:03.871892+00', 'proof', '2026-02-06 11:24:50.399000+00', 0, '#009000', '#ffffff', 0,
                    'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                    'provide the workflow with lines from a given file for each SYNC', 1, 'File Reader', true, NULL, 0, 'block', 'id_prog_file_reader')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_template_file_writer', 'proof', '2026-02-06 11:22:16.504007+00', 'proof', '2026-02-06 11:24:50.400000+00', 0, '#009000', '#ffffff', 0,
                    'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                    'Write data from an input line to a file', 1, 'File Writer', false, NULL, 0, 'block', 'id_prog_file_writer')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_template_float_2_string', 'proof', '2026-02-06 11:22:21.7428+00', 'proof', '2026-02-06 11:24:50.399000+00', 0, '#37e142', '#ffffff', 0,
                    'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                    'float to string converter that does not wait for sync', 1, 'Float2String', false, NULL, 2, 'block', 'id_prog_float_2_string')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_template_string_2_float', 'proof', '2026-02-06 11:22:28.531754+00', 'proof', '2026-02-06 11:24:50.399100+00', 0, '#37e142', '#ffffff', 0,
                    'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                    'string to float converter that does not wait for sync', 1, 'String2Float', false, NULL, 2, 'block', 'id_prog_string_2_float')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_ProcTimeSim', 'proof', '2026-02-06 11:22:33.209293+00', 'proof', '2026-02-06 11:24:50.399200+00', 0, '#009000', '#ffffff', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                    'A simple block that simulates a long-lasting processing time.', 1, 'ProcTimeSim', false, NULL, 2, 'block', 'id_proc_time_sim_program')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, text_color, communication_paradigm, container_image, description,
                                         interface_type,
                                name,
                                         shutdown_relevant, status, sync_strategy, type, program_id)
            VALUES ('id_template_tss_writer_4_values', 'proof', '2026-02-06 11:22:39.498172+00', 'proof', '2026-02-06 11:24:50.399300+00', 0, '#009000', '#ffffff', 0,
                    'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0', 'Write up to 4 values to the Time Series Service', 1, 'TSS writer 4 Vals', false, NULL, 0, 'block',
                    'id_prog_tss_writer_4_values')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, text_color, type, program_id)
            VALUES ('id_template_MQTT_Subscriber', 'proof', '2026-03-19 16:40:27.354766+00', 'proof', '2026-03-19 16:41:13.916100+00', 0, '#3d3d3d', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0', 'This module subscribes data from a mosquitto MQTT broker for PROOF.', 1, 'MQTT Subscriber', false, NULL, 0, NULL, 'block', 'id_prog_MQTT_Subscriber')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name, shutdown_relevant, status, sync_strategy, text_color, type, program_id)
            VALUES ('id_template_MQTT_Publisher', 'proof', '2026-03-19 16:41:13.914519+00', 'proof', '2026-03-19 16:41:13.916200+00', 0, '#3d3d3d', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0', 'This module publishes data to a mosquitto MQTT broker for PROOF.', 1, 'MQTT Publisher', false, NULL, 0, NULL, 'block', 'id_prog_MQTT_Publisher')
            ON CONFLICT DO NOTHING;


            --
            -- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
            --

            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('097ebd19-4b21-48b4-8960-755dd9c5438a', NULL, NULL, NULL, NULL, 3, 'Path to the input file to read lines from', 'file_name', 'file_name', 1, 4, NULL, NULL, true,
                    'id_template_file_reader', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('30d2cb63-b919-4831-9bc2-cc884164fb43', NULL, NULL, NULL, NULL, 3, 'Number of steps to perform reading one line from the input file. Set to -1 to read the entire file.',
                    'num_steps',
                    'num_steps', 1, 1, NULL, '-1', false, 'id_template_file_reader', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('d51460ad-2d78-4636-81b2-5607989645e1', NULL, NULL, NULL, NULL, 3, 'Whether to ignore the first line of the input file (e.g., header line)', 'ignore_first_line',
                    'ignore_first_line', 1, 0,
                    NULL, 'false', false, 'id_template_file_reader', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('e0702a54-a31a-4da1-ab7d-6da33fa6cd97', NULL, NULL, NULL, NULL, 3, 'Path to the output file where data will be written', 'file name', 'file_name', 1, 4, NULL, NULL, true,
                    'id_template_file_writer',
                    NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('5a24ab4d-7b0d-4d83-b987-e591c3dbb400', NULL, NULL, NULL, NULL, 3, 'append mode (a) or overwrite mode (w)', 'mode', 'mode', 1, 0, NULL, 'w', false, 'id_template_file_writer', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('53f6c3b3-3a0a-49c5-8817-5ba9ee410c9c', NULL, NULL, NULL, NULL, 3, 'Whether to add an additional Carriage Return and Line Feed after each write (true/false)', 'additionalCRLF',
                    'additionalCRLF', 1, 0, NULL, 'false', false, 'id_template_file_writer', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('e2a72ee2-84cb-4145-a024-d58a2998d0ff', NULL, NULL, NULL, NULL, 0, 'data to be written to a file', 'data', 'data', 2, 0, NULL, false, 'id_template_file_writer', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('f6c64d69-7f2c-4741-bb7a-3159c9ee2a5e', NULL, NULL, NULL, NULL, 0, 'input as Float', 'in', 'float_input', 2, 2, NULL, false, 'id_template_float_2_string', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('0cfb96b5-9935-4560-bbab-d25246eee29f', NULL, NULL, NULL, NULL, 0, 'input as String', 'in', 'string_input', 2, 0, NULL, false, 'id_template_string_2_float', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('74fe2de3-08a6-4ba5-826a-3b1bde66a55c', NULL, NULL, NULL, NULL, 3, 'virtual processing time in seconds as Float, default is 1.0', 'proc_time', 'proc_time', 1, 2, NULL, false,
                    'id_ProcTimeSim',
                    NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('a6202eb8-61e4-40fc-a10b-e6b9a1a5093d', NULL, NULL, NULL, NULL, 0, 'optional input as String', 'opt_in', 'opt_input', 2, 0, NULL, NULL, 'id_ProcTimeSim', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('fcce40d7-a0b6-4b47-bf3b-aea4a7040026', NULL, NULL, NULL, NULL, 3, NULL, 'tss_writer_url', 'tss_writer_url', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('aa543813-e5e9-4634-9e16-5d25930e5b98', NULL, NULL, NULL, NULL, 3, NULL, 'tags', 'tags', 1, 0, NULL, true, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('8afb4c7a-24b1-4fbb-93f4-fcef486ec8c5', NULL, NULL, NULL, NULL, 3, NULL, 'valName_1', 'valName_1', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('35094594-1508-47e4-9beb-50b448057d23', NULL, NULL, NULL, NULL, 3, NULL, 'valName_2', 'valName_2', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('e23866bb-8978-4f78-a2bb-fe7fd85b5f61', NULL, NULL, NULL, NULL, 3, NULL, 'valName_3', 'valName_3', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('fc4f3e99-39f1-4418-9512-9bb94129506a', NULL, NULL, NULL, NULL, 3, NULL, 'valName_4', 'valName_4', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('46aa1410-ef82-4359-a589-60d7b0654f98', NULL, NULL, NULL, NULL, 3, NULL, 'timeUnit', 'timeUnit', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('93ee881a-b9d7-42a2-9c1d-e4fbe9dec433', NULL, NULL, NULL, NULL, 3, NULL, 'resetInput', 'resetInput', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('ce895663-9723-4b76-810c-962a0acba70f', NULL, NULL, NULL, NULL, 0, NULL, 'value_1', 'value_1', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('26eb8463-f91e-476e-85fd-01d70822944f', NULL, NULL, NULL, NULL, 0, NULL, 'value_2', 'value_2', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('f72a8fc0-8a7e-4522-bfe4-de6068297927', NULL, NULL, NULL, NULL, 0, NULL, 'value_3', 'value_3', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('3a3c03fa-07f3-4c8f-b945-9dd71bb84d08', NULL, NULL, NULL, NULL, 0, NULL, 'value_4', 'value_4', 2, 2, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, required,
                                      template_id,
                                      block_id)
            VALUES ('2f72b49e-65bc-4933-8550-a8be06b6a419', NULL, NULL, NULL, NULL, 3, NULL, 'writeCP', 'writeCP', 1, 0, NULL, false, 'id_template_tss_writer_4_values', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('33ee8853-3b58-480c-acbd-2a71ce807466', 'proof', '2026-03-19 16:40:27.355051+00', 'proof', '2026-03-19 16:40:27.355051+00', 3, 'The host name', 'hostname', 'hostname', 1, 0, NULL, NULL, true, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('8250d141-453b-4832-8e7d-349091ab1e36', 'proof', '2026-03-19 16:40:27.355203+00', 'proof', '2026-03-19 16:40:27.355203+00', 3, 'The port number', 'port', 'port', 1, 1, NULL, NULL, true, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('63a8a126-3668-42f1-9247-a39737566b65', 'proof', '2026-03-19 16:40:27.355269+00', 'proof', '2026-03-19 16:40:27.355269+00', 3, 'The topic to subscribe to', 'topic', 'topic', 1, 0, NULL, NULL, true, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('2f964f57-ad47-4fd7-a594-a153b212d031', 'proof', '2026-03-19 16:40:27.355328+00', 'proof', '2026-03-19 16:40:27.355328+00', 3, 'The timeout in seconds for connecting to the broker', 'timeout', 'timeout', 1, 1, 'seconds', '1800', false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('b883379e-1bec-4850-91ad-9f580a2135c3', 'proof', '2026-03-19 16:40:27.355395+00', 'proof', '2026-03-19 16:40:27.355395+00', 3, 'The number of messages to wait for before proceeding', 'expected_message_count', 'expected_message_count', 1, 1, NULL, '1', false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('3ebf7abf-5984-4eb2-8eeb-40037c3e6746', 'proof', '2026-03-19 16:40:27.355451+00', 'proof', '2026-03-19 16:40:27.355451+00', 3, 'The client identifier', 'client_id', 'client_id', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('902d99be-198a-4da3-9dc9-ec0ba7307d6c', 'proof', '2026-03-19 16:40:27.355508+00', 'proof', '2026-03-19 16:40:27.355508+00', 3, 'Path to CA certificate file', 'cafile_path', 'cafile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('da5bea59-bdb0-4f04-ac83-343cb7eb0eaa', 'proof', '2026-03-19 16:40:27.3556+00', 'proof', '2026-03-19 16:40:27.3556+00', 3, 'Path to client certificate file', 'certfile_path', 'certfile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('c5693774-05f2-462a-975a-3a3d92602c02', 'proof', '2026-03-19 16:40:27.355678+00', 'proof', '2026-03-19 16:40:27.355678+00', 3, 'Path to client key file', 'keyfile_path', 'keyfile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('d8dff0c3-9b21-4448-b535-1b06628525a7', 'proof', '2026-03-19 16:40:27.355746+00', 'proof', '2026-03-19 16:40:27.355746+00', 3, 'Username for broker authentication', 'username', 'username', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('a6b78a8f-6f8f-4f26-b3e4-729dac0b89d1', 'proof', '2026-03-19 16:40:27.355805+00', 'proof', '2026-03-19 16:40:27.355805+00', 3, 'Password for broker authentication', 'password', 'password', 1, 0, NULL, NULL, false, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('311e866b-96aa-45ff-9c21-ab9ba4f0204d', 'proof', '2026-03-19 16:41:13.914667+00', 'proof', '2026-03-19 16:41:13.914667+00', 3, 'The host name', 'hostname', 'hostname', 1, 0, NULL, NULL, true, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('fda9a8eb-2ee3-414d-a77d-20645595bf10', 'proof', '2026-03-19 16:41:13.91488+00', 'proof', '2026-03-19 16:41:13.91488+00', 3, 'The port number', 'port', 'port', 1, 1, NULL, NULL, true, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('573e80c2-82eb-42fa-82dc-02e69fb07835', 'proof', '2026-03-19 16:41:13.914973+00', 'proof', '2026-03-19 16:41:13.914973+00', 3, 'The topic to publish to', 'topic', 'topic', 1, 0, NULL, NULL, true, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('50f04be1-6042-43cb-8505-2d30340e0d0e', 'proof', '2026-03-19 16:41:13.915036+00', 'proof', '2026-03-19 16:41:13.915036+00', 3, 'The timeout in seconds for connecting to the broker', 'timeout', 'timeout', 1, 1, 'seconds', '5', false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('9cb6a782-e2b1-4f9d-8808-8ab0946bfef6', 'proof', '2026-03-19 16:41:13.915097+00', 'proof', '2026-03-19 16:41:13.915097+00', 0, 'The message to publish', 'message', 'message', 2, 0, NULL, NULL, true, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('9dfdae85-35b8-46df-b362-58fbd211483f', 'proof', '2026-03-19 16:41:13.915158+00', 'proof', '2026-03-19 16:41:13.915158+00', 3, 'The Quality of Service level (0, 1, or 2)', 'QoS', 'QoS', 1, 1, NULL, '0', false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('81fa5ba9-a49f-44e8-bb6c-39bcb9985dbd', 'proof', '2026-03-19 16:41:13.915218+00', 'proof', '2026-03-19 16:41:13.915218+00', 3, 'The client identifier', 'client_id', 'client_id', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('7ca7df3d-22be-4bf1-bf78-a088e0c15550', 'proof', '2026-03-19 16:41:13.915362+00', 'proof', '2026-03-19 16:41:13.915362+00', 3, 'Path to CA certificate file', 'cafile_path', 'cafile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('4b30b79c-d6a9-4588-a8ae-4ad8284e1ee8', 'proof', '2026-03-19 16:41:13.915425+00', 'proof', '2026-03-19 16:41:13.915425+00', 3, 'Path to client certificate file', 'certfile_path', 'certfile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('c6dfa0d8-4b68-4fe2-9c49-e6f80419793a', 'proof', '2026-03-19 16:41:13.915485+00', 'proof', '2026-03-19 16:41:13.915485+00', 3, 'Path to client key file', 'keyfile_path', 'keyfile_path', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('24ee711f-5e6e-4732-82cb-6ef86a66bb16', 'proof', '2026-03-19 16:41:13.91555+00', 'proof', '2026-03-19 16:41:13.91555+00', 3, 'Username for broker authentication', 'username', 'username', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('87ec674b-814d-4271-9c49-02179c98f0b2', 'proof', '2026-03-19 16:41:13.915626+00', 'proof', '2026-03-19 16:41:13.915626+00', 3, 'Password for broker authentication', 'password', 'password', 1, 0, NULL, NULL, false, 'id_template_MQTT_Publisher', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('9073a580-c949-4cfb-9429-37007460a763', NULL, NULL, 'proof', '2026-03-20 08:19:13.063618+00', 3, 'Path to the input file to read lines from',
                    'file_name',
                    'file_name', 1, 4, NULL, NULL, true, NULL, 'Block_FileReader')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('fd0e946c-1ade-4b8a-9c6e-f981cb836710', NULL, NULL, 'proof', '2026-03-20 08:19:13.06367+00', 3,
                    'Number of steps to perform reading one line from the input file. Set to -1 to read the entire file.', 'num_steps', 'num_steps', 1, 1, NULL, '-1', false, NULL,
                    'Block_FileReader')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('0fc09cb2-c951-4cb1-93d1-5340a7d9567b', NULL, NULL, 'proof', '2026-03-20 08:19:13.063725+00', 3,
                    'Whether to ignore the first line of the input file (e.g., header line)', 'ignore_first_line', 'ignore_first_line', 1, 0, NULL, 'false', false, NULL, 'Block_FileReader')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('b31e6b16-5c71-4d65-b29b-1c41b422f99b', NULL, NULL, 'proof', '2026-03-20 08:19:13.063259+00', 3, 'Path to the output file where data will be written',
                    'file name',
                    'file_name', 1, 4, NULL, NULL, true, NULL, 'Block_FileWriter')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('8a9b039b-33cf-42a5-8711-1d7255b6dd43', NULL, NULL, 'proof', '2026-03-20 08:19:13.063364+00', 3, 'append mode (a) or overwrite mode (w)', 'mode',
                    'mode', 1, 0,
                    NULL, 'w', false, NULL, 'Block_FileWriter')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('4b6d12eb-fc27-489a-ab5a-41b349707f60', NULL, NULL, 'proof', '2026-03-20 08:19:13.063424+00', 3,
                    'Whether to add an additional Carriage Return and Line Feed after each write (true/false)', 'additionalCRLF', 'additionalCRLF', 1, 0, NULL, 'false', false, NULL, 'Block_FileWriter')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required,
                                      template_id,
                                      block_id)
            VALUES ('c1277f43-2813-4611-b3f3-d740e562fe20', NULL, NULL, 'proof', '2026-03-20 08:19:13.063489+00', 0, 'data to be written to a file', 'data', 'data', 2, 0,
                    NULL, NULL, false,
                    NULL, 'Block_FileWriter')
            ON CONFLICT DO NOTHING;

            --
            -- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
            --

            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('dca148ee-4f34-454e-b449-553e16171d82', NULL, NULL, NULL, NULL, 0, 'Line read from the input file at each SYNC', 'line', 'line', 2, 0, NULL, 'id_template_file_reader', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('0c6ecf49-716d-4398-ab19-0172cd55c53c', NULL, NULL, NULL, NULL, 0, 'output as String', 'out', 'string_output', 2, 0, NULL, 'id_template_float_2_string', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('a9067304-ed12-4094-9272-79f44f3c3627', NULL, NULL, NULL, NULL, 0, 'output as Float', 'out', 'float_output', 2, 2, NULL, 'id_template_string_2_float', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('6c72d75d-ec65-47c3-8c1e-3343e85e71d3', NULL, NULL, NULL, NULL, 0, 'optional output as String', 'opt_out', 'opt_output', 2, 0, NULL, 'id_ProcTimeSim', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('76a2ead1-a1fb-43ec-ab66-1f2201a2565e', 'proof', '2026-03-19 16:40:27.355887+00', 'proof', '2026-03-19 16:40:27.355887+00', 0, 'The received MQTT messages', 'received_messages', 'received_messages', 2, 0, NULL, 'id_template_MQTT_Subscriber', NULL)
            ON CONFLICT DO NOTHING;
            INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id,
                                       block_id)
            VALUES ('bdf9d634-b50d-46f2-b40a-0218be59e8e3', NULL, NULL, 'proof', '2026-03-20 08:19:13.063563+00', 0, 'Line read from the input file at each SYNC', 'line',
                    'line', 2, 0,
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
            INSERT INTO public.program_attached (program_id, attachment_id)
            VALUES ('id_prog_MQTT_Subscriber', 'id_attachment_MQTT_Subscriber')
            ON CONFLICT DO NOTHING;
            INSERT INTO public.program_attached (program_id, attachment_id)
            VALUES ('id_prog_MQTT_Publisher', 'id_attachment_MQTT_Publisher')
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

IF NOT EXISTS (SELECT 1 FROM public.workflow WHERE id = 'demo_energy-building-model') THEN
        --
        -- PostgreSQL database dump
        --

        -- Dumped from database version 17.6
        -- Dumped by pg_dump version 17.6

        --
        -- Data for Name: step_based_config; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.step_based_config (id, created_by, creation_date, last_modified_by, last_modified_date, default_step_size, duration, end_point, end_time, start_point, start_time)
        VALUES ('56db878b-3b32-47ad-acac-c9a5601f1523', 'proof', '2026-03-17 12:48:22.679557+00', 'proof', '2026-03-17 12:51:41.219664+00', 1, 1000, 24, 1000, 0, 0)
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.workflow (id, created_by, creation_date, last_modified_by, last_modified_date, communication_paradigm, description, label, simulation_strategy, step_based_config_id)
        VALUES ('demo_energy-building-model', 'proof', '2026-03-17 12:48:22.689428+00', 'proof', '2026-03-17 12:48:22.764123+00', 0, 'Simple virtual energy building model', 'DEMO_Energy Building Model', 3, '56db878b-3b32-47ad-acac-c9a5601f1523')
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
        VALUES ('attachment_2_StaticGenerator', 'proof', '2026-03-03 11:53:33.744402+00', 'proof', '2026-03-17 12:48:22.981801+00', 'Simple Building Model: StaticGenerator', 'attachment_2_StaticGenerator',
                'attachment_2_StaticGenerator/2_StaticGenerator.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('attachment_3_Building', 'proof', '2026-03-03 11:53:41.159355+00', 'proof', '2026-03-17 12:48:22.98212+00', 'Simple Building Model: Building', 'attachment_3_Building',
                'attachment_3_Building/3_Building.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('attachment_1_DataFeeder', 'proof', '2026-03-03 16:55:23.477945+00', 'proof', '2026-03-17 12:48:22.982683+00', 'Simple Building Model: DataFeeder', 'attachment_1_DataFeeder',
                'attachment_1_DataFeeder/1_DataFeeder.py')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.attachment (id, created_by, creation_date, last_modified_by, last_modified_date, description, label, path)
        VALUES ('attachment_4_Controller', 'proof', '2026-03-04 11:27:06.391369+00', 'proof', '2026-03-17 12:48:22.983467+00', 'Simple Building Model: Controller', 'attachment_4_Controller',
                'attachment_4_Controller/4_Controller.py')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('prog_2_StaticGenerator', 'proof', '2026-03-03 11:53:33.743406+00', 'proof', '2026-03-17 12:48:22.981865+00', 'Simple Building Model: StaticGenerator', 'attachment_2_StaticGenerator',
                'prog_2_StaticGenerator', 0, 'prog_2_StaticGenerator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('prog_3_Building', 'proof', '2026-03-03 11:53:41.158507+00', 'proof', '2026-03-17 12:48:22.982456+00', 'Simple Building Model: Building', 'attachment_3_Building',
                'prog_3_Building', 0, 'prog_3_Building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('prog_1_DataFeeder', 'proof', '2026-03-03 16:55:23.477945+00', 'proof', '2026-03-17 12:48:22.982744+00', 'Simple Building Model: DataFeeder', 'attachment_1_DataFeeder',
                'prog_1_DataFeeder', 0, 'prog_1_DataFeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program (id, created_by, creation_date, last_modified_by, last_modified_date, description, entry_point, label, runtime, tag)
        VALUES ('prog_4_Controller', 'proof', '2026-03-04 11:27:06.391369+00', 'proof', '2026-03-17 12:48:22.983529+00', 'Simple Building Model: Controller', 'attachment_4_Controller',
                'prog_4_Controller', 0, 'prog_4_Controller')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: block; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, text_color, type, program_id, workflow_id)
        VALUES ('3-building', 'proof', '2026-03-17 12:48:22.707286+00', 'proof', '2026-03-17 12:48:22.764728+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: Building', 3, NULL, '3. Building', 340, 206, true, NULL, 0, 'id_template_3_Building', '3. Building', NULL, 'block', 'prog_3_Building',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, text_color, type, program_id, workflow_id)
        VALUES ('1-datafeeder', 'proof', '2026-03-17 12:48:22.725587+00', 'proof', '2026-03-17 12:48:22.765073+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: DataFeeder', 1, NULL, '1. DataFeeder', 57, 177, true, NULL, 0, 'id_template_1_DataFeeder', '1. DataFeeder', NULL, 'block', 'prog_1_DataFeeder',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, text_color, type, program_id, workflow_id)
        VALUES ('2-static_generator', 'proof', '2026-03-17 12:48:22.731222+00', 'proof', '2026-03-17 12:48:22.765298+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: StaticGenerator', 2, NULL, '2. StaticGenerator', 361, 105, true, NULL, 0, 'id_template_2_StaticGenerator', '2. StaticGenerator', NULL, 'block', 'prog_2_StaticGenerator',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.block (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, index, interface_type, label, x,
                                y, shutdown_relevant, status, sync_strategy, template_id, template_name, text_color, type, program_id, workflow_id)
        VALUES ('4-controller', 'proof', '2026-03-17 12:48:22.738571+00', 'proof', '2026-03-17 12:48:22.765651+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: Controller', 4, NULL, '4. Controller', 424, 332, true, NULL, 0, 'id_template_4_Controller', '4. Controller', NULL, 'block', 'prog_4_Controller',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: connection; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('d29bd71f-e133-41d7-8941-66d579d11916', 'proof', '2026-03-20 08:19:39.277529+00', 'proof', '2026-03-20 08:19:39.286224+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '2fb2b0ad-3c4f-4942-a485-c477da8993b9', '597352fd-0202-4613-8cd2-a5d70f159abe', '1-datafeeder', '3-building', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('1a3701a2-6081-4d10-9d8b-651d0cdfeaf5', 'proof', '2026-03-20 08:19:39.278269+00', 'proof', '2026-03-20 08:19:39.286336+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '7a34e137-6f79-44cf-9fb1-3f08cfbdd693', '4ae757c3-ef43-4f59-b6ed-fc83e3f971ed', '1-datafeeder', '4-controller', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('1dc70de5-2212-4742-ad10-d544aff04762', 'proof', '2026-03-20 08:19:39.278718+00', 'proof', '2026-03-20 08:19:39.287098+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '9172de45-bcce-4474-98c0-abc33e852657', 'e41d9d5f-74d2-4ef0-a338-cb8c0fd7459c', '1-datafeeder', '2-static_generator', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('7b466417-93d2-4c5b-8d26-ae80935918eb', 'proof', '2026-03-20 08:19:39.279038+00', 'proof', '2026-03-20 08:19:39.287212+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '201f6b35-a8fc-4656-8fde-a69c6bd0feb9', '597352fd-0202-4613-8cd2-a5d70f159abe', '1-datafeeder', '2-static_generator', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('00b05d4f-1070-4dd1-9a69-4023acfa8003', 'proof', '2026-03-20 08:19:39.279536+00', 'proof', '2026-03-20 08:19:39.287299+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '6f7156ea-bf27-4e34-8ccc-d8f4ee788506', '67627059-3afa-4d46-a948-d7a9a8b5df0a', '2-static_generator', '4-controller', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('cdb7685f-65db-458b-b002-1eb13f10b191', 'proof', '2026-03-20 08:19:39.280173+00', 'proof', '2026-03-20 08:19:39.287399+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '1bde4cbf-5f05-419f-adaa-c2d55db7af10', 'cdace3f3-dc1f-4110-913e-436990b9c9be', '3-building', '4-controller', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.connection (id, created_by, creation_date, last_modified_by, last_modified_date, algorithm, animated, connection_type, input, output, source, target, type, workflow_id)
        VALUES ('f091c695-bddf-4dc3-a3f6-6ce78d901245', 'proof', '2026-03-20 08:19:39.280677+00', 'proof', '2026-03-20 08:19:39.287498+00', 'bezier-catmull-rom', NULL, 'FLOAT',
                '48587222-9d6a-492f-8945-0d5888161f84', 'dd138c92-9cb6-44f2-9952-fdd434969f9a', '4-controller', '3-building', 'block',
                'demo_energy-building-model')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, text_color, type, program_id)
        VALUES ('id_template_1_DataFeeder', 'proof', '2026-03-17 12:48:22.700000+00', 'proof', '2026-03-17 12:51:41.221000+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: DataFeeder', NULL, '1. DataFeeder', true, NULL, 0, '#ffffff', 'block', 'prog_1_DataFeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, text_color, type, program_id)
        VALUES ('id_template_2_StaticGenerator', 'proof', '2026-03-17 12:48:22.700000+00', 'proof', '2026-03-17 12:51:41.221100+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: StaticGenerator', NULL, '2. StaticGenerator', true, NULL, 0, '#ffffff', 'block', 'prog_2_StaticGenerator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, text_color, type, program_id)
        VALUES ('id_template_3_Building', 'proof', '2026-03-17 12:48:22.700000+00', 'proof', '2026-03-17 12:51:41.221200+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: Building', NULL, '3. Building', true, NULL, 0, '#ffffff', 'block', 'prog_3_Building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.template (id, created_by, creation_date, last_modified_by, last_modified_date, block_type, color, communication_paradigm, container_image, description, interface_type, name,
                                shutdown_relevant, status, sync_strategy, text_color, type, program_id)
        VALUES ('id_template_4_Controller', 'proof', '2026-03-17 12:48:22.700000+00', 'proof', '2026-03-17 12:51:41.221300+00', 0, '#0D41E1', 0, 'ghcr.io/kit-iai-proof/proof-worker-python:1.2.0',
                'Simple Building Model: Controller', NULL, '4. Controller', true, NULL, 0, '#ffffff', 'block', 'prog_4_Controller')
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: input; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('9172de45-bcce-4474-98c0-abc33e852657', NULL, NULL, 'proof', '2026-03-20 08:19:39.284951+00', 0, 'irradiance of Block 2_StaticGenerator', 'irradiance',
                'irradiance', 2, 2, NULL, NULL, true, NULL, '2-static_generator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('201f6b35-a8fc-4656-8fde-a69c6bd0feb9', NULL, NULL, 'proof', '2026-03-20 08:19:39.285047+00', 0, 'Outdoor temperature of Block 2_StaticGenerator', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', NULL, true, NULL, '2-static_generator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('2fb2b0ad-3c4f-4942-a485-c477da8993b9', NULL, NULL, 'proof', '2026-03-20 08:19:39.286042+00', 0, 'Outdoor Temperature of Block 3_Building', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', NULL, true, NULL, '3-building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('48587222-9d6a-492f-8945-0d5888161f84', NULL, NULL, 'proof', '2026-03-20 08:19:39.28596+00', 0, 'HVAC power of Block 3_Building', 'p_hvac',
                'p_hvac', 2, 2, NULL, NULL, true, NULL, '3-building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('422f7afc-2289-4fdb-9935-1069af502380', NULL, NULL, 'proof', '2026-03-20 08:19:39.286121+00', 3, 'Initial indoor Temperature of Block 3_Building',
                'Initial Indoor Temp', 'indoor_temp', 1, 2, '°C', NULL, true, NULL, '3-building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('6f7156ea-bf27-4e34-8ccc-d8f4ee788506', NULL, NULL, 'proof', '2026-03-20 08:19:39.284619+00', 0, 'Solar power of Block 4_Controller', 'pv_power',
                'pv_power', 2, 2, NULL, NULL, true, NULL, '4-controller')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('c1137387-18eb-417d-a31e-0cd2ed8b26d8', NULL, NULL, 'proof', '2026-03-20 08:19:39.284725+00', 3, 'Initial indoor Temperature of Block 4_Controller',
                'initial_indoor_temp', 'indoor_temp', 1, 2, '°C', NULL, true, NULL, '4-controller')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('7a34e137-6f79-44cf-9fb1-3f08cfbdd693', NULL, NULL, 'proof', '2026-03-20 08:19:39.284382+00', 0, 'Price of Block 4_Controller', 'price',
                'price', 2, 2, NULL, NULL, true, NULL, '4-controller')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('1bde4cbf-5f05-419f-adaa-c2d55db7af10', NULL, NULL, 'proof', '2026-03-20 08:19:39.284521+00', 0, 'Indoor Temperature of Block 4_Controller', 'indoor_temp',
                'indoor_temp', 2, 2, '°C', NULL, true, NULL, '4-controller')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('6a9e9857-5918-4bd8-9403-e476631ff798', 'proof', '2026-03-17 12:48:22.958272+00', 'proof', '2026-03-17 12:48:22.958272+00', 0, 'irradiance of Block 2_StaticGenerator', 'irradiance',
                'irradiance', 2, 2, NULL, NULL, true, 'id_template_2_StaticGenerator', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('c80d384e-c691-423d-baf9-4cfac3e8d063', 'proof', '2026-03-17 12:48:22.958516+00', 'proof', '2026-03-17 12:48:22.958516+00', 0, 'Outdoor temperature of Block 2_StaticGenerator', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', NULL, true, 'id_template_2_StaticGenerator', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('cdf3fd4b-56a1-4c0e-894b-549040d5144c', 'proof', '2026-03-17 12:48:22.961459+00', 'proof', '2026-03-17 12:48:22.961459+00', 0, 'HVAC power of Block 3_Building', 'p_hvac',
                'p_hvac', 2, 2, NULL, NULL, true, 'id_template_3_Building', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('8c9522ee-b22c-49b6-9a1a-258822d740f5', 'proof', '2026-03-17 12:48:22.9617+00', 'proof', '2026-03-17 12:48:22.9617+00', 0, 'Outdoor Temperature of Block 3_Building', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', NULL, true, 'id_template_3_Building', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('992d42ff-c8e9-400a-8747-6278f6577961', 'proof', '2026-03-17 12:48:22.961939+00', 'proof', '2026-03-17 12:48:22.961939+00', 3, 'Initial indoor Temperature of Block 3_Building',
                'Initial Indoor Temp', 'indoor_temp', 1, 2, '°C', NULL, true, 'id_template_3_Building', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('5bb685d4-2637-4f16-a6a9-22005dcc03f5', 'proof', '2026-03-17 12:48:22.971777+00', 'proof', '2026-03-17 12:48:22.971777+00', 0, 'Price of Block 4_Controller', 'price',
                'price', 2, 2, NULL, NULL, true, 'id_template_4_Controller', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('dce31abd-ee6e-48a1-9a17-20aa79135686', 'proof', '2026-03-17 12:48:22.972079+00', 'proof', '2026-03-17 12:48:22.972079+00', 0, 'Indoor Temperature of Block 4_Controller', 'indoor_temp',
                'indoor_temp', 2, 2, '°C', NULL, true, 'id_template_4_Controller', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('dadaee89-7007-4df7-8fa4-3f8a1082d3c5', 'proof', '2026-03-17 12:48:22.97234+00', 'proof', '2026-03-17 12:48:22.97234+00', 0, 'Solar power of Block 4_Controller', 'pv_power',
                'pv_power', 2, 2, NULL, NULL, true, 'id_template_4_Controller', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.input (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, default_value, required, template_id,
                                block_id)
        VALUES ('a8d8ceeb-e830-40ca-9316-0cce5b7a8329', 'proof', '2026-03-17 12:48:22.972651+00', 'proof', '2026-03-17 12:48:22.972651+00', 3, 'Initial indoor Temperature of Block 4_Controller',
                'initial_indoor_temp', 'indoor_temp', 1, 2, '°C', NULL, true, 'id_template_4_Controller', NULL)
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: output; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('dd138c92-9cb6-44f2-9952-fdd434969f9a', NULL, NULL, 'proof', '2026-03-20 08:19:39.284153+00', 0, 'HVAC power of Block 4_Controller', 'p_hvac',
                'p_hvac', 2, 2, NULL, NULL, '4-controller')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('67627059-3afa-4d46-a948-d7a9a8b5df0a', NULL, NULL, 'proof', '2026-03-20 08:19:39.28483+00', 0, 'Solar power of Block 2_StaticGenerator', 'pv_power',
                'pv_power', 2, 2, NULL, NULL, '2-static_generator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('597352fd-0202-4613-8cd2-a5d70f159abe', NULL, NULL, 'proof', '2026-03-20 08:19:39.285315+00', 0, 'Outdoor temperature of Block 1_DataFeeder', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', NULL, '1-datafeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('4ae757c3-ef43-4f59-b6ed-fc83e3f971ed', NULL, NULL, 'proof', '2026-03-20 08:19:39.285167+00', 0, 'Price of Block 1_DataFeeder', 'price',
                'price', 2, 2, NULL, NULL, '1-datafeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('e41d9d5f-74d2-4ef0-a338-cb8c0fd7459c', NULL, NULL, 'proof', '2026-03-20 08:19:39.285243+00', 0, 'irradiance of Block 1_DataFeeder', 'irradiance',
                'irradiance', 2, 2, NULL, NULL, '1-datafeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('cdace3f3-dc1f-4110-913e-436990b9c9be', NULL, NULL, 'proof', '2026-03-20 08:19:39.285813+00', 0, 'Indoor Temperature of Block 3_Building', 'indoor_temp',
                'indoor_temp', 2, 2, NULL, NULL, '3-building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('59e94158-b2b5-4a4d-9d6b-5d5f038da128', 'proof', '2026-03-17 12:48:22.956906+00', 'proof', '2026-03-17 12:48:22.956906+00', 0, 'Price of Block 1_DataFeeder', 'price',
                'price', 2, 2, NULL, 'id_template_1_DataFeeder', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('26b41590-47ad-40a8-9f75-fe7c93c2704c', 'proof', '2026-03-17 12:48:22.957164+00', 'proof', '2026-03-17 12:48:22.957164+00', 0, 'irradiance of Block 1_DataFeeder', 'irradiance',
                'irradiance', 2, 2, NULL, 'id_template_1_DataFeeder', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('bfbe4ace-98e4-4d0a-ade0-cf3bf99a4799', 'proof', '2026-03-17 12:48:22.957528+00', 'proof', '2026-03-17 12:48:22.957528+00', 0, 'Outdoor temperature of Block 1_DataFeeder', 'outdoor_temp',
                'outdoor_temp', 2, 2, '°C', 'id_template_1_DataFeeder', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('877d66e1-d861-4afd-bcb4-5f4d2c47b464', 'proof', '2026-03-17 12:48:22.959031+00', 'proof', '2026-03-17 12:48:22.959031+00', 0, 'Solar power of Block 2_StaticGenerator', 'pv_power',
                'pv_power', 2, 2, NULL, 'id_template_2_StaticGenerator', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('a1210a76-5ac2-446f-a925-029d5ea1bef8', 'proof', '2026-03-17 12:48:22.962196+00', 'proof', '2026-03-17 12:48:22.962196+00', 0, 'Indoor Temperature of Block 3_Building', 'indoor_temp',
                'indoor_temp', 2, 2, NULL, 'id_template_3_Building', NULL)
        ON CONFLICT DO NOTHING;
        INSERT INTO public.output (id, created_by, creation_date, last_modified_by, last_modified_date, communication_type, description, label, model_var_name, phase, type, unit, template_id, block_id)
        VALUES ('906235b8-af5b-40a6-8ced-ff6da42deccb', 'proof', '2026-03-17 12:48:22.972982+00', 'proof', '2026-03-17 12:48:22.972982+00', 0, 'HVAC power of Block 4_Controller', 'p_hvac',
                'p_hvac', 2, 2, NULL, 'id_template_4_Controller', NULL)
        ON CONFLICT DO NOTHING;


        --
        -- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: admin
        --



        --
        -- Data for Name: program_attached; Type: TABLE DATA; Schema: public; Owner: admin
        --

        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('prog_3_Building', 'attachment_3_Building')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('prog_1_DataFeeder', 'attachment_1_DataFeeder')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('prog_2_StaticGenerator', 'attachment_2_StaticGenerator')
        ON CONFLICT DO NOTHING;
        INSERT INTO public.program_attached (program_id, attachment_id)
        VALUES ('prog_4_Controller', 'attachment_4_Controller')
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
END $$;;