//PORTAL CREDITS SONG: STILL ALIVE

/* Controls:
 *
 * Orange []: Switch between showing and setting volume and speed
 * < and >: Change value
 */

//sheet music: http://www.scribd.com/doc/2183619/Portals-Still-Alive-Sheet-Music

byte volume = 2;
int speed = 90;

task menu()
{
  bool bolSetVolume = true;
  volume = Volume();

  TextOut(0, LCD_LINE8, StrCat("[]volume:", NumToStr(volume)), false);
  while(true)
  {
    Wait(175);
    if (!bolSetVolume)
    {
      if (ButtonPressed(BTNLEFT, false) && speed > 10)
      {
        speed = speed-10;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]speed:", NumToStr(speed)), false);
      }
      if (ButtonPressed(BTNRIGHT, false) && speed < 200)
      {
        speed = speed+10;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]speed:", NumToStr(speed)), false);
      }
      if (ButtonPressed(BTNCENTER, false))
      {
        bolSetVolume = true;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]volume:", NumToStr(volume)), false);
      }
    }
    else
    {
      if (ButtonPressed(BTNLEFT, false) && volume > 0)
      {
        volume = volume-1;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]volume:", NumToStr(volume)), false);
      }
      if (ButtonPressed(BTNRIGHT, false) && volume < 4)
      {
        volume = volume+1;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]volume:", NumToStr(volume)), false);
      }
      if (ButtonPressed(BTNCENTER, false))
      {
        bolSetVolume = false;
        ClearLine(8);
        TextOut(0, LCD_LINE8, StrCat("[]speed:", NumToStr(speed)), false);
      }
    }
  }
}

sub MyPlayTone(unsigned int frequency, unsigned int duration)
{
  PlayToneEx(frequency, duration*speed/100, volume, false);
}

sub MyWait(unsigned long ms)
{
  Wait(ms*speed/100);
}

sub ClearLyrics()
{
  TextOut(0,LCD_LINE1, "                ", false);
  TextOut(0,LCD_LINE2, "                ", false);
  TextOut(0,LCD_LINE3, "                ", false);
  TextOut(0,LCD_LINE4, "                ", false);
  TextOut(0,LCD_LINE5, "                ", false);
  TextOut(0,LCD_LINE6, "                ", false);
  TextOut(0,LCD_LINE7, "                ", false);
}

