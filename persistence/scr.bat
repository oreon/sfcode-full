@echo off

echo This program imports all .cvsignore contents into the svn:ignore property.
echo.
echo Current directory: "%cd%"
echo.

pause

echo.
echo Searching ...
call :CheckCVS "%cd%"
for /D /R %%i in (*) do call :CheckCVS "%%i"
echo Done

:: Ende
goto :eof

:CheckCVS
	set _cvs=%~1\.cvsignore
	echo Path "%~1"
	if exist "%_cvs%" (
	   echo setting "%_cvs%"
	   svn propset svn:ignore -F "%_cvs%" "%~1"
	)
	set _cvs=
goto :eof
