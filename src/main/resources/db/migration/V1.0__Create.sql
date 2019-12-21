SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.tips (
    id bigint NOT NULL,
    headline character varying(255),
    text character varying
);


CREATE SEQUENCE public.tips_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER SEQUENCE public.tips_id_seq OWNED BY public.tips.id;


ALTER TABLE ONLY public.tips
ADD CONSTRAINT tips_pkey PRIMARY KEY (id);

