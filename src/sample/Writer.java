package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
public class Writer
{
    static String Selectedtype ="";
    static  TextArea questa = new TextArea();
    static TextArea ansta = new TextArea();
    static ObservableList<String> list = FXCollections.observableArrayList("True Or False","Fill In The Blank","Multiple Choice Questions");
    final static  ComboBox<String> box = new ComboBox<String>(list);
    static  Stage window = new Stage();
    static TextField atf = new TextField();
    static TextField btf = new TextField();
    static TextField ctf = new TextField();
    static TextField dtf = new TextField();
    static Label al = new Label("a)");
    static Label bl = new Label("b)");
    static Label cl = new Label("c)");
    static Label dl = new Label("d)");
    static RadioButton trb = new RadioButton("True");
    static RadioButton frb = new RadioButton("False");
    public static void write()
    {
        window.setResizable(false);
        Scene scene;
        AnchorPane pane = new AnchorPane();
        questa.setPromptText("Enter Question Here");
        questa.setLayoutX(27);questa.setLayoutY(103);questa.setPrefSize(546,121);
        ansta.setLayoutX(27);ansta.setLayoutY(237);ansta.setPrefSize(546,65);
        ansta.setPromptText("Enter Answer Here");
        box.setLayoutX(225);box.setLayoutY(67);box.prefWidth(150);
        Button addb = new Button("Add");
        addb.setLayoutX(211);addb.setLayoutY(340);
        Button cancelb = new Button("Cancel");
        cancelb.setLayoutX(349);cancelb.setLayoutY(340);
        Label label = new Label("What is the type of Question?");
        label.setLayoutY(24);label.setLayoutX(221);
        atf.setLayoutX(95);atf.setLayoutY(188);atf.setPrefSize(64,25);
        btf.setLayoutX(220);btf.setLayoutY(188);btf.setPrefSize(64,25);
        ctf.setLayoutX(352);ctf.setLayoutY(188);ctf.setPrefSize(64,25);
        dtf.setLayoutX(476);dtf.setLayoutY(188);dtf.setPrefSize(64,25);
        al.setLayoutX(71);al.setLayoutY(186);al.setPrefSize(25,25);al.setFont(new Font(18));
        bl.setLayoutX(196);bl.setLayoutY(186);bl.setPrefSize(25,25);bl.setFont(new Font(18));
        cl.setLayoutX(328);cl.setLayoutY(186);cl.setPrefSize(25,25);cl.setFont(new Font(18));
        dl.setLayoutX(452);dl.setLayoutY(186);dl.setPrefSize(25,25);dl.setFont(new Font(18));
        trb.setLayoutX(274);trb.setLayoutY(200);trb.setPrefSize(81,36);trb.setFont(new Font(14));
        frb.setLayoutX(274);frb.setLayoutY(249);frb.setPrefSize(81,36);frb.setFont(new Font(14));
        ToggleGroup group = new ToggleGroup();
        trb.setToggleGroup(group);
        frb.setToggleGroup(group);
        trb.setSelected(true);
        pane.getChildren().addAll(questa,ansta,box,addb,cancelb,label);
        box.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            try {
                if (NewValue.equals("Multiple Choice Questions")) {
                    questa.setPrefHeight(65);
                    if(OldValue=="Fill In The Blank")
                    {
                        pane.getChildren().addAll(atf, btf, ctf, dtf, al, bl, cl, dl);
                    }
                    else {
                        pane.getChildren().removeAll(trb,frb);
                        pane.getChildren().addAll(atf, btf, ctf, dtf, al, bl, cl, dl,ansta);
                    }
                    ansta.setPromptText("Enter a or b or c or d");

                }
                else if (NewValue.equals("True Or False"))
                    {

                         questa.setPrefHeight(65);
                        pane.getChildren().removeAll(atf, btf, ctf, dtf, al, bl, cl, dl,ansta);
                        pane.getChildren().addAll(trb,frb);
                    }
                 else
                {
                    questa.setPrefHeight(121);
                    pane.getChildren().removeAll(atf, btf, ctf, dtf, al, bl, cl, dl,trb,frb);
                    pane.getChildren().addAll(ansta);
                }


            }
            catch (Exception e)
            {}
        });
        scene = new Scene(pane, 600, 400);
        window.setScene(scene);
        window.show();
        box.setOnAction(e ->type(box.getValue()));
        cancelb.setOnAction(e -> {
            window.close();
            questa.clear();
            ansta.clear();
            box.getSelectionModel().clearSelection();
            atf.clear();
            btf.clear();
            ctf.clear();
            dtf.clear();
        });
        addb.setOnAction(e -> {

            inc();
            questa.clear();
            ansta.clear();
            atf.clear();
            btf.clear();
            ctf.clear();
            dtf.clear();
            box.getSelectionModel().clearSelection();
        });

    }

    public static void type(String s)
    {
        Selectedtype=s;
    }
    public static void inc()
    {
        File ques = new File("src/sample/q"+Edit.sub+".txt");
        File ans = new File("src/sample/a"+Edit.sub+".txt");
        File temp = new File("src/sample/temp1.txt");
        String s="";
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(ques));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(ans,true));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(temp));

            if(Filen.anInt("Chemistry")!=0)writer2.newLine();
            String t="";
            if(box.getValue().equals("Fill In The Blank")) t = ansta.getText();
            else if(box.getValue().equals("True Or False")) t = trb.isSelected() ? "T" : "F";
            else t = ansta.getText();
            while(t.endsWith("\n") && t.length()!=1)
            {
               t = t.substring(0,t.length()-1);
            }
            writer2.write(t);
            while(!((s = reader1.readLine()).equals("##")))
            {
                writer1.write(s);
                writer1.newLine();
            }
            int a=0;
            if(box.getValue().equals("True Or False"))
                writer1.write("TF");


            else if(box.getValue().equals("Fill In The Blank"))
                writer1.write("FillBlank");
            else
            {
                writer1.write("MCA");a=1;
            }

            writer1.newLine();
            t = questa.getText();
            while(t.endsWith("\n"))
            {
                t = t.substring(0,t.length()-1);
            }
            writer1.write(t);
            writer1.newLine();
            if(a==1)
            {
                writer1.write("a) "+atf.getText());
                writer1.newLine();
                writer1.write("b) "+btf.getText());
                writer1.newLine();
                writer1.write("c) "+ctf.getText());
                writer1.newLine();
                writer1.write("d) "+dtf.getText());
                writer1.newLine();
            }
            writer1.write("##");
            reader1.close();
            writer1.close();
            writer2.close();
            BufferedReader reader2 = new BufferedReader(new FileReader(temp));
            BufferedWriter writer3 = new BufferedWriter(new FileWriter(ques));
            while(!((s = reader2.readLine()).equals("##")))
            {
                writer3.write(s);
                writer3.newLine();
            }
            writer3.write("##");
            reader1.close();
            writer3.close();
            Reader.read(Edit.sub);
            window.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
