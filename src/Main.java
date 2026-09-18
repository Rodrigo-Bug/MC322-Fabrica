import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //declaração de variaveis 
        int op =0;
        int escolherProduto =0;
        int qntProdutos;
        double demanda;
        Scanner teclado = new Scanner(System.in);
        Menu menu = new Menu("=");


        //declaração de materia prima
        MateriaPrima Aluminio = new MateriaPrima("AL2618", "Alumínio", 100.0, "kg", 10.0);


        //declaração de produtos
        Produto Cabecote3200A = new Cabecote("SJ3201A", "Cabecote 3200 Tipo A", 10, 0.8);
        //SJ - Aplicacao (Uniao rotativa), 320 - Tamanho da uniao, 1A - Tipo de peca (Cabecote tipo A)
        Produto Cabecote4550A = new Cabecote("SJ4551A", "Cabecote 4550 Tipo A", 13, 0.8);
        Produto Cabecote3200B = new Cabecote("SJ3201B", "Cabecote 3200 Tipo B", 15, 0.8);
        Produto Cabecote4550B = new Cabecote("SJ4551B", "Cabecote 4550 Tipo B", 18, 0.8);
        Produto Corpo3200 = new Corpo("SJ3202", "Corpo 3200", 30, 0.3);
        Produto Corpo4550 = new Corpo("SJ4552", "Corpo 4550", 40, 0.3);
        Produto TuboConector3200 = new TuboConector("SJ3203", "Tubo Conector 3200", 10, 0.6);
        Produto TuboConector3200R = new TuboConector("SJ3203R", "Tubo Conector 3200 revestido", 10, 0.8);
        Produto TuboConector4550 = new TuboConector("SJ4553", "Tubo Conector 4550", 15, 0.6);
        Produto TuboConector4550R = new TuboConector("SJ4553R", "Tubo Conector 4550 revestido", 15, 0.8);
        Produto[] produtos = { Cabecote3200A, Cabecote4550A, Cabecote3200B, Cabecote4550B, Corpo3200, Corpo4550,
                            TuboConector3200, TuboConector3200R, TuboConector4550, TuboConector4550R };

        //declaração de maquina
        Maquina torno = new Maquina("Torno", 50);


        //declaração de esteira (nome provisorio)
        Esteira esteira1 = new Esteira(50);
        Esteira esteira2 = new Esteira(50);

        //declaração de estação de inspeção (nome provisorio)
        EstacaoInspecao estacao1 = new EstacaoInspecao();

    do {

        //mensagem de inicialização

        menu.GenerateMenuTitle("RL SOLUCOES", true, "\"Movendo o futuro, peça por peça\"");

        System.out.println("Bem-vindos à nossa fábrica de unioes rotativas!\nAqui transformamos tecnologia e precisão "
                        + "\nem componentes que garantem segurança, \ndesempenho e durabilidade.\n\n");

        menu.GenerateMenuTitle("MENU PRINCIPAL", false, "");
        System.out.print("\n  BUDGET ATUAL:  " + GerenciadorProducao.getBudget() + "\n\n\n");
        System.out.print("  ATUALIZAR DEMANDAS\n\n1 - Atualizar demanda de cabecote\n2 - Atualizar demanda de corpo\n"
                        + "3 - Atualizar demanda de tubo conector\n\n\n");
        System.out.print("  INICIAR PRODUCAO\n\n4 - Fabricar cabecote\n5 - Fabricar corpo\n6 - Fabricar tubo conector"
                        + "\n\n\n");
        System.out.print("  CONSULTAS\n\n7 - Consultar estoque\n8 - Consultar materia-prima\n\n\n");
        System.out.print("  COMPRAS\n\n9 - Comprar materia-prima\n\n\n\n0 - SAIR\n\n\n");
        op = teclado.nextInt();
        
menu:   switch(op)
        {
            case 1:
                //codeblock
                break;
            case 2:
                //codeblock
                break;
            case 3:
                //codeblock
                break;
            case 4:
                System.out.print("Selecione o tipo de cabecote desejado (1-3): ");
                escolherProduto=teclado.nextInt()-1;
                break;
            case 5:
                //codeblock
                break;
            case 6:
                //codeblock
                break;
            case 7:
                //codeblock
                break;
            case 8:
                //codeblock
                break;
            case 9:
                //codeblock
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
    esteira1.desligar();
    esteira2.desligar();
    torno.desligar();
    estacao1.desligar();
    }
}
