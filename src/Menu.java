public class Menu
{
    private String adorno = "================================================================================\n";
    private int nOfSpaces;
    
    public void GenerateMenuTitle(String title)
    {
        System.out.println(adorno);
        nOfSpaces = Math.floor((80 - title.length())/2)
        for(int i = 0;i<nOfSpaces;i++)
        {
            System.out.print(" ");
        }
        System.out.print(title + "\n\n");
        System.out.print(adorno);
    }
}
