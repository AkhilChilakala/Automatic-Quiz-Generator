package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Reader
{
    static int c=1;
    static  int[] arr = new int[1000];
    public static int read(String sub)
    {
        int i=0;c=1;
        File ques = new File("src/sample/q"+sub+".txt");
        File ans = new File("src/sample/a"+sub+".txt");
        String s="";
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(ques));
            BufferedReader reader2 = new BufferedReader(new FileReader(ans));
            Edit.listView.getItems().clear();
            while(!((s = reader1.readLine()).equals("##")))
            {
                if(s.equals("MCA"))
                {
                    Edit.listView.getItems().addAll(reader1.readLine(), reader1.readLine(), reader1.readLine(), reader1.readLine(), reader1.readLine(), reader2.readLine(), "\n");
                    arr[i]=c++;arr[i+1]=arr[i+2]=arr[i+3]=arr[i+4]=arr[i];arr[i+5]=arr[i];arr[i+6]=0;i+=7;
                }

                else if(s.equals("FillBlank") || s.equals("TF"))
                {
                    Edit.listView.getItems().addAll(reader1.readLine(),reader2.readLine(),"\n");
                    arr[i]=c++;arr[i+1]=arr[i];arr[i+2]=0;i+=3;
                }
            }
            reader1.close();
            reader2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return c;
    }
}