task GLaDOS()
{
  //#: c,f
  //This was a triumph.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "This was a      ", false); //16 chars/line
  TextOut(0,LCD_LINE2, "triumph.        ", false);
  MyPlayTone(784,200); //g3
  MyWait(240);
  MyPlayTone(740,200); //f#3
  MyWait(240);
  MyPlayTone(659,200); //e3
  MyWait(240);
  MyPlayTone(659,200); //e3
  MyWait(240);
  MyPlayTone(740,200); //f#3
  MyWait(240);
  MyWait(240); //wait 1/8
  MyWait(480); //wait 1/4
  MyWait(960); //wait 1/2

  //I'm making a note here, HUGE SUCCESS.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "I'm making a    ", false);
  TextOut(0,LCD_LINE2, "note here,      ", false);
  TextOut(0,LCD_LINE3, "                ", false);
  TextOut(0,LCD_LINE4, "                ", false);
  TextOut(0,LCD_LINE5, "HUGE SUCCESS.   ", false);
  MyWait(480);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);

  //It's hard to overstate my satisfaction.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "It's hard to    ", false);
  TextOut(0,LCD_LINE2, "overstate my    ", false);
  TextOut(0,LCD_LINE3, "satisfaction.   ", false);
  MyWait(240);
  MyWait(480);
  MyWait(480);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,400);
  MyWait(480);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(554,400);
  MyWait(480);
  MyPlayTone(587,600);
  MyWait(700);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyPlayTone(740,400);
  MyWait(480);
  MyWait(240);
  MyWait(960);

  //Aperture Science,
  ClearLyrics();
  TextOut(0,LCD_LINE1, "  Aperture      ", false);
  TextOut(0,LCD_LINE2, "     Science,   ", false);
  MyWait(480);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //we do what we must because we can.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "we do what we   ", false);
  TextOut(0,LCD_LINE2, "must            ", false);
  TextOut(0,LCD_LINE3, "                ", false);
  TextOut(0,LCD_LINE4, "   because      ", false);
  TextOut(0,LCD_LINE5, "                ", false);
  TextOut(0,LCD_LINE6, "       we can.  ", false);
  MyWait(480);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //for the good of all of us
  ClearLyrics();
  TextOut(0,LCD_LINE1, "for the good of ", false);
  TextOut(0,LCD_LINE2, "all of us       ", false);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(554,600);
  MyWait(700);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(240);

  //except the ones who are dead.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "except the ones ", false);
  TextOut(0,LCD_LINE2, "who are dead.   ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyWait(480);


  //But there's no sense crying over ev'ry mistake,
  //b:b
  ClearLyrics();
  TextOut(0,LCD_LINE1, "But there's no  ", false);
  TextOut(0,LCD_LINE2, "sense crying    ", false);
  TextOut(0,LCD_LINE3, "over ev'ry      ", false);
  TextOut(0,LCD_LINE4, "mistake,        ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(523,400);
  MyWait(480);

  //you just keep on trying till you run out of cake,
  ClearLyrics();
  TextOut(0,LCD_LINE1, "you just keep on", false);
  TextOut(0,LCD_LINE2, "trying till you ", false);
  TextOut(0,LCD_LINE3, "run out of cake,", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,400);
  MyWait(480);

  //and the Science gets done and you make a neat gun
  ClearLyrics();
  TextOut(0,LCD_LINE1, "and the Science ", false);
  TextOut(0,LCD_LINE2, "gets done and   ", false);
  TextOut(0,LCD_LINE3, "you make a neat ", false);
  TextOut(0,LCD_LINE4, "gun             ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(880,400);
  MyWait(480);
  MyPlayTone(784,400);
  MyWait(480);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(784,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);

  //for the people who are still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "for the people  ", false);
  TextOut(0,LCD_LINE2, "who are         ", false);
  TextOut(0,LCD_LINE4, "   still alive. ", false);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);

  MyWait(480);
  MyWait(960);
  MyWait(1920);
  MyWait(1920);


  //#:c,f
  //I'm not even angry.
  ClearLyrics();
  TextOut(0,LCD_LINE3, "I'm not even    ", false);
  TextOut(0,LCD_LINE4, "angry.          ", false);
  MyWait(480);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,300);
  MyWait(360);
  MyPlayTone(740,300);
  MyWait(360);
  MyWait(480);
  MyWait(960);

  //I'm being so sincere right now
  ClearLyrics();
  TextOut(0,LCD_LINE1, "I'm being so    ", false);
  TextOut(0,LCD_LINE2, "sincere right   ", false);
  TextOut(0,LCD_LINE3, "now             ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,600);
  MyWait(700);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(440,400);
  MyWait(480);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //even though you broke my heart
  ClearLyrics();
  TextOut(0,LCD_LINE1, "even though you ", false);
  TextOut(0,LCD_LINE2, "broke my heart  ", false);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(554,400);
  MyWait(480);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,600);
  MyWait(700);

  //and killed me
  ClearLyrics();
  TextOut(0,LCD_LINE3, "  and killed me ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //and tore me to pieces
  MyWait(480);
  MyWait(240);
  ClearLyrics();
  TextOut(0,LCD_LINE1, "and tore me to  ", false);
  TextOut(0,LCD_LINE2, "    pieces      ", false);
  MyPlayTone(494,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //and threw ev'ry piece into a fire.
  MyWait(480);
  MyWait(240);
  ClearLyrics();
  TextOut(0,LCD_LINE1, "and threw ev'ry ", false);
  TextOut(0,LCD_LINE2, "piece           ", false);
  TextOut(0,LCD_LINE4, "   into         ", false);
  TextOut(0,LCD_LINE6, "       a fire.  ", false);
  MyPlayTone(494,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //As they burned it hurt because I was so happy for you!
  ClearLyrics();
  TextOut(0,LCD_LINE1, "As they burned  ", false);
  TextOut(0,LCD_LINE2, "it hurt because ", false);
  TextOut(0,LCD_LINE4, "I was so happy  ", false);
  TextOut(0,LCD_LINE5, "   for you!     ", false);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(554,400);
  MyWait(480);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyWait(480);


  //b:b
  //Now these points of data make a beatiful line;
  ClearLyrics();
  TextOut(0,LCD_LINE1, "Now these points", false);
  TextOut(0,LCD_LINE2, "of data make a  ", false);
  TextOut(0,LCD_LINE3, "beautiful line; ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(523,400);
  MyWait(480);

  //and we're out of beta, we're releasing on time.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "and we're out of", false);
  TextOut(0,LCD_LINE2, " beta,          ", false);
  TextOut(0,LCD_LINE4, "we're releasing ", false);
  TextOut(0,LCD_LINE5, "on time.        ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);

  //So I'm GLaD. I got burned. Think of all the things we learned
  ClearLyrics();
  TextOut(0,LCD_LINE1, "So I'm GLaD.    ", false);
  TextOut(0,LCD_LINE2, "I got burned.   ", false);
  TextOut(0,LCD_LINE4, "Think of all the", false);
  TextOut(0,LCD_LINE5, "things we       ", false);
  TextOut(0,LCD_LINE6, "learned         ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(880,400);
  MyWait(480);
  MyPlayTone(784,400);
  MyWait(480);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,400);
  MyWait(480);

  //For the people who are still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "for the people  ", false);
  TextOut(0,LCD_LINE2, "who are         ", false);
  TextOut(0,LCD_LINE4, "   still alive. ", false);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);
  MyWait(480);
  MyWait(960);
  MyWait(1920);
  MyWait(1920);


  //#:c,f
  //[One last thing:] Go ahead and leave me.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "One last thing: ", false);
  TextOut(0,LCD_LINE3, "Go ahead and    ", false);
  TextOut(0,LCD_LINE4, "leave me.       ", false);
  MyWait(960);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,100);
  MyWait(120);
  MyPlayTone(740,100);
  MyWait(120);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //I think I'd prefer to stay inside.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "I think I'd     ", false);
  TextOut(0,LCD_LINE2, "prefer to stay  ", false);
  TextOut(0,LCD_LINE3, "inside.         ", false);
  MyWait(480);
  MyWait(240);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //Maybe you'll find someone else to help you.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "Maybe you'll    ", false);
  TextOut(0,LCD_LINE2, "find someone    ", false);
  TextOut(0,LCD_LINE3, "else to help    ", false);
  TextOut(0,LCD_LINE4, "you.            ", false);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(554,400);
  MyWait(480);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,600);
  MyWait(700);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //Maybe Black Mesa...
  ClearLyrics();
  TextOut(0,LCD_LINE1, "Maybe Black Mesa", false);
  TextOut(0,LCD_LINE2, "      ...       ", false);
  MyWait(960);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //THAT WAS A JOKE. HA HA. FAT CHANCE
  ClearLyrics();
  TextOut(0,LCD_LINE1, "THAT WAS A JOKE.", false);
  TextOut(0,LCD_LINE3, "  HA HA.        ", false);
  TextOut(0,LCD_LINE5, "     FAT CHANCE.", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(440,400);
  MyWait(480);
  MyWait(240);
  MyWait(480);
  MyWait(960);

  //Anyway. This cake is great,
  ClearLyrics();
  TextOut(0,LCD_LINE1, "Anyway. This    ", false);
  TextOut(0,LCD_LINE2, "cake is great,  ", false);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(784,600);
  MyWait(700);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(554,400);
  MyWait(480);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyWait(240);

  //it's so delicious and moist.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "it's so         ", false);
  TextOut(0,LCD_LINE2, "delicious and   ", false);
  TextOut(0,LCD_LINE3, "moist.          ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyWait(480);


  //b:b
  //Look at me still talking when there's Science to do.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "Look at me still", false);
  TextOut(0,LCD_LINE2, "talking when    ", false);
  TextOut(0,LCD_LINE3, "there's Science ", false);
  TextOut(0,LCD_LINE4, "to do.          ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(523,400);
  MyWait(480);

  //When I look out there it makes me GLaD I'm not you.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "When I look out ", false);
  TextOut(0,LCD_LINE2, "there it makes  ", false);
  TextOut(0,LCD_LINE3, "me GLaD I'm not ", false);
  TextOut(0,LCD_LINE4, "you.            ", false);
  MyPlayTone(440,200);
  MyWait(240);
  MyPlayTone(466,200);
  MyWait(240);
  MyPlayTone(523,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(698,400);
  MyWait(480);
  MyPlayTone(698,400);
  MyWait(480);

  //I've experiments to run.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "I've experiments", false);
  TextOut(0,LCD_LINE2, "to run.         ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(932,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(784,400);
  MyWait(480);

  //There is research to be done
  ClearLyrics();
  TextOut(0,LCD_LINE1, "There is        ", false);
  TextOut(0,LCD_LINE2, "research to be  ", false);
  TextOut(0,LCD_LINE3, "done            ", false);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,400);
  MyWait(480);

  //on the people who are still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "on the people   ", false);
  TextOut(0,LCD_LINE2, "who are         ", false);
  TextOut(0,LCD_LINE4, "    still alive.", false);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(523,200);
  MyWait(240);
  MyPlayTone(587,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(698,200);
  MyWait(240);
  MyPlayTone(659,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);
  Wait(480);
  Wait(480);



  //#:c,f
  //[PS:] And believe me I am still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "PS:             ", false);
  TextOut(0,LCD_LINE3, "And believe me  ", false);
  TextOut(0,LCD_LINE4, "I am            ", false);
  TextOut(0,LCD_LINE6, "    still alive.", false);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(988,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,400);
  MyWait(480);
  MyWait(700);
  MyWait(480);
  MyWait(240);

  //[PPS:] I'm doing science and I'm still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "PPS:            ", false);
  TextOut(0,LCD_LINE3, "I'm doing       ", false);
  TextOut(0,LCD_LINE4, "science and I'm ", false);
  TextOut(0,LCD_LINE6, "    still alive.", false);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(988,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);
  MyWait(480);
  MyWait(240);

  //[PPPS:] I feel FANTASTIC and I'm still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "PPPS:           ", false);
  TextOut(0,LCD_LINE3, "I feel FANTASTIC", false);
  TextOut(0,LCD_LINE4, "and I'm         ", false);
  TextOut(0,LCD_LINE6, "    still alive.", false);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(988,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);
  MyWait(480);
  MyWait(480);

  //[FINAL THOUGHT:] While you're dying I'll be still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "FINAL THOUGHT:  ", false);
  TextOut(0,LCD_LINE3, "While you're    ", false);
  TextOut(0,LCD_LINE4, "dying I'll be   ", false);
  TextOut(0,LCD_LINE6, "    still alive.", false);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(988,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,400);
  MyWait(480);
  MyWait(480);
  MyWait(240);

  //[FINAL THOUGHT PS:]And when you're dead I will be still alive.
  ClearLyrics();
  TextOut(0,LCD_LINE1, "FINAL THOUGHT PS", false);
  TextOut(0,LCD_LINE3, "And when you're ", false);
  TextOut(0,LCD_LINE4, "dead I will be  ", false);
  TextOut(0,LCD_LINE6, "    still alvie.", false);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(988,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(587,400);
  MyWait(480);
  MyPlayTone(659,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,400);
  MyWait(480);
  MyWait(480);
  MyWait(240);

  //STILL ALIVE.
  ClearLyrics();
  TextOut(0,LCD_LINE4, " STILL ALIVE.   ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(880,200);
  MyWait(240);
  MyPlayTone(880,600);
  MyWait(700);
  MyWait(480);
  MyWait(240);

  //STILL ALIVE.
  ClearLyrics();
  TextOut(0,LCD_LINE4, " STILL ALIVE.   ", false);
  MyPlayTone(784,200);
  MyWait(240);
  MyPlayTone(740,200);
  MyWait(240);
  MyPlayTone(740,600);
  MyWait(700);
  MyWait(480);
  MyWait(960);
}


