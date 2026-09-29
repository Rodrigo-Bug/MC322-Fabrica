package outros;

public enum Cenario {
    IDEAL("Ideal", 10000.0, 0.55, 0.55),
    APOCALIPTICO("Apocaliptico", 1500.0, 2.20, 2.40);

    private final String descricao;
    private final double budgetInicial;
    private final double multiplicadorFalha;
    private final double multiplicadorDesgaste;

    Cenario(String descricao, double budgetInicial, double multiplicadorFalha, double multiplicadorDesgaste) {
        this.descricao = descricao;
        this.budgetInicial = budgetInicial;
        this.multiplicadorFalha = multiplicadorFalha;
        this.multiplicadorDesgaste = multiplicadorDesgaste;
    }

    public String getDescricao() { return descricao; }
    public double getBudgetInicial() { return budgetInicial; }
    public double getMultiplicadorFalha() { return multiplicadorFalha; }
    public double getMultiplicadorDesgaste() { return multiplicadorDesgaste; }

    public static Cenario escolha(int opcao) {
        return opcao == 2 ? APOCALIPTICO : IDEAL;
    }
}
