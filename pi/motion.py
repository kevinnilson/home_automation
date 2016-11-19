import RPi.GPIO as GPIO
import datetime
import time
from gpiozero import LED

SLEEP_TIME_AFTER_MOTION = 10;
PIR_TIME_AFTER_MOTION = 5

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

PIR = 22
GPIO.setup(PIR, GPIO.IN)
led = LED(24)

EPOCH = datetime.datetime.utcfromtimestamp(0)
last_motion = EPOCH

while True:
    if (GPIO.input(PIR)):
        print("Motion Detected")
        last_motion = datetime.datetime.now()
        if not led.is_lit:
            led.on()
    else:
        print("No Motion Detected")

    timedelta_since_motion = (datetime.datetime.now() - last_motion)

    if (last_motion != EPOCH):
        print("seconds since last motion: " + str(timedelta_since_motion))

    if led.is_lit and timedelta_since_motion.total_seconds() > SLEEP_TIME_AFTER_MOTION - PIR_TIME_AFTER_MOTION:
        led.off()

    time.sleep(.1)
