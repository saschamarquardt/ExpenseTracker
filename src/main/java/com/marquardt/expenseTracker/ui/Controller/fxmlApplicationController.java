package com.marquardt.expenseTracker.ui.Controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fxmlApplicationController extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		//create root app and add all child nodes
		Parent rootWindow;
		try {
			rootWindow = FXMLLoader.load(getClass().getResource("/com/marquardt/expenseTracker/ui/overview/fxmlOverviewPage.fxml"));
			//create the stage eg the window and bind the root application into it
			Scene scene = new Scene(rootWindow, 300, 500);
			primaryStage.setTitle("Expense Tracker");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
