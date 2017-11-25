#!/bin/bash

VERSION=0.2.0

function install() {
    echo "Creating necessary directories..."
    mkdir /home/$USER/.tasks

    echo "Installing the application..."
    cp --recursive resources/languages /home/$USER/.tasks/
    cp resources/base_data_file.xml /home/$USER/.tasks/tasks.xml
    cp resources/settings.xml /home/$USER/.tasks/settings.xml
    cp tasks-*.jar /home/$USER/.tasks
    cp --recursive dependency-jars /home/$USER/.tasks
    sudo cp resources/tasks.png /usr/share/pixmaps
    sudo cp resources/tasks.desktop /usr/share/applications
    sudo cp resources/tasks.sh /usr/bin/
}

function uninstall() {
    echo "Uninstalling the application..."
    rm /home/$USER/.tasks/tasks-*.jar
    sudo rm /usr/share/pixmaps/tasks.png
    sudo rm /usr/share/applications/tasks.desktop
    sudo rm /usr/bin/tasks.sh

    echo "Deleting unecessary directories..."
    rm --recursive --force /home/$USER/.tasks
}

function update() {
    if [[ ! -d /home/$USER/.tasks/languages ]];
    then
        echo "Language Files do Not Exist, creating them."
        cp --recursive resources/languages /home/$USER/.tasks/
    fi
    if [[ ! -f /home/$USER/.tasks/tasks.xml ]];
    then
        echo "Base data files do not exist, creating them."
        cp resources/base_data_file.xml /home/$USER/.tasks/tasks.xml
    fi
    if ! [[ -f /home/$USER/.tasks/settings.xml ]];
    then
        echo "Base settings file does not exist, creating it."
        cp resources/settings.xml /home/$USER/.tasks/settings.xml
    fi

    echo "Removing old application files."
    rm /home/$USER/.tasks/*.jar
    rm --recursive /home/$USER/.tasks/dependency-jars

    echo "Installing new application files"
    cp tasks-*.jar /home/$USER/.tasks/
    cp --recursive dependency-jars /home/$USER/.tasks/

    echo "Tasks updated to version: $VERSION"
}

echo "Chose an option"
echo "--> install -> To install the application."
echo "--> uninstall -> To uninstall the application."
echo "--> update -> To update your current application."
read option

if [ $option == "install" ];
then
    install
elif [ $option == "uninstall" ];
then
    uninstall
elif [ $option == "update" ];
then
    update
else
    echo "Invalid option."
fi
