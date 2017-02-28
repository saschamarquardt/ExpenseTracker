package com.marquardt.expenseTracker.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {

	private BigDecimal value;
	private String titel = "";
	private LocalDate expenseDate;
	private String description = "";
	private String category = "";

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	
	Expense(double amount) {
		this.value = new BigDecimal(amount);
		//set Date automatically if not specified by user
		this.expenseDate = LocalDate.now();
	}

	
	Expense(double amount, String expenseDate) {
		// TODO Auto-generated constructor sub
		this.value = new BigDecimal(amount);
		this.expenseDate = LocalDate.parse(expenseDate, this.formatter);
	}
	
	Expense(String titel, double amount, String expenseDate, String description, String category){
		this.titel = titel;
		this.value = new BigDecimal(amount);
		this.expenseDate = LocalDate.parse(expenseDate, this.formatter);
		this.description = description;
		this.category = category;
		
	}


	public String showExpenseDate(){
		String currentDate;
		if(this.expenseDate != null){
			
			currentDate = this.expenseDate.format(formatter);
			
		} else{
			currentDate = "Date not set";
		}
		
		return currentDate;
	}
	
	
	public String expenseToFile(){
		
		String expenseString;
		
		expenseString = this.titel + "," + this.value + "," + showExpenseDate() + "," + this.description + "," + this.category;
		
		return expenseString;
	}
	
	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * @return the expenseDate
	 */
	public LocalDate getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
	
	/**
	 * 
	 * @return The expense description
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Expense [value=" + value + ", titel=" + titel + ", expenseDate=" + expenseDate + ", description="
				+ description + ", category=" + category + "]";
	}




}
