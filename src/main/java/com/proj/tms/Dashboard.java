/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proj.tms;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author arsal
 */
public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Dashboard");

        // Create the registration form grid pane

        GridPane gridPane = createUserDashboard();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane, primaryStage);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createUserDashboard() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and
        // horizontally
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
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane, Stage primaryStage) {

        // Add Email Label

        // Add Submit Button
        Button buttonMovies = new Button("Movies");
        buttonMovies.setPrefHeight(40);
        buttonMovies.setDefaultButton(true);
        buttonMovies.setPrefWidth(100);
        gridPane.add(buttonMovies, 0, 0, 1, 1);
        GridPane.setHalignment(buttonMovies, HPos.RIGHT);

        Button buttonShow = new Button("Show");
        buttonShow.setPrefHeight(40);
        buttonShow.setDefaultButton(true);
        buttonShow.setPrefWidth(100);
        gridPane.add(buttonShow, 0, 1, 1, 1);
        GridPane.setHalignment(buttonShow, HPos.RIGHT);

        Button buttonPerformance = new Button("Performance");
        buttonPerformance.setPrefHeight(40);
        buttonPerformance.setDefaultButton(true);
        buttonPerformance.setPrefWidth(100);
        gridPane.add(buttonPerformance, 0, 2, 1, 1);
        GridPane.setHalignment(buttonPerformance, HPos.RIGHT);

        buttonMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Movies movies = new Movies();
                movies.start(primaryStage);

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
