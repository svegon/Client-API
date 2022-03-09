@echo off
echo Generating sources and setting up Eclipse (or IntelIJ IDEA)...
cd ..
call gradlew.bat genSources
call gradlew.bat eclipse
pause