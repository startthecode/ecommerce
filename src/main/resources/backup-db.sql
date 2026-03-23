--
-- PostgreSQL database cluster dump
--

-- Started on 2026-03-23 23:25:27

\restrict 0SCs9xJjSch2XldVf1qsE689ie2kxpO5fG2hCjMUdCrExVYC4NOjjzNotXDWAlY

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:zU1WoRnmJcsrRDILuFk9Aw==$oG/Ni/hfEwWroDynaQYyAs4ffSDekZjJ+ETsRQ1CPvs=:VDlGc673OO2Ihl4OTVrYsotB5QXvmDqoXDt8L8Ip8AE=';

--
-- User Configurations
--








\unrestrict 0SCs9xJjSch2XldVf1qsE689ie2kxpO5fG2hCjMUdCrExVYC4NOjjzNotXDWAlY

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

\restrict cTWdGyVsvEnQzaBmYKOgh2KJcrfRmswBGzCM35LHFWPXTCRinQuMEUT9CyKpl8q

-- Dumped from database version 18.2
-- Dumped by pg_dump version 18.2

-- Started on 2026-03-23 23:25:27

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

-- Completed on 2026-03-23 23:25:28

--
-- PostgreSQL database dump complete
--

\unrestrict cTWdGyVsvEnQzaBmYKOgh2KJcrfRmswBGzCM35LHFWPXTCRinQuMEUT9CyKpl8q

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

\restrict 1G4zttLW4EhJmGpUUvraeonXQqAyk3tV0zTiyWq2FS1MYqZ3nZ72BDStKUMrm7P

-- Dumped from database version 18.2
-- Dumped by pg_dump version 18.2

-- Started on 2026-03-23 23:25:28

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
-- TOC entry 219 (class 1259 OID 16457)
-- Name: blogs_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.blogs_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.blogs_table_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 25128)
-- Name: brand_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brand_table (
    brandid bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.brand_table OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 25252)
-- Name: brand_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.brand_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.brand_table_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25135)
-- Name: category_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_table (
    categoryid bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.category_table OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 25253)
-- Name: category_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_table_seq OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25111)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25142)
-- Name: images_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images_table (
    imageid bigint NOT NULL,
    imagelg character varying(255) NOT NULL,
    imagesm character varying(255) NOT NULL,
    thumbnail character varying(255) NOT NULL
);


ALTER TABLE public.images_table OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 25254)
-- Name: images_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.images_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.images_table_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25153)
-- Name: price_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.price_table (
    priceid bigint NOT NULL,
    compare_at_price double precision NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE public.price_table OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 25255)
-- Name: price_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.price_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.price_table_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 25161)
-- Name: product_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_table (
    productid bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    description character varying(250) NOT NULL,
    height double precision,
    length double precision,
    published_at timestamp(6) without time zone,
    short_description character varying(60),
    stock_quantity integer NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    width double precision,
    brand bigint,
    image bigint,
    price bigint,
    status bigint
);


ALTER TABLE public.product_table OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 25256)
-- Name: product_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_table_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 25174)
-- Name: products_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products_categories (
    product_id bigint NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.products_categories OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 25181)
-- Name: role_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_table (
    role_id integer NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.role_table OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 25257)
-- Name: role_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.role_table_seq OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 25188)
-- Name: status_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_table (
    statusid bigint NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT status_table_name_check CHECK (((name)::text = ANY ((ARRAY['DRAFT'::character varying, 'ACTIVE'::character varying, 'INACTIVE'::character varying, 'ARCHIVED'::character varying])::text[])))
);


ALTER TABLE public.status_table OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 25258)
-- Name: status_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.status_table_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 25196)
-- Name: tag_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag_table (
    tagid bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.tag_table OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 25259)
-- Name: tag_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tag_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tag_table_seq OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 25203)
-- Name: tags_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tags_categories (
    product_id bigint NOT NULL,
    tags_id bigint NOT NULL
);


ALTER TABLE public.tags_categories OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16508)
-- Name: user_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_table_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16517)
-- Name: user_tables_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_tables_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_tables_seq OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 25210)
-- Name: userdetail_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userdetail_table (
    userdetailid bigint NOT NULL,
    address character varying(255) NOT NULL,
    pincode bigint NOT NULL,
    userid bigint
);


ALTER TABLE public.userdetail_table OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 25260)
-- Name: userdetail_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.userdetail_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.userdetail_table_seq OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 25218)
-- Name: users_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_table (
    userid bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    role_id integer
);


ALTER TABLE public.users_table OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 25261)
-- Name: users_table_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_table_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_table_seq OWNER TO postgres;

