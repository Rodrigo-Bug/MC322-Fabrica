import java.util.ArrayList;

import Estrategias.EstrategiaProducao;
import Estrategias.Demandas.*;
import PackMaquinas.*;
import PackMateriaPrima.MateriaPrima;
import PackProdutos.Produto;

public class GerenciadorProducao
{
    private ArrayList<Demanda> demandas;
    private ArrayList<Produto> produtosFabricados;
    private ArrayList<Maquina> maquinas;
    private EstrategiaProducao estrategiaAnterior;
    private EstrategiaProducao estrategiaAtual;
    private MateriaPrima materiaPrima;
    private double budget;
    static private int idDemanda = 0;

    public void setEstrategia(EstrategiaProducao novaEstrategia)
    {
        this.estrategiaAnterior = this.estrategiaAtual;
        this.estrategiaAtual = novaEstrategia;
    }

    public void resetEstrategia()
    {
        this.estrategiaAtual = this.estrategiaAnterior;
    }

    public EstrategiaProducao getEstrategia()
    {
        return this.estrategiaAtual;
    }
    
    public void registrarDemanda(String tipoProduto, int quantidadeProdutos)
    {
        idDemanda++;
        Demanda oportunidade = new Demanda(idDemanda, tipoProduto, quantidadeProdutos);
        demandas.add(oportunidade);
    }

    public ArrayList<Demanda> getDemandas()
    {
        return this.demandas;
    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }

    public double getBudget()
    {
        return this.budget;
    }
    
    public void atualizarDemanda()
    {
        return;
    }

    public void executarProximaProducao()
    {
        
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
        return 0.0;
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
