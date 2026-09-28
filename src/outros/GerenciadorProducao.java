package outros;
import java.util.ArrayList;

import Estrategias.*;
import Estrategias.Demandas.*;
import PackMaquinas.*;
import PackMateriaPrima.*;
import PackProdutos.*;

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
    
    public void registrarDemanda(Produto tipoProduto, int quantidadeProdutos)
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

    public void fabricarDemanda()
    {
        
        return;
    }

    public void auditoriaGeral()
    {
        //Percorre coleções de objetos que implementam Auditavel e exibe um relatório consolidado da planta;
    }

    public void comprarMateriaPrima(MateriaPrima materia, int qnt)
    {
        this.budget=this.budget-qnt*materia.getCusto();
        materiaPrima.adicionarEstoque(qnt);
    }

    public double exibirBudget()
    {
        return budget;
    }

    public void exibirArmazem()
    {

        for (Produto produto : produtosFabricados) {
            System.out.printf("[INFO] Produto: %s, Quantidade: %d, Qualidade: %0.2f",);
        }

        //Lista todos os produtos acabados em estoque, com quantidade, qualidade e lote.
   
    }

    private double calcularCustoProducao(ArrayList<Demanda> demanda, int id, int qnt)
    {
        double custo=0;
        ArrayList <Maquina> maquinas = demanda.get(id).getProduto().getProcessoProducao();
        for (Maquina maquina : maquinas) {
            custo+=maquina.getCustoOperacao();
        }
        return  custo*qnt;
    
    }
}
