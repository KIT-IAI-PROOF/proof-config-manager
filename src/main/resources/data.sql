INSERT INTO public.block (x, y, creation_date, last_modified_date, color, created_by, description, id, label,
                          last_modified_by, template_id, type)
VALUES (705, 195, null, '2024-12-14 22:21:34.793000', '#0C63E7', 'admin', 'Influx Reader',
        'd77b3e5d-d8ba-4334-9b02-c1a204f7e599', 'Influx Reader', 'admin', null, 'block');
INSERT INTO public.block (x, y, creation_date, last_modified_date, color, created_by, description, id, label,
                          last_modified_by, template_id, type)
VALUES (990, 165, null, '2024-12-14 22:21:34.793000', '#0D41E1', 'admin', 'Influx Writer',
        '9b1df839-f54f-4229-838f-ab1130583b8a', 'Influx Writer', 'admin', null, 'block');
INSERT INTO public.edge (animated, creation_date, last_modified_date, algorithm, created_by, id, last_modified_by,
                         source, source_handle, target, target_handle, type)
VALUES (false, null, '2024-12-14 22:21:34.793000', 'bezier-catmull-rom', 'admin',
        'ec13a7ce-fff8-4556-8146-8d522d1305d0', 'admin', 'd77b3e5d-d8ba-4334-9b02-c1a204f7e599',
        '853d849c-3f96-4197-9ef7-e68b3b73fcfe', '9b1df839-f54f-4229-838f-ab1130583b8a',
        'b972797c-c5e9-471e-9a14-cda8a9736590', 'block');
INSERT INTO public.template (creation_date, last_modified_date, color, created_by, description, id, label,
                             last_modified_by, type)
VALUES ('2024-12-14 22:21:34.737000', '2024-12-14 22:21:34.741000', '#0C63E7', 'admin', 'Influx Reader',
        'cd181fef-394c-4b75-a16d-c08af6b80c91', 'Influx Reader', 'admin', 'block');
INSERT INTO public.template (creation_date, last_modified_date, color, created_by, description, id, label,
                             last_modified_by, type)
VALUES ('2024-12-14 22:21:34.751000', '2024-12-14 22:21:34.753000', '#0D41E1', 'admin', 'Influx Writer',
        '42b3fbba-7e6a-43f4-9d06-fe94f35855dd', 'Influx Writer', 'admin', 'block');
INSERT INTO public.template (creation_date, last_modified_date, color, created_by, description, id, label,
                             last_modified_by, type)
VALUES ('2024-12-14 22:21:34.760000', '2024-12-14 22:21:34.762000', '#0A85ED', 'admin', 'Transformer',
        '9d3b2043-4388-4b63-bb00-37903a726893', 'Transformer', 'admin', 'block');
INSERT INTO public.workflow (creation_date, last_modified_date, created_by, id, last_modified_by, label)
VALUES (null, '2024-12-14 22:21:34.793000', 'admin', '7b4f6e61-458b-4490-8b78-8f31ff12cd16', 'admin', 'Test');
INSERT INTO public.source_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES ('2024-12-14 22:21:34.740000', '2024-12-14 22:21:34.740000', 'admin', 'a63c7c8a-9bba-4c9d-9b7a-6b756b465264',
        'admin');
INSERT INTO public.source_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES ('2024-12-14 22:21:34.761000', '2024-12-14 22:21:34.761000', 'admin', '2ab7b07d-1f88-4dd8-b0f7-608d0eba2b8f',
        'admin');
INSERT INTO public.source_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES (null, '2024-12-14 22:21:34.793000', 'admin', '853d849c-3f96-4197-9ef7-e68b3b73fcfe', 'admin');
INSERT INTO public.target_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES ('2024-12-14 22:21:34.753000', '2024-12-14 22:21:34.753000', 'admin', '5836b387-a3af-4231-b752-5cb06eeb7438',
        'admin');
