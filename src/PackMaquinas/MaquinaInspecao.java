package PackMaquinas;

import MateriaPrima.*;
import PackProdutos.*;

 
 
public class MaquinaInspecao extends Maquina{   
   public MaquinaInspecao(String nome, int capacidadeMaxima, double custoOperacao) {
        this.nome = nome;
        this.health = 100;
        this.ligada = false;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha=0.01;
        this.custoOperacao=custoOperacao;
    }

    @Override
    public int processar(Produto produto, MateriaPrima materiaPrima, int demanda){
        int falhas=0;
        System.out.printf("\n[OK] Usinando %s", produto.getNome());
        
        for (int i=0; i<demanda; i++){
            if(verificarFalha()){
                falhas++;
            }
            this.health--;
        }

        System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());

        return 1;
    }

    @Override
    public String getTipo(){
        return "Maquina de Tratamento Superficial";
    }    

}