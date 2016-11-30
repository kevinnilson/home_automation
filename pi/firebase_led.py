import urllib
import time
import sys
import json
import requests
import exceptions
import datetime
from gpiozero import LED, Button

ledR = LED(17)
ledG = LED(27)
ledB = LED(22)

ledRBulb = LED(21)
ledGBulb = LED(20)
ledBBulb = LED(16)

URL = "https://ledcolor-c7c49.firebaseio.com/.json"

redLedState = False
greenLedState = False
blueLedState = False

while True:
    try:
        req_time = datetime.datetime.now()
        print "making request " + URL
        response = requests.get(URL).text

        print("response: (" + str((datetime.datetime.now() - req_time)) + ")" + response)
        colors = json.JSONDecoder().decode(response)

        red = colors["red"]
        green = colors["green"]
        blue = colors["blue"]

        if red != redLedState:
            if red:
                print "turning red on"
                ledR.on()
                ledRBulb.on()
            else:
                print "turning red off"
                ledR.off()
                ledRBulb.off()
        else:
            print "ignore - no changes in red"

        if green != greenLedState:
            if green:
                print "turning green on"
                ledG.on()
                ledGBulb.on()
            else:
                print "turning green off"
                ledG.off()
                ledGBulb.off()
        else:
            print "ignore - no changes in green"

        if blue != blueLedState:
            if blue:
                print "turning blue on"
                ledB.on()
                ledBBulb.on()
            else:
                print "turning red off"
                ledB.off()
                ledBBulb.off()
        else:
            print "ignore - no changes in blue"

        redLedState = red
        greenLedState = green
        blueLedState = blue

    except exceptions.TypeError as ex:
        print(ex)
    except:
        print "Unexpected error:", sys.exc_info()[0]
        time.sleep(1)

    time.sleep(.25)
