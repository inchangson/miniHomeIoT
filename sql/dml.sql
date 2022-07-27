
insert into customer (cust_id, cust_created_at, cust_name, cust_addr, phone_num) values
                                                                                     ('ktRookie01', '2022-04-25 22:00:12+09', '이상현', '서울시 서초구 우면동 176-5', 		'01073332222'),
                                                                                     ('ktRookie02', '2021-03-16 12:23:12+09', '오종빈', '부산시 남구 분포로 111', 			'01073001242'),
                                                                                     ('ktRookie03', '2022-06-08 22:27:17+09', '정용수', '서울시 동작구 동작대로29길 91', 	'01097771746'),
                                                                                     ('ktRookie04', '2021-02-14 17:17:12+09', '양현지', '경북 상주시 중앙로230', 			'01042648526'),
                                                                                     ('ktRookie05', '2022-11-07 21:22:15+09', '송희원', '전남 영암군 영암읍 군청로1', 		'01097006367');

insert into service  (cust_id, svc_created_at) values
                                                   ('ktRookie01', '2022-04-25 22:10:12+09'),
                                                   ('ktRookie02', '2021-03-16 12:33:12+09'),
                                                   ('ktRookie03', '2022-06-08 22:37:17+09'),
                                                   ('ktRookie04', '2021-02-14 17:27:12+09'),
                                                   ('ktRookie05', '2022-11-07 21:42:15+09');

insert into company (comp_id, external_id) values
                                               ('GOQUAL', 'HOME_IOT_GOQUAL'),
                                               ('BRUNT', 'HOME_IOT_BRUNT'),
                                               ('SAMSUNG', 'HOME_IOT_SAMSUNG'),
                                               ('COWAY', 'HOME_IOT_COWAY'),
                                               ('RINNAI', 'HOME_IOT_RINNAI');

insert into model
(model_id, model_name, model_code, comp_id) values
                                                ('GOQUAL_GLS_WF', 			'스위치', 	'0601', 'GOQUAL'),
                                                ('GOQUAL_CURTAIN', 			'커튼', 		'0602', 'GOQUAL'),
                                                ('GOQUAL_AIRPURIFIER', 		'공기청정기', 	'0603', 'GOQUAL'),
                                                ('GOQUAL_GLB_WF_CCTW', 		'조명', 		'0604', 'GOQUAL'),
                                                ('GOQUAL_GLB_WF_TW', 		'조명', 		'0605', 'GOQUAL'),
                                                ('GOQUAL_GLB_WF_STRIP_CCTW','스트립조명', 	'0606', 'GOQUAL'),
                                                ('GOQUAL_FAN', 				'선풍기', 	'0607', 'GOQUAL'),
                                                ('BRUNT_PLUG', 				'플러그', 	'0201', 'BRUNT'),
                                                ('SAMSUNG_FLOOR_AIRCON', 	'에어컨', 	'1901', 'SAMSUNG'),
                                                ('SAMSUNG_AIR_PURIFIER', 	'공기청정기', 	'1902', 'SAMSUNG'),
                                                ('COWAY_AIR_PURIFIER_1', 	'공기청정기', 	'0301', 'COWAY'),
                                                ('COWAY_AIR_PURIFIER_2', 	'공기청정기', 	'0302', 'COWAY'),
                                                ('RINNAI_WF-100_000', 		'보일러', 	'1801', 'RINNAI'),
                                                ('RINNAI_WF-100_002', 		'보일러', 	'1801', 'RINNAI');

