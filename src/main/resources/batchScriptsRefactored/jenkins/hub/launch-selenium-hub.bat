rem start /D "C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScriptsRefactored" java -jar selenium-server-standalone-3.11.0.jar -role hub -hubConfig "C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScriptsRefactored\jenkins\hub\hubconfig.json"

rem start /D "C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScriptsRefactored" java -jar selenium-server-standalone-3.11.0.jar -role hub -hubConfig "C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScriptsRefactored\jenkins\hub\hubconfig.json"
rem start /B /D "src\main\resources\batchScriptsRefactored" java -jar selenium-server-standalone-3.11.0.jar -role hub -hubConfig "jenkins/hub/hubconfig.json"

start /B /D "../.." java -jar selenium-server-standalone-3.11.0.jar -role hub -hubConfig "jenkins/hub/hubconfig.json"


