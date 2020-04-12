import java.util.*;
class Triangleochcirkel
{
	public static void main(String[] args)
	{
		//skapar ett object av klassen
		Triangel TriangelObject = new Triangel();

		//Inmatningsmetod
		Scanner in = new Scanner (System.in);
	 	in.useLocale (Locale.US);
		boolean loop = true;
		while(loop == true)
		{
			//V�lj
			System.out.println("V�lj vilken typ av ber�kning du vill g�ra:");
			System.out.println(
			"1. Ber�kna area. \n" +
			"2. Ber�kna vinkel p� r�tviknklig triangel. \n" +
			"3. Ber�kna omkrets. \n" +
			"4. Ber�kna bisektris. \n" +
			"5. Ber�kna radien och diametern p� en inskriven och omskriven triangel. \n" +
			"6. Vad �r meningen med livet? \n" +
			"7. Avsluta programmet");

			int valAvBerakning = in.nextInt();

			if(valAvBerakning > 7 || valAvBerakning < 1)
			{
				System.out.println("Fel inmatning");
				System.exit(0);
			}

			switch(valAvBerakning)
			{
				case 1:
					System.out.println("Ange bredd p� triangel: ");
					int bredd = in.nextInt();
					System.out.println("Ange h�jd p� triangel: ");
					int hight = in.nextInt();
					int area = TriangelObject.calcarea(bredd, hight);
					System.out.println("Triangelns area �r: " + area + " A.E");
					System.out.println();
				break;

				case 2:
					System.out.println("Ange n�rliggande katets l�ngd");
					double mkatet = in.nextInt();
					System.out.println("Ange hypotenusans l�ngd");
					double nkatet = in.nextInt();

					if(mkatet > nkatet)
					{
						System.out.println("hypotenusan kan inte vara mindre �n n�rliggande katet \n");

						break;
					}

					double agnle = TriangelObject.calcagnle(nkatet, mkatet);
					System.out.println("Vinkel mellan katet och hypotenusa �r: " + agnle);
					System.out.println();
				break;

				case 3:
					System.out.println("Ange triangelns bas:");
					double sida = in.nextDouble();
					System.out.println("Ange triangelns andra sida:");
					double sida2 = in.nextDouble();
					System.out.println("Ange triangelns tredje sida:");
					double sida3 = in.nextDouble();
					double omkrets = TriangelObject.calcomkrets(sida,sida2,sida3);
					System.out.println("Triangelns omkrets �r: " + omkrets);
					System.out.println();
				break;

				case 4:
					System.out.println("�r alla sidor och vinklar (i radianer) k�nda? y/n");
					String yOrn = in.next();
					switch(yOrn.toLowerCase())
					{
						case "y":
							System.out.println("Ange l�ngd p� sida 1");
							double sidaA = in.nextDouble();
							System.out.println("Ange l�ngd p� sida 2");
							double sidaB = in.nextDouble();
							System.out.println("Ange l�ngd p� sida 3");
							double sidaC = in.nextDouble();

							System.out.println("Ange vinkel mellan sida 1 och 2");
							double vinkelAB = in.nextDouble();
							System.out.println("Ange vinkel mellan sida 1 och 3");
							double vinkelAC = in.nextDouble();
							System.out.println("Ange vinkel mellan sida 2 och 3");
							double vinkelBC = in.nextDouble();

							double bisAB = TriangelObject.calcbisAB(sidaA,sidaB,vinkelAB);
							double bisAC = TriangelObject.calcbisAC(sidaA,sidaC,vinkelAC);
							double bisBC = TriangelObject.calcbisBC(sidaB,sidaC,vinkelBC);

							System.out.println("Bisektrisen mellan sida 1 och 2 �r: " + bisAB + "\n" +
							"Bisektrisen mellan sida 1 och 3 �r: " + bisAC + "\n" +
							"Bisektrisen mellan sida 2 och 3 �r: " + bisBC);
							System.out.println();

						break;

						case "n":
							System.out.println("Mata in f�rsta sidan");
							double sida1 = in.nextDouble();
							System.out.println("Mata in andra sidan");
							double sidan2 = in.nextDouble();
							System.out.println("Mata in vinkeln mellan dessa sidor (i radianer)");
							double vinkeln12 = in.nextDouble();

							double bis12 = TriangelObject.calcbisBC(sida1,sidan2,vinkeln12);

							System.out.println("Bisektrisen mellan sida 1 och 2 �r: " + bis12);
							System.out.println();

						break;
				}
				break;
				case 5:
					System.out.println("�r alla 3 sidor k�nda? y/n");
					String trueorfalse = in.next();
					switch(trueorfalse.toLowerCase())
					{
						case "y":
							//Inmatning
							System.out.println("Mata in l�ngd 1");
							double side1 = in.nextDouble();
							System.out.println("Mata in l�ngd 2");
							double side2 = in.nextDouble();
							System.out.println("Mata in l�ngd 3");
							double side3 = in.nextDouble();



							//Ber�kningar via klassen Triangel
							double omkretsen = TriangelObject.calcomkrets(side1,side2,side3);

							double semiperimeter = TriangelObject.calcSemiperimeter(omkretsen);

							double arean = TriangelObject.calcAreaTreSidor(semiperimeter, side1, side2, side3);

							double radieomskriven = TriangelObject.calcOmskrivenRadie(side1,side2,side3,arean);

							double radieinskriven = TriangelObject.calcInskrivenRadie(arean,semiperimeter);

							System.out.println("Den omskrivna cirkelns radie �r: " + radieomskriven + " Och diametern �r: " + radieomskriven * 2);
							System.out.println("Den inskrivna cirkelns radie �r: " + radieinskriven + " och diametern �r: " + radieinskriven * 2);
						break;

						case "n":
							System.out.println("Programmet kommer nu avslutas.");
						break;
				}
				break;
				case 6:
					System.out.println("Meningen med livet �r 42");
				break;
				case 7:
					loop = false;
				break;
			}
		}
	}
}