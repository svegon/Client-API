@echo off        
echo.
cd ..
set /p id=Pull Request #
set /p name=Branch: pr%id%-
set branch=pr%id%-%name%
call git fetch origin pull/%id%/head:%branch%
call git checkout %branch%
pause