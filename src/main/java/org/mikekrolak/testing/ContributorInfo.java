package org.mikekrolak.testing;

public class ContributorInfo {
	private String name;
	private String userId;

	public ContributorInfo(final String name, final String userId) {
		if (!name.isBlank()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("name must not be blank");
		}
		if (!userId.isBlank()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("userId must not be blank");
		}
	}

	public String getName() {
		return this.name;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public static boolean isAmountPaidEnough(int paidAmount, int owedAmount){
		   if(owedAmount > 0){
		     return paidAmount >= owedAmount;
		   }
		   else{
		     throw new IllegalArgumentException("owedAmount must be greater than 0");
		   }
		}

}
