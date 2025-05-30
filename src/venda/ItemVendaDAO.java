package venda;

import java.sql.*;
import db.ConectaBanco;

public class ItemVendaDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public void salvar(ItemVenda item) throws SQLException {
        String sql = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conecta.obtemConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getVendaId());
            stmt.setInt(2, item.getProdutoId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getValorUnitario());
            stmt.setDouble(5, item.getValorTotal());

            stmt.executeUpdate();
        }
    }
}
