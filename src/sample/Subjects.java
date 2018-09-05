package sample;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.File;

public class Subjects
{
    static Scene scene;
    public static void start()
    {
        AnchorPane pane = new AnchorPane();
        Label label1 = new Label("Edit Questions");
        Label mathl = new Label("3");
        Label biol = new Label("3");
        Label cheml = new Label("3");
        Label label2 = new Label("No.  Of  Questions  Available");
        Button mathb = new Button("Maths");
        Button biob = new Button("Biology");
        Button chemb  = new Button("Chemistry");
        Button gen = new Button("Generate");
        gen.setPrefSize(152,42);
        gen.setLayoutX(180);gen.setLayoutY(327);gen.setFont(new Font(18));
        label1.setLayoutX(88);label1.setLayoutY(66);label1.setFont(new Font(18));
        label2.setLayoutX(275);label2.setLayoutY(66);label2.setFont(new Font(17));
        mathb.setLayoutX(73);mathb.setLayoutY(121);
        mathl.setLayoutX(351);mathl.setLayoutY(131);mathl.setFont(new Font(18));
        biob.setLayoutX(73);biob.setLayoutY(255);
        biol.setLayoutX(351);biol.setLayoutY(265);biol.setFont(new Font(18));
        chemb.setLayoutX(73);chemb.setLayoutY(188);
        cheml.setLayoutX(351);cheml.setLayoutY(198);cheml.setFont(new Font(18));
        cheml.setText((Integer.toString(Filen.anInt("Chemistry"))));
        biol.setText((Integer.toString(Filen.anInt("Biology"))));
        mathl.setText((Integer.toString(Filen.anInt("Maths"))));
        mathb.setPrefSize(152,42);
        biob.setPrefSize(152,42);
        chemb.setPrefSize(152,42);
        pane.getChildren().addAll(label1,label2,mathb,mathl,biob,biol,chemb,cheml,gen);
        scene = new Scene(pane, 600, 400);
        Main.window.setScene(scene);
        Main.window.show();
        mathb.setOnAction(e -> Edit.call("Maths"));
        biob.setOnAction(e -> Edit.call("Biology"));
        chemb.setOnAction(e -> Edit.call("Chemistry"));
        gen.setOnAction(e -> Randomize.start());

    }
}
