import java.util.Scanner;

public class Maquina {
    private String nome;
    private boolean ligada;
    //private int capacidadeMaxima;

    public Maquina(String nome, int capacidadeMaxima) {
        this.nome = nome;
        //this.capacidadeMaxima = capacidadeMaxima;
        this.ligada = false;
    }

    public void ligar() {
        this.ligada = true;
        System.out.printf("\n[OK] %s ligado(a).", this.nome);
    }

    public void desligar() {
        this.ligada = false;
        System.out.printf("\n[OK] %s desligado(a).",this.nome);
    }

    public int processar(MateriaPrima materiaPrima, Produto obraPrima,double demandaNecessaria) {
        if (this.ligada) {
            if(demandaNecessaria%obraPrima.getDemandaMateriaPrima()<0.01){
                System.out.printf("\n[OK] %s processando %.2f %s de %s...",this.nome, demandaNecessaria, materiaPrima.getUnidade(), materiaPrima.getNome());
                System.out.printf("\n[OK] Produto %s - %s criado. ", obraPrima.getId(), obraPrima.getNome());
                materiaPrima.consumir(demandaNecessaria);
                return (int)(demandaNecessaria/obraPrima.getDemandaMateriaPrima());
            }else{
                System.out.printf("\n[NOK] Quantidade insuficiente: o valor enviado não atende ao requisito de produção\n[INFO] Envie múltiplos exatos do custo do produto.\nDeseja adicionar %.2f de materia prima faltante para completar o item? (responder não ira concelar a produção pois ainda não foi implementado o estoque da maquina)\n1 - Sim\n2 - Não\nEscolha: \nFUNÇÃO INACABADA",obraPrima.getDemandaMateriaPrima()-(demandaNecessaria%obraPrima.getDemandaMateriaPrima()));
                 Scanner teclado = new Scanner(System.in);
                int escolha = teclado.nextInt();
                teclado.close();
                if(escolha==1){
                     /*if (Aluminio.verificarDisponibilidade(obraPrima.getDemandaMateriaPrima()-(demandaNecessaria%obraPrima.getDemandaMateriaPrima()))) {
                        if(esteira1.adicionarItem(materiaPrima, obraPrima.getDemandaMateriaPrima()-(demandaNecessaria%obraPrima.getDemandaMateriaPrima()))){
                            if(esteira1.transportarMaquina(this)){
                            }
                        }
                    }*/

                    return processar(materiaPrima, obraPrima, demandaNecessaria+demandaNecessaria%obraPrima.getDemandaMateriaPrima());
                    
                }else{
                    System.out.printf("\nVoltando ao menu principal.");
                    return 0;
                }
            }

        }else{
            System.out.printf("\n[NOK] %s esta desligado(a), não pode Processar itens.\nDeseja ligar?\n1 - Ligar\n2 - Sair\nEscolha:  ", this.nome);
            Scanner teclado = new Scanner(System.in);
            int escolha = teclado.nextInt();
            teclado.close();
            if(escolha==1){
                ligar();
                return processar(materiaPrima, obraPrima, demandaNecessaria);
            }else{
                System.out.printf("\nVoltando ao menu principal.");
                return 0;
            }
        }
    }

    public String getNome() {
        return this.nome;
    }

    public boolean estaLigada() {
        return this.ligada;
    }
}
