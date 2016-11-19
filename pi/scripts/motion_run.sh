#!/usr/bin/expect -f

spawn ssh pi@192.168.1.101

expect "assword:"
send "raspberry\r"

expect "pi@raspberrypi"
send "cd home_automation\r"

expect "pi@raspberrypi"
send "python motion.py\r"

expect "pi@raspberrypi"
send "exit\r"

interact
exit

