#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Author: Mario Pérez Esteso <mario@geekytheory.com>
# Web: geekytheory.com

import serial
import time
import MySQLdb
import smbus # This import is for I2C communication

# For Raspberry Pi version 1, use "bus = smbus.SMBus(0)"
# I use Model B so I start the bus with:
bus = smbus.SMBus(1)

# This is the address we setup in the Arduino program
address = 0x04

# Text variable for debugging
txt=''

number_arduinos = 2

# Define parameters of the database
hostname="localhost"
dbname="inalambricas"
user="root"
password="pass.."

# Connect to database
db = MySQLdb.connect(hostname, user, password, dbname)
cursor = db.cursor()

def writeToArduino(value):
	for n in value:
		bus.write_byte(address, ord(n))
	return -1

def updateTemp(myString):
	try:
		values = myString.split()
		sql = "UPDATE Devices SET TEMPERATURE="+str(values[2])+" WHERE ID="+str(values[1])+""
		cursor.execute(sql)
	except:
		print "Read Data Error"
	return 0

def readChar():
	readedChar = bus.read_byte(address)
	return chr(readedChar)

while True:
	for i in range(1, 3):
		sql = "SELECT LEDA, LEDB, LEDC FROM Devices WHERE ID="+str(i)+""
		cursor.execute(sql)
		rows = cursor.fetchall()
		msg =str(i) + " "
		for j in range(0, 3):
			msg+= str(rows[0][j]) + " "

		msg=msg+"f" #f = EOL (End Of Line)
		print msg 

		# Send the result of the query to Arduino 
		# and clean the message variable
		writeToArduino(msg)
		msg = ""

		# Let the Arduinos communicate :)
		time.sleep(0.5)

		while True:
			data = ""
			for i in range(0, 12):
				data += readChar()
			print data
			updateTemp(data+"")
			time.sleep(1);
			break
		
		time.sleep(3)
db.close()
arduino.close()

