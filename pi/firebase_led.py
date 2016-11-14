import urllib
import time

url = "https://ledcolor-c7c49.firebaseio.com/.json"

while True:
    try:
        response = urllib.urlopen(url).read()

        print(response)
    except urllib.error.HTTPError as err:
        print(err.code)

    time.sleep(1)
