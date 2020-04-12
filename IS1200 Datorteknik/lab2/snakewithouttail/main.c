#include <pic32mx.h>
#include <stdint.h>


void *stdin, *stdout, *stderr;

#define DISPLAY_VDD PORTFbits.RF6
#define DISPLAY_VBATT PORTFbits.RF5
#define DISPLAY_COMMAND_DATA PORTFbits.RF4
#define DISPLAY_RESET PORTGbits.RG9


#define DISPLAY_VDD_PORT PORTF
#define DISPLAY_VDD_MASK 0x40
#define DISPLAY_VBATT_PORT PORTF
#define DISPLAY_VBATT_MASK 0x20
#define DISPLAY_COMMAND_DATA_PORT PORTF
#define DISPLAY_COMMAND_DATA_MASK 0x10
#define DISPLAY_RESET_PORT PORTG
#define DISPLAY_RESET_MASK 0x200
#define ROWLEN 8
#define COLLEN 16
#define ROWWID 4

char textbuffer[4][16];

static const uint8_t const font[] = {
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 94, 0, 0, 0, 0,
	0, 0, 4, 3, 4, 3, 0, 0,
	0, 36, 126, 36, 36, 126, 36, 0,
	0, 36, 74, 255, 82, 36, 0, 0,
	0, 70, 38, 16, 8, 100, 98, 0,
	0, 52, 74, 74, 52, 32, 80, 0,
	0, 0, 0, 4, 3, 0, 0, 0,
	0, 0, 0, 126, 129, 0, 0, 0,
	0, 0, 0, 129, 126, 0, 0, 0,
	0, 42, 28, 62, 28, 42, 0, 0,
	0, 8, 8, 62, 8, 8, 0, 0,
	0, 0, 0, 128, 96, 0, 0, 0,
	0, 8, 8, 8, 8, 8, 0, 0,
	0, 0, 0, 0, 96, 0, 0, 0,
	0, 64, 32, 16, 8, 4, 2, 0,
	0, 62, 65, 73, 65, 62, 0, 0,
	0, 0, 66, 127, 64, 0, 0, 0,
	0, 0, 98, 81, 73, 70, 0, 0,
	0, 0, 34, 73, 73, 54, 0, 0,
	0, 0, 14, 8, 127, 8, 0, 0,
	0, 0, 35, 69, 69, 57, 0, 0,
	0, 0, 62, 73, 73, 50, 0, 0,
	0, 0, 1, 97, 25, 7, 0, 0,
	0, 0, 54, 73, 73, 54, 0, 0,
	0, 0, 6, 9, 9, 126, 0, 0,
	0, 0, 0, 102, 0, 0, 0, 0,
	0, 0, 128, 102, 0, 0, 0, 0,
	0, 0, 8, 20, 34, 65, 0, 0,
	0, 0, 20, 20, 20, 20, 0, 0,
	0, 0, 65, 34, 20, 8, 0, 0,
	0, 2, 1, 81, 9, 6, 0, 0,
	0, 28, 34, 89, 89, 82, 12, 0,
	0, 0, 126, 9, 9, 126, 0, 0,
	0, 0, 127, 73, 73, 54, 0, 0,
	0, 0, 62, 65, 65, 34, 0, 0,
	0, 0, 127, 65, 65, 62, 0, 0,
	0, 0, 127, 73, 73, 65, 0, 0,
	0, 0, 127, 9, 9, 1, 0, 0,
	0, 0, 62, 65, 81, 50, 0, 0,
	0, 0, 127, 8, 8, 127, 0, 0,
	0, 0, 65, 127, 65, 0, 0, 0,
	0, 0, 32, 64, 64, 63, 0, 0,
	0, 0, 127, 8, 20, 99, 0, 0,
	0, 0, 127, 64, 64, 64, 0, 0,
	0, 127, 2, 4, 2, 127, 0, 0,
	0, 127, 6, 8, 48, 127, 0, 0,
	0, 0, 62, 65, 65, 62, 0, 0,
	0, 0, 127, 9, 9, 6, 0, 0,
	0, 0, 62, 65, 97, 126, 64, 0,
	0, 0, 127, 9, 9, 118, 0, 0,
	0, 0, 38, 73, 73, 50, 0, 0,
	0, 1, 1, 127, 1, 1, 0, 0,
	0, 0, 63, 64, 64, 63, 0, 0,
	0, 31, 32, 64, 32, 31, 0, 0,
	0, 63, 64, 48, 64, 63, 0, 0,
	0, 0, 119, 8, 8, 119, 0, 0,
	0, 3, 4, 120, 4, 3, 0, 0,
	0, 0, 113, 73, 73, 71, 0, 0,
	0, 0, 127, 65, 65, 0, 0, 0,
	0, 2, 4, 8, 16, 32, 64, 0,
	0, 0, 0, 65, 65, 127, 0, 0,
	0, 4, 2, 1, 2, 4, 0, 0,
	0, 64, 64, 64, 64, 64, 64, 0,
	0, 0, 1, 2, 4, 0, 0, 0,
	0, 0, 48, 72, 40, 120, 0, 0,
	0, 0, 127, 72, 72, 48, 0, 0,
	0, 0, 48, 72, 72, 0, 0, 0,
	0, 0, 48, 72, 72, 127, 0, 0,
	0, 0, 48, 88, 88, 16, 0, 0,
	0, 0, 126, 9, 1, 2, 0, 0,
	0, 0, 80, 152, 152, 112, 0, 0,
	0, 0, 127, 8, 8, 112, 0, 0,
	0, 0, 0, 122, 0, 0, 0, 0,
	0, 0, 64, 128, 128, 122, 0, 0,
	0, 0, 127, 16, 40, 72, 0, 0,
	0, 0, 0, 127, 0, 0, 0, 0,
	0, 120, 8, 16, 8, 112, 0, 0,
	0, 0, 120, 8, 8, 112, 0, 0,
	0, 0, 48, 72, 72, 48, 0, 0,
	0, 0, 248, 40, 40, 16, 0, 0,
	0, 0, 16, 40, 40, 248, 0, 0,
	0, 0, 112, 8, 8, 16, 0, 0,
	0, 0, 72, 84, 84, 36, 0, 0,
	0, 0, 8, 60, 72, 32, 0, 0,
	0, 0, 56, 64, 32, 120, 0, 0,
	0, 0, 56, 64, 56, 0, 0, 0,
	0, 56, 64, 32, 64, 56, 0, 0,
	0, 0, 72, 48, 48, 72, 0, 0,
	0, 0, 24, 160, 160, 120, 0, 0,
	0, 0, 100, 84, 84, 76, 0, 0,
	0, 0, 8, 28, 34, 65, 0, 0,
	0, 0, 0, 126, 0, 0, 0, 0,
	0, 0, 65, 34, 28, 8, 0, 0,
	0, 0, 4, 2, 4, 2, 0, 0,
	0, 120, 68, 66, 68, 120, 0, 0,
};

