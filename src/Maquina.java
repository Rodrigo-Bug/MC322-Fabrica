import java.util.Scanner;

abstract class Maquina implements Auditavel
{
    protected String nome;
    protected int health;
    protected boolean ligada;
    protected int capacidadeMaxima;
    protected double probabilidadeFalha;
    protected double custoOperacao;

    /*A saúde (health) de cada máquina deve ser um valor entre 0 e 100. A cada uso (ou seja, a cada ciclo de
fabricação em que a máquina for empregada):
• A saúde da máquina deve sofrer uma pequena redução aleatória (ex: entre 0 e 3 pontos), simulando
o desgaste natural;

• A probabilidade de falha durante a produção deve ser inversamente proporcional à saúde atual (má-
quinas mais saudáveis falham menos);

• Ao atingir saúde 0 (ou um limiar crítico), a máquina deve ser considerada quebrada e não pode mais
operar até ser reparada (reparo pode ser implementado como método extra, se desejado);
• O método precisaManutencao() deve retornar true quando a saúde estiver abaixo de um limiar
configurável (ex: 30).
Livre para adicionar métodos como reparar(), getSaude(), setSaude(), ou atributos como
historicoFalhas, conforme a necessidade do projeto. */
    public Maquina(String nome, int capacidadeMaxima) {
        this.nome = nome;
        //this.capacidadeMaxima = capacidadeMaxima;
        this.ligada = false;
    }

    //Abstract
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

    public String getTipo()
    {
        return "";
    }

    //Concrete
    public void ligar() {
        this.ligada = true;
        System.out.printf("\n[OK] %s ligado(a).", this.nome);
    }

    public void desligar() {
        this.ligada = false;
        System.out.printf("\n[OK] %s desligado(a).",this.nome);
    }

    public String getNome() {
        return this.nome;
    }

    public double getCustoOperacao()
    {
        return this.custoOperacao;
    }

    public boolean estaLigada() {
        return this.ligada;
    }

    public boolean verificarFalha()
    {
        return true;
    }
}
