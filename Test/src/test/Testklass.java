package test;
public class Testklass {
	public static void main(String[] args) {
	      mystery(3);
	   } 
	
	public static void mystery(int n) {
	      if (n > 0){
	         mystery(n-1);
	         System.out.print(n * 4);
	         mystery(n-1);
	      } 
	   }
}