package Estrategias;
import java.util.ArrayList;

import Estrategias.Demandas.Demanda;

public interface EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas, double orcamentoDisponivel);
    public String getNomeEstrategia();
}
