
import java.util.ArrayList;

public class Terminal
{
    private static int nOfSpaces;

    public static void generateMenuTitle(String title, Boolean useHeader, String header)
    {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        marginHandler("", 1);
        marginHandler(title, 1);
        if (useHeader) {marginHandler(header, 1);}
        marginHandler("", 0);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void generateInfo(Boolean useTitle, String title, ArrayList<String> info)
    {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        marginHandler("", 1);
        if (useTitle) {marginHandler(title, 1);}
        marginHandler("", 0);
        for(int i = 0;i < info.size();i++)
        {
            marginHandler(info.get(i), 0);
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }
    
    public static void generateMenu(String subheader, int start, ArrayList<String> options)
    {
        System.out.println("╓──────────────────────────────────────────────────────────────────────────────╖");
        marginHandler("  " + subheader,0);
        for(int i = start;i <= (options.size() + start - 1);i++)
        {
            marginHandler(i + " - " + options.get(i - start), 0);
        }
        marginHandler("", 0);
        marginHandler("  0 - SAIR", 0);
        marginHandler("", 0);
        marginHandler("", 0);
        System.out.println("╙──────────────────────────────────────────────────────────────────────────────╜");
    }

    private static void marginHandler(String text, int align)
    {
        if(text.length() > 74)
        {
            System.out.print("║  " + text.substring(0, 74) + "  ║\n");
            marginHandler(text.substring(74), align);
            return;
        }
        switch (align)
        {
        case 1:
            System.out.print("║");
            nOfSpaces = (int) Math.floor((78 - text.length())/2);
            for(int i = 0;i<nOfSpaces;i++) {System.out.print(" ");}
            System.out.print(text);
            nOfSpaces = (int) Math.ceil((78 - text.length())/2);
            for(int i = 0;i<nOfSpaces;i++) {System.out.print(" ");}
            System.out.print("║\n");
            break;
        case 2:
            System.out.print("║");
            nOfSpaces = (int) Math.ceil((76 - text.length()));
            for(int i = 0;i<nOfSpaces;i++) {System.out.print(" ");}
            System.out.print(text);
            System.out.print("  ║\n");
            break;
        default:
            System.out.print("║  ");
            System.out.print(text);
            nOfSpaces = (int) Math.ceil((76 - text.length()));
            for(int i = 0;i<nOfSpaces;i++) {System.out.print(" ");}
            System.out.print("║\n");
            break;
        }
    }
}
