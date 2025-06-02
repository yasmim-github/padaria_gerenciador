package venda;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private boolean produzido; // novo atributo
    private double pesoEmKg;   // novo atributo

    // Construtor completo
    public Produto(int id, String nome, double preco, int quantidade, boolean produzido, double pesoEmKg) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.produzido = produzido;
        this.pesoEmKg = pesoEmKg;
    }

    // Construtor para produto não produzido (peso em kg = 0)
    public Produto(int id, String nome, double preco, int quantidade) {
        this(id, nome, preco, quantidade, false, 0);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public boolean isProduzido() { return produzido; }
    public double getPesoEmKg() { return pesoEmKg; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setProduzido(boolean produzido) { this.produzido = produzido; }
    public void setPesoEmKg(double pesoEmKg) { this.pesoEmKg = pesoEmKg; }

    // Método para calcular o valor total
    public double calcularValorTotal() {
        if (produzido) {
            return preco * pesoEmKg;
        } else {
            return preco * quantidade;
        }
    }
}
