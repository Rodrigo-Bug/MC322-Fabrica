package Estrategias.Demandas;

import PackProdutos.Produto;

public class Demanda {
    private int id;
    private Produto tipoProduto;
    private int quantidadeProdutos;
    private int quantidadeProduzida;
    private StatusDemanda status;

    public Demanda(int id, Produto tipoProduto, int quantidadeProdutos) {
        this.id=id; this.tipoProduto=tipoProduto; this.quantidadeProdutos=quantidadeProdutos;
        this.quantidadeProduzida=0; this.status=StatusDemanda.PENDENTE;
    }
    public int getId() { return id; }
    public Produto getProduto() { return tipoProduto; }
    public int getQuantidadeProdutos() { return quantidadeProdutos; }
    public int getQuantidadeProduzida() { return quantidadeProduzida; }
    public int getQuantidadeRestante() { return Math.max(0, quantidadeProdutos-quantidadeProduzida); }
    public StatusDemanda getStatus() { return status; }
    public void atualizarQuantidade(int q) { if (status==StatusDemanda.PENDENTE && q>0) quantidadeProdutos=q; }
    public double calcularMateriaPrimaNecessaria() { return getQuantidadeRestante()*tipoProduto.getDemandaMateriaPrima(); }
    public double estimarCustoOperacao() { return tipoProduto.getProcessoProducao().stream().mapToDouble(m->m.getCustoOperacao()).sum()*getQuantidadeRestante(); }
    public boolean ehFinanceiramenteViavel(double orcamento) { return estimarCustoOperacao() <= orcamento; }
    
    
    public void iniciar() { if (status==StatusDemanda.PENDENTE) status=StatusDemanda.EM_PRODUCAO; }
    
    
    public void registrarProduzidos(int q) { if (status!=StatusDemanda.EM_PRODUCAO || q<=0) return; quantidadeProduzida=Math.min(quantidadeProdutos, quantidadeProduzida+q); if (quantidadeProduzida>=quantidadeProdutos) status=StatusDemanda.CONCLUIDA; }
    public void cancelar() { if (status!=StatusDemanda.CONCLUIDA) status=StatusDemanda.CANCELADA; }
    public String getDemanda() { return id+" | Produto: "+tipoProduto.getNome()+" | Quantidade: "+quantidadeProdutos+" | Produzida: "+quantidadeProduzida+" | Status: "+status.getDescricao(); }
}
