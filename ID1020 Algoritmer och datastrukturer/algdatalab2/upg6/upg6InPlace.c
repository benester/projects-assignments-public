#include <stdio.h>
/**
* By Benjamin Gafvelin
* Created 2019-09-15
* Updated 2019-09-16
* This program is a in-place algorithm, that places all negative numbers to the left and all possitiv numbers to the right, using only O(1) extra memory
*
**/

/**
* An In-place algorithm that moves all negative number from the input to the beginning of the array, and possitive numbers at the end of the array.
* @param arr, the array to be sorted by the in-place algorithm
* @param length, the length of the array. 
**/

void sort (int arr[], int length){
	int j = 0;
	for(int i = 0; i < length ;i++){
		// All to the left j is negative
		// All between j and i-1 is positive 
		// All to the right if i is unknown
		if(arr[i] < 0){
			int temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			j++;
		}
	}
}

/**
** A function that prints the content of an array
** @param arr, is the array to be printed
** @param length is the length of the array to be printed
**/
void printArray(int arr[], int length){
	for(int i = 0; i < length ;i++){
		printf("%d ", arr[i]);
		if(i<length-1){
			printf(",");
		}
	}
}


int main(){
	int amnt;
	
	printf("%s", "Enter the amount of integers you wish to enter");
	scanf("%d", &amnt); 
	printf("%s", "Enter your integers");
	int arr[amnt];
	for(int i = 0; i < amnt; i++){
		scanf("%d", &arr[i]);
	}
	
	sort(arr, amnt);
	printArray(arr, amnt);
}