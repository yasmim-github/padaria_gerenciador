package venda;

import java.sql.*;
import db.ConectaBanco;

public class EstoqueLogDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public void salvar(EstoqueLog log) throws SQLException {
        String sql = "INSERT INTO estoque_log (produto_id, usuario_id, acao, quantidade, data_hora) VALUES (?, ?, ?, ?, ?)";
        Connection conn = conecta.obtemConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, log.getProdutoId());
        stmt.setInt(2, log.getUsuarioId());
        stmt.setString(3, log.getAcao());
        stmt.setInt(4, log.getQuantidade());
        stmt.setTimestamp(5, log.getDataHora());

        stmt.executeUpdate();
        conn.close();
    }
}
