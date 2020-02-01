package org.mikekrolak.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ContributorInfoTest {

	@Test
	public void testCreateAContributorBothNonBlank() {
		ContributorInfo ci = new ContributorInfo("tester", "testId");
		ci.getName();
		assertThat(ci.getName(), is("tester"));
	}

	
	
	@Test
	public void testAmountPaidEnoughWhenNothingPaid() {
		assertFalse(ContributorInfo.isAmountPaidEnough(0, 1));
	}

	@Test
	public void testIsPaidAmountEnoughWhenNothingOwed() {
		try {
			ContributorInfo.isAmountPaidEnough(0, 0);
			//fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException illArgExp) {
			assertThat(illArgExp.getMessage(), is("owedAmount must be greater than 0"));
		}
	}
}