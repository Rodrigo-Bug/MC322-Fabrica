import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int op =0;
        int escolherProduto =0;
        int qntProdutos;
        double demanda;
        Scanner teclado = new Scanner(System.in);
        //public MateriaPrima(int id, String nome, double quantidade, String unidade, double quantidadeMinima)
        //MateriaPrima materiaPrima = new MateriaPrima(1, "Aluminio", 100.0, "kg", 10.0);
        //Produto produto = new Produto(1, "Produto A");

        MateriaPrima Aluminio = new MateriaPrima("AL2618", "Alumínio", 100.0, "kg", 10.0);

        Produto Pistao = new Produto("PI0001", "Pistão", 5);
        Produto BlocoMotor = new Produto("BM0001", "Bloco do Motor", 30);
        Produto Blococabecote  = new Produto("BC0001", "Bloco do Cabeçote", 15);

        Produto[] produtos = { Pistao, BlocoMotor, Blococabecote };

        Maquina torno = new Maquina("Torno", 50);
        Esteira esteira1 = new Esteira(50);
        EstacaoInspecao estacao1 = new EstacaoInspecao();





        System.out.println("=========================================\n            PLANTA INDUSTRIAL            \n=========================================");
        System.out.println("\nMatéria-Prima: "+ Aluminio.getId() + " - " + Aluminio.getNome());
        System.out.println("Quantidade: " + Aluminio.getQuantidade() + " " + Aluminio.getUnidade());
        System.out.println("Unidade: " + Aluminio.getUnidade());
        System.out.println("\nProdutos Disponíveis: ");
        for(int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + " - " + produtos[i].getNome() + " (Demanda: " + produtos[i].getDemandaMateriaPrima() +" " + Aluminio. getUnidade() + ")");
        }




    do {
    

        System.out.println("\n=========================================\n              MENU PRINCIPAL             \n=========================================");
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
                            esteira1.adicionarItem(produtos[escolherProduto], demanda);
                            if (!estacao1.estaLigada()) {
                                estacao1.ativar();
                            }
                            if(esteira1.transportarInspecao()){
                                esteira1.removerItem();
                                estacao1.inspecionar(produtos[escolherProduto], qntProdutos);
                                System.out.println("\n\n=========================================\n      PRODUÇÃO CONCLUIDA COM SUCESSO     \n=========================================");
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
    torno.desligar();
    estacao1.desligar();
    }
}