--
-- TOC entry 5128 (class 0 OID 25128)
-- Dependencies: 223
-- Data for Name: brand_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.brand_table (brandid, name) FROM stdin;
102	LogiTech
\.


--
-- TOC entry 5129 (class 0 OID 25135)
-- Dependencies: 224
-- Data for Name: category_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category_table (categoryid, name) FROM stdin;
102	accessories
103	computer
\.


--
-- TOC entry 5127 (class 0 OID 25111)
-- Dependencies: 222
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	2	seed reference data	SQL	V2__seed_reference_data.sql	-1599538605	postgres	2026-03-23 23:07:32.205783	4	t
\.


--
-- TOC entry 5130 (class 0 OID 25142)
-- Dependencies: 225
-- Data for Name: images_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.images_table (imageid, imagelg, imagesm, thumbnail) FROM stdin;
1	mouse1.png	mouse1.png	mouse1.png
\.


--
-- TOC entry 5131 (class 0 OID 25153)
-- Dependencies: 226
-- Data for Name: price_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.price_table (priceid, compare_at_price, price) FROM stdin;
102	1199	899
\.


--
-- TOC entry 5132 (class 0 OID 25161)
-- Dependencies: 227
-- Data for Name: product_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_table (productid, created_at, description, height, length, published_at, short_description, stock_quantity, title, updated_at, width, brand, image, price, status) FROM stdin;
102	2026-03-23 23:23:27.152923	Ergonomic wireless mouse with USB receiver and long battery life.	3.799999952316284	12.5	2026-03-23 23:23:27.134283	Ergonomic wireless mouse	150	Wireless Mouse	2026-03-23 23:23:27.152923	6.199999809265137	102	1	102	2
\.


--
-- TOC entry 5133 (class 0 OID 25174)
-- Dependencies: 228
-- Data for Name: products_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products_categories (product_id, category_id) FROM stdin;
102	102
102	103
\.


--
-- TOC entry 5134 (class 0 OID 25181)
-- Dependencies: 229
-- Data for Name: role_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_table (role_id, role) FROM stdin;
1	ADMIN
2	USER
3	SELLER
4	MANAGER
5	CUSTOMER_SUPPORT
6	DELIVERY_AGENT
7	INVENTORY_MANAGER
8	FINANCE
9	MARKETING
10	GUEST
\.


--
-- TOC entry 5135 (class 0 OID 25188)
-- Dependencies: 230
-- Data for Name: status_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.status_table (statusid, name) FROM stdin;
1	DRAFT
2	ACTIVE
3	INACTIVE
4	ARCHIVED
\.


--
-- TOC entry 5136 (class 0 OID 25196)
-- Dependencies: 231
-- Data for Name: tag_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag_table (tagid, name) FROM stdin;
102	wireless
103	mouse
104	electronics
\.


--
-- TOC entry 5137 (class 0 OID 25203)
-- Dependencies: 232
-- Data for Name: tags_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tags_categories (product_id, tags_id) FROM stdin;
102	102
102	103
102	104
\.


--
-- TOC entry 5138 (class 0 OID 25210)
-- Dependencies: 233
-- Data for Name: userdetail_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userdetail_table (userdetailid, address, pincode, userid) FROM stdin;
\.


--
-- TOC entry 5139 (class 0 OID 25218)
-- Dependencies: 234
-- Data for Name: users_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_table (userid, email, name, password, username, role_id) FROM stdin;
1	ashishji@gmail.com	Ashish	$2a$12$S8lqCEcA.v9aDky5/Q6RTeaQZwPi3yG6OjeoTCAYw3QF6rNFUbtDm	ashishji	2
\.


--
-- TOC entry 5155 (class 0 OID 0)
-- Dependencies: 219
-- Name: blogs_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.blogs_table_seq', 1, false);


--
-- TOC entry 5156 (class 0 OID 0)
-- Dependencies: 235
-- Name: brand_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brand_table_seq', 151, true);


--
-- TOC entry 5157 (class 0 OID 0)
-- Dependencies: 236
-- Name: category_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_table_seq', 151, true);


--
-- TOC entry 5158 (class 0 OID 0)
-- Dependencies: 237
-- Name: images_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_table_seq', 1, true);


--
-- TOC entry 5159 (class 0 OID 0)
-- Dependencies: 238
-- Name: price_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.price_table_seq', 151, true);


--
-- TOC entry 5160 (class 0 OID 0)
-- Dependencies: 239
-- Name: product_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_table_seq', 151, true);


--
-- TOC entry 5161 (class 0 OID 0)
-- Dependencies: 240
-- Name: role_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_table_seq', 1, false);


--
-- TOC entry 5162 (class 0 OID 0)
-- Dependencies: 241
-- Name: status_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_table_seq', 1, false);


