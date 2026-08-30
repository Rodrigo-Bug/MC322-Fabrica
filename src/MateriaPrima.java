public class MateriaPrima {
    private String  id;
    private String nome;
    private double quantidade;
    private String unidade;
    private double quantidadeMinima;

    public MateriaPrima(String id, String nome, double quantidade, String unidade, double quantidadeMinima) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    public void consumir(double quantidadeDemandada) {
        this.quantidade -= quantidadeDemandada;
    }

    public void adicionarEstoque(double quantidadeAdicional) {
        this.quantidade += quantidadeAdicional;
    }


    
    public boolean verificarDisponibilidade(double quantidadeDemandada) {
        System.out.print("\n[OK] Verificando disponibilidade de Alumínio");
        if(this.quantidade >= quantidadeDemandada){
            System.out.printf("\n[OK] Demanda de %.2f kg pode ser atendida.", quantidadeDemandada);
        }else{
            System.out.printf("\n[NOK] Demanda de %.2f kg não pode ser atendida.\nVoltando ao menu principal", quantidadeDemandada);
        }
        return this.quantidade >= quantidadeDemandada;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }
}

