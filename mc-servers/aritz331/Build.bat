@echo off
if [%1]==[] (goto interactive)
if [%1]==[-v] (goto 25)
exit /b

:interactive
title BuildTools
choice /c YN /N /M "Do you want to build the Spigot server [Y/N]? "
if errorlevel 2 (cls & goto done)
cls
set /p vrsn="Version (press enter without input to use latest version): " || set vrsn="latest"
echo.
echo ----------------------------------------------------------------------------------------------
java -jar BuildTools.jar --rev %vrsn%
:done
echo ----------------------------------------------------------------------------------------------
echo.
echo Done
echo Press any key to exit... && pause >nul
exit /b

:25
BuildTools.jar -rev %2