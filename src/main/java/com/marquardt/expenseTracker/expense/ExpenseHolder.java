package com.marquardt.expenseTracker.expense;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

public class ExpenseHolder {

	private List<Expense> expenseList;

	public ExpenseHolder() {
		this.expenseList = new ArrayList<>();
	}

	public boolean insertNewExpense(double amount) {
		return this.expenseList.add(new Expense(amount));

	}

	public boolean insertNewExpense(double amount, String date) {
		return this.expenseList.add(new Expense(amount, date));

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
	public List<Expense> getExpenseByDate() {

		return expenseList;

	}

	/**
	 * 
	 * @param date String with the desired date in the format dd-MM-yyyy
	 * @return A list with all expenses matching the criteria
	 */
	public List<Expense> getExpenseByDate(String date) {
		
		List<Expense> partialExpenceList = new ArrayList();
		
		LocalDate searchDate = LocalDate.parse(date);
		
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
	 */
	public void persistAllExpenses(){
		File storageFile = new File("misc/storedExpences.csv");
		
	}
	
	
	/**
	 * Load the stored expenses from the given input file
	 * @param inputFile The path to the storage file
	 */
	public List<Expense> loadExpensesFromFile(String inputFile){
		
		List<Expense> retrievedExpenses = new ArrayList<>();
		File input = new File(inputFile);
		
		return retrievedExpenses;
	}

}
