package PackProdutos;
import Interfaces.Auditavel;
import Interfaces.Manutencao;

public abstract class Produto implements Auditavel, Manutencao{
    protected String id;
    protected String nome;
    protected StatusProduto status;
    protected double quantidadeMateriaPrimaNecessaria;
    protected double qualidade;
    protected double probabilidadeFalhaAcumulada;
    protected double tempoUsinagem;
    protected double tempoTratamentoSuperficial;
    static protected int totalProdutosFabricados; //VERIFICAR SE É PRA SER STATIC!!!


    //enums

    public enum StatusProduto {
        Não_Iniciado,
        Usinado,
        Tratado_Superficialmente,
        Inspecionado,
        Finalizado,
    }

     //Abstract
    public abstract void processar();
    public abstract double calcularTempoProducao(int demanda);
    public abstract String getTipo();

    //Concrete

    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public StatusProduto getStatus() {
        return status;
    }

    public void setStatus(StatusProduto status)
    {
        this.status = status;
    }
  
    public double getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public void upProbabilidadeFalha()
    {
        this.probabilidadeFalhaAcumulada += 0.1;
    }

    public double getQualidade(){
        return this.qualidade;
    }


    @Override 
    public String gerarRelatorioDiagnostico()
    {
        return("Probabilidade de falha do produto de qualidade " + this.qualidade + " está em " +this.probabilidadeFalhaAcumulada+ " atualmente.");
    }
    
    @Override 
    public boolean precisaDeManutencao()
    {
        if(this.probabilidadeFalhaAcumulada>0.8){
            return true;
        }else{
            return false;
        }
    }
}

//Usando o nome dos produtos como subclasses, para expandir as opcoes de nomes no main.
class Cabecote extends Produto
{
    public Cabecote(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade,double tempoUsinagem,double tempoTratamentoSuperficial)
    {
        this.id = id;
        this.nome = nome;
        this.status = StatusProduto.Não_Iniciado;
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
        return "Cabeçote";
    }

@Override
    public void processar(){
        switch (this.status) {
        case Não_Iniciado:
            this.status=StatusProduto.Usinado;
            break;

        case Usinado:
            this.status=StatusProduto.Tratado_Superficialmente;
            break;

        case Tratado_Superficialmente:
            this.status=StatusProduto.Inspecionado;
            break;

        case Inspecionado:
            this.status=StatusProduto.Finalizado;
            break;

        case Finalizado:
            break;
        }

    
}
}
class Corpo extends Produto{
    public Corpo(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade,double tempoUsinagem,double tempoTratamentoSuperficial){
        this.id = id;
        this.nome = nome;
        this.status = StatusProduto.Não_Iniciado;
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
        return "Corpo";
    }

    @Override
    public void processar(){
        switch (this.status) {
        case Não_Iniciado:
            this.status=StatusProduto.Usinado;
            break;

        case Usinado:
            this.status=StatusProduto.Tratado_Superficialmente;
            break;

        case Tratado_Superficialmente:
            this.status=StatusProduto.Inspecionado;
            break;

        case Inspecionado:
            this.status=StatusProduto.Finalizado;
            break;

        case Finalizado:
            break;
        }
}
}
class TuboConector extends Produto{
    public TuboConector(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade,double tempoUsinagem,double tempoTratamentoSuperficial){
        this.id = id;
        this.nome = nome;
        this.status = StatusProduto.Não_Iniciado;
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
        return "Tubo Conector";
    }

    @Override
    public void processar(){
        switch (this.status) {
        case Não_Iniciado:
            this.status=StatusProduto.Usinado;
            break;

        case Usinado:
            this.status=StatusProduto.Tratado_Superficialmente;
            break;

        case Tratado_Superficialmente:
            this.status=StatusProduto.Inspecionado;
            break;

        case Inspecionado:
            this.status=StatusProduto.Finalizado;
            break;

        case Finalizado:
            break;
        }
    }
    }
    