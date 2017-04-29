/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author nathanielclayarnold
 */
class Udc3Gui extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button(); 
        Button btn4 = new Button(); 
        Button btn5 = new Button(); 
       
        btn.setText("Logout");
        btn2.setText("Set Concensus Scores");
        btn3.setText("Create a Data Set");
        btn4.setText("View Graph");
        btn5.setText("Choose Data Set");
        
        // Logout 
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        // Set Concensus Scores
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        }); 
        
        // Create a Data Set 
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               
               Label label = new Label("\nFilter By State (Select All that Apply):"); 
               CheckComboBox cbxState = new CheckComboBox();
               cbxState.getItems().addAll(((Object[])State.values()));
               
               Label label2 = new Label("\nFilter By Party (Select All that Apply):");
               CheckComboBox cbxParty = new CheckComboBox(); 
               cbxParty.getItems().addAll(((Object[])Party.values()));
               
               VBox vbox = new VBox(); 
               vbox.setPadding(new Insets(10));
               vbox.setSpacing(8); 
               vbox.getChildren().add(label);
               vbox.getChildren().add(cbxState);
               vbox.getChildren().add(label2); 
               vbox.getChildren().add(cbxParty); 
                
               HBox hbox = new HBox();
               hbox.setPadding(new Insets(15, 12, 15, 12));
               hbox.setSpacing(10);
               hbox.setStyle("-fx-background-color: #336699;");
               hbox.getChildren().addAll(btn, btn2, btn3, btn4, btn5);
                
               BorderPane border = new BorderPane();
               border.setTop(hbox); 
               border.setLeft(vbox);
               Scene scene = new Scene(border, 700, 300); 
               
               ScrollBar scroll = new ScrollBar();
               scroll.setLayoutX(scene.getWidth()-scroll.getWidth());
               scroll.setMin(0);
               scroll.setOrientation(Orientation.VERTICAL);
               scroll.setPrefHeight(180);
               scroll.setMax(360);
               border.setRight(scroll); 
               
               scroll.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    vbox.setLayoutY(-new_val.doubleValue());
                }
            });
               
               primaryStage.setTitle("Citizen UDC3 Administrator");
               primaryStage.setScene(scene);
               primaryStage.show();
            }
        });
        
        // View Graph 
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        // Choose Data Set 
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.getChildren().addAll(btn, btn2, btn3, btn4, btn5);
        
        BorderPane border = new BorderPane();
        border.setTop(hbox); 
        Scene scene = new Scene(border, 700, 300); 
        
        primaryStage.setTitle("Citizen UDC3 Administrator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
