package Maquinas;

import Interfaces.*;
import Main.Main;
import MateriaPrima.*;
import Produtos.*;

abstract class Maquina implements Auditavel, Manutencao
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


    //Abstract
    public abstract int processar(Produto produto, MateriaPrima materiaPrima, int demanda);

    public abstract String getTipo();

    //Concrete
    public void ligar() {
        this.ligada = true;
        System.out.printf("\n[OK] %s ligado(a).", this.nome);
    }

    public void desligar() {
        this.ligada = false;
        System.out.printf("\n[OK] %s desligado(a).",this.nome);
    }

    public boolean estaLigada() {
        return this.ligada;
    }

    public String getNome() {
        return this.nome;
    }

    public double getCustoOperacao() {
        return this.custoOperacao;
    }

   
    protected boolean verificarFalha()
    {
        if(Main.RANDOM.nextDouble() < this.probabilidadeFalha){
            return true;
        }else{
            return false;
        }
    }
    //retorna true caso a maquina quebre
    protected boolean dano() {
        this.probabilidadeFalha+=0.005;
        if (this.health>1) {
            this.health--;
            return false;
        }else{
            this.health--;
            System.out.printf("\n[NOK] %s está quebrada", getNome());
            return true;
        }
    }

    //Interfaces

    @Override
    public String gerarRelatorioDiagnostico(){
        return String.valueOf(this.health);
    }

    @Override
    public boolean precisaDeManutencao(){
        if(this.health<20){
            return true;
        }else{
            return false;   
        }  
    }
}