/*const uint8_t const snake[] = {
	0, 0, 0, 0, 0, 0, 0, 0,
};*/

 uint8_t icon[] = {
	255, 255, 255, 255, 255, 255, 255, 255,				//Top vänster		(rad 0)       index 0-7
	255, 255, 255, 255, 255, 255, 255, 255,		//Top vänster + ett steg åt höger  (rad 0)	index 8-15
	255, 255, 255, 255, 255, 255, 255, 255	,				//Top vänster + 2 steg åt höger 	(rad 0)		index 16-23
	255, 255, 255, 255, 255, 255, 255, 255,					//Top vänster + 3 steg åt höger     (rad 0)	index 24- 31
	0, 0, 0, 0, 0, 0, 0, 0,							//en rad ner, vänster                       (rad 1)	 index 32-39
	255, 255, 255, 255, 255, 255, 255, 255,			//index 40-47
	255, 255, 255, 255, 255, 255, 255, 255,			//index 48-55
	255, 255, 255, 255, 255, 255, 255, 255,			//index 56-63
	255, 255, 255, 255, 255, 255, 255, 255,			//en rad ner 	(rad 2) index 64-71
	255, 255, 255, 255, 255, 255, 255, 255,			//index 72-79
	255, 255, 255, 255, 255, 255, 255, 255,			//index 80-87
	255, 255, 255, 255, 255, 255, 255, 255,			//index 88-95
	255, 255, 255, 255, 255, 255, 255, 255,			//en rad ner  (rad 3) index 96-103
	255, 255, 255, 255, 255, 255, 255, 255,			//index 104-111
	255, 255, 255, 255, 255, 255, 255, 255,			//index 112 - 119
	255, 255, 255, 255, 255, 255, 255, 255,			//index 120-127
};

 uint8_t foodar[] = {
	255, 255, 255, 255, 255, 255, 255, 255,				//Top vänster		(rad 0)       index 0-7
	255, 255, 255, 255, 255, 255, 255, 255,		//Top vänster + ett steg åt höger  (rad 0)	index 8-15
	255, 255, 255, 255, 255, 255, 255, 255,				//Top vänster + 2 steg åt höger 	(rad 0)		index 16-23
	255, 255, 255, 255, 255, 255, 255, 255,					//Top vänster + 3 steg åt höger     (rad 0)	index 24- 31
	255, 255, 255, 255, 255, 255, 255, 255,						//en rad ner, vänster                       (rad 1)	 index 32-39
	255, 255, 255, 255, 255, 255, 255, 255,			//index 40-47
	255, 255, 255, 255, 255, 255, 255, 255,			//index 48-55
	255, 255, 255, 255, 255, 255, 255, 255,			//index 56-63
	255, 255, 255, 255, 255, 255, 255, 255,			//en rad ner 	(rad 2) index 64-71
	255, 255, 255, 255, 255, 255, 255, 255,			//index 72-79
	255, 255, 255, 255, 255, 255, 255, 255,			//index 80-87
	255, 255, 255, 255, 255, 255, 255, 255,			//index 88-95
	255, 255, 255, 255, 255, 255, 255, 255,			//en rad ner  (rad 3) index 96-103
	255, 255, 255, 255, 255, 255, 255, 255,			//index 104-111
	255, 255, 255, 255, 255, 255, 255, 255,			//index 112 - 119
	255, 255, 255, 255, 255, 255, 255, 255,			//index 120-127
};

