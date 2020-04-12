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

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr( void )
{
  return;
}

/* Lab-specific initialization goes here */
void labinit( void )
{
	//initiera trise
	volatile int * trise = (volatile int *) 0xbf886100;
	*trise = *trise &0xFF00;  //Clearar de 8 ls bitarna. Då de 16 ls bitarna används för att kontrollera outputs. 
	//Initiera PORTD, så att bitar 11 till 5 är inputs
	TRISD = TRISD | 0xFE0; 
	
	
  return;
}

/* This function is called repetitively from the main program */
void labwork( void )
{
  volatile int * porte = (volatile int *) 0xbf886110;  //Sätter på lampor
  int btns = getbtn();
  if(btns){
	int sw = getsw();
	if(btns&0x4){ //BTN4
		mytime = (mytime & 0x0FFF) | (sw << 12); //Mytime = 0x5759; => 0x(sw)759;
	}
	if(btns&0x2){ //BTN3
		mytime = (mytime & 0xF0FF) | (sw << 8);	
	}
	if(btns&0x1){ //BTN2
		mytime = (mytime & 0xFF0F) | (sw << 4);
	}
  }
  
  delay( 1000 );
  time2string( textstring, mytime );
  display_string( 3, textstring );
  display_update();
  tick( &mytime );
  *porte = *porte + 1; 
  display_image(96, icon);
}
