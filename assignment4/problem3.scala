object problem3 extends App{
	def multiply(n: Int): Int = {
		if(n == 0)
			1
		else
			multiply(n-1)*n
	}
	def sum(n: Int): Int = {
		if(n == 0)
			0
		else
			sum(n/10) + n%10
	}
	print("Enter the value of n:")
	val n = scala.io.StdIn.readInt()
	val result = multiply(n)
	println(sum(result))
}
