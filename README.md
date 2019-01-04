# Andrill

Andrill is a android mobile application interacting with a back-end API that is vulnerabile with various levels of difficulty. It provides a test environment for security professionals, security enthusiast and the programmers who are eager to learn about mobile security. 
# Easy to Setup
Andrill back-end server can be easily setup by a docker command. The APK is compiled by a bash script in the server-side. Each Andrill level has its own installation. However, Almost all installations are similar. The installations don't have any dependencies, fews lines edit and few commands are needed to setup the server and the application.

# Attack Scenarios
The scenarios are vary from simplest vulnerabilities to a conpeptual desging flaws. The following table shows the scenarios designed so far. 

| Number      | Options     | Description | Goal    |
|  :----:     |    :----:   |    :----:   |  :----: |
|[Andrill 1](https://github.com/Voorivex/Andrill/tree/master/Andrill-1)|SSL Pin: False, Extra Encryption Layer: False, HTTP|An Android application connecting to a back-end through HTTP|Capturing application traffic, Reading administrator's note|
|2|SSL Pin: False, Extra Encryption Layer: False, HTTPs|An Android application connecting to a back-end through self-signed SSL certificate| Capturing application traffic, Reading administrator's note|
|3|SSL Pin: False, Extra Encryption Layer: False, HTTPs|An Android application connecting to a back-end through signed SSL certificate| Capturing application traffic + Discovering vulnerabilities in back-end|
|4|SSL Pin: False, Extra Encryption Layer: Yes, HTTPs|An Android application connecting to a back-end through signed SSL certificate – Hard-coded AES Key + IV|Capturing application traffic|
|5|SSL Pin: False, Extra Encryption Layer: Yes, HTTPs, debuggable: True|An android application connecting to a back-end through signed SSL certificate - Receiving AES Key + IV and storing in a file|Capturing application traffic|
|6|SSL Pin: False, Extra Encryption Layer: Yes, HTTPs, debuggable: False|An android application connecting to a back-end through signed SSL certificate - Receiving AES Key + IV and storing in a file|Capturing application traffic|
|7|SSL Pin: False, Extra Encryption Layer: Yes, HTTPs|An Android application connecting to a back-end through signed SSL certificate - Receiving AES Key + IV and storing in a file encrypted by a key stored in Android key-store|CCapturing application traffic + Discovering vulnerabilities in back-end|
|8|SSL Pin: True, Extra Encryption Layer: False, HTTPs|An Android application connecting to a back-end through signed SSL certificate - SSL certificate has Pinned, method 1| Capturing application traffic|
|9|SSL Pin: True, Extra Encryption Layer: False, HTTPs|An Android application connecting to a back-end through signed SSL certificate - SSL certificate has Pinned, method 2|Capturing application traffic|
|10|SSL Pin: True, Extra Encryption Layer: False, HTTPs| An Android application connecting to a back-end through signed SSL certificate - SSL certificate has Pinned, method 3|Capturing application traffic + Discovering vulnerabilities in back-end|
|11|SSL Pin: True, Extra Encryption Layer: YES, HTTPs|An Android application connecting to a back-end through signed SSL certificate - SSL certificate has Pinned - Receiving AES Key + IV and storing in a file encrypted by a key stored in Android key-store|Capturing application traffic + Discovering vulnerabilities in back-end|
|12|SSL Pin: True, Extra Encryption Layer: YES, HTTPs|An Android application connecting to a back-end through signed SSL certificate - SSL certificate has Pinned - AES Key hard-coded and encrypted by another key = (hash of Android sign)|Capturing application traffic + Discovering vulnerabilities in back-end|
|13|SSL Pin: True, Extra Encryption Layer: False, HTTPs, Root Detection: True|An Android application connecting to a back-end through signed SSL certificate|Capturing application traffic + Discovering vulnerabilities in back-end|
|14|SSL Pin: True, Extra Encryption Layer: False, HTTPs, Root Detection: True, VM Detection: True|An Android application connecting to a back-end through signed SSL certificate|Capturing application traffic + Discovering vulnerabilities in back-end|
