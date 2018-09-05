package sample;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;

import static sample.Reader.arr;

public class Edit
{
    static Scene scene;
    static  ListView<String> listView = new ListView<>();
    static String sub;
    public static void call(String sub)
    {
        Edit.sub = sub;
        AnchorPane pane = new AnchorPane();
        listView.setLayoutY(27);listView.setPrefHeight(329);listView.setPrefWidth(600);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Button addb = new Button("Add");
        addb.setLayoutX(120);addb.setLayoutY(357);
        addb.setPrefSize(88,42);
        Button deleteb = new Button("Delete");
        deleteb.setPrefSize(88,42);
        deleteb.setLayoutX(334);deleteb.setLayoutY(357);
        Button modifyb = new Button("Modify");
        modifyb.setPrefSize(88,42);
        modifyb.setLayoutX(228);modifyb.setLayoutY(357);
        Button backb = new Button("Go Back");
        backb.setLayoutX(11);backb.setLayoutY(357);
        backb.setPrefSize(88,42);
        Button cnt = new Button("Continue");
        cnt.setPrefSize(152,42);
        cnt.setLayoutX(439);cnt.setLayoutY(357);
        MenuBar bar = new MenuBar();
        Menu filem = new Menu("_File");
        Menu editm = new Menu("_Edit");
        MenuItem addmi = new MenuItem("Add ");
        MenuItem modifymi = new MenuItem("Modify");
        MenuItem deletemi = new MenuItem("Delete");
        MenuItem close = new MenuItem("Exit ");
        filem.getItems().addAll(addmi,close);
        editm.getItems().addAll(modifymi,deletemi);
        bar.getMenus().addAll(filem,editm);
        bar.setLayoutY(2);bar.setPrefWidth(600);
        pane.getChildren().addAll(listView,cnt,addb,bar,deleteb,modifyb,backb);
        scene = new Scene(pane, 600, 400);
        Main.window.setScene(scene);
        Main.window.show();
        Reader.read(sub);
        addb.setOnAction(e -> Writer.write());
        listView.setOnMouseClicked(e -> select() );
        deleteb.setOnAction(e -> {
            delete();
            Reader.read(sub);
        });
        deletemi.setOnAction(e -> {
            delete();
            Reader.read(sub);
        });
        cnt.setOnAction(e -> Randomize.start());
        backb.setOnAction(e -> Subjects.start());
        modifyb.setOnAction(e -> World.change(listView.getSelectionModel().getSelectedIndices(),listView.getSelectionModel().getSelectedItems()));
        modifymi.setOnAction(e -> World.change(listView.getSelectionModel().getSelectedIndices(),listView.getSelectionModel().getSelectedItems()));
        addmi.setOnAction(e -> Writer.write());
        close.setOnAction(e -> Main.window.close());
    }

    public static void select()
    {
        int index= listView.getSelectionModel().getSelectedIndex();
        listView.getSelectionModel().clearSelection();
            for(int i=-5;i<6; i++)
                if( arr[index]!=0 && index+i >=0 && arr[index]== arr[index+i] )
                    listView.getSelectionModel().select(index+i);
        listView.requestFocus();
    }
    public static void delete()
    {
        ObservableList<String> list = listView.getSelectionModel().getSelectedItems();
        if(list.size()!=0) {
                File ques = new File("src/sample/q" + sub + ".txt");
                File ans = new File("src/sample/a" + sub + ".txt");
                File temp1 = new File("src/sample/temp1.txt");
                File temp2 = new File("src/sample/temp2.txt");
                String s = "";
                try {
                    BufferedReader reader1 = new BufferedReader(new FileReader(ques));
                    BufferedReader reader2 = new BufferedReader(new FileReader(ans));
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(temp1));
                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(temp2));
                    String type = "";
                    int b = 1;
                    int n = 0, req = 0;
                    do {
                        if (b != 0) reader1.mark(10000);
                        b = 1;
                        s = reader1.readLine();

                        if (s.equals("MCA") || s.equals("FillBlank") || s.equals("TF")) {
                            type = s;
                            b = 0;
                            n++;
                        }
                        if(s.equals("##"))
                            writer1.write("##");
                        if (!s.equals(list.get(0)) && b!=0 && !s.equals("##")) {
                            reader1.reset();
                            writer1.write(reader1.readLine());
                            writer1.newLine();
                            writer1.write(reader1.readLine());
                            writer1.newLine();
                        } else if(s.equals(list.get(0))) {
                            reader1.reset();
                            if (type.equals("TF") || type.equals("FillBlank")) {
                                req = n;
                                reader1.readLine();
                                reader1.readLine();
                            } else if (type.equals("MCA")) {
                                req = n;
                                reader1.readLine();
                                reader1.readLine();
                                reader1.readLine();
                                reader1.readLine();
                                reader1.readLine();
                                reader1.readLine();
                            }

                        }

                    } while (!(s.equals("##")));
                    reader1.close();
                    writer1.close();
                    String br;
                    for (int i = 1; i <= n; i++)
                        if (i != req) {
                            writer2.write(reader2.readLine());
                            writer2.newLine();
                        } else {
                            reader2.readLine();
                        }
                    reader2.close();
                    writer2.close();
                    BufferedReader reader3 = new BufferedReader(new FileReader(temp1));
                    BufferedWriter writer3 = new BufferedWriter(new FileWriter(ques));
                    BufferedReader reader4 = new BufferedReader(new FileReader(temp2));
                    BufferedWriter writer4 = new BufferedWriter(new FileWriter(ans));
                    while (!((s = reader3.readLine()).equals("##"))) {
                        writer3.write(s);
                        writer3.newLine();
                    }
                    writer3.write("##");
                    reader3.close();
                    writer3.close();
                    for (int i = 2; i < n; i++) {
                        br = reader4.readLine();
                        writer4.write(br);
                        writer4.newLine();
                    }
                    writer4.write(reader4.readLine());
                    reader4.close();
                    writer4.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
