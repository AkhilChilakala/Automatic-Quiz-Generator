package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Filen
{
    public static int anInt(String sub)
    {
        int c=0,d;
        File ans = new File("src/sample/a"+sub+".txt");
        try {
            BufferedReader reader2 = new BufferedReader(new FileReader(ans));
            while(reader2.readLine() != null)
            {
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;


    }
}
