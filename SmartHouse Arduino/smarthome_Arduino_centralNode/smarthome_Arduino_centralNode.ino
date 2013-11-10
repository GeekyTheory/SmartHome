/**
  Author: Mario Perez Esteso <mario@geekytheory.com>
  Website: http://geekytheory.com
*/

#define numberOfBytes 10
#define numberOfLEDS 3

char id = '1'; //Arduino identifier
int LEDC = 13, LEDB = 12 , LEDA = 11;
const int LEDS[3] = {11, 12, 13};

void setup () {
  Serial.begin(9600);
  for (int i=11; i<14; i++) {
    pinMode(i, OUTPUT);
    digitalWrite(i, LOW);
  }
}

void loop() {
  char command[numberOfBytes];
  int pos = 0; // position in read buffer
  while (Serial.available() > numberOfBytes) {
    if (Serial.read() == 0x00) { //send a 0 before your string as a start byte
      for (int i=0; i<numberOfBytes; i++) {
        command[i] = Serial.read();
      }
      manageMessage(command);
    }
  }
}

  
void printBuffer(char buff[]) {
  for(int i=0; i<numberOfBytes; i++) {
    Serial.print(buff[i]);
  }
  Serial.println("");
}
void manageMessage(char buff[]) {
  int k=0;
  if(strlen(buff) == numberOfBytes-1) {
  if(buff[0] == id) {
    for (int j = 2; k<numberOfLEDS; j+=2) {
      Serial.print(buff[j]);
      Serial.println(LEDS[k]);
        if(buff[j] == '0') {
          digitalWrite(LEDS[k], LOW);
        } else {
          digitalWrite(LEDS[k], HIGH);
        }
        k++;
      }
    } else { //Need to implement a function to send data to other Arduinos
      Serial.print("Send to ");
      Serial.print(buff[0]);
      Serial.print(" the following message: ");
      printBuffer(buff);
    }
  }
}
