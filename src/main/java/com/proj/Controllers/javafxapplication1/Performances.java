/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proj.Controllers.javafxapplication1;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author arsal
 */
public class Performances extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       primaryStage.setTitle("Book Ticket For Movie");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    
    
    
    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Book Movie Ticket");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(0, 0,0,0));

        // Add Select Movie Label
        Label emailLabel = new Label("Select Performances : ");
        gridPane.add(emailLabel, 0, 1);

        // Add Dropdown for movie names
        ComboBox PerformancesComboBox = new ComboBox();
         PerformancesComboBox.getItems().addAll(
            "Performances 1",
            "Performances 2" 
        );
        gridPane.add(PerformancesComboBox, 1, 1);

        // Add Select Movie Label
        Label theaterLabel = new Label("Select Theater : ");
        gridPane.add(theaterLabel, 0, 2);

        // Add Dropdown for movie names
        ComboBox theaterComboBox = new ComboBox();
         theaterComboBox.getItems().addAll(
            "theater 1",
            "theater 2" 
        );
        gridPane.add(theaterComboBox, 1, 2);
        
        
        // Add Select Movie Time
        Label movieTimeLabel = new Label("Movie Time : ");
        gridPane.add(movieTimeLabel, 0, 3);

        // Add Dropdown for movie names
        ComboBox movieTimeComboBox = new ComboBox();
         movieTimeComboBox.getItems().addAll(
            "7:30 AM",
            "7:20 PM" 
        );
        gridPane.add(movieTimeComboBox, 1, 3);

       
        
        // Add Select Movie Time
        Label customerPhone = new Label("Enter Phone : ");
        gridPane.add(customerPhone, 0, 4);
        
        TextField phoneField = new TextField();
        
        phoneField.setPrefWidth(120);
        phoneField.setMaxWidth(120);

        gridPane.add(phoneField, 1, 4);
        
        // Add Submit Button
        Button submitButton = new Button("Save");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        
       
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        
        
        
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                
               
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome ");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
