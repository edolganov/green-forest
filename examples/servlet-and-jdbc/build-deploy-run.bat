set INIT_DIR=%CD%
set SERVER_HOME=c:\chapaj\projects\green-forest\app\apache-tomcat
set SERVER_DEPLOY=%SERVER_HOME%\webapps


call ant
if %errorlevel% GEQ 1 goto ERROR_IN_BUILD

taskkill /fi "WINDOWTITLE eq Tomcat"

for /d %%i in (%SERVER_DEPLOY%\*) do rd /s /q %%i
del /s /q %SERVER_DEPLOY%\*.*
rd /s /q "%SERVER_DEPLOY%\..\work"
xcopy ".\build\*.war" 		"%SERVER_DEPLOY%\" /y /f
mkdir "%SERVER_DEPLOY%\..\temp"


pushd %SERVER_HOME%\bin
run-debug.bat
popd

goto ALL_DONE

:ERROR_IN_BUILD
echo ERROR IN BUILD
pause
goto ALL_DONE

:ALL_DONE
::pause