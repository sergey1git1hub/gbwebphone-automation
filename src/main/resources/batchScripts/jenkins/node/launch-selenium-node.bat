cmdkey.exe /add:se-grid-node /user:se-grid-node\sergey /pass:123456
psexec64.exe \\se-grid-node -s -i "c:\selenium-remote\launch-selenium-node.bat"
cmdkey.exe /delete:se-grid-node