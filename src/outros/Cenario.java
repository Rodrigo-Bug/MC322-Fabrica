package outros;
enum Cenario
{
    IDEAL,/*("Cenário ideal, representa uma simulação onde está tudo dando certo, as máquinas estão bem cuidadas e a verba é abundante. Use este cenário para observar o fluxo da fábrica."*/
    CAÓTICO;/*"Cenário caótico, representa uma simulação onde a fábrica está mal cuidada, as máquinas estão sem manutenção e a verba está em falta. Use este cenário para testar a robustez das estratégias implementadas."*/

    public static GerenciadorProducao escolha(int chc)
    {
        switch(chc)
        {
            case 1:
                GerenciadorProducao ideal = new GerenciadorProducao();
                System.out.println("Cenário ideal selecionado.");
                break;
            case 2:
                System.out.println("Cenário caótico selecionado.");
                break;
            default:
                System.out.println("Nenhum cenário selecionado.");
                break;
        }
    }
}