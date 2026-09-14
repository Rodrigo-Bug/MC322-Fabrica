abstract class Produto {
    protected String id;
    protected String nome;
    protected String status;
    protected double quantidadeMateriaPrimaNecessaria;
    protected double qualidade;
    protected double probabilidadeFalhaAcumulada;
    static protected int totalProdutosFabricados;

     //Abstract
    public abstract void processar();
    public abstract void definirDemandaMateriaPrima(double quantidadeMateriaPrimaNecessaria);
    public abstract void calcularTempoProducao();
    public abstract void getTipo();

    //Concrete
    public void setStatus(String status)
    {
        this.status = status;
    }

    public void upProbabilidadeFalha()
    {
        this.probabilidadeFalhaAcumulada += 0.1;
    }

    public double getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public double getQualidade()
    {
        return qualidade;
    } 
}

//Usando o nome dos produtos como subclasses, para expandir as opcoes de nomes no main.
class Cabecote extends Produto
{
    public Cabecote(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade)
    {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.qualidade = qualidade;
    }
}

class Corpo extends Produto
{
    public Corpo(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade)
    {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.qualidade = qualidade;
    }
}

class TuboConector extends Produto
{
    public TuboConector(String id, String nome, double quantidadeMateriaPrimaNecessaria, double qualidade)
    {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.qualidade = qualidade;
    }
}