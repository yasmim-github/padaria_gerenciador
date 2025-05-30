package venda;

import java.sql.*;
import db.ConectaBanco;

public class VendaDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public int salvar(Venda venda) throws SQLException {
        String sql = "INSERT INTO vendas (usuario_id, data_hora, total, valor_recebido, troco) VALUES (?, ?, ?, ?, ?)";
        Connection conn = conecta.obtemConexao();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, venda.getUsuarioId());
        stmt.setTimestamp(2, venda.getDataHora());
        stmt.setDouble(3, venda.getTotal());
        stmt.setDouble(4, venda.getValorRecebido());
        stmt.setDouble(5, venda.getTroco());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        conn.close();
        return id;
    }
}
