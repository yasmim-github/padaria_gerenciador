package venda;

import java.sql.*;
import db.ConectaBanco;

public class ProdutoDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public Produto buscarProdutoPorId(int id) throws SQLException {
        String sql = "SELECT pb.nome, pnp.preco_venda, pnp.quantidade " +
                     "FROM produtos_nao_produzidos pnp " +
                     "JOIN produtos_base pb ON pnp.id = pb.id WHERE pb.id = ?";

        Connection conn = conecta.obtemConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nome = rs.getString("nome");
            double preco = rs.getDouble("preco_venda");
            int quantidade = rs.getInt("quantidade");
            conn.close();
            return new Produto(id, nome, preco, quantidade);
        }
        conn.close();
        return null;
    }

    public void baixarEstoque(int id, int quantidade) throws SQLException {
        String sql = "UPDATE produtos_nao_produzidos SET quantidade = quantidade - ? WHERE id = ?";
        Connection conn = conecta.obtemConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, quantidade);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        conn.close();
    }
}
