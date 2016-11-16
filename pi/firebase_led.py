import urllib
import time
import sys
import json
import requests
import exceptions
from gpiozero import LED, Button

ledR = LED(17)
ledG = LED(27)
ledB = LED(22)

ledRBulb = LED(21)
ledGBulb = LED(20)
ledBBulb = LED(16)

url = "https://ledcolor-c7c49.firebaseio.com/.json"

while True:
    try:

        response = requests.get(url).text

        print("response:" + response)
        colors = json.JSONDecoder().decode(response)

        red = colors["red"]
        green = colors["green"]
        blue = colors["blue"]

        if red:
            ledR.on()
            ledRBulb.on()

        else:
            ledR.off()
            ledRBulb.off()

        if green:
            ledG.on()
            ledGBulb.on()
        else:
            ledG.off()
            ledGBulb.off()

        if blue:
            ledB.on()
            ledBBulb.on()
        else:
            ledB.off()
            ledBBulb.off()




    except exceptions.TypeError as ex:
        print(ex)
    except:
        print "Unexpected error:", sys.exc_info()[0]
        time.sleep(.5)

    time.sleep(.1)
