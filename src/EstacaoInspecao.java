public class EstacaoInspecao {
    private boolean ligada;
    private int produtosInspecionados;


public EstacaoInspecao() {
        this.ligada = false;
        this.produtosInspecionados = 0;
    }

    public void ativar() {
        System.out.printf("\n[OK] Estação de Inspeção ligada.");
        this.ligada = true;
    }

    public void desligar() {
        System.out.printf("\n[OK] Estação de Inspeção desligada.");
        this.ligada = false;
    }

    public boolean estaLigada(){
        return ligada;
    }

    public void inspecionar(Produto produto, int qnt) {
        if (ligada) {
            System.out.printf("\n[OK] Inspecionado(s) %d produtos %s - %s",qnt, produto.getId(), produto.getNome());
            produtosInspecionados+= qnt;
        }
    }

    public int getTotalInspecionados() {
        return produtosInspecionados;
    }
}
