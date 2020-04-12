
public class Triangel
{

	public static int calcarea(int with, int hojd)
	{
		int area = (with * hojd)/2;
		return area;
	}
	public static double calcagnle(double langdy, double langdx)
	{
		double rad = Math.acos(langdx/langdy);
		double agnle = rad * (180/Math.PI);
		return agnle;
	}
	public static double calcomkrets(double bas, double sida1, double sida2)
	{
		double omkrets = bas + sida1 + sida2;
		return omkrets;
	}
	public static double calchojd(double sida1, double sida2, double sida3, double omkrets)
	{

		double hojd = 0;
		return hojd;
	}

	public static double calcbisAB(double sidaA, double sidaB, double vinkelAB)
	{
		double p = 2 * sidaA * sidaB * Math.cos(vinkelAB/2);
		double bis = p / (sidaA + sidaB);

		return bis;

	}
	public static double calcbisAC(double sidaA, double sidaC, double vinkelAC)
	{

		double p = 2 * sidaA * sidaC * Math.cos(vinkelAC/2);
		double bis = p / (sidaA + sidaC);

		return bis;
	}
	public static double calcbisBC(double sidaB, double sidaC, double vinkelBC)
	{
		double p = 2 * sidaB * sidaC * Math.cos(vinkelBC/2);
		double bis = p / (sidaB + sidaC);
		return bis;
	}
	public static double calcAreaTreSidor(double semip, double sidea, double sideb, double sidec)
	{

		//Heron's formel
		double arean = Math.sqrt(semip * (semip - sidea) * (semip - sideb) * (semip - sidec));

		return arean;
	}

	public static double calcOmskrivenRadie(double side1, double side2, double side3, double area)
	{
		double diameter = (side1 * side2 * side3)/(2 * area);
		double radie = diameter/2;
		return radie;
	}
	public static double calcSemiperimeter(double omkrets)
	{
		double semiperimeter = omkrets/2;
		return semiperimeter;
	}

	public static double calcInskrivenRadie(double area, double semip)
	{
		double radie = area/semip;
		return radie;
	}

}