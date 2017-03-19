package com.marquardt.expenseTracker.ui.Controller;

import java.io.IOException;
import com.marquardt.expenseTracker.expense.Expense;
import com.marquardt.expenseTracker.expense.ExpenseHolder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fxmlApplicationController extends Application {
	
	
	ExpenseHolder expenseHolder = new ExpenseHolder();
	
	@FXML TextField expenseName;
	@FXML TextField expenseAmount;
	@FXML TextField expenseDate;
	@FXML Text addingExpenseOutput;
	@FXML ObservableList<Expense> observableExpenseList;
	@FXML ListView<Expense> expenseView;
	
	@FXML private void addNewExpenseToList(){
		addingExpenseOutput.setText(""); //clearing output for new execution
		
		//check values from input fields or set them to a default value
		String name = validateExpenseName(expenseName.getText());
		String value = expenseAmount.getText();
		String date = validateExpenseDate(expenseDate.getText());
		String desc = validateExpenseDescription();
		String category = validateExpenseCategory();
		
		Expense newExpense = expenseHolder.insertNewExpense(name, value, date, desc, category);
		if(newExpense != null){
			addingExpenseOutput.setText("Expense added"); //give output result
		}else{
			System.out.println("Failed to add Expense, sorry...");
		}
		
		//store the expense into a file directly
		try {
			expenseHolder.appendToStorageFile(newExpense);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//clears the text fields after use
		expenseName.clear();
		expenseAmount.clear();
		expenseDate.clear();
		
		for(int i = 0; i < expenseHolder.getExpenseByDate().size(); i++){
			System.out.println(expenseHolder.getExpenseByDate().get(i).toString());
		}
		
	}
	

	private String validateExpenseCategory() {
		return "";
	}


	private String validateExpenseDescription() {
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
			e.printStackTrace();
		}
				
		
	}

	@FXML
	private void show(){
		System.out.println(this.expenseHolder.getExpenseByDate().size());
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initialize(){
		//load expenses from file
		expenseHolder.loadExpensesFromFile();
		//put the expenses from the list in to the observable list		
		observableExpenseList = expenseHolder.getExpenseByDate();
		expenseView.setItems(observableExpenseList);
		
	}
	
	
	
}
