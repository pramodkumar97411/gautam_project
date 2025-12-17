@echo off
echo Compiling Gym User Management System...
javac -d . Main.java gui/*.java models/*.java services/*.java utils/*.java
if %errorlevel% == 0 (
    echo Compilation successful!
    echo Run the application using: run.bat
) else (
    echo Compilation failed!
)
pause
