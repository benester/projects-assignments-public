#include <stdio.h>


int main(){
char c;
printf("Enter your string");
c = getchar();
while(c != EOF){
		
		if(c!='a'){
			
		printf("%c",c);
		}
		else{
			printf("%c",'x');
			
		}
	c = getchar();
}
}