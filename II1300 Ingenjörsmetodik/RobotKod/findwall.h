#define wheels OUT_BC
#define USSensor IN_4
#define power 67

void FindWall() {

    SetSensorLowspeed(USSensor);
    int a[300], size, i;
    size = 300;

    OnFwdSync(wheels, power, -100);

    for(i=0; i<size; i++) {
        a[i] = SensorUS(USSensor);
    }

    OnFwd(wheels,0);

    int smallest = a[0];

    for(i=1; i<size; i++) {
        if(smallest > a[i]) {
            smallest = a[i];
          }
    }
    int counter = 0;
    OnFwdSync(wheels, power, -100);
    while(SensorUS(USSensor) != smallest || SensorUS(USSensor) > smallest) {
      counter++;
      if (counter == 300) {
        smallest++;
        counter = 0;
      }
    }

    OnFwd(wheels,0);
    Wait(1000);

}
