import Interfaces.Auditavel;

abstract class Produto implements Auditavel
{
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

    public String gerarRelatorioDiagnostico()
    {
        return("Probabilidade de falha do produto de qualidade " + this.qualidade + " está em " this.probabilidadeFalhaAcumulada " atualmente.");
    }

    public boolean precisaDeManutencao()
    {
        if(this.probabilidadeFalhaAcumulada 
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

    public int processar(Produto produto, MateriaPrima materiaPrima, int demanda)
    {
        int falhas=0;
        int qnt=0;
        System.out.printf("\n[OK] Usinando %s", produto.getNome());
        
        for (int i=0; i<demanda; i++){
            qnt++;
            if(verificarFalha()){
                produto.upProbabilidadeFalha();
                falhas++;
            }
            if(dano()){
                System.out.printf("\n [NOK] Não foi possivel usinar %d %s(s)",demanda-qnt, produto.getNome());
                System.out.printf("\n [OK] Foram usinados %d %s(s)",qnt, produto.getNome());
                System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());
                return qnt;
            }

        }
        System.out.printf("\n [OK] Foram usinados %d %s(s)",qnt, produto.getNome());
        System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());

        return qnt;
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
