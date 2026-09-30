package PackProdutos;

import java.util.ArrayList;
import PackInterfaces.Auditavel;
import PackMaquinas.Maquina;

public abstract class Produto implements Auditavel {
    protected String id;
    protected String nome;
    protected StatusProduto status = StatusProduto.FUNDIDO;
    protected double quantidadeMateriaPrimaNecessaria;
    protected double qualidade;
    protected double probabilidadeFalhaAcumulada;
    protected ArrayList<Maquina> processoProducao;
    protected double tempoUsinagem;
    protected double tempoTratamentoSuperficial;
    private int lote;
    private static int totalProdutosFabricados = 0;

    public abstract void processar(StatusProduto proxStatus);
    public abstract double calcularTempoProducao(int demanda);
    public abstract String getTipo();
    public abstract Produto criarNovaUnidade(String novoId, int lote);

    public String getId() { return id; }
    public String getNome() { return nome; }
    public StatusProduto getStatus() { return status; }
    public void setStatus(StatusProduto status) { this.status = status; }
    public double getDemandaMateriaPrima() { return quantidadeMateriaPrimaNecessaria; }
    public double getQuantidadeMateriaPrimaPorUnidade() { return quantidadeMateriaPrimaNecessaria; }
    public double getQualidade() { return qualidade; }
    public double getProbabilidadeFalhaAcumulada() { return probabilidadeFalhaAcumulada; }
    public ArrayList<Maquina> getProcessoProducao() { return processoProducao; }
    public int getLote() { return lote; }
    protected void setLote(int lote) { this.lote = lote; }

    public void upProbabilidadeFalha() { probabilidadeFalhaAcumulada = Math.min(1.0, probabilidadeFalhaAcumulada + 0.10); }
    protected static void incrementarTotalProdutosFabricados() { totalProdutosFabricados++; }
    public static int getTotalProdutosFabricados() { return totalProdutosFabricados; }

    @Override
    public String gerarRelatorioDiagnostico() {
        return String.format("Produto: %s | Tipo: %s | Qualidade: %.2f | Risco: %.2f | Lote: %d",
                nome, getTipo(), qualidade, probabilidadeFalhaAcumulada, lote);
    }

    @Override
    public boolean precisaManutencao() {
        return qualidade < 0.60 || probabilidadeFalhaAcumulada > 0.50;
    }
}