insert into resource_type
(rsc_type_group, rsc_type_code, rsc_type_name, value_type, model_id) values
                                                                         ('connection-status', 	'value', '스위치.연결', 		'boolean', 'GOQUAL_GLS_WF'),
                                                                         ('power-switch', 		'value', '스위치.파워스위치', 	'boolean', 'GOQUAL_GLS_WF'),
                                                                         ('connection-status', 	'value', '커튼.연결', 			'boolean', 'GOQUAL_CURTAIN'),
                                                                         ('power-switch', 		'value', '커튼.파워스위치', 		'boolean', 'GOQUAL_CURTAIN'),
                                                                         ('connection-status', 	'value', '공기청정기.연결', 		'boolean', 'GOQUAL_AIRPURIFIER'),
                                                                         ('power-switch', 		'value', '공기청정기.파워스위치', 	'boolean', 'GOQUAL_AIRPURIFIER'),
                                                                         ('connection-status', 	'value', '조명.연결', 			'boolean', 'GOQUAL_GLB_WF_CCTW'),
                                                                         ('power-switch', 		'value', '조명.파워스위치', 		'boolean', 'GOQUAL_GLB_WF_CCTW'),
                                                                         ('connection-status', 	'value', '조명.연결', 			'boolean', 'GOQUAL_GLB_WF_TW'),
                                                                         ('power-switch', 		'value', '조명.파워스위치', 		'boolean', 'GOQUAL_GLB_WF_TW'),
                                                                         ('connection-status', 	'value', '스트립조명.연결', 		'boolean', 'GOQUAL_GLB_WF_STRIP_CCTW'),
                                                                         ('power-switch', 		'value', '스트립조명.파워스위치', 	'boolean', 'GOQUAL_GLB_WF_STRIP_CCTW'),
                                                                         ('power-switch', 		'value', '선풍기.파워스위치', 	'boolean', 'GOQUAL_FAN'),
                                                                         ('connection-status', 	'value', '플러그.연결', 		'boolean', 'BRUNT_PLUG'),
                                                                         ('power-switch', 		'value', '플러그.파워스위치',		'boolean', 'BRUNT_PLUG'),
                                                                         ('connection-status', 	'value', '에어컨.연결', 		'boolean', 'SAMSUNG_FLOOR_AIRCON'),
                                                                         ('power-switch', 		'value', '에어컨.파워스위치',		'boolean', 'SAMSUNG_FLOOR_AIRCON'),
                                                                         ('connection-status', 	'value', '공기청정기.연결', 		'boolean', 'SAMSUNG_AIR_PURIFIER'),
                                                                         ('power-switch', 		'value', '공기청정기.파워스위치', 	'boolean', 'SAMSUNG_AIR_PURIFIER'),
                                                                         ('connection-status', 	'value', '공기청정기.연결', 		'boolean', 'COWAY_AIR_PURIFIER_1'),
                                                                         ('power-switch', 		'value', '공기청정기.파워스위치', 	'boolean', 'COWAY_AIR_PURIFIER_1'),
                                                                         ('connection-status', 	'value', '공기청정기.연결', 		'boolean', 'COWAY_AIR_PURIFIER_2'),
                                                                         ('power-switch', 		'value', '공기청정기.파워스위치', 	'boolean', 'COWAY_AIR_PURIFIER_2'),
                                                                         ('connection-status', 	'value', '보일러.연결', 		'boolean', 'RINNAI_WF-100_000'),
                                                                         ('power-switch', 		'value', '보일러.파워스위치',		'boolean', 'RINNAI_WF-100_000'),
                                                                         ('connection-status', 	'value', '보일러.연결', 		'boolean', 'RINNAI_WF-100_002'),
                                                                         ('power-switch', 		'value', '보일러.파워스위치',		'boolean', 'RINNAI_WF-100_002')
;

insert into device
(model_id, dvc_name, cust_id) values
                                  ('GOQUAL_GLS_WF', 			'거실스위치', 		'ktRookie01'),
                                  ('GOQUAL_CURTAIN', 			'큰방커튼', 		'ktRookie02'),
                                  ('GOQUAL_AIRPURIFIER', 		'아이공기청정기', 	'ktRookie03'),
                                  ('GOQUAL_GLB_WF_CCTW', 		'주방조명', 		'ktRookie04'),
                                  ('GOQUAL_GLB_WF_TW', 		'욕실조명', 		'ktRookie05'),
                                  ('GOQUAL_GLB_WF_STRIP_CCTW','침실스트립조명', 	'ktRookie02'),
                                  ('GOQUAL_FAN', 				'무선선풍기', 		'ktRookie01'),
                                  ('BRUNT_PLUG', 				'간이플러그', 		'ktRookie04'),
                                  ('SAMSUNG_FLOOR_AIRCON', 	'거실에어컨', 		'ktRookie03'),
                                  ('SAMSUNG_AIR_PURIFIER', 	'거실공기청정기', 	'ktRookie05'),
                                  ('COWAY_AIR_PURIFIER_1', 	'주방공기청정기', 	'ktRookie05'),
                                  ('COWAY_AIR_PURIFIER_2', 	'큰방공기청정기', 	'ktRookie04'),
                                  ('RINNAI_WF-100_000', 		'전체보일러', 		'ktRookie03'),
                                  ('RINNAI_WF-100_002', 		'다락방보일러', 	'ktRookie02');

insert into resource
(dvc_seq, rsc_type_seq, rsc_value) values
                                       (8, 14, 'false'),
                                       (8, 15, 'false'),
                                       (11, 20, 'false'),
                                       (11, 21, 'false'),
                                       (12, 22, 'false'),
                                       (12, 23, 'false'),
                                       (3, 5, 'false'),
                                       (3, 6, 'false'),
                                       (2, 3, 'false'),
                                       (2, 4, 'false'),
                                       (7, 13, 'false'),
                                       (4, 7, 'false'),
                                       (4, 8, 'false'),
                                       (6, 11, 'false'),
                                       (6, 12, 'false'),
                                       (5, 9, 'false'),
                                       (5, 10, 'false'),
                                       (1, 1, 'false'),
                                       (1, 2, 'false'),
                                       (13, 24, 'false'),
                                       (13, 25, 'false'),
                                       (14, 26, 'false'),
                                       (14, 27, 'false'),
                                       (10, 18, 'false'),
                                       (10, 19, 'false'),
                                       (9, 16, 'false'),
                                       (9, 17, 'false')
;