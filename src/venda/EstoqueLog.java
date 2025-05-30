package venda;

import java.sql.Timestamp;

public class EstoqueLog {
    private int id;
    private int produtoId;
    private int usuarioId;
    private String acao; // "entrada", "saida" ou "ajuste"
    private int quantidade;
    private Timestamp dataHora;

    public EstoqueLog(int id, int produtoId, int usuarioId, String acao, int quantidade, Timestamp dataHora) {
        this.id = id;
        this.produtoId = produtoId;
        this.usuarioId = usuarioId;
        this.acao = acao;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public int getProdutoId() { return produtoId; }
    public int getUsuarioId() { return usuarioId; }
    public String getAcao() { return acao; }
    public int getQuantidade() { return quantidade; }
    public Timestamp getDataHora() { return dataHora; }
}
