package com.proj.tms;

import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import com.proj.Services.UserService;
import com.proj.Models.User;

public class SignInForm {

    public void showSignInForm(Stage primaryStage) {
        primaryStage.setTitle("Sign In Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid");

        Text scenetitle = new Text("Welcome back!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        userName.getStyleClass().add("label");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.getStyleClass().add("textfield");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        pw.getStyleClass().add("label");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.getStyleClass().add("textfield");
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        btn.getStyleClass().add("button");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        actiontarget.getStyleClass().add("actiontarget");
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                UserService userService = new UserService();
                User user = userService.getUserByUsernameAndPassword(userTextField.getText(), pwBox.getText());

                if (user != null) {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Sign in successful!");
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Invalid username or password.");
                }
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
