public class Produto {
    private String id;
    private String nome;
    private String status;
    private double quantidadeMateriaPrimaNecessaria;

    public Produto(String id, String nome, double quantidadeMateriaPrimaNecessaria) {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
    }

    public void processar() {
        this.status = "Processado";
    }

    public void definirDemandaMateriaPrima(double quantidadeMateriaPrimaNecessaria) {
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
    }

    public double getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
}
