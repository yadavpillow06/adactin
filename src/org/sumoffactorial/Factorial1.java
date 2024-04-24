package org.sumoffactorial;

public class Factorial1 {

public static void main(String[] args) {
	int n=5;
	int sum = sumOfFactorial(n);
	System.out.println("Sumoffactorial:"+n+ " " +sum);
	
}
public  static int factorial(int n) {
	if(n==0) {
		return 1;
	}else
	{ 
		return (n* factorial(n-1));
	}
}
public static int sumOfFactorial(int n) {
	// TODO Auto-generated method stub
	int sum=0;
	for (int i = 0; i <= n; i++) {
		sum= sum+factorial(i);
	}
	return sum;
}


}
