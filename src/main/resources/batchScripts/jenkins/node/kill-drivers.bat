cmdkey.exe /add:se-grid-node /user:se-grid-node\sergey /pass:123456
"C:\Users\sergey\.jenkins\workspace\gbwebphone-automation\src\main\resources\batchScripts\psexec64.exe" \\se-grid-node -s -i "c:\selenium-remote\kill-drivers.bat"
cmdkey.exe /delete:se-grid-node