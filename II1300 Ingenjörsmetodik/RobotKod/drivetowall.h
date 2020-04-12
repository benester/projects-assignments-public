#define wheels OUT_BC
#define USSensor IN_4

 void DriveToWall(){

     OnFwdSync(wheels,60,0);
     SetSensorLowspeed(USSensor);

     while(SensorUS(USSensor) > 30){
     }

     OnFwdSync(wheels,0,0);
}
