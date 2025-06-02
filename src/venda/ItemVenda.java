package venda;

public class ItemVenda {
    private int id;
    private int vendaId;
    private int produtoId;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private String nomeProduto;
    private boolean produzido; // novo atributo
    private double pesoEmKg;   // novo atributo

    public ItemVenda(int id, int vendaId, int produtoId, int quantidade, double valorUnitario, double valorTotal, String nomeProduto) {
        this.id = id;
        this.vendaId = vendaId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.nomeProduto = nomeProduto;
    }


   
    public int getId() { return id; }
    public int getVendaId() { return vendaId; }
    public int getProdutoId() { return produtoId; }
    public int getQuantidade() { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }
    public double getValorTotal() { return valorTotal; }
    public String getNomeProduto() { return nomeProduto; }
    public boolean isProduzido() { return produzido; }
    public double getPesoEmKg() { return pesoEmKg; }

   
    public void setVendaId(int vendaId) { this.vendaId = vendaId; }
    public void setPesoEmKg(double pesoEmKg) { this.pesoEmKg = pesoEmKg; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setProduzido(boolean produzido) { this.produzido = produzido; }

    
    public void calcularValorTotal() {
        if (produzido) {
            this.valorTotal = valorUnitario * pesoEmKg;
        } else {
            this.valorTotal = valorUnitario * quantidade;
        }
    }
}
