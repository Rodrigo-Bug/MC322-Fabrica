package PackProdutos;
import PackInterfaces.*;

public abstract class Produto implements Auditavel, Manutencao
{
    protected String id;
    protected String nome;
    protected StatusProduto status = StatusProduto.FUNDIDO;
    protected double quantidadeMateriaPrimaNecessaria;
    protected double qualidade;
    protected double probabilidadeFalhaAcumulada;
    protected double tempoUsinagem;
    protected double tempoTratamentoSuperficial;
    static protected int totalProdutosFabricados; //VERIFICAR SE É PRA SER STATIC!!!

     //Abstract
    public abstract void processar(StatusProduto proxStatus);
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