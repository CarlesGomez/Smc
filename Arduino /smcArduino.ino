#include <SPI.h>
#include "Adafruit_BLE_UART.h"

// Connect CLK/MISO/MOSI to hardware SPI
// e.g. On UNO & compatible: CLK = 13, MISO = 12, MOSI = 11
#define ADAFRUITBLE_REQ 10
#define ADAFRUITBLE_RDY 2     // This should be an interrupt pin, on Uno thats #2 or #3
#define ADAFRUITBLE_RST 9

Adafruit_BLE_UART BTLEserial = Adafruit_BLE_UART(ADAFRUITBLE_REQ, ADAFRUITBLE_RDY, ADAFRUITBLE_RST);

//Arrays for the 4 inputs**********************************************
String sensorValue[9] = {"0","1","2","3","4","5","6","7","8"};
int counter = 0;

void setup() {
  Serial.begin(9600);
  while(!Serial); // Leonardo/Micro should wait for serial init
  Serial.println(F("Adafruit Bluefruit Low Energy nRF8001 Print echo demo"));
  // BTLEserial.setDeviceName("NEWNAME"); /* 7 characters max! */

  BTLEserial.begin();

}

/**************************************************************************/
/*!
    Constantly checks for new events on the nRF8001
*/
/**************************************************************************/
aci_evt_opcode_t laststatus = ACI_EVT_DISCONNECTED;

void loop() {
  // Tell the nRF8001 to do whatever it should be working on.
  BTLEserial.pollACI();

  // Ask what is our current status
  aci_evt_opcode_t status = BTLEserial.getState();
  // If the status changed....
  if (status != laststatus) {
    // print it out!
    if (status == ACI_EVT_DEVICE_STARTED) {
        Serial.println(F("* Advertising started"));
    }
    if (status == ACI_EVT_CONNECTED) {
        Serial.println(F("* Connected!"));
    }
    if (status == ACI_EVT_DISCONNECTED) {
        Serial.println(F("* Disconnected or advertising timed out"));
    }
    // OK set the last status change to this one
    laststatus = status;
  }

  if (status == ACI_EVT_CONNECTED) {
    // Lets see if there's any data for us!
    if (BTLEserial.available()) {
      Serial.print("* "); Serial.print(BTLEserial.available()); Serial.println(F(" bytes available from BTLE"));
    }
     //Read arduino sensor values
      readSensors();
      //Send values to android
      sendAndroidValues();
    }
    delay(10000);
}

void readSensors()
{
      //read the analog in value to the sensor array
      //Array with length of array
      if(counter == 0)
      {
        //Moto on
        sensorValue[0] = "0";
        //Moto off
        sensorValue[1] = "0";
        //Moto in traction
        sensorValue[2] = "1";
        counter = counter + 1;
      } 
      else if(counter == 1)
      { 
        //Moto no traction
        sensorValue[0] = "0";
        //Moto vibracy
        sensorValue[1] = "5";
        //Moto battery
        sensorValue[2] = "6";
        //sensorValue[2] = String(analogRead(analogPin));
        counter = counter + 1;
        
      }
      else
      {
        //moto velocity
        sensorValue[0] = String(random(0,45));
        //moto acceleration
        sensorValue[1] = "7";
        //moto brake
        sensorValue[2] = "8";
        counter = 0;
      }
}

//sends the values from the sensor over serial to BT module
void sendAndroidValues()
{
    String s = "";
    for(int k=0; k<3; k++)
    {
      s= s + sensorValue[k] + ";";
    }
    s = s + ";";

      // We need to convert the line to bytes
      uint8_t sendbuffer[s.length()];
      s.getBytes(sendbuffer, s.length());
      char sendbuffersize = min(s.length(), s.length());

      // write the data
      BTLEserial.write(sendbuffer, sendbuffersize);
}
