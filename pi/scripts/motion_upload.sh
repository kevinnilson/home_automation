#!/usr/bin/expect -f

spawn sftp pi@192.168.168.9
expect "assword:"
send "raspberry\r"

expect ">"
send "mkdir -p home_automation\r"

expect ">"
send "cd home_automation\r"

expect ">"
send "put ../motion.py\r"

expect ">"
send "exit\r"
interact
