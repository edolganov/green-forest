set INIT_DIR=%CD%
set WEB_DIR=%CD%\web
set SERVER_HOME=c:\chapaj\projects\green-forest\app\apache-tomcat
set SERVER_DEPLOY=%SERVER_HOME%\webapps
set DEST_DIR=%SERVER_DEPLOY%\gf-servlet-jdbc


call ant
if %errorlevel% GEQ 1 goto ERROR_IN_BUILD

taskkill /fi "WINDOWTITLE eq Tomcat"

for /d %%i in (%SERVER_DEPLOY%\*) do rd /s /q %%i
del /s /q %SERVER_DEPLOY%\*.*
rd /s /q "%SERVER_DEPLOY%\..\work"
xcopy ".\build\*.war" 		"%SERVER_DEPLOY%\" /y /f
mkdir "%SERVER_DEPLOY%\..\temp"


pushd %SERVER_HOME%\bin
call run-debug.bat
popd

::pause 15 sec
cd %INIT_DIR%
ping localhost -w 1000 -n 15 > nul

rd /s /q %DEST_DIR%\img
MKLINK /D %DEST_DIR%\img %WEB_DIR%\img

rd /s /q %DEST_DIR%\css
MKLINK /D %DEST_DIR%\css %WEB_DIR%\css

rd /s /q %DEST_DIR%\js
MKLINK /D %DEST_DIR%\js %WEB_DIR%\js


rd /s /q %DEST_DIR%\WEB-INF\jsp
MKLINK /D %DEST_DIR%\WEB-INF\jsp %WEB_DIR%\WEB-INF\jsp


goto ALL_DONE

:ERROR_IN_BUILD
echo ERROR IN BUILD
pause
goto ALL_DONE

:ALL_DONE
::pause