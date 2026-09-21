import java.util.ArrayList;

public interface EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas, double orcamentoDisponivel);
    public String getNomeEstrategia();
}

public class EstrategiaFiFo implements EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas, double orcamentoDisponivel)
    {

    }

    public String getNomeEstrategia()
    {

    }
}

public class EstrategiaMaiorDemanda implements EstrategiaProducao
{
    public Demanda selecionarDemanda(ArrayList<Demanda> demandas, double orcamentoDisponivel)
    {

    }

    public String getNomeEstrategia()
    {

    }
}
