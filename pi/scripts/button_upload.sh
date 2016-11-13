#!/usr/bin/expect -f

spawn sftp pi@192.168.1.96
expect "assword:"
send "raspberry\r"

#expect ">"
#send "mkdir halloween\r"

expect ">"
send "cd halloween\r"

expect ">"
send "put ../porch.py\r"

expect ">"
send "exit\r"
interact
