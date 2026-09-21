enum EstatusDemanda
{
    PENDENTE("Demanda cadastrada, aguardando processamento"),
    EM_PRODUCAO("Demanda processada e em produção"),
    CONCLUIDA("Demanda concluída, produtos prontos para entrega"),
    CANCELADA("Demanda cancelada por falta de orçamento ou matéria prima");

    private String descricao;
    
    private EstatusDemanda(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return descricao;
    }
}