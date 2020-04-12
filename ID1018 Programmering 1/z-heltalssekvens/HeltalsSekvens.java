// HeltalsSekvens.java

// ett program som på olika sätt hanterar en sekvens med heltal


class HeltalsSekvens
{
    public static void main (String[] args)
	{
		System.out.println ("EN SEKVENS MED HELTAL\n\n");

		// inmatningsverktyg
		java.util.Scanner    in = new java.util.Scanner (System.in);
		in.useLocale (java.util.Locale.US);

        // mata in ett antal heltal
        int[]    u = new int[10];
        System.out.println ("ange 10 heltal i en rad:");
        for (int i = 0; i < u.length; i++)
            u[i] = in.nextInt ();
        System.out.println ();

        // det minsta av heltalen och heltalens medelvärde
        int    min = u[0];
        int    sum = u[0];
        for (int i = 1; i < u.length; i++)
        {
            if (u[i] < min)
                min = u[i];
            sum += u[i];
		}
        double    mean = (double) sum / (u.length);

        // visa resultat
        System.out.println ("minsta heltalet och heltalens medelvärde");
        System.out.printf ("%4d  |  %7.2f\n\n\n", min, mean);

	}
}
