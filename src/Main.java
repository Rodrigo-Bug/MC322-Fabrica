import java.util.Scanner;
import java.util.ArrayList;

import PackInterfaces.*;
import PackMaquinas.*;
import PackMateriaPrima.*;
import PackProdutos.*;
import Estrategias.*;
import Estrategias.Demandas.*;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static final Random RANDOM = new Random();
    public static void main(String[] args) throws Exception
    {
        //declaração de variaveis 
        int op;
        //declaracao de objetos
        Scanner teclado = new Scanner(System.in);
        GerenciadorProducao CEO;

        //declaração de materia prima
        MateriaPrima Aluminio = new MateriaPrima("AL2618", "Alumínio", 100.0, "kg", 10.0);

        //declaração de produtos

        //declaração de maquina
        Map<String, Maquina> maquinas = new HashMap<>();
        Maquina torno = new MaquinaUsinagem( "Torno", 50, 10); 

        //declaração de esteira (nome provisorio)
        Esteira esteira1 = new Esteira(50);
        Esteira esteira2 = new Esteira(50);

        //declaração de estação de inspeção (nome provisorio)
        EstacaoInspecao estacao1 = new EstacaoInspecao();

    do
    {

        //mensagem de inicialização
        System.out.print("  Escolha o cenário de simulação:" + "\n"
                         + "1 CENÁRIO IDEAL" + "\n"
                         + "2 CENÁRIO CAÓTICO" + "\n"
                         + "0 PORQUE ESCOLHER UM CENÁRIO?");
        CEO = Cenario.escolha(teclado.nextInt());

        Terminal.generateMenuTitle("RL SOLUCOES", true, "\"Movendo o futuro, peça por peça\"");

        System.out.println("Bem-vindos à nossa fábrica de motores!\nAqui transformamos tecnologia e precisão "
                        + "\nem componentes que garantem segurança, \ndesempenho e durabilidade.\n\n");

        Terminal.generateMenuTitle("MENU PRINCIPAL", false, "");
        
        Terminal.generateInfo(false, null, new ArrayList<String>() {{
            add("ESTRATEGIA ATUAL:  " + CEO.getEstrategia().getNomeEstrategia());
            add("BUDGET:  " + CEO.getBudget());
        }});

        Terminal.generateMenu("Selecione o módulo que deseja acessar:", 1, new ArrayList<String>() {{
            add("Modulo Comercial");
            add("Modulo Fabrica");
            add("Modulo Estoque");
            add("Modulo Compras");
            add("Configuracoes");
        }});
        
        op = teclado.nextInt();

menu:
        switch(op)
        {
        case 1:
            do
            {
                Terminal.generateMenuTitle("COMERCIAL", false, "");
                Terminal.generateMenu("Selecione a acao desejada:", 1, new ArrayList<String>() {{
                    add("Atualizar Demandas");
                    add("Consultar historico de demandas");
                }});
                op = teclado.nextInt();

                switch(op)
                {
                    case 1:
                        do
                        {
                            Terminal.generateMenu("Atualizar Demandas", 1, new ArrayList<String>() {{
                                add("Demanda de cabecote");
                                add("Demanda de virabrequim");
                                add("Demanda de pistao");
                                add("Demanda de bloco");
                                add("Demanda de motor completo");
                            }});
                            op = teclado.nextInt();
                            System.out.print("Informe a quantidade de demanda: ");
                            int qnt = teclado.nextInt();

                            switch(op)
                            {
                                case 1:
                                    CEO.registrarDemanda("Cabecote", qnt);
                                    System.out.printf("\n[OK] Demanda de " + qnt
                                    + " cabecotes registrada com sucesso.");
                                    break;
                                case 2:
                                    CEO.registrarDemanda("Virabrequim", qnt);
                                    System.out.printf("\n[OK] Demanda de " + qnt
                                    + " virabrequins registrada com sucesso.");
                                    break;
                                case 3:
                                    CEO.registrarDemanda("Pistao", qnt);
                                    System.out.printf("\n[OK] Demanda de " + qnt
                                    + " pistoes registrada com sucesso.");
                                    break;
                                case 4:
                                    CEO.registrarDemanda("Bloco", qnt);
                                    System.out.printf("\n[OK] Demanda de " + qnt
                                    + " blocos registrada com sucesso.");
                                    break;
                                case 5:
                                    CEO.registrarDemanda("Motor Completo", qnt);
                                    System.out.printf("\n[OK] Demanda de " + qnt
                                    + " motores completos registrada com sucesso.");
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.print("[ERROR] Falha no registro da demanda" + "\n"
                                    +  "Por favor tente novamente" + "\n");
                                    break;
                            }
                        } while(op != 0);
                        break;
                    case 2:
                        Terminal.generateInfo(true, "Gerando relatorio. . .", new ArrayList<String>() {{
                            add("Relatorio de demandas registradas:");
                            for (Demanda demanda : CEO.getDemandas()) {
                                add(demanda.getDemanda());
                            }
                        }});
                        break;
                    case 0:
                        break;
                    default:
                        System.out.print("Opcao invalida, voltando ao menu principal.");
                        break menu;
                }
            } while(op != 0);
            break;
            
        case 2:
            do
            {
                Terminal.generateMenuTitle("FABRICA", false, "");
                Terminal.generateInfo(true, "Condicao de materia-prima", new ArrayList<String>() {{
                    add("Quantidade de " + Aluminio.getNome() + " disponivel: " + Aluminio.getQuantidade() + " " + 
                    Aluminio.getUnidade());
                }});
                Terminal.generateMenu("Selecione o produto desejado:", 1, new ArrayList<String>() {{
                    add("Fabricar cabecote");
                    add("Fabricar virabrequim");
                    add("Fabricar pistao");
                    add("Fabricar bloco");
                    add("Fabricar demanda");
                }});
                op = teclado.nextInt();

                switch(op)
                {
                    case 1:
                        System.out.println("[OK] Cabecote selecionado para producao. . .");
                        System.out.println("Iniciar producao de imediato? (1 - SIM / 2 - NAO)");
                        op = teclado.nextInt();
                        if (op == 1)
                        {
                            CEO.registrarDemanda("Cabecote", 1);
                            CEO.setEstrategia(new EstrategiaUrgente());
                            System.out.println("[OK] Producao urgente iniciada");
                            CEO.executarProximaProducao();
                            CEO.resetEstrategia();
                        }
                        break;
                    case 2:
                        //codeblock
                        break;
                    case 0:
                        break;
                    default:
                        System.out.print("Opcao invalida, voltando ao menu principal.");
                        break menu;
                }
            } while(op != 0);
            break;
        case 3:
            do
            {
                Terminal.generateMenuTitle("ESTOQUE", false, "");
                Terminal.generateMenu("Selecione a acao desejada:", 1, new ArrayList<String>() {{
                    add("Atualizar Demandas");
                    add("Visualizar Relatorios");
                }});
                op = teclado.nextInt();

                switch(op)
                {
                    case 1:
                        //codeblock
                        break;
                    case 2:
                        //codeblock
                        break;
                    case 0:
                        break;
                    default:
                        System.out.print("Opcao invalida, voltando ao menu principal.");
                        break menu;
                }
            } while(op != 0);
            break;
        case 4:
            do
            {
                Terminal.generateMenuTitle("COMPRAS", false, "");
                Terminal.generateMenu("Selecione a acao desejada:", 1, new ArrayList<String>() {{
                    add("Atualizar Demandas");
                    add("Visualizar Relatorios");
                }});
                op = teclado.nextInt();

                switch(op)
                {
                    case 1:
                        do
                        {
                            Terminal.generateMenuTitle("FABRICA", false, "");
                            Terminal.generateMenu("Selecione a acao desejada:", 1, new ArrayList<String>() {{
                                add("Atualizar Demandas");
                                add("Visualizar Relatorios");
                            }});
                            op = teclado.nextInt();

                            switch(op)
                            {
                                case 1:
                                    //codeblock
                                    break;
                                case 2:
                                    //codeblock
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.print("Opcao invalida, voltando ao menu principal.");
                                    break menu;
                            }
                        } while(op != 0);
                        break;
                    case 2:
                        //codeblock
                        break;
                    case 0:
                        break;
                    default:
                        System.out.print("Opcao invalida, voltando ao menu principal.");
                        break menu;
                }
            } while(op != 0);
            break;
        case 5:
            do
            {
                Terminal.generateMenuTitle("Configuracoes", false, "");
                Terminal.generateMenu("Selecione a acao desejada:", 1, new ArrayList<String>() {{
                    add("Atualizar Demandas");
                    add("Visualizar Relatorios");
                }});
                op = teclado.nextInt();

                switch(op)
                {
                    case 1:
                        //codeblock
                        break;
                    case 2:
                        //codeblock
                        break;
                    case 0:
                        break;
                    default:
                        System.out.print("Opcao invalida, voltando ao menu principal.");
                        break menu;
                }
            } while(op != 0);
            break;
        case 0:
            break;
        default:
            System.out.print("Opcao invalida, voltando ao menu principal. . .");
            break;
        }

        if (op == 2){
            System.out.printf("\n[OK] A quantitade de %s - %s é de: %.2f %s\n",Aluminio.getId(), Aluminio.getNome(), Aluminio.getQuantidade(), Aluminio.getUnidade());
        }else if (op == 1){
            System.out.print("Selecione o produto (1-10): ");
            escolherProduto=teclado.nextInt()-1;
            System.out.print("Informe a demanda de materia prima: ");
            demanda=teclado.nextInt();
            
            if (Aluminio.verificarDisponibilidade(demanda)) {
                if (!esteira1.estaLigada()) {
                    esteira1.ligar();
                }
                if (!torno.estaLigada()) {
                    torno.ligar();
                }

                if(esteira1.adicionarItem(Aluminio, demanda)){

                    if(esteira1.transportarMaquina(torno)){
                        qntProdutos = torno.processar((MateriaPrima)esteira1.removerItem(),produtos[escolherProduto], demanda);
                        if(qntProdutos>0){
                            if(!esteira2.estaLigada()) {
                                esteira2.ligar();
                            }
                            esteira2.adicionarItem(produtos[escolherProduto], demanda);
                            if (!estacao1.estaLigada()) {
                                estacao1.ativar();
                            }
                            if(esteira2.transportarInspecao()){
                                esteira2.removerItem();
                                estacao1.inspecionar(produtos[escolherProduto], qntProdutos);
                                System.out.println("\n\n=============================================\n        PRODUÇÃO CONCLUIDA COM SUCESSO       \n=============================================");
                                System.out.printf("\nEstoque restante de %s - %s: %.2f %s\n", Aluminio.getId(), Aluminio.getNome(), Aluminio.getQuantidade(), Aluminio.getUnidade());
                            }
                        }
                    }
                }
            } 
        }



    } while (op != 0);
    teclado.close();
    System.out.printf("Saindo...");
    }
}
