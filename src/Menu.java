public class Menu
{
    private String adorno = "================================================================================\n";
    private int nOfSpaces;
    
    public void GenerateMenuTitle(String title, Boolean useHeader, String header)
    {
        System.out.println(adorno);
        nOfSpaces = Math.floor((80 - title.length())/2)
        for(int i = 0;i<nOfSpaces;i++)
        {
            System.out.print(" ");
        }
        System.out.print(title + "\n");
        if useHeader
        {
            nOfSpaces = Math.floor((80 - header.length())/2)
            for(int i = 0;i<nOfSpaces;i++)
            {
                System.out.print(" ");
            }
            System.out.print(header + "\n");
        }
        System.out.print("\n");
        System.out.println(adorno);
        System.out.print("\n");
    }
    
    public void GenerateMenu(String subheader, int start, ArrayList<String> options)
    {
        System.out.println("  " + subheader + "\n");
        for(int i = start;i <= (options.size() + start - 1);i++)
        {
            System.out.println(i + " - " + options.get(i - start));
        }
    }
}
