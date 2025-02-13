#!/bin/bash

# Compile the project
mvn clean package

echo "Choose which implementation to run:"
echo "1 - Single-process (Threads)"
echo "2 - Multi-process (Separate PIDs)"

read -p "Enter your choice (1 or 2): " choice

if [ "$choice" == "1" ]; then
    echo "Starting Single-process version..."
    java -jar target/message-exchange-1.0-SNAPSHOT.jar
elif [ "$choice" == "2" ]; then
    echo "Starting Multi-process version..."
    java -cp target/classes com.example.PlayerServer &  # Run server in background
    sleep 2  # Give server time to start
    java -cp target/classes com.example.PlayerClient     # Run client
else
    echo "Invalid choice! Exiting."
fi