--
-- TOC entry 5163 (class 0 OID 0)
-- Dependencies: 242
-- Name: tag_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tag_table_seq', 151, true);


--
-- TOC entry 5164 (class 0 OID 0)
-- Dependencies: 220
-- Name: user_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_table_seq', 1, false);


--
-- TOC entry 5165 (class 0 OID 0)
-- Dependencies: 221
-- Name: user_tables_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_tables_seq', 51, true);


--
-- TOC entry 5166 (class 0 OID 0)
-- Dependencies: 243
-- Name: userdetail_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.userdetail_table_seq', 1, false);


--
-- TOC entry 5167 (class 0 OID 0)
-- Dependencies: 244
-- Name: users_table_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_table_seq', 1, true);


--
-- TOC entry 4922 (class 2606 OID 25134)
-- Name: brand_table brand_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand_table
    ADD CONSTRAINT brand_table_pkey PRIMARY KEY (brandid);


--
-- TOC entry 4926 (class 2606 OID 25141)
-- Name: category_table category_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_table
    ADD CONSTRAINT category_table_pkey PRIMARY KEY (categoryid);


--
-- TOC entry 4919 (class 2606 OID 25126)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 4930 (class 2606 OID 25152)
-- Name: images_table images_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images_table
    ADD CONSTRAINT images_table_pkey PRIMARY KEY (imageid);


--
-- TOC entry 4932 (class 2606 OID 25160)
-- Name: price_table price_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price_table
    ADD CONSTRAINT price_table_pkey PRIMARY KEY (priceid);


--
-- TOC entry 4934 (class 2606 OID 25173)
-- Name: product_table product_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT product_table_pkey PRIMARY KEY (productid);


--
-- TOC entry 4942 (class 2606 OID 25180)
-- Name: products_categories products_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_categories
    ADD CONSTRAINT products_categories_pkey PRIMARY KEY (product_id, category_id);


--
-- TOC entry 4944 (class 2606 OID 25187)
-- Name: role_table role_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_table
    ADD CONSTRAINT role_table_pkey PRIMARY KEY (role_id);


--
-- TOC entry 4948 (class 2606 OID 25195)
-- Name: status_table status_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_table
    ADD CONSTRAINT status_table_pkey PRIMARY KEY (statusid);


--
-- TOC entry 4952 (class 2606 OID 25202)
-- Name: tag_table tag_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag_table
    ADD CONSTRAINT tag_table_pkey PRIMARY KEY (tagid);


--
-- TOC entry 4956 (class 2606 OID 25209)
-- Name: tags_categories tags_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_categories
    ADD CONSTRAINT tags_categories_pkey PRIMARY KEY (product_id, tags_id);


--
-- TOC entry 4958 (class 2606 OID 25247)
-- Name: userdetail_table uk59of1uesj0mcnkdn26atekua; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userdetail_table
    ADD CONSTRAINT uk59of1uesj0mcnkdn26atekua UNIQUE (userid);


--
-- TOC entry 4946 (class 2606 OID 25241)
-- Name: role_table uk86uy1d04van8ldgrmh4pjx87j; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_table
    ADD CONSTRAINT uk86uy1d04van8ldgrmh4pjx87j UNIQUE (role);


--
-- TOC entry 4928 (class 2606 OID 25233)
-- Name: category_table uk9ih3gpabyp66d7hfntl3wx1d9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_table
    ADD CONSTRAINT uk9ih3gpabyp66d7hfntl3wx1d9 UNIQUE (name);


--
-- TOC entry 4936 (class 2606 OID 25235)
-- Name: product_table ukcyuqfwfgfx2p1jc2weph1lw1h; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT ukcyuqfwfgfx2p1jc2weph1lw1h UNIQUE (title);


--
-- TOC entry 4938 (class 2606 OID 25239)
-- Name: product_table ukhm5kd8pcm8omf56lfywl65l46; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT ukhm5kd8pcm8omf56lfywl65l46 UNIQUE (price);


--
-- TOC entry 4962 (class 2606 OID 25249)
-- Name: users_table ukk9gbvc9xvagimmj1y060txmha; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_table
    ADD CONSTRAINT ukk9gbvc9xvagimmj1y060txmha UNIQUE (email);


--
-- TOC entry 4950 (class 2606 OID 25243)
-- Name: status_table ukkg5g88ggwp3rr86osd7q5bjrm; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_table
    ADD CONSTRAINT ukkg5g88ggwp3rr86osd7q5bjrm UNIQUE (name);


--
-- TOC entry 4924 (class 2606 OID 25231)
-- Name: brand_table ukl29uwut7mgfu3nwy7unqnfuqj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand_table
    ADD CONSTRAINT ukl29uwut7mgfu3nwy7unqnfuqj UNIQUE (name);


