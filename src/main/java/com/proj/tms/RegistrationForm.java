package com.proj.tms;

import com.proj.Services.UserService;
import com.proj.Models.User;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
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

import org.springframework.stereotype.Component;

@Component
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
                String username = userTextField.getText();
                String password = pwBox.getText();
                String json = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8081/api/users"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                        .build();

                // Print request info
                System.out.println("Request to URL: " + request.uri());
                System.out.println("Request method: " + request.method());
                System.out.println("Request headers: " + request.headers());
                System.out.println("Request body: " + json);

                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    // Print response info
                    System.out.println("Response code: " + response.statusCode());
                    System.out.println("Response headers: " + response.headers());
                    System.out.println("Response body: " + response.body());

                    if (response.statusCode() == 201) {
                        actiontarget.setFill(Color.GREEN);
                        actiontarget.setText("User registered successfully!");
                    } else {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Registration failed.");
                    }
                } catch (Exception ex) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("An error occurred: " + ex.getMessage());
                }
            }
        });

        Scene scene = new Scene(grid, 400, 275);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