void delay(int cyc) {
	int i;
	for(i = cyc; i > 0; i--);
}

uint8_t spi_send_recv(uint8_t data) {
	while(!(SPI2STAT & 0x08));
	SPI2BUF = data;
	while(!(SPI2STAT & 0x01));
	return SPI2BUF;
}

void display_init() {
	DISPLAY_COMMAND_DATA_PORT &= ~DISPLAY_COMMAND_DATA_MASK;
	delay(10);
	DISPLAY_VDD_PORT &= ~DISPLAY_VDD_MASK;
	delay(1000000);
	
	spi_send_recv(0xAE);
	DISPLAY_RESET_PORT &= ~DISPLAY_RESET_MASK;
	delay(10);
	DISPLAY_RESET_PORT |= DISPLAY_RESET_MASK;
	delay(10);
	
	spi_send_recv(0x8D);
	spi_send_recv(0x14);
	
	spi_send_recv(0xD9);
	spi_send_recv(0xF1);
	
	DISPLAY_VBATT_PORT &= ~DISPLAY_VBATT_MASK;
	delay(10000000);
	
	spi_send_recv(0xA1);
	spi_send_recv(0xC8);
	
	spi_send_recv(0xDA);
	spi_send_recv(0x20);
	
	spi_send_recv(0xAF);
}

void display_string(int line, char *s) {
	int i;
	if(line < 0 || line >= 4)
		return;
	if(!s)
		return;
	
	for(i = 0; i < 16; i++)
		if(*s) {
			textbuffer[line][i] = *s;
			s++;
		} else
			textbuffer[line][i] = ' ';
}

void display_image(int x, uint8_t *data) {
	int i, j;
	
	for(i = 0; i < 4; i++) {
		DISPLAY_COMMAND_DATA_PORT &= ~DISPLAY_COMMAND_DATA_MASK;
		spi_send_recv(0x22);
		spi_send_recv(i);
		
		spi_send_recv(x & 0xF);
		spi_send_recv(0x10 | ((x >> 4) & 0xF));
		
		DISPLAY_COMMAND_DATA_PORT |= DISPLAY_COMMAND_DATA_MASK;
		
		for(j = 0; j < 32; j++)
			spi_send_recv(~data[i*32 + j]);
	}
}

void display_update() {
	int i, j, k;
	int c;
	for(i = 0; i < 4; i++) {
		DISPLAY_COMMAND_DATA_PORT &= ~DISPLAY_COMMAND_DATA_MASK;
		spi_send_recv(0x22);
		spi_send_recv(i);
		
		spi_send_recv(0x0);
		spi_send_recv(0x10);
		
		DISPLAY_COMMAND_DATA_PORT |= DISPLAY_COMMAND_DATA_MASK;
		
		for(j = 0; j < 16; j++) {
			c = textbuffer[i][j];
			if(c & 0x80)
				continue;
		
			for(k = 0; k < 8; k++)
				spi_send_recv(font[c*8 + k]);
		}
	}
}
//Vår egen kod börjar här