--
-- TOC entry 4940 (class 2606 OID 25237)
-- Name: product_table ukl4ffp79gtp6sxm6gnw4flds6j; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT ukl4ffp79gtp6sxm6gnw4flds6j UNIQUE (image);


--
-- TOC entry 4954 (class 2606 OID 25245)
-- Name: tag_table ukljb0884h81cqdn6ll1bdmlbaa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag_table
    ADD CONSTRAINT ukljb0884h81cqdn6ll1bdmlbaa UNIQUE (name);


--
-- TOC entry 4964 (class 2606 OID 25251)
-- Name: users_table ukn9aj79eaiuhioi3qxwiwqp46y; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_table
    ADD CONSTRAINT ukn9aj79eaiuhioi3qxwiwqp46y UNIQUE (username);


--
-- TOC entry 4960 (class 2606 OID 25217)
-- Name: userdetail_table userdetail_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userdetail_table
    ADD CONSTRAINT userdetail_table_pkey PRIMARY KEY (userdetailid);


--
-- TOC entry 4966 (class 2606 OID 25229)
-- Name: users_table users_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_table
    ADD CONSTRAINT users_table_pkey PRIMARY KEY (userid);


--
-- TOC entry 4920 (class 1259 OID 25127)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 4967 (class 2606 OID 25277)
-- Name: product_table fk36nm4dxkoh8xacww9thju1hmv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT fk36nm4dxkoh8xacww9thju1hmv FOREIGN KEY (status) REFERENCES public.status_table(statusid);


--
-- TOC entry 4971 (class 2606 OID 25282)
-- Name: products_categories fk7ee78aml2355f4slvwipuqvnq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_categories
    ADD CONSTRAINT fk7ee78aml2355f4slvwipuqvnq FOREIGN KEY (category_id) REFERENCES public.category_table(categoryid);


--
-- TOC entry 4968 (class 2606 OID 25272)
-- Name: product_table fk7itut42bg9rmf1u8potymeft6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT fk7itut42bg9rmf1u8potymeft6 FOREIGN KEY (price) REFERENCES public.price_table(priceid);


--
-- TOC entry 4976 (class 2606 OID 25307)
-- Name: users_table fkd375pkp9inxh2kptbyjehw9xo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_table
    ADD CONSTRAINT fkd375pkp9inxh2kptbyjehw9xo FOREIGN KEY (role_id) REFERENCES public.role_table(role_id);


--
-- TOC entry 4975 (class 2606 OID 25302)
-- Name: userdetail_table fkde26yihhi6riygduffhgy5vai; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userdetail_table
    ADD CONSTRAINT fkde26yihhi6riygduffhgy5vai FOREIGN KEY (userid) REFERENCES public.users_table(userid);


--
-- TOC entry 4973 (class 2606 OID 25297)
-- Name: tags_categories fkdpuolw9kgfnm2ows3haqubqkt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_categories
    ADD CONSTRAINT fkdpuolw9kgfnm2ows3haqubqkt FOREIGN KEY (product_id) REFERENCES public.product_table(productid);


--
-- TOC entry 4969 (class 2606 OID 25262)
-- Name: product_table fkguqmy2sfji4t2mifljv6ty46r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT fkguqmy2sfji4t2mifljv6ty46r FOREIGN KEY (brand) REFERENCES public.brand_table(brandid);


--
-- TOC entry 4972 (class 2606 OID 25287)
-- Name: products_categories fkh4kr459pnsip72in2k812plgc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_categories
    ADD CONSTRAINT fkh4kr459pnsip72in2k812plgc FOREIGN KEY (product_id) REFERENCES public.product_table(productid);


--
-- TOC entry 4974 (class 2606 OID 25292)
-- Name: tags_categories fkmlrf0f8pwcshbkk15sg0o3xxm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_categories
    ADD CONSTRAINT fkmlrf0f8pwcshbkk15sg0o3xxm FOREIGN KEY (tags_id) REFERENCES public.tag_table(tagid);


--
-- TOC entry 4970 (class 2606 OID 25267)
-- Name: product_table fkn4pi524nh9bl1791t95fr584l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_table
    ADD CONSTRAINT fkn4pi524nh9bl1791t95fr584l FOREIGN KEY (image) REFERENCES public.images_table(imageid);


-- Completed on 2026-03-23 23:25:28

--
-- PostgreSQL database dump complete
--

\unrestrict 1G4zttLW4EhJmGpUUvraeonXQqAyk3tV0zTiyWq2FS1MYqZ3nZ72BDStKUMrm7P

-- Completed on 2026-03-23 23:25:28

--
-- PostgreSQL database cluster dump complete
--

