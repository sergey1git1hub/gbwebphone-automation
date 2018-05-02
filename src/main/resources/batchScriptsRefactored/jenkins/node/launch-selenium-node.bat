rem start /D "C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScriptsRefactored" psexec64.exe -i \\se-grid-node -s -u se-grid-node\sergey -p 123456  "c:\selenium-remote\launch-selenium-node.bat"
cmdkey.exe /add:se-grid-node /user:se-grid-node\sergey /pass:123456
psexec64.exe \\se-grid-node -i "c:\selenium-remote\launch-selenium-node.bat"
cmdkey.exe /delete:se-grid-node