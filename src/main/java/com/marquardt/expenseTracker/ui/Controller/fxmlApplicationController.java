package com.marquardt.expenseTracker.ui.Controller;

import java.io.IOException;

import org.junit.internal.Throwables;

import com.marquardt.expenseTracker.expense.Expense;
import com.marquardt.expenseTracker.expense.ExpenseHolder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fxmlApplicationController extends Application {
	
	ExpenseHolder expenseHolder = new ExpenseHolder();
	
	@FXML TextField expenseName;
	@FXML TextField expenseAmount;
	@FXML TextField expenseDate;
	@FXML Text addingExpenseOutput;
	
	@FXML private void addNewExpenseToList(){
		addingExpenseOutput.setText(""); //clearing output for new execution
		
		//check values from input fields or set them to a default value
		String name = validateExpenseName(expenseName.getText());
		String value = expenseAmount.getText();
		String date = validateExpenseDate(expenseDate.getText());
		String desc = validateExpenseDescription();
		String category = validateExpenseCategory();
		
		expenseHolder.insertNewExpense(name, value, date, desc, category);
		addingExpenseOutput.setText("Expense added"); //give output result
		
		//clears the text fields after use
		expenseName.clear();
		expenseAmount.clear();
		expenseDate.clear();
		
		for(int i = 0; i < expenseHolder.getExpenseByDate().size(); i++){
			System.out.println(expenseHolder.getExpenseByDate().get(i).toString());
		}
		
	}
	

	private String validateExpenseCategory() {
		// TODO Auto-generated method stub
		return "";
	}


	private String validateExpenseDescription() {
		// TODO Auto-generated method stub
		return "";
	}


	private String validateExpenseDate(String text) {
		if(text.length() != 0){
			return text;
		}else{
			return "";
		}
	}


	private String validateExpenseName(String text) {
		if(text.length() != 0){
			return text;
		}else{
			return "undefined";
		}
	}


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

	@FXML private void show(){
		System.out.println(this.expenseHolder.getExpenseByDate().size());
		
	}
	
	private void saveData() throws IOException{
		this.expenseHolder.persistAllExpenses();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws IOException{
		System.out.println("persisting expenses...");
		saveData();
		System.out.println("expenses persisted");
	}
	
	
}
