/**
  Author: Mario Perez Esteso <mario@geekytheory.com>
  Website: http://geekytheory.com
*/
#include <Wire.h>

#define SLAVE_ADDRESS 0x04
#define numberOfBytes 10 //Not sure if I am going to use this define
#define numberOfLEDS 3

char id = '1'; //Arduino identifier
int LEDC = 13, LEDB = 12 , LEDA = 11;
const int LEDS[3] = {11, 12, 13};

int number = 0;
int state = 0;
int index = 0;
int pos = 0;
char buffer[32];

void setup() {
  Serial.begin(9600);
  // Initialize I2C as slave
  Wire.begin(SLAVE_ADDRESS);

  // define callbacks for i2c communication
  Wire.onReceive(receiveData);
  Wire.onRequest(sendData);

  for (int i=11; i<14; i++) {
    pinMode(i, OUTPUT);
    digitalWrite(i, LOW);
  }
}

void loop() {
  delay(100);
}

// callback for received data
void receiveData(int byteCount){
  while (Wire.available() > 0) {
    // read the incoming byte:
    char inByte = Wire.read();
    // add to our read buffer
    buffer[pos++] = inByte;
    // check for delimiter
    if (inByte == 'f') {
      buffer[pos++] = '\0'; // delimit
      pos=0;
      manageMessage(buffer);
    } else {
      //Do nothing for now
    }
  }
}

void receiveSerial(){
  while (Serial.available() > 0) {
    // Read the incoming byte:
    char inByte = Serial.read();
    // Add to our read buffer
    if(inByte == 't') {
    buffer[pos++] = inByte;
    // Check for delimiter (f=EOF)
    while (inByte != 'f' && (Serial.available()>0)) {
      inByte = Serial.read();
      buffer[pos++] = inByte;
    }
    Serial.println(buffer);
    buffer[pos++] = '\0';
    pos=0;
  }
  }
}

void manageMessage(char buff[]) {
  int k=0;
  if(buff[0] == id) {
    for (int j = 2; k<numberOfLEDS; j+=2) {
        if(buff[j] == '0') {
          digitalWrite(LEDS[k], LOW);
        } else {
          digitalWrite(LEDS[k], HIGH);
        }
        k++;
      }
      //Simulation of temperature analog read
      String temp = "t 1 19.23 f";
      temp.toCharArray(buffer, 32);
    } else { //Need to implement a function to send data to other Arduinos
      printBuffer(buff);
      delay(500);
      receiveSerial();
    } 
}

void printBuffer(char buff[]) {
  for(int i=0; i<strlen(buff); i++) { //Could do this with a while
    Serial.print(buff[i]);
  }
}

// callback for sending data
void sendData() { 
  if(buffer[0]=='t') {
    Wire.write(buffer[index]);
    ++index;
    if (index >= 12) {
         index = 0;
    }
  }
}



