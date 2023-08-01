CREATE DATABASE investment;
\c investment;
--
-- PostgreSQL database dump
--
--
-- Name: investor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE investor (
    date_of_birth timestamp(6) without time zone,
    id bigint NOT NULL,
    email_address character varying(255),
    name character varying(255),
    surname character varying(255),
    telephone_number character varying(255)
);


ALTER TABLE investor OWNER TO postgres;

--
-- Name: investor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE investor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE investor_id_seq OWNER TO postgres;

--
-- Name: investor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE investor_id_seq OWNED BY investor.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
    balance numeric(38,2),
    id integer NOT NULL,
    investor_id bigint,
    product_type character varying(255),
    CONSTRAINT product_product_type_check CHECK (((product_type)::text = ANY ((ARRAY['RETIREMENT'::character varying, 'SAVINGS'::character varying])::text[])))
);


ALTER TABLE product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: withdrawal_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE withdrawal_event (
    amount numeric(38,2),
    product_id integer,
    status smallint,
    id bigint NOT NULL,
    investor_id bigint,
    product_type character varying(255),
    to_account character varying(255),
    CONSTRAINT withdrawal_event_status_check CHECK (((status >= 0) AND (status <= 2)))
);


ALTER TABLE withdrawal_event OWNER TO postgres;

--
-- Name: withdrawal_event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE withdrawal_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE withdrawal_event_id_seq OWNER TO postgres;

--
-- Name: withdrawal_event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE withdrawal_event_id_seq OWNED BY withdrawal_event.id;


--
-- Name: investor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investor ALTER COLUMN id SET DEFAULT nextval('investor_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: withdrawal_event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY withdrawal_event ALTER COLUMN id SET DEFAULT nextval('withdrawal_event_id_seq'::regclass);


--
-- Data for Name: investor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY investor (date_of_birth, id, email_address, name, surname, telephone_number) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (balance, id, investor_id, product_type) FROM stdin;
\.


--
-- Data for Name: withdrawal_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY withdrawal_event (amount, product_id, status, id, investor_id, product_type, to_account) FROM stdin;
\.


--
-- Name: investor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('investor_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 1, false);


--
-- Name: withdrawal_event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('withdrawal_event_id_seq', 1, false);


--
-- Name: investor investor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investor
    ADD CONSTRAINT investor_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: withdrawal_event withdrawal_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY withdrawal_event
    ADD CONSTRAINT withdrawal_event_pkey PRIMARY KEY (id);


--
-- Name: product fk38ttt82y6eg0ffnr1ngwxq47a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk38ttt82y6eg0ffnr1ngwxq47a FOREIGN KEY (investor_id) REFERENCES investor(id);


--
-- Name: withdrawal_event fkbg1h49541cg8qnr4rsswjgeuh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY withdrawal_event
    ADD CONSTRAINT fkbg1h49541cg8qnr4rsswjgeuh FOREIGN KEY (product_id) REFERENCES product(id);


--
-- PostgreSQL database dump complete
--



INSERT INTO investor (date_of_birth,email_address,name,surname,telephone_number) VALUES ('1950-05-30 00:00:00.0','test.t1@gmail.co.com','KK','Yoyo','071111111');
INSERT INTO product (balance, product_type, investor_id) VALUES (500000.00, 'RETIREMENT', 1);
INSERT INTO product (balance,product_type, investor_id) VALUES (36000.00, 'SAVINGS',1);