#!/usr/bin/expect -f

spawn sftp pi@192.168.1.96
expect "assword:"
send "raspberry\r"


expect ">"
send "cd home_automation\r"

expect ">"
send "put ../firebase_led.py\r"

expect ">"
send "exit\r"
interact