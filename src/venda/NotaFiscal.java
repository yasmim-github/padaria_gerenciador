package venda;

import java.sql.Timestamp;

public class NotaFiscal {
    private int id;
    private int vendaId;
    private String numeroNF;
    private String serie;
    private String modelo;
    private Timestamp dataEmissao;
    private String chaveAcesso;
    private String nomeEmpresa;
    private String nomeAtendente;
    private String cnpj;
    private String endereco;
    private String inscricaoEstadual;
    private String nomeCliente;
    private String cpfCliente;
    private String formaPagamento;
    private String parcelamento;
    private double icms;
    private double ipi;
    private double pis;
    private double cofins;

    public NotaFiscal(int vendaId, String numeroNF, String serie, String modelo, Timestamp dataEmissao,
                       String chaveAcesso, String nomeEmpresa, String nomeAtendente, String cnpj, String endereco,
                       String inscricaoEstadual, String nomeCliente, String cpfCliente, String formaPagamento,
                       String parcelamento, double icms, double ipi, double pis, double cofins) {
        this.vendaId = vendaId;
        this.numeroNF = numeroNF;
        this.serie = serie;
        this.modelo = modelo;
        this.dataEmissao = dataEmissao;
        this.chaveAcesso = chaveAcesso;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeAtendente = nomeAtendente;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.inscricaoEstadual = inscricaoEstadual;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.formaPagamento = formaPagamento;
        this.parcelamento = parcelamento;
        this.icms = icms;
        this.ipi = ipi;
        this.pis = pis;
        this.cofins = cofins;
    }

    public int getVendaId() { return vendaId; }
    public Timestamp getDataEmissao() { return dataEmissao; }
    public String getNumeroNF() { return numeroNF; }
    public String getSerie() { return serie; }
    public String getModelo() { return modelo; }
    public String getChaveAcesso() { return chaveAcesso; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public String getNomeAtendente() { return nomeAtendente; }
    public String getCnpj() { return cnpj; }
    public String getEndereco() { return endereco; }
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public String getNomeCliente() { return nomeCliente; }
    public String getCpfCliente() { return cpfCliente; }
    public String getFormaPagamento() { return formaPagamento; }
    public String getParcelamento() { return parcelamento; }
    public double getIcms() { return icms; }
    public double getIpi() { return ipi; }
    public double getPis() { return pis; }
    public double getCofins() { return cofins; }
}
