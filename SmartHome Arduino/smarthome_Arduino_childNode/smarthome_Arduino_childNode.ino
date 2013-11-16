/**
  Author: Mario Perez Esteso <mario@geekytheory.com>
  Website: http://geekytheory.com
*/

#define numberOfBytes 10
#define numberOfLEDS 3

int pos = 0; // Position in read buffer
char buffer[32];

char id = '2'; //Arduino identifier
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
  delay(100);
  while (Serial.available() > 0) {
    // Read the incoming byte:
    char inByte = Serial.read();
    // Add to our read buffer
    buffer[pos++] = inByte;
    // Check for delimiter
    if (inByte == 'f') {
      buffer[pos++] = '\0'; // delimit
      pos=0;
      manageMessage(buffer);
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
      Serial.print("t 2 22.2 f");
    }
  
}

void printBuffer(char buff[]) {
  for(int i=0; i<strlen(buff); i++) { //Could do this with a while
    Serial.print(buff[i]);
  }
}
