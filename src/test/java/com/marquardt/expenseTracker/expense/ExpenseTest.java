package com.marquardt.expenseTracker.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.marquardt.expenseTracker.expense.Expense;

public class ExpenseTest {
	
	@Test
	public void testExpenseReturnsOne() {
		
		Expense expense = new Expense(1);
		
		Assert.assertEquals(expense.getValue(), new BigDecimal(1));
	}
	
	@Test
	public void testExpenseReturnsTwo() {
		
		Expense expense = new Expense(2);
		
		Assert.assertEquals(expense.getValue(), new BigDecimal(2));

	}
	
	@Test
	public void testExpenseReturnsDecimal(){
		
		Expense expense = new Expense(3.15);
		
		Assert.assertEquals(expense.getValue(), new BigDecimal(3.15));
	}
	
	@Test
	public void testExpenseStoresCurrentDate(){
		
		Expense expense = new Expense(1);
		LocalDate currentDate = LocalDate.now();
		
		LocalDate expenseDate = expense.getExpenseDate();
		System.out.println("show the date how it is stored in the expense class: " + expenseDate.toString());
		System.out.println("show the date how it is displayed via function: " + expense.showExpenseDate());
		Assert.assertEquals(expenseDate, currentDate);
	}
	
	@Test
	public void testExpenseShowsDateThatIsManuallyEntered(){
		Expense expense = new Expense(1, "24-05-2016");
		LocalDate currentDate = LocalDate.now();
		
		LocalDate expenseDate = expense.getExpenseDate();
		System.out.println(expenseDate.toString());
		System.out.println(expense.showExpenseDate());
	}
}
