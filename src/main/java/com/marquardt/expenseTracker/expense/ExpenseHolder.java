package com.marquardt.expenseTracker.expense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpenseHolder {

	private ObservableList<Expense> expenseList;
	String storageFilePath = "misc/storedExpences.csv";

	public ExpenseHolder() {
		this.expenseList = FXCollections.observableArrayList();
	}
	
	
	@Deprecated
	public boolean insertNewExpense(double amount) {
		return this.expenseList.add(new Expense(amount));

	}

	@Deprecated
	public boolean insertNewExpense(double amount, String date) {
		return this.expenseList.add(new Expense(amount, date));

	}
	
	public Expense insertNewExpense(String titel, String value, String expenseDate, String description, String category){
		Expense newExpense = new Expense(titel, value, expenseDate, description, category);
		this.expenseList.add(newExpense);
		return newExpense;
	}
	
	public boolean insertNewExpense(Expense expense){
		return this.expenseList.add(expense);
	}

	public BigDecimal calculateSum() {
		BigDecimal totalAmount;
		totalAmount = new BigDecimal(0);

		for (int i = 0; i < this.expenseList.size(); i++) {
			totalAmount = totalAmount.add(this.expenseList.get(i).getValue());
		}

		return totalAmount;
	}

	/**
	 * 
	 * Returns all Expenses
	 * 
	 * @return A list of all stored expenses
	 */
	public ObservableList<Expense> getExpenseByDate() {

		return expenseList;

	}

	/**
	 * 
	 * @param date String with the desired date in the format dd-MM-yyyy
	 * @return A list with all expenses matching the criteria
	 */
	public List<Expense> getExpenseByDate(String date) {
		
		List<Expense> partialExpenceList = new ArrayList();
		
		LocalDate searchDate = LocalDate.parse(date, Expense.formatter);
		
		for(int i = 0; i < this.expenseList.size(); i++){
			if (this.expenseList.get(i).getExpenseDate().isEqual(searchDate)) {
				partialExpenceList.add(this.expenseList.get(i));
			}
		}
		
		return partialExpenceList;

	}
	
	/**
	 * 
	 * @param fDate String with the starting date in the format dd-MM-yyyy
	 * @param tDate String with the end date in the format dd-MM-yyyy
	 * @return A list with all expenses matching the criteria
	 */
	public List<Expense> getExpenseByDate(String fDate, String tDate){
		
		List<Expense> partialExpenseList = new ArrayList<>();
		
		LocalDate fromDate = LocalDate.parse(fDate, Expense.formatter);
		LocalDate toDate = LocalDate.parse(tDate, Expense.formatter);
		
		for(int i = 0; i < this.expenseList.size(); i++){
			if (this.expenseList.get(i).getExpenseDate().isAfter(fromDate) && this.expenseList.get(i).getExpenseDate().isBefore(toDate.plusDays(1))){
				partialExpenseList.add(this.expenseList.get(i));
			}
		}
		
		return partialExpenseList;
	}
	
	/**
	 * Stores all Expenses in a csv file for persistence
	 * @throws IOException 
	 */
	public void persistAllExpenses() throws IOException{
		File storageFile = new File(storageFilePath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile));
		
		//write out the whole list
		for(Expense expense : this.expenseList){
			System.out.println(expense.expenseToFile());
			try {
				writer.write(expense.expenseToFile());
				System.out.println(expense.expenseToFile());
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.close();
		
	}
	
	public void appendToStorageFile(Expense expense) throws IOException{
		
		File storageFile = new File(storageFilePath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile, true));
		writer.write(expense.expenseToFile());
		writer.newLine();
		writer.close();
		
	}
	
	
	/**
	 * Load the stored expenses from the given input file
	 * @param inputFile The path to the storage file
	 */
	public boolean loadExpensesFromFile(){
		
		File inputFile = new File(storageFilePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			try {
				while(reader.ready()){
					String[] nextExpense = reader.readLine().split(",", -1);
					expenseList.add(new Expense(nextExpense[0], nextExpense[1], nextExpense[2], nextExpense[3], nextExpense[4]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}

}
