package Estrategias;
import java.util.List;
import Estrategias.Demandas.*;
public class EstrategiaUrgente implements EstrategiaProducao {
    public Demanda selecionarDemanda(List<Demanda> demandas,double orcamento){ for(int i=demandas.size()-1;i>=0;i--) if(demandas.get(i).getStatus()==StatusDemanda.PENDENTE) return demandas.get(i); return null; }
    public String getNomeEstrategia(){ return "Urgente"; }
}
