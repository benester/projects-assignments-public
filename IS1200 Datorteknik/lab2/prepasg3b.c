#include <stdio.h>
#include <stdlib.h>

int main(){
	int nrElements;
	printf("\n","Enter size of array");
	scanf("%d", &nrElements);
	int array [nrElements];
	int i = 0;
	int j = nrElements -1;
	while(i<nrElements){
		scanf("%d" , &array[i]);
		i++;
	}
	while(j >= 0){
		printf("%d\n", array [j]);
		j--;
	}
}