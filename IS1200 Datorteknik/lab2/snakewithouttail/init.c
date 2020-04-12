#include <pic32mx.h>
#include <stdint.h>
#include <stdio.h>

void spi_setup(){
/* Set up peripheral bus clock */
	OSCCON &= ~0x180000;
	OSCCON |= 0x080000;
	
	/* Set up output pins */
	AD1PCFG = 0xFFFF;
	ODCE = 0x0;
	TRISECLR = 0xFF;
	PORTE = 0x0;
	
	/* Output pins for display signals */
	PORTF = 0xFFFF;
	PORTG = (1 << 9);
	ODCF = 0x0;
	ODCG = 0x0;
	TRISFCLR = 0x70;
	TRISGCLR = 0x200;
	
	/* Set up input pins */
	TRISDSET = (1 << 8);
	TRISFSET = (1 << 1);
	
	/* Set up SPI as master */
	SPI2CON = 0;
	SPI2BRG = 4;
	
	/* Clear SPIROV*/
	SPI2STATCLR &= ~0x40;
	/* Set CKP = 1, MSTEN = 1; */
        SPI2CON |= 0x60;
	
	/* Turn on SPI */
	SPI2CONSET = 0x8000;
	
	/* Initisiera lampor*/ 
	//initiera trise
	volatile int * trise = (volatile int *) 0xbf886100;
	*trise = *trise &0xFF00;  //Clearar de 8 ls bitarna. Då de 16 ls bitarna används för att kontrollera outputs.
}

void welcomeScreen(){
	display_init();
	display_string(0, "Welcome");
	display_string(1, "to");
	display_string(2, "Our");
	display_string(3, "Snake Game");
	display_update();
	delay(10000000);
	display_string(0, "");
	display_string(1, "");
	display_string(2, "");
	display_string(3, "");
	display_update();
	delay(100000);
}

