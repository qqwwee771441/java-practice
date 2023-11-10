/**
 * 
 */
package com.fxmx.exam;

/**
 * @author Choi kwangjin
 *
 */
import java.lang.Integer;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main Method");
		
		Integer n = 1_000_000;
		System.out.println(n.intValue());
		System.out.println(Integer.max(n, 1));
		System.out.println(Integer.min(n, 1));
		System.out.println(Integer.sum(n, 1));
		System.out.println(Integer.valueOf("2022"));
		System.out.println(Integer.toBinaryString(2022));
		System.out.println(Integer.toOctalString(2022));
		System.out.println(Integer.toHexString(2022));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		BigInteger big1 = new BigInteger("1000000000000000");
		BigInteger big2 = new BigInteger("9999999999999999");
		System.out.println(big1.add(big2));
		System.out.println(big1.subtract(big2));
		System.out.println(big1.multiply(big2));
		System.out.println(big1.divide(big2));
		BigInteger big3 = new BigInteger("1");
		System.out.println(big3.intValueExact());
		BigDecimal dec1 = new BigDecimal("0.1");
		BigDecimal dec2 = new BigDecimal("0.2");
		System.out.println(dec1.add(dec2));
		System.out.println(dec1.subtract(dec2));
		System.out.println(dec1.multiply(dec2));
		System.out.println(dec1.divide(dec2));
		
		System.out.println(Math.sqrt(2));
		System.out.println(Math.PI);
		System.out.println(Math.toDegrees(Math.PI));
		System.out.println(Math.sin(Math.toRadians(45)));
		System.out.println(Math.cos(Math.toRadians(45)));
		System.out.println(Math.tan(Math.toRadians(45)));
		System.out.println(Math.log(10));
		System.out.println(Math.pow(2, 10));
		
		Random rand = new Random();
		System.out.println(rand.nextInt(100));
		
		for(StringTokenizer st = new StringTokenizer("010-2789-4873", "-"); st.hasMoreTokens(); ) {
			System.out.println(st.nextToken());
		}
		
		int[] arr1 = {5, 4, 3, 2, 1};
		int[] arr2 = Arrays.copyOf(arr1, 5);
		Arrays.sort(arr2); // compareTo
		int[] arr3 = Arrays.copyOfRange(arr2, 0, 5);
		System.arraycopy(arr3, 0, arr1, 0, 5);
		System.out.println(Arrays.equals(arr1, arr2));
		System.out.println(Arrays.binarySearch(arr2, 4));
		
		System.out.println( sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) );
	}
	
	public static int sum(int...is) {
		int result = 0;
		for(int i : is)
			result += i;
		return result;
	}
}
