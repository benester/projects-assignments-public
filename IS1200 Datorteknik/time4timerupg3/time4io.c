#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h" 

int getsw (void){
	
	return ((PORTD & 0xf00)>>8); 
}

int getbtn (void){
	
	return ((PORTD & 0xE0)>>5);
}