public class DenKortasteVagen
{
	public static int[] mellanstationer (double[] a, double[][] b,double[] c)
	{
		int[] stationer = new int [2 + 1];
		double kort = a[1] + b[1][1] + c[1];

		for(int k=1; k<=a.length -1;k++)
			for(int m = 1; m<=b[1].length -1; m++)
				if(kort >= a[k]+b[k][m]+c[m])
				{
					kort = a[k]+b[k][m]+c[m];
					stationer[1] = k;
					stationer[2] = m;
				}
		return stationer;
	}
	public static double langd (double[] a, double[][] b, double[] c)
	{
		double kortastevagen = a[1] + b[1][1] + c[1];
		for(int i = 1; i<=a.length - 1;i++)
			for(int j = 1; j<=b[1].length - 1; j++)
			{
				if(kortastevagen >= a[i]+b[i][j]+c[j])
					kortastevagen = a[i]+b[i][j]+c[j];
			}
		return kortastevagen;
	}
}