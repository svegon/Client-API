@echo off        
echo.
echo OLD VERSION!!! Only use for mappings that don't support v2.
cd ..
set /p targetMappings=Target Mappings (net.fabricmc.yarn): 
call gradlew.bat migrateMappings -PtargetMappingsArtifact="%targetMappings%" -PinputDir="%cd%/src/main/java" -PoutputDir="%cd%/src/main/new java"
rmdir /s /q "%cd%/src/main/java"
move "%cd%/src/main/new java" "%cd%/src/main/java"
rem gradlew.bat downloadAssets
pause