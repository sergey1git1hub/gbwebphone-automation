start /D "C:\selenium-remote" java -Dwebdriver.chrome.driver=C:\seleniumdrivers\chromedriver.exe -Dwebdriver.ie.driver=C:\seleniumdrivers\iedriverserver.exe -Dwebdriver.opera.driver=C:\seleniumdrivers\operadriver.exe -Dwebdriver.operablink.driver=C:\seleniumdrivers\operadriver.exe -jar selenium-server-standalone-3.11.0.jar -role node  -nodeConfig nodeconfig.json