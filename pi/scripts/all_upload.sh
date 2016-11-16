#!/usr/bin/expect -f

spawn sftp pi@192.168.1.102
expect "assword:"
send "raspberry\r"

expect ">"
send "mkdir home_automation\r"

expect ">"
send "cd home_automation\r"


expect ">"
send "put ../button.py\r"


#expect ">"
#send "put ../porch.py\r"

expect ">"
send "exit\r"
interact