'''
	Author: Mario Pérez Esteso <mario@geekytheory.com>
	Website: http://geekytheory.com
'''
import serial
import time
import MySQLdb

arduino=serial.Serial('/dev/ttyACM0',baudrate=9600, timeout = 3.0)
arduino.open()
txt=''
number_arduinos = 2
hostname="localhost"
dbname="inalambricas"
user="root"
password="pass.."
db = MySQLdb.connect(hostname, user, password, dbname)
cursor = db.cursor()

while True:
	for i in range(1, 3):
		sql = "SELECT LEDA, LEDB, LEDC FROM Devices WHERE ID="+str(i)+""
		cursor.execute(sql)
		rows = cursor.fetchall()
		msg = str(i) + " "
		for j in range(0, 3):
			msg+= str(rows[0][j]) + " "
		msg="\0"+msg+"f\0" #f = EOL (End Of Line)
		print msg 
		arduino.write(msg)
		msg = ""		
		time.sleep(3)
		
db.close()
arduino.close()