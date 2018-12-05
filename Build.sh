#!/bin/bash

mkdir Build
mkdir tmp

javac *.java -d tmp
cd tmp
jar cfv BattleShipCLI.jar *.class 
jar ufe BattleShipCLI.jar main main.class
mv BattleShipCLI.jar ../Build/BattleShipJAVACLI.jar
cd ..
cp Fleet.txt Build/Fleet.txt
