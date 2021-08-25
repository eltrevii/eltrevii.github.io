@echo off
title Aritz331_'s Minecraft Server Starter
goto start

:NotFound
echo.
echo ERROR: version not found. Trying to use BuildTools...
echo.
echo ^<------------------------------------------------------------------^>
%~dp0Build.bat -v %rnv%
echo ^<------------------------------------------------------------------^>
exit /b

:start
cd %~dp0
set rnv=""
echo Which Minecraft version do you want to run?
set /p rnv="> "
if NOT exist spigot-%rnv%.jar (goto NotFound)
title Spigot server: %rnv%
echo.
echo ^<------------------------------------------------------------------^>
java -Dfile.encoding=UTF8 -Xms1G -Xmx1G -jar spigot-%rnv%.jar nogui
echo.
echo ^<------------------------------------------------------------------^>