INSERT INTO public.target_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES ('2024-12-14 22:21:34.761000', '2024-12-14 22:21:34.761000', 'admin', '3047b297-b26e-41bf-b152-2c0ccfbe8b91',
        'admin');
INSERT INTO public.target_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES ('2024-12-14 22:21:34.762000', '2024-12-14 22:21:34.762000', 'admin', '2bc942f7-6a22-4b15-97bd-68bcf96cc715',
        'admin');
INSERT INTO public.target_handle (creation_date, last_modified_date, created_by, id, last_modified_by)
VALUES (null, '2024-12-14 22:21:34.793000', 'admin', 'b972797c-c5e9-471e-9a14-cda8a9736590', 'admin');
INSERT INTO public.block_source_handles (block_id, source_handles_id)
VALUES ('d77b3e5d-d8ba-4334-9b02-c1a204f7e599', '853d849c-3f96-4197-9ef7-e68b3b73fcfe');
INSERT INTO public.block_target_handles (block_id, target_handles_id)
VALUES ('9b1df839-f54f-4229-838f-ab1130583b8a', 'b972797c-c5e9-471e-9a14-cda8a9736590');
INSERT INTO public.template_source_handles (source_handles_id, template_id)
VALUES ('a63c7c8a-9bba-4c9d-9b7a-6b756b465264', 'cd181fef-394c-4b75-a16d-c08af6b80c91');
INSERT INTO public.template_source_handles (source_handles_id, template_id)
VALUES ('2ab7b07d-1f88-4dd8-b0f7-608d0eba2b8f', '9d3b2043-4388-4b63-bb00-37903a726893');
INSERT INTO public.template_target_handles (target_handles_id, template_id)
VALUES ('5836b387-a3af-4231-b752-5cb06eeb7438', '42b3fbba-7e6a-43f4-9d06-fe94f35855dd');
INSERT INTO public.template_target_handles (target_handles_id, template_id)
VALUES ('3047b297-b26e-41bf-b152-2c0ccfbe8b91', '9d3b2043-4388-4b63-bb00-37903a726893');
INSERT INTO public.template_target_handles (target_handles_id, template_id)
VALUES ('2bc942f7-6a22-4b15-97bd-68bcf96cc715', '9d3b2043-4388-4b63-bb00-37903a726893');
INSERT INTO public.workflow_blocks (blocks_id, workflow_id)
VALUES ('d77b3e5d-d8ba-4334-9b02-c1a204f7e599', '7b4f6e61-458b-4490-8b78-8f31ff12cd16');
INSERT INTO public.workflow_blocks (blocks_id, workflow_id)
VALUES ('9b1df839-f54f-4229-838f-ab1130583b8a', '7b4f6e61-458b-4490-8b78-8f31ff12cd16');
INSERT INTO public.workflow_edges (edges_id, workflow_id)
VALUES ('ec13a7ce-fff8-4556-8146-8d522d1305d0', '7b4f6e61-458b-4490-8b78-8f31ff12cd16');
INSERT INTO public.execution (status, creation_date, last_modified_date, created_by, id, label, last_modified_by,
                              started_at, stopped_at, workflow_id)
VALUES (1, '2024-12-14 22:21:34.792000', '2024-12-14 22:21:34.792000', 'admin', 'd758b24e-392e-45f2-8d34-a89c4171ae75',
        'Example', 'admin', '2024-12-14T22:21:34.785210', '2024-12-14T22:21:34.785221',
        '7b4f6e61-458b-4490-8b78-8f31ff12cd16');
INSERT INTO public.execution (status, creation_date, last_modified_date, created_by, id, label, last_modified_by,
                              started_at, stopped_at, workflow_id)
VALUES (1, '2024-12-14 23:20:35.792000', '2024-12-14 22:21:34.792000', 'admin', 'a5d8b24e-392e-45f2-8d34-a89c4171ae75',
        'Example', 'admin', '2024-12-14T22:21:34.785210', '2024-12-14T22:21:34.785221',
        '7b4f6e61-458b-4490-8b78-8f31ff12cd16');
