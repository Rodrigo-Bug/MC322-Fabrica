package Estrategias;

import java.util.ArrayList;
import Estrategias.Demandas.Demanda;

public class EstrategiaUrgente implements EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas, double orcamentoDisponivel)
    {
        return demandas.get(demandas.size() - 1);
    }
    public String getNomeEstrategia()
    {
        return "Urgente";
    }
}
