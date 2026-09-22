package Maquinas;

import MateriaPrima.*;
import Produtos.*;

class MaquinaUsinagem extends Maquina{
    public MaquinaUsinagem(String nome, int capacidadeMaxima, double custoOperacao) {
        this.nome = nome;
        this.health = 100;
        this.ligada = false;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha=0.01;
        this.custoOperacao=custoOperacao;
    }


    //recebe quantidade do produto a ser fabricado, multiplica pela quantidade de material necessario para pruduzir o produto, verifica estoque e produz
    @Override
    public int processar(Produto produto, MateriaPrima materiaPrima, int demanda ){
        int falhas=0;
        int qnt=0;
        System.out.printf("\n[OK] Usinando %s", produto.getNome());
        
        for (int i=0; i<demanda; i++){
            qnt++;
            if(verificarFalha()){
                produto.upProbabilidadeFalha();
                falhas++;
            }
            if(dano()){
                System.out.printf("\n[NOK] Não foi possível usinar %d %s(s)",demanda-qnt, produto.getNome());
                System.out.printf("\n[OK] Foram usinado(s) %d %s(s)",qnt, produto.getNome());
                System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());
                return qnt;
            }

        }
        System.out.printf("\n[OK] Foram usinados %d %s(s)",qnt, produto.getNome());
        System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());

        return qnt;
    }

    @Override
    public String getTipo(){
        return "Maquina de Usinagem";
    }    

}

