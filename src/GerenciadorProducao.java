import java.util.ArrayList;

import PackMaquinas.Maquina;
import PackMateriaPrima.MateriaPrima;
import PackProdutos.Produto;

public class GerenciadorProducao
{
    private ArrayList<Demanda> demandas;
    private ArrayList<Produto> produtosFabricados;
    private ArrayList<Maquina> maquinas;
    private EstrategiaProducao estrategiaAtual;
    private MateriaPrima materiaPrima;
    private double budget;

    public void setEstrategia(EstrategiaProducao novaEstrategia)
    {
        this.estrategiaAtual = novaEstrategia;
    }
    
    public void registrarDemanda(Demanda oportunidade)
    {
        demandas.add(oportunidade);
    }
    
    public void atualizarDemanda()
    {
        return;
    }

    public void executarProximaProducao()
    {
        //Utiliza estrategiaAtual.selecionarDemanda(...) para identificar a demanda correta e inicia a fabricação;
        return;
    }

    public void auditoriaGeral()
    {
        //Percorre coleções de objetos que implementam Auditavel e exibe um relatório consolidado da planta;
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
        //Lista todos os produtos acabados em estoque, com quantidade, qualidade e lote.
        return;
    }

    private void calcularCustoProducao()
    {
        return;
    }
}
