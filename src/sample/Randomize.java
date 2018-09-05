package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Randomize
{
    public static void start()
    {
        Scene scene;
        AnchorPane pane = new AnchorPane();
        Label l1 = new Label("Choose Subject");
        l1.setLayoutX(66);l1.setLayoutY(44);l1.setPrefSize(140,36);l1.setFont(new Font(18));
        Label l2 = new Label("Available Questions : ");
        l2.setLayoutX(352);l2.setLayoutY(93);l1.setPrefSize(169,25);l1.setFont(new Font(16));
        Label l3 = new Label("Choose no. of Questions");
        l3.setLayoutX(51);l3.setLayoutY(152);l3.setPrefSize(188,36);l3.setFont(new Font(16));
        TextField tf = new TextField();
        tf.setPromptText("10");
        tf.setLayoutX(362);tf.setLayoutY(158);
        Button edit = new Button("Edit Questions");
        edit.setLayoutX(75);edit.setLayoutY(285);edit.setPrefSize(140,39);edit.setFont(new Font(18));
        Button gen = new Button("Generate");
        gen.setLayoutX(367);gen.setLayoutY(285);gen.setPrefSize(140,39);gen.setFont(new Font(18));
        ComboBox<String> box = new ComboBox<>();
        box.setLayoutX(357);box.setLayoutY(50);box.setPrefWidth(150);
        box.getItems().addAll("Maths","Chemistry","Biology");
        pane.getChildren().addAll(l1,l2,l3,tf,edit,gen,box);
        scene = new Scene(pane,600,400);
        Main.window.setScene(scene);
        Main.window.show();
        edit.setOnAction(e -> Subjects.start());
        box.getSelectionModel().selectedItemProperty().addListener((v,oldv,newv) -> {
            l2.setText("Available Questions :  " +Filen.anInt(box.getValue()));
        });
        gen.setOnAction(e -> Final.rand(box.getValue(),Integer.parseInt(tf.getText())));
        tf.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Final.rand(box.getValue(),Integer.parseInt(tf.getText()));

            }
            });
            }
    }

