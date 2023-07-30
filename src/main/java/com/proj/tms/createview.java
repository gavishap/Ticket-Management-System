package com.proj.tms;

import java.sql.SQLException;
import java.util.ArrayList;

import com.proj.Models.Event;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class createview {
    // public void start(Stage primaryStage) {
    // try {
    // events = FXCollections.observableArrayList(eventDAO.getAllEvents());
    // System.out.println("Application Started: ");
    // Event.printEventsAsTable(new ArrayList<>(events));
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // TableView<Event> tableView = new TableView<>();

    // TableColumn<Event, Integer> column1 = new TableColumn<>("Id");
    // column1.setCellValueFactory(new PropertyValueFactory<>("id"));

    // TableColumn<Event, String> column2 = new TableColumn<>("Name");
    // column2.setCellValueFactory(new PropertyValueFactory<>("name"));

    // TableColumn<Event, String> column3 = new TableColumn<>("Location");
    // column3.setCellValueFactory(new PropertyValueFactory<>("location"));

    // TableColumn<Event, String> column4 = new TableColumn<>("Date");
    // column4.setCellValueFactory(new PropertyValueFactory<>("date"));

    // TableColumn<Event, Integer> column5 = new TableColumn<>("Venue Id");
    // column5.setCellValueFactory(new PropertyValueFactory<>("venueId"));

    // tableView.getColumns().add(column1);
    // tableView.getColumns().add(column2);
    // tableView.getColumns().add(column3);
    // tableView.getColumns().add(column4);
    // tableView.getColumns().add(column5);

    // tableView.setItems(events);

    // StackPane root = new StackPane();
    // root.getChildren().add(tableView);

    // primaryStage.setTitle("Event Data");
    // primaryStage.setScene(new Scene(root, 450, 300));
    // primaryStage.show();
    // }
}
