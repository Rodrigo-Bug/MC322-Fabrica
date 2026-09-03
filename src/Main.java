import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //declaração de variaveis 
        int op =0;
        int escolherProduto =0;
        int qntProdutos;
        double demanda;
        Scanner teclado = new Scanner(System.in);


        //declaração de materia prima
        MateriaPrima Aluminio = new MateriaPrima("AL2618", "Alumínio", 100.0, "kg", 10.0);


        //declaração de produto
        Produto Pistao = new Produto("PI0001", "Pistão", 5);
        Produto BlocoMotor = new Produto("BM0001", "Bloco do Motor", 30);
        Produto Blococabecote  = new Produto("BC0001", "Bloco do Cabeçote", 15);
        Produto[] produtos = { Pistao, BlocoMotor, Blococabecote };


        //declaração de maquina
        Maquina torno = new Maquina("Torno", 50);


        //declaração de esteira (nome provisorio)
        Esteira esteira1 = new Esteira(50);
        Esteira esteira2 = new Esteira(50);

        //declaração de estação de inspeção (nome provisorio)
        EstacaoInspecao estacao1 = new EstacaoInspecao();




        //mensagem de inicialização

        System.out.println("=============================================\n                 RL AutoParts                \n       \"Movendo o futuro, peça por peça\"       \n=============================================\n");

        System.out.println("Bem-vindos à nossa fábrica de peças automotivas!\nAqui transformamos tecnologia e precisão \nem componentes que garantem segurança, \ndesempenho e durabilidade para o seu veículo.\n");

        
        System.out.println("=============================================\n              PLANTA INDUSTRIAL              \n=============================================");
        System.out.println("\nMatéria-Prima: "+ Aluminio.getId() + " - " + Aluminio.getNome());
        System.out.println("Quantidade: " + Aluminio.getQuantidade() + " " + Aluminio.getUnidade());
        System.out.println("Unidade: " + Aluminio.getUnidade());
        System.out.println("\nProdutos Disponíveis: ");
        for(int i = 0; i < produtos.length; i++) {
            System.out.println( (i + 1) + " - " + produtos[i].getNome() + " (Demanda: " + produtos[i].getDemandaMateriaPrima() +" " + Aluminio. getUnidade() + ")");
        }




    do {
    

        System.out.println("\n=============================================\n                MENU PRINCIPAL               \n=============================================\n");
        System.out.print("1 - Iniciar produção\n2 - Consultar estoque\n3 - Sair\n\nEscolha: ");
        op = teclado.nextInt();
        
        
        if (op == 2){
            System.out.printf("\n[OK] A quantitade de %s - %s é de: %.2f %s\n",Aluminio.getId(), Aluminio.getNome(), Aluminio.getQuantidade(), Aluminio.getUnidade());
        }else if (op == 1){
            System.out.print("Selecione o produto (1-3): ");
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



    } while (op != 3);
    teclado.close();
    System.out.printf("Saindo...");
    esteira1.desligar();
    esteira2.desligar();
    torno.desligar();
    estacao1.desligar();
    }
}
