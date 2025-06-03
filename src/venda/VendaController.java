package venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class VendaController extends JFrame {
    private VendaView view;
    private List<ItemVenda> itensVenda = new ArrayList<>();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public VendaController(VendaView view) {
        this.view = view;
    }

    public void adicionarProduto() {
        try {
            String idStr = JOptionPane.showInputDialog("ID do Produto:");
            int idProduto = Integer.parseInt(idStr);

            Produto produto = produtoDAO.buscarProdutoPorId(idProduto);
            if (produto == null) {
                JOptionPane.showMessageDialog(view, "Produto não encontrado.");
                return;
            }

            double valorUnitario = produto.getPreco();
            double valorTotal = 0.0;

            ItemVenda item = new ItemVenda(0, 0, idProduto, 0, valorUnitario, 0.0, produto.getNome());
            item.setProduzido(produto.isProduzido());

            if (produto.isProduzido()) {
                String pesoStr = JOptionPane.showInputDialog("Informe os quilos (kg), ex: 0.5:");
                double pesoEmKg = Double.parseDouble(pesoStr);
                item.setPesoEmKg(pesoEmKg);
                item.calcularValorTotal();
            } else {
                String qtdStr = JOptionPane.showInputDialog("Informe as unidades:");
                int quantidade = Integer.parseInt(qtdStr);
                item.setQuantidade(quantidade);
                item.calcularValorTotal();
            }

            itensVenda.add(item);

            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.addRow(new Object[]{
                idProduto,
                produto.getNome(),
                produto.isProduzido() ? item.getPesoEmKg() + " kg" : item.getQuantidade() + " un",
                valorUnitario,
                item.getValorTotal(),
                produto.isProduzido() ? "Produzido" : "Não Produzido"
            });

            atualizarTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro: " + e.getMessage());
        }
    }




    private void atualizarTotal() {
        double total = itensVenda.stream().mapToDouble(ItemVenda::getValorTotal).sum();
        view.getTxtTotal().setText(String.format("%.2f", total));
    }

    public void finalizarVenda() {
        try {
            double total = Double.parseDouble(view.getTxtTotal().getText().replace(",", "."));
            double recebido = Double.parseDouble(view.getTxtValorRecebido().getText().replace(",", "."));
            double troco = recebido - total;

            if (troco < 0) {
                JOptionPane.showMessageDialog(view, "Valor recebido insuficiente.");
                return;
            }

            view.getTxtTroco().setText(String.format("%.2f", troco));

            VendaDAO vendaDAO = new VendaDAO();
            ItemVendaDAO itemDAO = new ItemVendaDAO();
            EstoqueLogDAO logDAO = new EstoqueLogDAO();

            Venda venda = new Venda(1, Timestamp.valueOf(LocalDateTime.now()), total, recebido, troco);
            int vendaId = vendaDAO.salvar(venda);

            for (ItemVenda item : itensVenda) {
                item.setVendaId(vendaId);
                itemDAO.salvar(item);

                if (item.isProduzido()) {
                    produtoDAO.baixarEstoque(item.getProdutoId(), item.getPesoEmKg());
                    
                    EstoqueLog log = new EstoqueLog(0, item.getProdutoId(), 1, "saida", 
                            item.getPesoEmKg(), Timestamp.valueOf(LocalDateTime.now()));
                    logDAO.salvar(log);
                } else {
                    produtoDAO.baixarEstoque(item.getProdutoId(), item.getQuantidade());
                    
                    EstoqueLog log = new EstoqueLog(0, item.getProdutoId(), 1, "saida", 
                            item.getQuantidade(), Timestamp.valueOf(LocalDateTime.now()));
                    logDAO.salvar(log);
                }
            }

            
            NotaFiscalDAO notaDAO = new NotaFiscalDAO();

            NotaFiscal nota = new NotaFiscal(
                    vendaId,
                    "0001",                                  
                    "1",                                     
                    "55",                                    
                    Timestamp.valueOf(LocalDateTime.now()),  
                    gerarChaveAcesso(),                      
                    "Padaria do Padoca",                    
                    "Atendente 01",                          
                    "00.000.000/0001-00",                    
                    "Rua da Alegria, 123",                  
                    "123456789",                             
                    "Cliente Padrão",                        
                    "000.000.000-00",                        
                    "Dinheiro",                              
                    "À vista",                               
                    total * 0.18,                            
                    total * 0.04,                            
                    total * 0.0165,                          
                    total * 0.076                            
            );

            notaDAO.salvar(nota);

            
            exibirNotaFiscal(nota);

            JOptionPane.showMessageDialog(view, "Venda realizada com sucesso!\nNota fiscal emitida.");
            limpar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro na venda: " + e.getMessage() + "\nVerifique se os campos foram \npreenchidos corretamente.");
        }
    }

    
    private String gerarChaveAcesso() {
        StringBuilder chave = new StringBuilder();
        while (chave.length() < 44) {
            chave.append(UUID.randomUUID().toString().replace("-", ""));
        }
        return chave.substring(0, 44);
    }

   
    private void exibirNotaFiscal(NotaFiscal nota) {
        StringBuilder sb = new StringBuilder();
        sb.append("========= NOTA FISCAL =========\n");
        sb.append("Nº: ").append(nota.getNumeroNF()).append(" | Série: ").append(nota.getSerie()).append("\n");
        sb.append("Chave de Acesso: ").append(nota.getChaveAcesso()).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("Empresa: ").append(nota.getNomeEmpresa()).append("\n");
        sb.append("CNPJ: ").append(nota.getCnpj()).append("\n");
        sb.append("Endereço: ").append(nota.getEndereco()).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("Cliente: ").append(nota.getNomeCliente()).append("\n");
        sb.append("CPF: ").append(nota.getCpfCliente()).append("\n");
        sb.append("-----------------------------------------\n");

        for (ItemVenda item : itensVenda) {
            if (item.isProduzido()) {
                sb.append(String.format("%.2f kg ", item.getPesoEmKg()));
            } else {
                sb.append(item.getQuantidade()).append(" un ");
            }
            sb.append(item.getNomeProduto()).append(" - R$ ")
              .append(String.format("%.2f", item.getValorTotal())).append("\n");
        }


        sb.append("-----------------------------------------\n");
        sb.append("Total: R$ ").append(String.format("%.2f", nota.getIcms() + nota.getIpi() + nota.getPis() + nota.getCofins() + 
                   itensVenda.stream().mapToDouble(ItemVenda::getValorTotal).sum())).append("\n");
        sb.append("ICMS: R$ ").append(String.format("%.2f", nota.getIcms())).append("\n");
        sb.append("IPI: R$ ").append(String.format("%.2f", nota.getIpi())).append("\n");
        sb.append("PIS: R$ ").append(String.format("%.2f", nota.getPis())).append("\n");
        sb.append("COFINS: R$ ").append(String.format("%.2f", nota.getCofins())).append("\n");
        sb.append("=========================================");

        JOptionPane.showMessageDialog(view, sb.toString());
    }

    private void limpar() {
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        model.setRowCount(0);
        view.getTxtTotal().setText("");
        view.getTxtTroco().setText("");
        view.getTxtValorRecebido().setText("");
        itensVenda.clear();
    }
}