package com.marquardt.expenseTracker.ui.overview;


import com.marquardt.expenseTracker.expense.ExpenseHolder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Overview extends Application {
	
	ExpenseHolder expenseHolder = new ExpenseHolder();

	@Override
	public void start(Stage primaryStage) {
		
		Button btn = new Button();
		btn.setText("click me!");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello it's me");
				
			}
			
		});
		
		TextField fromDateField = new TextField();
		fromDateField.setPromptText("dd-MM-yyyy");
		TextField toDateField = new TextField();
		toDateField.setPromptText("dd-MM-yyyy");
		
		fromDateField.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(fromDateField.getText().length() == 10 && toDateField.getText().length() != 10){
					try{
						expenseHolder.getExpenseByDate(fromDateField.getText());
					}catch(Throwable e){
						
					}
				}else if (fromDateField.getText().length() == 10 && toDateField.getText().length() == 10){
					try{
						expenseHolder.getExpenseByDate();
					}catch(Throwable e){
						
					}
				}else{
					try{
						expenseHolder.getExpenseByDate();
					}catch(Throwable e){
						
					}
				}
			}
		});
		
		
		//create root app and add all child nodes
		StackPane rootWindow = new StackPane();
		rootWindow.getChildren().add(btn);
		rootWindow.getChildren().add(fromDateField);
		rootWindow.getChildren().add(toDateField);
		
		//create the stage eg the window and bind the root application into it
		Scene scene = new Scene(rootWindow, 300, 500);
		primaryStage.setTitle("Expense Tracker");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
