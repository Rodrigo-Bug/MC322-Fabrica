package PackMaquinas;

import MateriaPrima.*;
import PackProdutos.*;



public class MaquinaTratamentoSuperficial extends Maquina{

    public MaquinaTratamentoSuperficial(String nome, int capacidadeMaxima, double custoOperacao) {
        this.nome = nome;
        this.health = 100;
        this.ligada = false;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha=0.01;
        this.custoOperacao=custoOperacao;
    }

    @Override
    public int processar(Produto produto, MateriaPrima materiaPrima, int demanda ){
        int falhas=0;
        int qnt=0;
        System.out.printf("\n[OK] Realizando tratamento superficial em %s", produto.getNome());
        
        for (int i=0; i<demanda; i++){
            qnt++;
            if(verificarFalha()){
                produto.upProbabilidadeFalha();
                falhas++;
            }
            if(dano()){
                System.out.printf("\n[NOK] Não foi possível tratar superficialmente %d %s(s)",demanda-qnt, produto.getNome());
                System.out.printf("\n[OK] Foram tratados superficialmente %d %s(s)",qnt, produto.getNome());
                System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());
                return qnt;
            }

        }
        System.out.printf("\n[OK] Foram tratados superficialmente %d %s(s)",qnt, produto.getNome());
        System.out.printf("\n[INFO] %d falha(s) ocorreram durante o processamento de %s.", falhas, produto.getNome());

        return qnt;
    }

    @Override
    public String getTipo(){
        return "Maquina de Tratamento Superficial";
    }    

}

