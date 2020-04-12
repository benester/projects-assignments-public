/* mipslabwork.c

   This file written 2015 by F Lundevall
   Updated 2017-04-21 by F Lundevall

   This file should be changed by YOU! So you must
   add comment(s) here with your name(s) and date(s):

   This file modified 2017-04-31 by Ture Teknolog 

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */
int mytime = 0x5957;
int timeoutcount = 0;
int prime = 1234567;



char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr( void )
{	

	if(IFS(0) & 0x100){
		timeoutcount++;
		if(timeoutcount == 10)
		{	 
			time2string( textstring, mytime );
			display_string( 3, textstring );
			display_update();
			tick( &mytime );
			timeoutcount = 0;
		}
		IFS(0) = IFS(0)&0x8000; //För att ej nollställa den andra flaggan 
	}
	if(IFS(0) & 0x8000)
	{
	    PORTE = PORTE +1; 
		IFS(0) = IFS(0)& 0x100;
	}
			//Nollställer flaggan 
}
/* Lab-specific initialization goes here */
void labinit( void )
{
	//initiera trise
  TRISE = TRISE & 0xFF00;  //Clearar de 8 ls bitarna. Då de 16 ls bitarna används för att kontrollera outputs. 
	
  T2CON = 0x70; // Stop timer and clear control register
  TMR2 = 0x0; //Clear timer register 
  int TMR2PERIOD = (((80000000)/256)/10); //Period every 0.1 seconds
  PR2 = TMR2PERIOD;
  T2CONSET = 0x8000; //Start timer
  
  PORTE = 0;  //Släcker alla lampor 
  
  IPC(3)=0x03000000; //Sätter prioriteten på den externa switchen // <28:26>  och <25:24> 
  IPC(2)=0x1C;	//Sätter prioriteten på interrupten, <4:2> priority <1:0> subpriority
  IEC(0) = 0x8100; 	//Aktiverar interrupt för timer2 <8> och INT3 <15> 
  enable_interrupt();  	//Kallar på assembly funktionen som kör instruktionen ei (enable interrupt) globalt 
  
	//1000 0001 0000 0000
	
  return;
}

/* This function is called repetitively from the main program */
void labwork( void )
{
 prime = nextprime( prime );
 display_string( 0, itoaconv( prime ) );
 display_update();
}
