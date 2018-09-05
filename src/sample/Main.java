package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    static Stage window;
    Scene login;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Mini Project");
        AnchorPane pane = new AnchorPane();
        Label namel = new Label("UserName");
        Label passwordl = new Label("Password");
        TextField namet = new TextField();
        namet.setPromptText("Username");
        namel.setLayoutX(64.0);namel.setLayoutY(93.0);
        namet.setLayoutX(64.0);namet.setLayoutY(128.0);
        PasswordField passwordt = new PasswordField();
        passwordt.setPromptText("password");
        passwordl.setLayoutX(64.0);passwordl.setLayoutY(165.0);
        passwordt.setLayoutX(64.0);passwordt.setLayoutY(200.0);
        Button btn = new Button("Log In");
        btn.setLayoutX(64.0);btn.setLayoutY(255.0);
        btn.setOnAction(e -> {
            if(namet.getText().equals("user") && passwordt.getText().equals("pass"))
                Subjects.start();
            else
                AlertWindow.login("Access Denied Wrong Credentials","Try Again");

        });
        passwordt.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if(namet.getText().equals("user") && passwordt.getText().equals("pass"))
                    Subjects.start();
                else
                    AlertWindow.login("Access Denied","Try Again");
            }
        });

        pane.getChildren().addAll(namel,namet,passwordl,passwordt,btn);
        login = new Scene(pane, 300, 400);
        window.setScene(login);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
