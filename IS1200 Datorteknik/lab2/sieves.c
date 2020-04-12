#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define COLUMNS 6
int coloumncounter = 0;

void print_number(int i){
	
	if(coloumncounter % COLUMNS == 0)
		printf("\n");
	coloumncounter++;
	
	printf("%10d", i);
}

void print_sieves(int max_prim)
{

	char sieves [max_prim];  //char är 1 byte
	
	for(int i = 2; i< max_prim; i++)		//Sätt alla värden i arrayen till true
		sieves[i] = 'a';
	
	for(int i = 2; i<=sqrt(max_prim); i++)	//For räknar upp, i får inte överskrida kvadratroten ur max_prim
		if(sieves[i] == 'a')
			for(int j = i*i; j<= max_prim; j += i)	//i^2, i^2 +i, i^2 +2i osv 
				sieves[j] = 'b'; 		//Sätter alla tal som är en faktor av i till false
	
	for(int i = 2; i <= max_prim; i++)
		if(sieves[i] == 'a')
			print_number(i);
	
}


int main(int argc, char *argv[]){
  if(argc == 2)
    print_sieves(atoi(argv[1]));
  else
    printf("Please state an interger number.\n");
  return 0;
}
