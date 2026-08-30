import java.util.Scanner;

public class Esteira {
    private Object item;
    private boolean emMovimento;
    private double capacidadeMaxima;

    public Esteira(double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.item = null;
        this.emMovimento = false;
    }

    public void ligar() {
        this.emMovimento = true;
        System.out.printf("\n[OK] Esteira ligada.");
    }

    public boolean estaLigada(){
        return emMovimento;
    }

    public void desligar() {
        this.emMovimento = false;
        System.out.printf("\n[OK] Esteira desligada.");
    }

    public boolean adicionarItem(Object novoItem, double peso) {
        if(this.item == null){
            if(verificarCapacidade(peso)){
                this.item = novoItem;
                System.out.printf("\n[OK] %s - %s colocado na esteira.", getId(novoItem), getNome(novoItem ));  
                return true;   
            }else{
                System.out.printf("\n[NOK] Peso/volume é maior que a capacidade maxima da esteira.\nVoltando ao menu Principal");    
                return false;
            }
        }else {
            System.out.printf("\n[NOK] %s - %s não pode ser colocado na esteira pois ela esta ocupada.\nVoltando ao menu Principal.", getId(novoItem), getNome(novoItem));    
            return false;
        }
    }

    public Object removerItem() {
        if (this.item == null) {
            System.out.printf("\n[NOK] não ha nenhum item para ser removido.");    
        return null;
        }else {
            System.out.printf("\n[OK] %s - %s foi removido da esteira." ,getId(this.item), getNome(this.item));    
            Object item1;
            item1 = this.item;
            this.item=null;
            return item1; 
        }     
    }

    public boolean verificarCapacidade(double peso) {
        return peso<=capacidadeMaxima;
    }


    public boolean transportarMaquina(Maquina local){
        if (emMovimento) {
            System.out.printf("\n[OK] %s - %s movido até a %s.", getId(this.item), getNome(this.item), local.getNome());
            return true;
        }else  {
            System.out.printf("\n[NOK] Esteira esta desligada, não pode transportar itens.\nDeseja ligar?\n1 - Ligar\n2 - Sair\nEscolha:  ");
            Scanner teclado = new Scanner(System.in);
            int escolha;
            escolha=teclado.nextInt();
            teclado.close();
            if(escolha==1){
                ligar();
                transportarMaquina(local);
                return true;
            }else{
                System.out.printf("\nVoltando ao menu principal.");
                return false;
            }
        }
    }


    public boolean transportarInspecao(){
        if (emMovimento) {
            System.out.printf("\n[OK] %s - %s movido até a estação de inspeção.", getId(this.item), getNome(this.item));
            return true;
        }else  {
            System.out.printf("\n[NOK] Esteira esta desligada, não pode transportar itens.\nDeseja ligar?\n1 - Ligar\n2 - Sair\nEscolha:  ");
            Scanner teclado = new Scanner(System.in);
            int escolha;
            escolha=teclado.nextInt();
            teclado.close();
            if(escolha==1){
                ligar();
                transportarInspecao();
                return true;
            }else{
                System.out.printf("\nVoltando ao menu principal.");
                return false;
            }
        }
    }



    private String getId(Object item) {

    if (item instanceof MateriaPrima) {
        return ((MateriaPrima) item).getId();
    }

    if (item instanceof Produto) {
        return ((Produto) item).getId();
    }

    return null;
}

    private String getNome(Object item) {

    if (item instanceof MateriaPrima) {
        return ((MateriaPrima) item).getNome();
    }

    if (item instanceof Produto) {
        return ((Produto) item).getNome();
    }

    return null;
}
}
