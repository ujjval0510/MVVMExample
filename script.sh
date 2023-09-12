#!/bin/bash

# Get the current timestamp
timestamp=$(date +"%Y%m%d_%H%M%S")

# Create a folder with the timestamp
mkdir "$timestamp"

# Optional: Change to the newly created folder
cd "$timestamp"

# Additional steps or commands if needed
cp /Users/bittu/AndroidStudioProjects/CodeLab/MVVMExample/build.gradle .
