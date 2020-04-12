#define arm OUT_A

void ThrowBook() {

  OnFwd(arm,100);
  Wait(500);
  OnFwd(arm,-100);
  Wait(500);
  OnFwd(arm,0);

}
