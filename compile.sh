#!/bin/bash
echo "Compiling Gym User Management System..."
javac -d . Main.java gui/*.java models/*.java services/*.java utils/*.java
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Run the application using: ./run.sh"
else
    echo "Compilation failed!"
fi
