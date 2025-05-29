package venda;

import java.sql.*;
import db.ConectaBanco;

public class NotaFiscalDAO {
    private ConectaBanco conecta = new ConectaBanco();

    public void salvar(NotaFiscal nf) throws SQLException {
        String sql = """
            INSERT INTO nota_fiscal (
                venda_id, numero_nf, serie, modelo, data_emissao, chave_acesso,
                nome_empresa, nome_atendente, cnpj, endereco, inscricao_estadual,
                nome_cliente, cpf_cliente, forma_pagamento, parcelamento,
                icms, ipi, pis, cofins
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = conecta.obtemConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nf.getVendaId());
            stmt.setString(2, nf.getNumeroNF());
            stmt.setString(3, nf.getSerie());
            stmt.setString(4, nf.getModelo());
            stmt.setTimestamp(5, nf.getDataEmissao());
            stmt.setString(6, nf.getChaveAcesso());
            stmt.setString(7, nf.getNomeEmpresa());
            stmt.setString(8, nf.getNomeAtendente());
            stmt.setString(9, nf.getCnpj());
            stmt.setString(10, nf.getEndereco());
            stmt.setString(11, nf.getInscricaoEstadual());
            stmt.setString(12, nf.getNomeCliente());
            stmt.setString(13, nf.getCpfCliente());
            stmt.setString(14, nf.getFormaPagamento());
            stmt.setString(15, nf.getParcelamento());
            stmt.setDouble(16, nf.getIcms());
            stmt.setDouble(17, nf.getIpi());
            stmt.setDouble(18, nf.getPis());
            stmt.setDouble(19, nf.getCofins());

            stmt.executeUpdate();
        }
    }
}
