#!/usr/bin/expect -f

spawn ssh pi@192.168.168.7

expect "assword:"
send "raspberry\r"

expect "pi@raspberrypi"
send "cd home_automation\r"

expect "pi@raspberrypi"
send "python firebase_led.py\r"

expect "pi@raspberrypi"
send "exit\r"

interact

exit