package PackProdutos;

public class Bloco extends Produto
{
    public Bloco(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade,double tempoUsinagem,double tempoTratamentoSuperficial){
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.qualidade = qualidade;
        this.probabilidadeFalhaAcumulada=0;
        this.tempoUsinagem=tempoUsinagem;
        this.tempoTratamentoSuperficial=tempoTratamentoSuperficial;
        //this.totalProdutosFabricados=0;
    }

    @Override
    public double calcularTempoProducao(int demanda){
        return  demanda*(this.tempoUsinagem+this.tempoTratamentoSuperficial);
    }

    @Override
    public String getTipo(){
        return "Bloco";
    }

    @Override
    public void processar(StatusProduto proxStatus){
        switch (proxStatus) {
        case USINADO:
            this.status=StatusProduto.USINADO;
            break;

        case TRATADO_SUPERFICIALMENTE:
            this.status=StatusProduto.TRATADO_SUPERFICIALMENTE;
            break;

        case INSPECIONADO:
            this.status=StatusProduto.INSPECIONADO;
            break;

        case FINALIZADO:
            this.status=StatusProduto.FINALIZADO;
            break;
        
        default:
            break;
        }
    }
}