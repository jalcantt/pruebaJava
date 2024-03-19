package com.prueba.alvaro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public HashMap<String, Object> getProductosList(Producto producto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("PRODUCTBD")
                .withProcedureName("sp_ejemplo")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("p_id", Types.INTEGER),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_fec_registro", Types.DATE),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR),
                        new SqlOutParameter("p_codigo", Types.INTEGER),
                        new SqlOutParameter("p_mensaje", Types.VARCHAR),
                })
                .returningResultSet("p_cursor", new RowMapper<Producto>() {
                    @Override
                    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Producto producto1 = new Producto();
                        producto1.setId(rs.getInt(1));
                        producto1.setNombre(rs.getString(2));
                        producto1.setFechaResgistro(rs.getDate(3));

                        return producto1;
                    }
                });

        MapSqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_id", producto.getId())
                .addValue("p_nombre", producto.getNombre())
                .addValue("p_fec_registro", producto.getFechaResgistro());


        Map<String, Object> outParams = simpleJdbcCall.execute(inParams);
        List<Producto> productosList = (List<Producto>) outParams.get("p_cursor");
        Object codigo = outParams.get("p_codigo");
        Object mensaje = outParams.get("p_mensaje");

        HashMap<String, Object> outputs = new HashMap<>();
        outputs.put("cursor",productosList);
        outputs.put("p_codigo",codigo);
        outputs.put("p_mensaje",mensaje);

        return outputs;
    }


}
