import java.util.*;
class BestamDenKortasteVagen
{
	public static void main (String[] args)
	{
		DenKortasteVagen kortObject = new DenKortasteVagen();

		Scanner in = new Scanner (System.in);
	 	in.useLocale (Locale.US);

		System.out.println("Ange antal stationer i zon 2");
		int m = in.nextInt();
		System.out.println("Ange antal stationer i zon 3");
		int n = in.nextInt();

		double [] a = new double [m+1];
		double [][] b = new double[m +1][n+1];
		double [] c = new double[n+1];

		for(int index = 1; index <= m; index++)
		{
			System.out.println("Ange längden mellan station x till U[" + index + "]: ");
			a[index] = in.nextDouble();
		}
		for(int index1 = 1; index1 <= m; index1++)
		{
			for(int index2 = 1; index2 <= n; index2++)
			{
				System.out.println("Ange längden mellan stationen U[" + index1 + "] till station V["+index2+"]: ");
				b[index1][index2] = in.nextDouble();
			}
		}
		for(int index3 = 1; index3 <= n; index3++)
		{
			System.out.println("Ange längden mellan station V["+index3+"] till station Y");
			c[index3] = in.nextDouble();
		}

		double kortaste = kortObject.langd(a,b,c);
		int[] mellanstation = kortObject.mellanstationer(a,b,c);

		System.out.println("Den kortaste vägen är: " + kortaste +" Längd enheter lång");
		System.out.println("Och går från x till station U["+ mellanstation[1] + "] till station v[" + mellanstation[2]+"] till y");
	}
}