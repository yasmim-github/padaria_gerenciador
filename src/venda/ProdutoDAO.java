package venda;

import java.sql.*;
import db.ConectaBanco;

public class ProdutoDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public Produto buscarProdutoPorId(int id) throws SQLException {
        Connection conn = conecta.obtemConexao();

       
        String sqlNaoProduzido = "SELECT pb.nome, pnp.preco_venda, pnp.quantidade " +
                                 "FROM produtos_nao_produzidos pnp " +
                                 "JOIN produtos_base pb ON pnp.id = pb.id WHERE pb.id = ?";

        PreparedStatement stmtNaoProduzido = conn.prepareStatement(sqlNaoProduzido);
        stmtNaoProduzido.setInt(1, id);
        ResultSet rs = stmtNaoProduzido.executeQuery();

        if (rs.next()) {
            String nome = rs.getString("nome");
            double preco = rs.getDouble("preco_venda");
            int quantidade = rs.getInt("quantidade");
            conn.close();
            
            return new Produto(id, nome, preco, quantidade, false, 0);
        }
        rs.close();
        stmtNaoProduzido.close();

        
        String sqlProduzido = "SELECT pb.nome, pp.preco_por_quilo " +
                              "FROM produtos_produzidos pp " +
                              "JOIN produtos_base pb ON pp.id = pb.id WHERE pb.id = ?";

        PreparedStatement stmtProduzido = conn.prepareStatement(sqlProduzido);
        stmtProduzido.setInt(1, id);
        ResultSet rs2 = stmtProduzido.executeQuery();

        if (rs2.next()) {
            String nome = rs2.getString("nome");
            double preco = rs2.getDouble("preco_por_quilo");
            conn.close();
            
            return new Produto(id, nome, preco, 0, true, 0);
        }
        rs2.close();
        stmtProduzido.close();

        conn.close();
        return null; 
    }

    public void baixarEstoque(int id, double quantidade) throws SQLException {
        String sql = "UPDATE produtos_nao_produzidos SET quantidade = quantidade - ? WHERE id = ?";
        Connection conn = conecta.obtemConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, quantidade);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        conn.close();
    }
}
