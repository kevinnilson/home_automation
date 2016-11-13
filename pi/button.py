import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

GPIO.setup(17,GPIO.IN)

input = GPIO.input(17)

while True:
  if (GPIO.input(17)):
    print("Button Pressed")


# from gpiozero import LED, Button
# from time import sleep
# import random
#
# ledR = LED(17)
# ledG = LED(27)
# ledB = LED(22)
#
# while True:
#     color = random.choice(['r', 'g', 'b'])
#     choice = random.choice([0, 1])
#
#     print(color, choice)
#
#     if (color == 'r'):
#         if (choice == 0):
#             ledR.off()
#         else:
#             ledR.on()
#
#     if (color == 'g'):
#         if (choice == 0):
#             ledG.off()
#         else:
#             ledG.on()
#
#
#     if (color == 'b'):
#         if (choice == 0):
#             ledB.off()
#         else:
#             ledB.on()
#     sleep(.01)