// min returnerar det minsta elementet i en sekventiell samling.
// Om samlingen �r tom, kastas ett undantag av typen IllegalArgumentException.
class Extraupg
{

public static void main (String[] args)
{
	int[] element = {9,102,3,17,35,12,9,9,5,100,16,23,56,102,90,23,2,22,100};
	int vmin;
	int vmin2;
	vmin = min(element);
	vmin2 = min2(element);
	System.out.println("minsta v�rdet: " + vmin);
	System.out.println("minsta v�rdet andra sort: " + vmin2);
}

public static int min (int[] element) throws IllegalArgumentException
{
	if (element.length == 0)
	throw new IllegalArgumentException ("tom samling");

 // h�r ihop med sp�rutskriften 2:
  int antalVarv = 1;

	int[] sekvens = element;
	int antaletPar = sekvens.length / 2;
	int antaletOparadeElement = sekvens.length % 2;
	int antaletTankbaraElement = antaletPar + antaletOparadeElement;
	int i = 0;
	int j = 0;
	System.out.println (java.util.Arrays.toString (sekvens));

	while (sekvens.length > 1)
	{
// skilj ur en delsekvens med de t�nkbara elementen
		int[] delsekvens = new int[antaletTankbaraElement]; //FLYTTADE NER F�R ATT G�RA SEKVENS MINDRE
		i = 0;
		j = 0;
		while (j < antaletPar)
		{
			delsekvens[j++] = (sekvens[i] < sekvens[i + 1])? sekvens[i] : sekvens[i + 1];
			i += 2;
			//System.out.println("jo" + delsekvens[j]);
		}
		if (antaletOparadeElement == 1)
			delsekvens[j] = sekvens[sekvens.length - 1];

	//	System.out.println(delsekvens[j]);
		System.out.println("Sekvens l�ngd: " + sekvens.length);

		// utg� nu ifr�n delsekvensen

		sekvens = delsekvens;
		antaletPar = antaletTankbaraElement / 2;
		antaletOparadeElement = antaletTankbaraElement % 2;
		antaletTankbaraElement = antaletPar + antaletOparadeElement;

		// sp�rutskrift 1 � f�r att f�lja sekvensen
		 System.out.println (java.util.Arrays.toString (sekvens));
		// sp�rutskrift 2 - f�r att avsluta loopen i f�rv�g
		 //(f�r att kunna se vad som h�nder i b�rjan)
		// if (antalVarv++ == 10)
		// System.exit (0);
	}
	// sekvens[0] �r det enda �terst�ende t�nkbara elementet
	// - det �r det minsta elementet
	return sekvens[0];
}
public static int min2 (int[] element) throws IllegalArgumentException {
	int temp;
	for(int i = 0; i<element.length;i++)
		for(int j = 1; j <element.length - i;j++)
			if(element[j]<element[i]){
				temp = element [i];
				element[i] = element [j];
				element[j] = temp;
			}


	return element[0];

}

}