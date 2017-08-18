#!/bin/bash

#echo "Creating necessary directories"
#mkdir /home/$USER/.tasks

#echo "Installing Application"
#cp tasks-0.1.jar /home/$USER/.tasks/

#echo "Installing Necessary Dependencies"
#cp -r dependency-jars /home/$USER/.tasks/
#sudo cp resources/tasks.png /usr/share/pixmaps
#sudo cp resources/tasks.desktop /usr/share/applications/
#sudo cp resources/tasks.sh /usr/bin/

function install() {
    echo "Creating necessary directories..."
    mkdir /home/$USER/.tasks

    echo "Installing the application..."
    cp resources/base_data_file.xml /home/$USER/.tasks/tasks.xml
    cp tasks-0.1.jar /home/$USER/.tasks
    sudo cp resources/tasks.png /usr/share/pixmaps
    sudo cp resources/tasks.desktop /usr/share/applications
    sudo cp resources/tasks.sh /usr/bin/
}

function uninstall() {
    echo "Uninstalling the application..."
    rm /home/$USER/tasks-0.1.jar
    sudo rm /usr/share/pixmaps/tasks.png
    sudo rm /usr/share/applications/tasks.desktop
    sudo rm /usr/bin/tasks.sh

    echo "Deleting unecessary directories..."
    rm --recursive --force /home/$USER/.tasks
}

echo "Chose an option"
echo "--> install -> To install the application."
echo "--> uninstall -> To uninstall the application."
read option

if [ $option == "install" ];
then
    install
elif [ $option == "uninstall" ];
then
    uninstall
else
    echo "Invalid option."
fi