/* Intializes random number generator */
//srandvariabel((unsigned) time(&t));

/* initsieras till startvärdet för vår snake */
int currentRow = 1;
int currentColPos = 0;
int currentCol = 0;
int snakelen = 1;
char direction = 'E';
int randvariabel = 7345;
int lastfoodpos[3] = {0,0,0};
int hasEaten = 0;


int food[3] = {0,0,0}; //food[0] = x-col koordinat, food[1] = col koordinat , food[2] = y koordinat

int snake[15][3];

int randomTal()
{
	srand((unsigned) randvariabel);
	
	int retur = rand() % 4;
	
    return (retur);	
}

void addSnake(){
	if(hasEaten == 1)
	{
		hasEaten = 0;
		int i = 0;
		for(i;i<4;i++)
		{
			snake[snakelen-1][i] = snake[snakelen-2][i];
		}
	}
}


void has_eaten()
{
	if(snake[0][0] == food[0] && snake[0][1] == food[1] && snake[0][2] == food[2])  //Endast huvudet som kan käka
	{
		clear_food();
		create_food();
		snakelen++; 
		hasEaten = 1;
	}
}

void button_interrupt()
{
	randvariabel++;
	volatile int p = ((PORTD >> 5) & 0x3);
	switch (p)
	{
		case (2):
			move_left();
			break;
		case (1):
			move_right();
			break;
		case (3):
			break;
	}
	snake[0][0] = currentColPos;
	snake[0][1] = currentCol;
	snake[0][2] = currentRow;
	
	has_eaten();
    IFSCLR(1) = 0x1; //clear flag
}

void create_food()
{
	int j = 0;
	while (j<4){
		lastfoodpos[j] = food[j];
		j++;
	}
	
	while(lastfoodpos[0] == food[0] && lastfoodpos[1] == food[1] && lastfoodpos[2] == food[2])
	{
	int x0 = randomTal();
	randvariabel++;			//Ändra seed
	int x1 = randomTal();
	randvariabel++;			//Ändra seed
	int x2 = randomTal();
	food[0] = (x0);		//x-col koordinat
	food[1] = (x1);		//col koordinat
	food[2] = (x2);		//y koordinat
	}

	
	int i = 0;
	while(i < 8)
	{
		foodar[((food[2]*32) + (food[0]*8)) + i] = 0;
		i++;
	}
}

void clear_food()
{
	int i = 0;
	while(i < 8)
	{
		foodar[((food[2]*32) + (food[0]*8)) + i] = 255;
		i++;
	}
	//display_image(food[1]*32,foodar);
}

void draw_snake()
{
	int drawBoth = 1;
	int i = 0;
	while(i < 8) 
	{
		icon[((currentRow*32) + (currentColPos*8)) + i] = 0;   //*32, då de finns index per rad(4 totalt), *8 då det finns 8 index per row(16 totalt)
				
		if(food[1] == currentCol)							//Om maten och ikonen är i samma kolumn
		{
			icon[((food[2]*32) + (food[0]*8)) + i] = 0;		//så ritar vi in maten i ikonens bild
			drawBoth = 0;
		}
		i++;
	}
	
	display_image((currentCol*32), icon);					//Ritar ut ikonen (och möjligtvis maten om de är i samma kolumn)
	
	if(drawBoth) 											//blir endast true om maten och ikonen är i olika kolumner
		display_image(food[1]*32, foodar);
	
	int j = 0;
	while(j < 8)
	{
		icon[((food[2]*32) + (food[0]*8) + j)] = 255;		//Sudda bort maten från ikonens bild (ifall att vi t.ex. byter kolumn). Utan detta så kan kvarrester uppstå
		j++;
	}
}

void clr_snake()
{
	int i = 0;
	while(i < 8)
	{
		icon[((currentRow*32) + (currentColPos*8)) + i] = 255;   //*32, då de finns index per rad(4 totalt), *8 då det finns 8 index per row(16 totalt)
		i++;
	}
	display_image((currentCol*32),icon);
}

void move_north()
{	
	clr_snake();
	currentRow--;
	if(currentRow < 0)
	{
		currentRow = 3; 
	}
	snake[0][2] = currentRow;
	draw_snake();
	
}
void move_south()
{	
	clr_snake();
	currentRow++;
	if(currentRow > 3)
	{	
		currentRow = 0;
	}
	snake[0][2] = currentRow;
	draw_snake();
}

