package Estrategias.Demandas;

import PackProdutos.*;

public class Demanda
{
    private int id;
    private Produto tipoProduto;
    private int quantidadeProdutos;
    private EstatusDemanda status;

    public Demanda(int id, Produto tipoProduto, int quantidadeProdutos)
    {
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        this.status = EstatusDemanda.PENDENTE;
    }
    public void atualizarQuantidade(int quantidadeProdutos){
        this.quantidadeProdutos=quantidadeProdutos;
    }

    public String getDemanda()
    {
        return id + "\tProduto: " + tipoProduto + "\tQuantidade: " + quantidadeProdutos + "\tStatus: " + status;
    }

    public void atender(EstatusDemanda demanda)
    {
        this.status = demanda;
    }

    public double calcularMateriaPrimaNecessaria()
    {
        return this.quantidadeProdutos*this.tipoProduto.getDemandaMateriaPrima();
    }

    public Produto getProduto(){
        return tipoProduto;
    }
}
