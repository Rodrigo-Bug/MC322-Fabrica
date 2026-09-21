import java.util.ArrayList;

public interface EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas);
    public String getNomeEstrategia();
}


