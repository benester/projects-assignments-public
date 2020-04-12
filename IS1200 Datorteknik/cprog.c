#include <stdio.h>
#include <windows.h>

int randomshit(int a, int b, int d)
{
	int c = a+b;
	if(d<=256){
		d++;
	}
	else{
		d = 1;
	}
	printf("%x  " , c);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), d);
	return randomshit(c,a,d);
}

int main()
{
	printf("hello");
	int c =1;
	c = randomshit(1,2, 1);
	printf("%8d\n", c);

return 0;
}

