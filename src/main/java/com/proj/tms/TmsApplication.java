package com.proj.tms;

import com.proj.DAO.EventDAO;
import com.proj.Models.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootApplication
@ComponentScan({"com.proj.Controllers", "com.proj.Services", "com.proj.sql",  "com.proj.tms", "com.proj.Utils", "com.proj.DAO", "com.proj.Models" })
public class TmsApplication extends Application {

    private ConfigurableApplicationContext context;
    private EventDAO eventDAO;
    private ObservableList<Event> events;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = SpringApplication.run(TmsApplication.class, args);
    }

    public void initDatabase() {
        try {
            events = FXCollections.observableArrayList(eventDAO.getAllEvents());
            System.out.println("Database Initialized: ");
            Event.printEventsAsTable(new ArrayList<>(events));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        RegistrationForm regForm = context.getBean(RegistrationForm.class);
        regForm.showRegistrationForm(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
}
