import java.util.*; // Scanner
import static java.lang.System.out;
class OperationerMedNaturligaHeltalGivnaSomTeckenstrangar
{
	public static void main (String[] args)
	{
		out.println ("OPERATIONER MED NATURLIGA HELTAL GIVNA SOM TECKENSTRANGAR\n");
		// mata in två naturliga heltal
		Scanner in = new Scanner (System.in);
		out.println ("två naturliga heltal:");
		String tal1 = in.next ();
		String tal2 = in.next ();
		out.println ();

		// addera heltalen och visa resultatet
		String summa = addera (tal1, tal2);
		visa (tal1, tal2, summa, '+');
		// subtrahera heltalen och visa resultatet
		String differans = subtrahera (tal1, tal2);
		visa (tal1, tal2, differans, '-');
	}
	// addera tar emot två naturliga heltal givna som teckensträngar, och returnerar deras summa som en teckensträng.
	public static String addera (String tal1, String tal2)
	{
		String sum = "";
		//För fall med addition med 0
		if(tal1.length() == 1 && tal1.charAt(tal1.length()-1) == '0')
				sum = tal2;
		else if(tal2.length() == 1 && tal2.charAt(tal2.length()-1) == '0')
				sum = tal1;
		else
		{
			int carryon = 0;
			int antalloop;
			int value;
			if(tal1.length() > tal2.length())
			{
				antalloop = tal2.length();
			}
			else
			{
				antalloop = tal1.length();
			}
			for(int i = 1; i <= antalloop; i++)
			{
				int x = Character.getNumericValue(tal1.charAt(tal1.length()-i));
				int y = Character.getNumericValue(tal2.charAt(tal2.length()-i));
				if(carryon == 1)
				{
					x += 1;
					carryon = 0;
				}
				value = x + y;
				if(value >= 10)
				{
					carryon = 1;
					value -= 10;
				}
				char result = (char) (value + '0');
				sum += result;
			}
			//Flyttar ned tal som inte adderas, samt hanterar carryon från sista adderade talet
			if(tal1.length() > tal2.length())
			{
				int remainder = tal1.length() - tal2.length();
				for(int i = remainder; i >= 1; i--)
				{
					int z = Character.getNumericValue(tal1.charAt(i-1));
					if(carryon == 1)
					{
						z++;
						carryon = 0;
						if(z>=10)
						{
							carryon = 1;
							z -= 10;
						}
					}
					char resultat = (char)(z+'0');
					sum += resultat;
				}
			}
			else if(tal1.length()<tal2.length())
			{
				int remainder = tal2.length() - tal1.length();
				for(int i = remainder; i >= 1; i--)
				{
					int z = Character.getNumericValue(tal2.charAt(i-1));
					if(carryon == 1)
					{
						z++;
						carryon = 0;
						if(z>=10)
						{
							carryon = 1;
							z-=10;
						}
					}
					char resultat = (char)(z+'0');
					sum += resultat;
				}
			}
			if(carryon == 1)
				sum+= '1';
			sum = new StringBuilder(sum).reverse().toString();
		}
			sum = removeZero(sum);
			return sum;
	}
	// subtrahera tar emot två naturliga heltal givna som teckensträngar, och returnerar
	// deras differens som en teckensträng.
	// Det första heltalet är inte mindre än det andra heltalet.
	public static String subtrahera (String tal1, String tal2)
	{
		tal1 = removeZero(tal1);
		tal2 = removeZero(tal2);
		boolean biggernumber = false;
		String diff = "";
		if(tal1.length() > tal2.length())
		{
			biggernumber = true;
		}
		//Kollar ifall första talet är större eller lika med det andra talet;
		else if(tal1.length() == tal2.length())
		{
			int x = Character.getNumericValue(tal1.charAt(0)); //Första talets mest värdefulla siffra.
			int y = Character.getNumericValue(tal2.charAt(0)); //Andra talets mest värdefulla siffta
			if(x < y)
				System.out.println("Subtraktionen kommer ej utföras om första talet är mindre än det andra.");
			else if(x > y)
				biggernumber = true;
			else if(x == y)
			{
				biggernumber = true;
				for(int i = 1; i <= tal1.length()-1; i++)
				{
					x = Character.getNumericValue(tal1.charAt(i));
					y = Character.getNumericValue(tal2.charAt(i));

					if(x < y)
					{
						System.out.println("Subtraktionen kommer ej utföras om första talet är mindre än det andra");
						biggernumber = false;
					}
					else if(x==y || x > y)
						biggernumber = true;
				}
			}
		}
		else
			System.out.println("Subtraktion kommer ej utföras om första talet är mindre än det andra.");

		int carryon = 0;
		int value = 0;
			//Om första talet är större än det andra, utförs subtraktionen här
		if(biggernumber == true)
		{

			for(int j = 1; j<= tal2.length(); j++)
			{
				int x = Character.getNumericValue(tal1.charAt(tal1.length() - j));
				int y = Character.getNumericValue(tal2.charAt(tal2.length() - j));

				if(carryon == 1)
				{
					x -= 1;
				}
				value = x-y;
				carryon = 0;
				if(value < 0)
				{
					value += 10;
					carryon = 1;
				}

				char resultat = (char)(value + '0');
				diff += resultat;
			}
		}

		//För att hantera icke subtraherade siffror och icke hanterad carryon subtraktion
		if(tal1.length() > tal2.length() && biggernumber == true)
		{
			int remainder = tal1.length() - tal2.length();
			int z = 0;
				for(int i = remainder; i >= 1; i--)
				{
					z = Character.getNumericValue(tal1.charAt(i-1));
					if(carryon == 1)
						z-=1;

					carryon = 0;
					if(z<0)
					{
						carryon = 1;
						z+= 10;
					}
					char resultat = (char)(z+'0');
					diff += resultat;
				}
		}
		//Vänder på strängen, då minst värdefulla siffran är på mest värdefullas plats, osv.
		diff = new StringBuilder(diff).reverse().toString();
		//Tar bort överflödiga 0or
		if(diff.length()>= 1)
			diff = removeZero(diff);
		return diff;
	}
	// visa visar två givna naturliga heltal, och resultatet av en aritmetisk operation
	// utförd i samband med hetalen

	public static void visa (String tal1, String tal2, String resultat, char operator)
	{
		// sätt en lämplig längd på heltalen och resultatet
		int len1 = tal1.length ();
		int len2 = tal2.length ();
		int len = resultat.length ();
		int maxLen = Math.max (Math.max (len1, len2), len);
		tal1 = sattLen (tal1, maxLen - len1);
		tal2 = sattLen (tal2, maxLen - len2);
		resultat = sattLen (resultat, maxLen - len);
		// visa heltalen och resultatet
		out.println (" " + tal1);
		out.println ("" + operator + " " + tal2);
		for (int i = 0; i < maxLen + 2; i++)
		out.print ("-");
		out.println ();
		out.println (" " + resultat + "\n");
	}
	// sattLen lägger till ett angivet antal mellanslag i början av en given sträng
	public static String sattLen (String s, int antal)
	{
		StringBuilder sb = new StringBuilder (s);
		for (int i = 0; i < antal; i++)
		sb.insert (0, " ");
		return sb.toString ();
	}
	public static String removeZero(String tal)
	{
		while(tal.charAt(0) == '0' && tal.length() > 1)
		{
			tal = new StringBuilder(tal).deleteCharAt(0).toString();
		}
		return tal;
	}
}
