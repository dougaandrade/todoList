-- Caso precisa limpar o banco de dados, execute o script abaixo

DO $$ 
DECLARE 
    r RECORD;
BEGIN
    SET session_replication_role = 'replica';

    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'TRUNCATE TABLE ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;

    FOR r IN (SELECT c.relname AS sequence_name
              FROM pg_class c
              JOIN pg_namespace n ON n.oid = c.relnamespace
              WHERE c.relkind = 'S' AND n.nspname = 'public') LOOP
        EXECUTE 'ALTER SEQUENCE ' || quote_ident(r.sequence_name) || ' RESTART WITH 1';
    END LOOP;

    SET session_replication_role = 'origin';
END $$;
