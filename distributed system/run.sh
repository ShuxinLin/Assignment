#!/bin/sh

javac Node.java
javac Network.java

java Network "$@"
