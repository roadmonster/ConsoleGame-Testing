#!/bin/bash
#==========================================================================
# CPSC 5210
# Group 2: Edward Lam, Hao Li, David Pierce
# 3rd June 2020
# Milestone #2
#
# File: buildTestSuite.sh
#
# Description:
# This shell script builds the test suite for the Java Game Console Card Game
# using JUnit test for unit testing associated from Milestone 1.
#
# DEPENDENCIES, LIMITATIONS, & DESIGN NOTES:
# Dependencies :
# 1. openjdk version "11.0.6"
# 2. "junit-platform-console-standalone-1.5.0-M1.jar" required at JUNIT_JAR (default: "lib" directory under project root)
# Design Notes :
# 1. All source files and all J-Unit test files are built/compiled by this script.
# 2. Artifacts are placed in the $OUT_DIR(default: "bin" under project root) directory
# 3. Run chmod +x buildTestSuite.sh before executing the script
# Limitations :
# 1. Due to memory limitations on SU's CS1 server, the $CS1_HACK (-J-Xmx512m)
# variable is used to limit the memory used by the JVM.
#
# Example Usage:
# "./buildTestSuite.sh"
#==========================================================================
set -o nounset
#==========================================================================
# Constants
#==========================================================================
mydir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" > /dev/null && pwd )"
echo $mydir
OUT_DIR="bin"
JUNIT_JAR="lib/junit-platform-console-standalone-1.5.0-M1.jar"

# Hack required due to memory limitations on CS1
CS1_HACK="-J-Xmx512m" # Limit heap to 512 MB
LOG_FILE="buildTestSuiteLog.txt"
#==========================================================================
# Script
#==========================================================================
echo "Building test suite..."
echo "Creating directory for build artifacts..."
mkdir -p $mydir/$OUT_DIR | tee -a $LOG_FILE
echo Removing stale artifacts...
rm -f -v $mydir/$OUT_DIR/../*.class | tee -a $LOG_FILE
# Remove previous log files
echo Cleaning up stale log files...
rm -f -v $LOG_FILE
echo "Printing javac version..."
javac $CS1_HACK -version | tee -a $LOG_FILE
echo "Building source code..."
javac -J-Xmx512m -d $mydir/$OUT_DIR/ -cp $mydir/src/com/company/**/:$mydir/$JUNIT_JAR  $mydir/src/com/company/**/*.java | tee -a $LOG_FILE
if [ $? != 0 ]; then
 echo "ERROR: Unable to build Aborting build..."
 exit 1
else
 echo "Build success!"
fi
