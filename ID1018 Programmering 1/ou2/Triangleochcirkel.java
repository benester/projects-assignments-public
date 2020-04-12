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
			//Välj
			System.out.println("Välj vilken typ av beräkning du vill göra:");
			System.out.println(
			"1. Beräkna area. \n" +
			"2. Beräkna vinkel på rätviknklig triangel. \n" +
			"3. Beräkna omkrets. \n" +
			"4. Beräkna bisektris. \n" +
			"5. Beräkna radien och diametern på en inskriven och omskriven triangel. \n" +
			"6. Vad är meningen med livet? \n" +
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
					System.out.println("Ange bredd på triangel: ");
					int bredd = in.nextInt();
					System.out.println("Ange höjd på triangel: ");
					int hight = in.nextInt();
					int area = TriangelObject.calcarea(bredd, hight);
					System.out.println("Triangelns area är: " + area + " A.E");
					System.out.println();
				break;

				case 2:
					System.out.println("Ange närliggande katets längd");
					double mkatet = in.nextInt();
					System.out.println("Ange hypotenusans längd");
					double nkatet = in.nextInt();

					if(mkatet > nkatet)
					{
						System.out.println("hypotenusan kan inte vara mindre än närliggande katet \n");

						break;
					}

					double agnle = TriangelObject.calcagnle(nkatet, mkatet);
					System.out.println("Vinkel mellan katet och hypotenusa är: " + agnle);
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
					System.out.println("Triangelns omkrets är: " + omkrets);
					System.out.println();
				break;

				case 4:
					System.out.println("Är alla sidor och vinklar (i radianer) kända? y/n");
					String yOrn = in.next();
					switch(yOrn.toLowerCase())
					{
						case "y":
							System.out.println("Ange längd på sida 1");
							double sidaA = in.nextDouble();
							System.out.println("Ange längd på sida 2");
							double sidaB = in.nextDouble();
							System.out.println("Ange längd på sida 3");
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

							System.out.println("Bisektrisen mellan sida 1 och 2 är: " + bisAB + "\n" +
							"Bisektrisen mellan sida 1 och 3 är: " + bisAC + "\n" +
							"Bisektrisen mellan sida 2 och 3 är: " + bisBC);
							System.out.println();

						break;

						case "n":
							System.out.println("Mata in första sidan");
							double sida1 = in.nextDouble();
							System.out.println("Mata in andra sidan");
							double sidan2 = in.nextDouble();
							System.out.println("Mata in vinkeln mellan dessa sidor (i radianer)");
							double vinkeln12 = in.nextDouble();

							double bis12 = TriangelObject.calcbisBC(sida1,sidan2,vinkeln12);

							System.out.println("Bisektrisen mellan sida 1 och 2 är: " + bis12);
							System.out.println();

						break;
				}
				break;
				case 5:
					System.out.println("är alla 3 sidor kända? y/n");
					String trueorfalse = in.next();
					switch(trueorfalse.toLowerCase())
					{
						case "y":
							//Inmatning
							System.out.println("Mata in längd 1");
							double side1 = in.nextDouble();
							System.out.println("Mata in längd 2");
							double side2 = in.nextDouble();
							System.out.println("Mata in längd 3");
							double side3 = in.nextDouble();



							//Beräkningar via klassen Triangel
							double omkretsen = TriangelObject.calcomkrets(side1,side2,side3);

							double semiperimeter = TriangelObject.calcSemiperimeter(omkretsen);

							double arean = TriangelObject.calcAreaTreSidor(semiperimeter, side1, side2, side3);

							double radieomskriven = TriangelObject.calcOmskrivenRadie(side1,side2,side3,arean);

							double radieinskriven = TriangelObject.calcInskrivenRadie(arean,semiperimeter);

							System.out.println("Den omskrivna cirkelns radie är: " + radieomskriven + " Och diametern är: " + radieomskriven * 2);
							System.out.println("Den inskrivna cirkelns radie är: " + radieinskriven + " och diametern är: " + radieinskriven * 2);
						break;

						case "n":
							System.out.println("Programmet kommer nu avslutas.");
						break;
				}
				break;
				case 6:
					System.out.println("Meningen med livet är 42");
				break;
				case 7:
					loop = false;
				break;
			}
		}
	}
}