package PackProdutos;

import java.util.ArrayList;
import PackMaquinas.Maquina;

public class Bloco extends Produto {
    public Bloco(String id, String nome, double materiaPrima, double qualidade, ArrayList<Maquina> processo, double tempoUsinagem, double tempoTratamento) {
        this.id=id; this.nome=nome; this.quantidadeMateriaPrimaNecessaria=materiaPrima; this.qualidade=qualidade;
        this.processoProducao=processo; this.tempoUsinagem=tempoUsinagem; this.tempoTratamentoSuperficial=tempoTratamento;
    }
    @Override public double calcularTempoProducao(int demanda) { return demanda*(tempoUsinagem+tempoTratamentoSuperficial); }
    @Override public String getTipo() { return "Bloco"; }
    @Override public void processar(StatusProduto proxStatus) {
        this.status=proxStatus;
        if (proxStatus == StatusProduto.FINALIZADO) incrementarTotalProdutosFabricados();
    }
    @Override public Produto criarNovaUnidade(String novoId, int lote) {
        Bloco p = new Bloco(novoId, nome, quantidadeMateriaPrimaNecessaria, qualidade, processoProducao, tempoUsinagem, tempoTratamentoSuperficial);
        p.setLote(lote);
        return p;
    }
}
