#!/bin/bash

echo "Creating necessary directories"
mkdir /home/$USER/.tasks

echo "Installing Application"
cp tasks-0.1.jar /home/$USER/.tasks/

echo "Installing Necessary Dependencies"
cp -r dependency-jars /home/$USER/.tasks/
sudo cp resources/tasks.png /usr/share/pixmaps
sudo cp resources/tasks.desktop /usr/share/applications/ 
sudo cp resources/tasks.sh /usr/bin/
