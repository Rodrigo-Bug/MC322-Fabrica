import java.util.ArrayList;

public class GerenciadorProducao
{
    private ArrayList<Demanda> demandas;
    private ArrayList<Produto> produtosFabricados;
    private ArrayList<Maquina> maquinas;
    private MateriaPrima materiaPrima;
    private double budget;

    public void registrarDemanda(Demanda oportunidade)
    {
        demandas.add(oportunidade);
    }
    
    public void atualizarDemanda()
    {
        return;
    }

    public void fabricarDemanda()
    {
        return;
    }

    public void comprarMateriaPrima()
    {
        return;
    }

    public double exibirBudget()
    {
        return;
    }

    public void exibirArmazem()
    {
        return;
    }

    private void calcularCustoProducao()
    {
        return;
    }
}
