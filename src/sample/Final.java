package sample;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Final
{
    static Scene scene;
    static ListView<String> listView = new ListView<>();
    static String sub;
    static  int r;
    static int x;
    static Random rand = new Random();
    static Button swap = new Button("Solutions");
    static ArrayList<Integer> randlist = new ArrayList<Integer>();
    public static void rand(String sub, int r)
    {
        Final.r=r;
        Final.sub=sub;
        AnchorPane pane = new AnchorPane();
        listView.setLayoutY(27);listView.setPrefHeight(329);listView.setPrefWidth(600);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Button save = new Button("Export");
        save.setPrefSize(152,42);
        save.setLayoutX(439);save.setLayoutY(357);save.setFont(new Font(18));
        Button life = new Button("Back");
        life.setLayoutX(8);life.setLayoutY(357);life.setPrefSize(152,42);
        life.setFont(new Font(18));
        swap.setLayoutX(225);swap.setLayoutY(357);swap.setPrefSize(152,42);
        swap.setFont(new Font(18));
        MenuBar bar = new MenuBar();
        Menu filem = new Menu("_File");
        Menu editm = new Menu("_Edit");
        bar.getMenus().addAll(filem,editm);
        MenuItem close = new MenuItem("Exit ");
        MenuItem export = new MenuItem("Export ");
        filem.getItems().addAll(close);
        editm.getItems().addAll(export);
        bar.setLayoutY(2);bar.setPrefWidth(600);
        for (int j=1; j<=Filen.anInt(sub); j++)
        randlist.add(j);
        Collections.shuffle(randlist);
        pane.getChildren().addAll(listView,save,bar,life,swap);
        scene = new Scene(pane, 600, 400);
        Main.window.setScene(scene);
        Main.window.show();
        life.setOnAction(e -> Randomize.start());
        write();
        save.setOnAction(e -> create());
        export.setOnAction(e -> create());
        swap.setOnAction(e -> {
            if(swap.getText().equals("Solutions"))
            swap.setText("Questions");
            else
                swap.setText("Solutions");
            write();
        });
        close.setOnAction(e -> Main.window.close());

    }

    public static void write()
    {
        listView.getItems().clear();

        for (int k=0; k<r; k++)
        {
            x=randlist.get(k);
            for(int i=0;i<1000; i++)
                if(Reader.arr[i]==x)
                {

                    if(swap.getText().equals("Solutions"))
                    {
                        if((i+1) < Edit.listView.getItems().size() && !(Edit.listView.getItems().get(i+1).equals("\n")))
                        {
                            listView.getItems().add(Edit.listView.getItems().get(i));
                            if(Reader.arr[i+2]==0)
                                listView.getItems().add("\n");
                        }
                    }
                    else
                    {
                        if((i+1) < Edit.listView.getItems().size() && (Edit.listView.getItems().get(i+1).equals("\n")))
                        {
                            listView.getItems().add(Edit.listView.getItems().get(i));listView.getItems().add("\n");
                        }


                    }

                }



        }
    }

    public static void create()
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        AlertWindow.login("Choose file for Questions","Continue");
        File fileq = fileChooser.showSaveDialog(Main.window);
        AlertWindow.login("Choose file for Answers","Continue");
        File filea = fileChooser.showSaveDialog(Main.window);


        if(fileq == null || filea == null){
            AlertWindow.login("No File selected","Try Again");
        }
        else{
            try
            {
                BufferedWriter writerq = new BufferedWriter(new FileWriter(fileq));
                BufferedWriter writera = new BufferedWriter(new FileWriter(filea));
                String s="";
                swap.setText("Solutions");
                write();
                for(int i=0; i<listView.getItems().size();i++)
                {
                    s=listView.getItems().get(i);
                        writerq.write(s);
                        writerq.newLine();
                }
                writerq.close();
                swap.setText("Questions");
                write();
                for(int i=0; i<listView.getItems().size();i++)
                {
                    s=listView.getItems().get(i);
                    writera.write(s);
                    writera.newLine();
                }
                writera.close();
                AlertWindow.login("Exported Successfully","Close");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
    }
}
