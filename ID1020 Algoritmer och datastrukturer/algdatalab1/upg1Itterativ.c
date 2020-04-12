#include <stdio.h>
#include <stdlib.h>

int main(){
	printf("Enter 5 characters\n");
	char array [5];
	int i = 0;
	int j = 4;
	
	//Enters the characters into an array
	while(i<5){
		scanf("%c" , &array[i]);
		i++;
	}
	//Prints the array backwards
	while(j >= 0){
		printf("%c", array [j]);
		j--;
	}
}