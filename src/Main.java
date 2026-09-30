import java.util.*;
import Estrategias.*;
import Estrategias.Demandas.Demanda;
import PackMaquinas.*;
import PackMateriaPrima.MateriaPrima;
import PackProdutos.*;
import outros.*;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("RL SOLUCOES - Fabrica de componentes para motores");
        System.out.println("\"Movendo o futuro, peca por peca\"");
        System.out.println("Desenvolvido por: Rodrigo Gonçaves e Lucas Marques"); 
        System.out.println("============================================================");

        Cenario cenario = escolherCenario();
        MateriaPrima aluminio = new MateriaPrima("AL2618","Aluminio",100.0,"kg",10.0);

        MaquinaUsinagem torno = new MaquinaUsinagem("Torno CNC",50,10.0,cenario);
        MaquinaTratamentoSuperficial tratamento = new MaquinaTratamentoSuperficial("Linha de Tratamento",50,7.5,cenario);
        MaquinaInspecao inspecao = new MaquinaInspecao("Inspecao Dimensional",50,5.0,cenario);
        ArrayList<Maquina> processo = new ArrayList<>(List.of(torno,tratamento,inspecao));

        ArrayList<Produto> catalogo = new ArrayList<>();
        catalogo.add(new Cabecote("CAT-CAB","Cabecote",8.0,0.90,processo,4.0,2.0));
        catalogo.add(new Virabrequim("CAT-VIR","Virabrequim",10.0,0.85,processo,5.0,2.5));
        catalogo.add(new Pistao("CAT-PIS","Pistao",2.0,0.70,processo,2.0,1.0));
        catalogo.add(new Bloco("CAT-BLO","Bloco do Motor",15.0,0.95,processo,7.0,3.0));

        GerenciadorProducao g = new GerenciadorProducao(cenario,aluminio);
        g.adicionarMaquina(torno); g.adicionarMaquina(tratamento); g.adicionarMaquina(inspecao);

        int op;
        do {
            cabecalho(g);
            System.out.println("1 - Demandas");
            System.out.println("2 - Fabricacao");
            System.out.println("3 - Consultar estoque/armazem");
            System.out.println("4 - Comprar materia-prima");
            System.out.println("5 - Gerenciar estrategia");
            System.out.println("6 - Auditoria");
            System.out.println("7 - Alterar cenario");
            System.out.println("0 - Sair");
            op=lerInt("Escolha: ");
            switch(op){
                case 1 -> menuDemandas(g,catalogo);
                case 2 -> g.executarProximaProducao();
                case 3 -> { g.exibirEstoque(); g.exibirArmazem(); }
                case 4 -> { double q=lerDouble("Quantidade de aluminio para comprar: "); System.out.println(g.comprarMateriaPrima(q)?"[OK] Compra realizada.":"[NOK] Compra invalida ou budget insuficiente."); }
                case 5 -> menuEstrategia(g);
                case 6 -> g.gerarAuditoriaGeral();
                case 7 -> {cenario=escolherCenario(); g.setCenario(cenario);}
                case 0 -> System.out.println("Encerrando a fabrica.");
                default -> System.out.println("Opcao invalida.");
            }
        } while(op!=0);
    }

    private static Cenario escolherCenario(){
        while(true){ System.out.println("1 - Cenario Ideal\n2 - Cenario Apocaliptico"); int op=lerInt("Escolha: "); if(op==1||op==2)return Cenario.escolha(op); System.out.println("Opcao invalida."); }
    }
    private static void cabecalho(GerenciadorProducao g){
        System.out.println("\n============================================================");
        System.out.println("ESTRATEGIA ATUAL: "+g.getEstrategia().getNomeEstrategia());
        System.out.println("CENARIO ATIVO: "+g.getCenario().getDescricao());
        System.out.printf("BUDGET ATUAL: R$ %.2f%n",g.getBudget());
        System.out.println("============================================================");
    }
    private static void menuDemandas(GerenciadorProducao g,ArrayList<Produto> catalogo){
        System.out.println("1 - Registrar demanda\n2 - Atualizar demanda\n3 - Listar demandas\n0 - Voltar");
        int op=lerInt("Escolha: ");
        if(op==1){ for(int i=0;i<catalogo.size();i++)System.out.println((i+1)+" - "+catalogo.get(i).getNome()); int p=lerInt("Produto: "); int q=lerInt("Quantidade: "); if(p>=1&&p<=catalogo.size()&&q>0){g.registrarDemanda(catalogo.get(p-1),q);System.out.println("[OK] Demanda registrada.");}else System.out.println("Dados invalidos."); }
        else if(op==2){ int id=lerInt("ID da demanda: "); int q=lerInt("Nova quantidade: "); System.out.println(g.atualizarDemanda(id,q)?"[OK] Atualizada.":"[NOK] Demanda nao encontrada."); }
        else if(op==3){ if(g.getDemandas().isEmpty())System.out.println("Nenhuma demanda."); for(Demanda d:g.getDemandas())System.out.println(d.getDemanda()); }
    }
    private static void menuEstrategia(GerenciadorProducao g){
        System.out.println("1 - Ordem de Chegada\n2 - Maior Demanda\n3 - Maximo de Produtos"); int op=lerInt("Escolha: ");
        switch(op){ case 1 -> g.setEstrategia(new EstrategiaOrdemChegada()); case 2 -> g.setEstrategia(new EstrategiaMaiorDemanda()); case 3 -> g.setEstrategia(new EstrategiaMaximoProdutos()); default -> System.out.println("Opcao invalida."); }
    }
    private static int lerInt(String msg){ while(true){ System.out.print(msg); if(teclado.hasNextInt())return teclado.nextInt(); teclado.next(); System.out.println("Digite apenas numeros inteiros."); } }
    private static double lerDouble(String msg){ while(true){ System.out.print(msg); if(teclado.hasNextDouble())return teclado.nextDouble(); teclado.next(); System.out.println("Digite apenas numeros."); } }
}
