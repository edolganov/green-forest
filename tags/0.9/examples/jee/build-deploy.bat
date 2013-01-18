call props.bat

call ant
if %errorlevel% GEQ 1 goto ERROR_IN_BUILD

xcopy ".\build\*.war" 		"%SERVER_DEPLOY%\" /y /f