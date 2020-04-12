#define wheels OUT_BC
#define left OUT_B

//KRÄVER NY LÖSNING
void Rotera90H() {

  Wait(100);
  RotateMotor(left,80,410);
  OnFwdSync(wheels,0,0);

}


void Rotera90V() {

  Wait(100);
  RotateMotor(left,80,-392);
  OnFwdSync(wheels,0,0);

}
