package Estrategias.Demandas;
public class Demanda
{
    private int id;
    private String tipoProduto;
    private int quantidadeProdutos;
    private EstatusDemanda status;

    public Demanda(int id, String tipoProduto, int quantidadeProdutos)
    {
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        this.status = EstatusDemanda.PENDENTE;
    }
    public void atualizarQuantidade(int quantidadeProdutos){
        this.quantidadeProdutos=quantidadeProdutos;
    }

    public String getDemanda()
    {
        return id + "\tProduto: " + tipoProduto + "\tQuantidade: " + quantidadeProdutos + "\tStatus: " + status;
    }

    public void atender()
    {
        this.status = EstatusDemanda.CONCLUIDA;
    }

    public double calcularMateriaPrimaNecessaria()
    {
        return 0.0;
    }
}
