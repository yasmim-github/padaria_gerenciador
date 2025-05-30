package venda;

import java.sql.Timestamp;

public class Venda {
    private int usuarioId;
    private Timestamp dataHora;
    private double total;
    private double valorRecebido;
    private double troco;

    public Venda(int usuarioId, Timestamp dataHora, double total, double valorRecebido, double troco) {
        this.usuarioId = usuarioId;
        this.dataHora = dataHora;
        this.total = total;
        this.valorRecebido = valorRecebido;
        this.troco = troco;
    }

    public int getUsuarioId() { return usuarioId; }
    public Timestamp getDataHora() { return dataHora; }
    public double getTotal() { return total; }
    public double getValorRecebido() { return valorRecebido; }
    public double getTroco() { return troco; }
}