void move_west()
{	
	clr_snake();
	currentColPos--;
	if(currentColPos < 0)
	{
		currentCol--;
		currentColPos = 3; 
	}
	if(currentCol < 0)
	{
		currentCol = 3;
	}
	snake[0][0] = currentColPos;
	snake[0][1] = currentCol;
	draw_snake();
}

void move_east(void)
{		//Höger i detta demo	
	clr_snake();
	currentColPos++;
	if(currentColPos > 3)	//Hanterar slut på columnen 
	{	
	currentCol++;
	currentColPos = 0;
	}
	if(currentCol > 3){
		currentCol = 0;
	}
	snake[0][0] = currentColPos;
	snake[0][1] = currentCol;
	draw_snake();
}

void move_fwd()
{
	switch(direction)
	{
		case 'N':
			move_north();
			break;
		case 'S':
			move_south();
			break;
		case 'W':
			move_west();
			break;
		case 'E':
			move_east();
			break;
	}
	
	snake[0][0] = currentColPos;
	snake[0][1] = currentCol;
	snake[0][2] = currentRow;
	
	has_eaten();
}

void move_left()
{
	switch(direction)
	{
		case 'N':
			move_west();
			direction = 'W';
			break;
		case 'S':
			move_east();
			direction = 'E';
			break;
		case 'W':
			move_south();
			direction = 'S';
			break;
		case 'E':
			move_north();
			direction = 'N';
			break;
	}		
}

void move_right(void)
{
	switch(direction)
	{
		case 'N':
			move_east();
			direction = 'E';
			break;
		case 'S':
			move_west();
			direction = 'W';
			break;
		case 'W':
			move_north();
			direction = 'N';
			break;
		case 'E':
			move_south();
			direction = 'S';
			break;
	}
}

void button_setup(void) {
    /* Change notice (CN) pins provide PIC32 abivlity to generate interrupts to processor in response
     * to the change of state on selected input pins (corresopnding TRISxbits must be 1).
     * Enabled pin values are compared with values sampled during the last read operation of the designated PORTx reg.
     * If pin value is different from last value read, mismatch condition is generated. The enabled pins all generate
     * the same interrupt signal (ORed together). Enabled pins are "sampled" on every internal system clock cycle, SYSCLK.
     * - Section 12 i/o. 12.3.3
     *   1. Disable CPU itnerrupts
     *   2. Set desired CN I/O pin as an input by setting TRISx corresponding to 1
*/    TRISDSET = 0xf0;  /*
     *   3. Enable CN module by setting the ON bit (CNCONx<15>) = 1
     */ CNCONSET = 0x8000; /*
     *   4. Enable individual CN input pins, enable optional pull-ups or pull-downs, dvs CN14, Cn15, CN16 (se chipkit data sheet för 34,35,36pin)
     */ CNENSET = 0x1C000; /*
     *   5. Read the corresponding PORTx registers to clear the CN interrupt
     *   6. Configure the CN interrupt priority bits, CNIP<2:0> (IPC6<20:18>) and subpriority bits CNIS<1:0> (IPC6<17:16>
     */ IPC(6) |= 0x1F0000; /*
     *   7. Clear the CN interrupt flag bit by setting CNIF bit (IFS1<0> = 0
     */ IFSCLR(1) = 0x1; /*
     *   8. Configure CN pin interrupt for a specific edge detect using EDGEDETECT bit in CNCONx reg, and set up. We dont EDGEDETECT? So skip, or set 0. CNCONbit11
     *   edge control using the CNENx/CNNEx bits (?)
     *   9. Enable CN interrupt enable bit, by setting CNIE bit (IEC1<0>) = 1
     */ IECSET(1) = 0x1; /*
     *   10. Enable CPU interrupts (noteself: use assembly stuff from lab3)
     */ enable_interrupt();
}


int main(void) {
	spi_setup();
	button_setup();
	welcomeScreen();
	/* Initisiera lampor*/ 
	//initiera trise
	volatile int * trise = (volatile int *) 0xbf886100;
	*trise = *trise &0xFF00;  //Clearar de 8 ls bitarna. Då de 16 ls bitarna används för att kontrollera outputs.

	display_image(0, icon);		//Inten flyttar 1 steg åt höger varje 16e
	
	/*snake[0][0] = currentColPos;
	snake[0][1] = currentCol;
	snake[0][2] = currentRow;*/
	
	create_food();	

	while(1)
	{
		move_fwd();
		addSnake();
		PORTE = snakelen;
		delay(2000000);
		randvariabel++;
	}
	
	for(;;) ;
	return 0;
}



