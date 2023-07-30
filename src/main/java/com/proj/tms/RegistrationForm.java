package com.proj.tms;

import com.proj.Services.UserService;
import com.proj.Models.User;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class RegistrationForm {
    public void showRegistrationForm(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid");

        Text scenetitle = new Text("Welcome");
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

        Hyperlink signInLink = new Hyperlink("Already have an account? Sign in");
        signInLink.getStyleClass().add("hyperlink");
        signInLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SignInForm signInForm = new SignInForm();
                signInForm.showSignInForm(primaryStage);
            }
        });
        grid.add(signInLink, 1, 5);

        PasswordField pwBox = new PasswordField();
        pwBox.getStyleClass().add("textfield");
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign up");
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
                User newUser = new User();
                newUser.setUsername(userTextField.getText());
                newUser.setPassword(pwBox.getText());
                UserService userService = new UserService();
                User createdUser = userService.createUser(newUser);

                if (createdUser != null) {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("User registered successfully!");
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Registration failed.");
                }
            }
        });

        Scene scene = new Scene(grid, 400, 275);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
