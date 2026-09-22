public class Demanda
{
    private String tipoProduto;
    private int quantidadeProdutos;
    private EstatusDemanda status;

    public void atualizarQuantidade(int quantidadeProdutos){
        this.quantidadeProdutos=quantidadeProdutos;
    }

    public void atender()
    {
        this.atendida = true;
    }

    public double calcularMateriaPrimaNecessaria(){
        return tipoProduto;
    }
}
