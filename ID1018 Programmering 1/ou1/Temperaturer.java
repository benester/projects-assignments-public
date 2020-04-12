import java.util.*; // Scanner, Locale
class Temperaturer
{
 public static void main (String[] args)
 {
	 System.out.println ("TEMPERATURER\n");

	 // inmatningsverktyg
	 Scanner in = new Scanner (System.in);
	 in.useLocale (Locale.US);

	 // mata in uppgifter om antalet veckor och antalet mätningar

	 System.out.print ("antalet veckor: ");
	 int antalVeckor = in.nextInt ();
	 System.out.print ("antalet mätningar per vecka: ");
	 int antalMatningarPerVecka = in.nextInt ();

	 // plats att lagra temperaturer

	 double[][] t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];

	 // mata in temperaturerna

	 for (int vecka = 1; vecka <= antalVeckor; vecka++)
	 {
		 System.out.println ("temperaturer - vecka " + vecka + ":");
		 for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
		 t[vecka][matning] = in.nextDouble ();
	 }

	 System.out.println ();

	 // visa temperaturerna

	 System.out.println ("temperaturerna:");
	 for (int vecka = 1; vecka <= antalVeckor; vecka++)
	 {
		 for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
		 System.out.print (t[vecka][matning] + " ");
		 System.out.println ();
	 }

	 System.out.println ();

	 // den minsta, den största och medeltemperaturen – veckovis
	 double[] minT = new double[antalVeckor + 1];
	 double[] maxT = new double[antalVeckor + 1];
	 double[] sumT = new double[antalVeckor + 1];
	 double[] medelT = new double[antalVeckor + 1];

	  for(int i = 1; i <= antalVeckor; i++)
	  {
           minT[i] = t[i][1];
           maxT[i] = t[i][1];
           sumT[i] = t[i][1];

	  	for(int j = 2; j <= antalMatningarPerVecka; j++)
	  	{
			sumT[i] += t[i][j];

			if(minT[i] > t[i][j])
			{
				minT[i] = t[i][j];
			}
			if(maxT[i] < t[i][j])
			{
				maxT[i] = t[i][j];
			}
		}
		medelT[i] = (double) sumT[i]/ antalMatningarPerVecka;
	}

	for(int week = 1; week <= antalVeckor; week++)
	{
		System.out.println("Minsta värde vecka " + week + ": " + minT[week]
		+ "| Största värde vecka " + week + ": " + maxT[week]
		+ " | medelvärde för vecka " + week + ": " + medelT[week]);
	}

	  // beräkna den minsta, den största och medeltemperaturen - hela mätperioden
	  double minTemp = minT[1];
	  double maxTemp = maxT[1];
	  double sumTemp = sumT[1];
	  double medelTemp = 0;

	  for(int period = 2; period <= antalVeckor; period++)
	  {
		  if(minTemp > minT[period])
		  		minTemp = minT[period];
		  if(maxTemp < maxT[period])
		  		maxTemp = maxT[period];
		  sumTemp += sumT[period];
		  medelTemp = sumTemp/(antalVeckor * antalMatningarPerVecka);
 	  }
  System.out.println("Minsta temp över " + antalVeckor + " Veckor: " + minTemp + " | Största: " + maxTemp + " | Medeltemp: " + medelTemp);

	  }
}
