#include <stdio.h>
#include <ctype.h>

int main(){
	char c;
	while((c=getchar()) != EOF){
		if(isalpha(c) || c==' ' || c=='\n')
		{
			putchar(c);
		}
		else
		{
			putchar(' ');
		}	
	}	
}