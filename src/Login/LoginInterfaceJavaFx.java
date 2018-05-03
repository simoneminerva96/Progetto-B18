package Login;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;
import java.util.Scanner;
/*
   CLASSE PER INTERFACCIA GRAFICA DI LOGIN E REGISTRAZIONE
   DA COMMENTARE OPPORTUNAMENTE OGNI METODO/ATTRIBUTO
*/
public class LoginInterfaceJavaFx extends Application {

    public static void main(String[] args) {launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scanner scanner = new Scanner(System.in);

        primaryStage.setTitle("Login");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        Text sceneTitle = new Text("Login");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        grid.add(sceneTitle,0,0,2,1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        Button btn2 = new Button ("Sign up");

        HBox hbBtn = new HBox(10);
        HBox hbBtn2 = new HBox(10);

        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);

        hbBtn.getChildren().add(btn);
        hbBtn2.getChildren().add(btn2);

        grid.add(hbBtn, 1, 4);
        grid.add(hbBtn2,0,4);

        final Text actiontarget = new Text();
        final Text actiontarget2 = new Text();

        grid.add(actiontarget, 1, 6);
        grid.add(actiontarget2, 1,7);

        btn.setOnAction(new EventHandler<ActionEvent>() { //bottone per il login
            @Override
            public void handle(ActionEvent e) {

                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("BENVENUTO!");

            }
        });


        btn2.setOnAction(new EventHandler<ActionEvent>() { //bottone per la registrazione

            @Override
            public void handle(ActionEvent e) {

                actiontarget2.setFill(Color.FIREBRICK);
                actiontarget2.setText("Ti sei registrato");
            }
        });


        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
