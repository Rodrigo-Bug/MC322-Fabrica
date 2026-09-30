package outros;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Estrategias.*;
import Estrategias.Demandas.*;
import PackInterfaces.Auditavel;
import PackMaquinas.Maquina;
import PackMateriaPrima.MateriaPrima;
import PackProdutos.*;

public class GerenciadorProducao {
    private final ArrayList<Demanda> demandas = new ArrayList<>();
    private final ArrayList<Produto> produtosFabricados = new ArrayList<>();
    private final ArrayList<Maquina> maquinas = new ArrayList<>();
    private EstrategiaProducao estrategiaAtual = new EstrategiaOrdemChegada();
    private EstrategiaProducao estrategiaAnterior = estrategiaAtual;
    private final MateriaPrima materiaPrima;
    private double budget;
    private Cenario cenario;
    private int idDemanda = 0;
    private int proximoLote = 1;
    private int proximoProduto = 1;
    private static final Scanner teclado = new Scanner(System.in);

    public GerenciadorProducao(Cenario cenario, MateriaPrima materiaPrima) {
        this.cenario=cenario; this.materiaPrima=materiaPrima; this.budget=cenario.getBudgetInicial();
    }


    public void adicionarMaquina(Maquina m){ if(m!=null){ m.configurarCenario(cenario); maquinas.add(m); } }


    public void setEstrategia(EstrategiaProducao nova){ if(nova!=null){ estrategiaAnterior=estrategiaAtual; estrategiaAtual=nova; } }
    public void resetEstrategia(){ estrategiaAtual=estrategiaAnterior; }
    public EstrategiaProducao getEstrategia(){ return estrategiaAtual; }


    public Cenario getCenario(){ return cenario; }
    public  void setCenario(Cenario cenario){this.cenario=cenario;}


    public double getBudget(){ return budget; }
    public List<Demanda> getDemandas(){ return demandas; }
    public List<Produto> getProdutosFabricados(){ return produtosFabricados; }


    public void registrarDemanda(Produto produto,int quantidade){ if(produto!=null && quantidade>0) demandas.add(new Demanda(++idDemanda,produto,quantidade)); }
    public boolean atualizarDemanda(int id,int quantidade){ for(Demanda d:demandas) if(d.getId()==id){ d.atualizarQuantidade(quantidade); return true; } return false; }


    public void executarProximaProducao(){
        Demanda demanda=estrategiaAtual.selecionarDemanda(demandas,budget);
        if(demanda==null){ System.out.println("[INFO] Nenhuma demanda elegivel."); return; }
        fabricarDemanda(demanda);
    }


    public void fabricarDemanda(Demanda demanda){
        if(demanda==null || demanda.getStatus()!=StatusDemanda.PENDENTE) return;

        int quantidade=demanda.getQuantidadeRestante();
        double materiaNecessaria=demanda.calcularMateriaPrimaNecessaria();
        double custo=calcularCustoProducao(demanda,quantidade);
        
        if(!materiaPrima.verificarDisponibilidade(materiaNecessaria)){

            int op;
             System.out.printf("\n[NOK] Materia-prima insuficiente. Dejesa compara %.2fKg de Aluminio para realizar a demanda ?\n", materiaNecessaria-materiaPrima.getQuantidade()); 
             do {
                System.out.printf("1 - Comprar %.2fKg de Aluminio\n", materiaNecessaria-materiaPrima.getQuantidade());
                System.out.println("2 - Não produzir a demanda e voltar ao menu");
                System.out.println("3 - Cancelar demanda");
                op=lerInt("Escolha: ");
                switch(op){
                    case 1 -> {System.out.println(comprarMateriaPrima(materiaNecessaria-materiaPrima.getQuantidade())?"[OK] Compra realizada.":"[NOK] Compra invalida ou budget insuficiente."); break;}
                    case 2 -> {System.out.println("[OK] Voltando para o menu");return;}
                    case 3 -> {System.out.println("[OK] Demanda Cancelada");demanda.cancelar();return; }
                    default -> System.out.println("Opcao invalida.");
                }
            }while (op!=1);
        }

        if(custo>budget){ System.out.println("[NOK] Budget insuficiente. Demanda cancelada."); demanda.cancelar(); return; }
        demanda.iniciar();
        int lote=proximoLote++;
        int concluidos=0;
        for(int i=0;i<quantidade;i++){
            Produto unidade=demanda.getProduto().criarNovaUnidade("P"+(proximoProduto++),lote);
            if(!materiaPrima.consumir(unidade.getDemandaMateriaPrima())) break;
            boolean sucesso=true;
            for(Maquina m:unidade.getProcessoProducao()){
                if(budget<m.getCustoOperacao()){ sucesso=false; break; }
                m.ligar(); budget-=m.getCustoOperacao();
                int feitos=m.processar(unidade,materiaPrima,1); m.desligar();
                if(feitos<1){ sucesso=false; break; }
            }
            if(sucesso){ unidade.processar(StatusProduto.FINALIZADO); produtosFabricados.add(unidade); concluidos++; }
        }
        demanda.registrarProduzidos(concluidos);
        if(demanda.getStatus()!=StatusDemanda.CONCLUIDA){ demanda.cancelar(); System.out.println("[NOK] Producao interrompida; demanda cancelada."); }
        else System.out.println("[OK] Demanda "+demanda.getId()+" concluida: "+concluidos+" unidade(s).");
    }

    public boolean comprarMateriaPrima(double q){ 
        if(q<=0)return false; 
        double custo=q*materiaPrima.getCusto(); 
        if(custo>budget)return false; 
        budget-=custo; 
        materiaPrima.adicionarEstoque(q); 
        return true; 
    }


    public void exibirBudget(){ System.out.printf("Budget atual: R$ %.2f%n",budget); }


    public void exibirEstoque(){ System.out.printf("%s: %.2f %s | custo/unidade: R$ %.2f%n",materiaPrima.getNome(),materiaPrima.getQuantidade(),materiaPrima.getUnidade(),materiaPrima.getCusto()); }
    public void exibirArmazem(){
        if(produtosFabricados.isEmpty()){ System.out.println("Armazem vazio."); return; }
        for(Produto p:produtosFabricados) System.out.printf("%s | %s | lote %d | qualidade %.2f | risco %s%n",p.getId(),p.getNome(),p.getLote(),p.getQualidade(),p.precisaManutencao()?"ATENCAO":"NORMAL");
        System.out.println("Quantidade total: "+produtosFabricados.size());
    }

    public void gerarAuditoriaGeral(){
        ArrayList<Auditavel> itens=new ArrayList<>(); itens.addAll(maquinas); itens.addAll(produtosFabricados);
        if(itens.isEmpty()){ System.out.println("Nenhum item para auditar."); return; }
        for(Auditavel a:itens) System.out.println(a.gerarRelatorioDiagnostico());
    }


    private double calcularCustoProducao(Demanda d,int q){ double custo=0; for(Maquina m:d.getProduto().getProcessoProducao()) custo+=m.getCustoOperacao(); return custo*q; }

    private static int lerInt(String msg){
         while(true){ 
            System.out.print(msg); 
            if(teclado.hasNextInt())return teclado.nextInt();
            teclado.next(); 
            System.out.println("Digite apenas numeros inteiros."); 
       }
    }
}