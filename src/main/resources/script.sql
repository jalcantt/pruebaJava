CREATE TABLE productos (
    id NUMBER,
    nombre VARCHAR2(255),
    fec_registro DATE
);

CREATE OR REPLACE PROCEDURE sp_ejemplo (
    p_id IN NUMBER,
    p_nombre IN VARCHAR2,
    p_fec_registro IN DATE,
    p_cursor OUT SYS_REFCURSOR,
    p_codigo OUT NUMBER,
    p_mensaje OUT VARCHAR2
) AS
BEGIN
    -- Insertar el nuevo producto en la tabla
    INSERT INTO productos (id, nombre, fec_registro)
    VALUES (p_id, p_nombre, p_fec_registro);

    -- Abrir el cursor para devolver los datos del producto insertado
    OPEN p_cursor FOR
        SELECT * FROM productos WHERE TRUNC(fec_registro) =  TRUNC(CURRENT_DATE);

    -- Asignar valores para los parámetros de salida
    p_codigo := 0; -- Código de éxito
    p_mensaje := 'Producto insertado correctamente';
EXCEPTION
    WHEN OTHERS THEN
        -- Asignar valores para los parámetros de salida en caso de error
        p_codigo := SQLCODE;
        p_mensaje := SQLERRM;
END sp_ejemplo;
