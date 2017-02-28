package com.marquardt.expenseTracker.expense;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.marquardt.expenseTracker.expense.Expense;
import com.marquardt.expenseTracker.expense.ExpenseHolder;


public class TestExpenseHolder {

	ExpenseHolder expenseHolder;

	@Before
	public void testInit() {
		this.expenseHolder = new ExpenseHolder();
		for (int i = 0; i < 10; i++) {
			this.expenseHolder.insertNewExpense(100);
		}
	}

	@Test
	public void testInsertOneExpense() {
		this.expenseHolder = new ExpenseHolder();

		Assert.assertTrue(expenseHolder.insertNewExpense(100));

	}

	@Test
	public void testInsertMultipleExpenses() {
		expenseHolder = new ExpenseHolder();

		boolean[] actuals = new boolean[10];
		boolean[] expecteds = { true, true, true, true, true, true, true, true, true, true };

		for (int i = 0; i < 10; i++) {

			actuals[i] = expenseHolder.insertNewExpense(100);
		}

		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testRetrieveOneExpense() {

		BigDecimal retrievedAmount = expenseHolder.getExpenseByDate().get(2).getValue();
		BigDecimal expected = new BigDecimal(100);

		Assert.assertEquals(expected, retrievedAmount);

	}

	@Test
	public void retrieveMultipleExpenses() {

		BigDecimal val1, val2;
		val1 = expenseHolder.getExpenseByDate().get(2).getValue();
		val2 = expenseHolder.getExpenseByDate().get(5).getValue();

		Assert.assertEquals(new BigDecimal(100), val1);
		Assert.assertEquals(new BigDecimal(100), val2);
	}

	@Test
	public void testSumUpMultipleExpenses() {
		BigDecimal val1, val2;
		val1 = expenseHolder.getExpenseByDate().get(2).getValue();
		val2 = expenseHolder.getExpenseByDate().get(5).getValue();

		Assert.assertEquals(new BigDecimal(200), val1.add(val2));

	}

	@Test
	public void testSumFunctionFromExpenseHolder() {

		BigDecimal result = expenseHolder.calculateSum();

		Assert.assertEquals(new BigDecimal(1000), result);
	}

	@Test
	public void testGetExpenseByDate() {

		String dateString = "24-02-2017";
		// change format of date string dd-mm-yyyy
		String day = dateString.substring(0, 2);
		String month = dateString.substring(3, 5);
		String year = dateString.substring(6, 10);

		expenseHolder.insertNewExpense(200, dateString);

		List<Expense> expenseListMatch = expenseHolder.getExpenseByDate(year + "-" + month + "-" + day);
		Assert.assertEquals(1, expenseListMatch.size());
	}
	
	@Test
	public void testReturnListWithMultipleExpensesShoudBeThree() {
		for (int i = 0; i < 8; i++) {
			this.expenseHolder.insertNewExpense(100);
		}
		
		this.expenseHolder.insertNewExpense(250, "24-10-2015");
		this.expenseHolder.insertNewExpense(250, "05-07-2016");
		this.expenseHolder.insertNewExpense(250, "13-01-2017");
		
		List<Expense> expenseListMatch = expenseHolder.getExpenseByDate("01-01-2010", "01-02-2017");
		
		for(Expense match : expenseListMatch){
			System.out.println(match.showExpenseDate());
		}
		Assert.assertEquals(3, expenseListMatch.size());
	}
	
	@Test
	public void testReturnListWithMultipleMatchesShouldBeTwo(){
		for (int i = 0; i < 8; i++) {
			this.expenseHolder.insertNewExpense(100);
		}
		
		this.expenseHolder.insertNewExpense(250, "24-10-2015");
		this.expenseHolder.insertNewExpense(250, "05-07-2016");
		this.expenseHolder.insertNewExpense(250, "13-01-2017");
		
		List<Expense> expenseListMatch = expenseHolder.getExpenseByDate("01-01-2010", "12-01-2017");
		
		for(Expense match : expenseListMatch){
			System.out.println(match.showExpenseDate());
		}
		Assert.assertEquals(2, expenseListMatch.size());
	}
	
	@Test
	public void testReturnListWithMultipleMatchesShouldBeThree(){
		for (int i = 0; i < 8; i++) {
			this.expenseHolder.insertNewExpense(100);
		}
		
		this.expenseHolder.insertNewExpense(250, "24-10-2015");
		this.expenseHolder.insertNewExpense(250, "05-07-2016");
		this.expenseHolder.insertNewExpense(250, "13-01-2017");
		
		List<Expense> expenseListMatch = expenseHolder.getExpenseByDate("01-01-2010", "13-01-2017");
		
		for(Expense match : expenseListMatch){
			System.out.println(match.showExpenseDate());
		}
		Assert.assertEquals(3, expenseListMatch.size());
	}
	
	@Test
	public void testPersistFiveExpenses(){
		this.expenseHolder.insertNewExpense(250);
		this.expenseHolder.insertNewExpense(450);
		this.expenseHolder.insertNewExpense(50);
		this.expenseHolder.insertNewExpense(100, "13-01-2016");
		this.expenseHolder.insertNewExpense(-80, "15-04-2005");
		
		try {
			this.expenseHolder.persistAllExpenses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRetrieveExpensesFromFile(){
		
		String inputFile = "misc/storedExpences.csv";
		
		this.expenseHolder.loadExpensesFromFile(inputFile);
		
		for(Expense expense : this.expenseHolder.getExpenseByDate()){
			System.out.println(expense.toString());
		}
	}

}
