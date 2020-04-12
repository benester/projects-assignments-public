/*
  Based on Blink without Delay

  Turns on and off severala digital pins,
  without using the delay() function. This means that other code can run at the
  same time without being interrupted by the LED code.

  The circuit:
  - Use pins 8 to 13 (including on-board LED on 13)
  - Note: Most Arduinos have an on-board LED you can control. On the UNO, MEGA
    and ZERO it is attached to digital pin 13, on MKR1000 on pin 6. LED_BUILTIN
    is set to the correct LED pin independent of which board is used.
    If you want to know what pin the on-board LED is connected to on your
    Arduino model, check the Technical Specs of your board at:
    https://www.arduino.cc/en/Main/Products

  created 2005
  by David A. Mellis
  modified 8 Feb 2010
  by Paul Stoffregen
  modified 11 Nov 2013
  by Scott Fitzgerald
  modified 9 Jan 2017
  by Arturo Guadalupi
  modified 2018
  by Carl-Mikael Zetterling 

  http://www.arduino.cc/en/Tutorial/BlinkWithoutDelay
*/

// constants won't change. Used here to set a pin number:
const int ledPin5 =  LED_BUILTIN;// the number of the LED pin
const int ledPin4 = 12;
const int ledPin3 = 11;
const int ledPin2 = 10;
const int ledPin1 = 9;
const int ledPin0 = 8;

// Variables will change:
int ledState5 = LOW;             // ledState used to set the LED
int ledState4 = LOW;
int ledState3 = LOW;
int ledState2 = LOW;
int ledState1 = LOW;
int ledState0 = LOW;

// Generally, you should use "unsigned long" for variables that hold time
// The value will quickly become too large for an int to store
unsigned long previousMillis = 0;        // will store last time LED was updated

// constants won't change:
const long interval = 200;           // interval at which to blink led0 (milliseconds)

void setup() {
  // set the digital pins as outputs:
  pinMode(ledPin5, OUTPUT);
  pinMode(ledPin4, OUTPUT);
  pinMode(ledPin3, OUTPUT);
  pinMode(ledPin2, OUTPUT);
  pinMode(ledPin1, OUTPUT);
  pinMode(ledPin0, OUTPUT);
}

void loop() {
  // here is where you'd put code that needs to be running all the time.

  // check to see if it's time to blink the LED; that is, if the difference
  // between the current time and last time you blinked the LED is bigger than
  // the interval at which you want to blink the LED.
  unsigned long currentMillis = millis();

  if (currentMillis - previousMillis >= interval) {
    // save the last time you blinked the LED
    previousMillis = currentMillis;

    // if the LED is off turn it on and vice-versa:
    if (ledState0 == LOW) {
      ledState0 = HIGH; // toggle and be done
    } else {
      ledState0 = LOW;
      if (ledState1 == LOW) {
              ledState1 = HIGH; // toggle and be done
      } else {
        ledState1 = LOW;
        if (ledState2 == LOW) {
                ledState2 = HIGH; // toggle and be done
        } else {
          ledState2 = LOW;
          if (ledState3 == LOW) {
                  ledState3 = HIGH; // toggle and be done
          } else {
            ledState3 = LOW;
            if (ledState4 == LOW) {
                    ledState4 = HIGH; // toggle and be done
            } else {
              ledState4 = LOW;
              if (ledState5 == LOW) {
                      ledState5 = HIGH; // toggle and be done
              } else {
                ledState5 = LOW;
              }
            }
          }
        }
      }
    }
  
  
    // set the LED with the ledState of the variable:
    digitalWrite(ledPin5, ledState5);
    digitalWrite(ledPin4, ledState4);
    digitalWrite(ledPin3, ledState3);
    digitalWrite(ledPin2, ledState2);
    digitalWrite(ledPin1, ledState1);
    digitalWrite(ledPin0, ledState0);
  }
}
