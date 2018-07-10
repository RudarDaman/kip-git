class BankAccount{
	println("Welcome to Knoldus Bank Account")
	
	private var balance = 0
	
	def deposit(money: Int) = {
		balance += money
		println(s"Balance After Saving: $balance")
	}
	
	def withdraw(money: Int) = {
		if(balance > 0){
			balance -= money
			println(s"Balance After Withdraw: $balance")
		}
		else{
			println("Balance is: 0. Please save money to withdraw")
		}
	}
}


