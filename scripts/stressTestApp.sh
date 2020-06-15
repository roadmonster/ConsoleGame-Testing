#!/bin/bash
#===============================================================================
# Team 2
# David Pierce, Edward Lam, Hao Li
#                              Milestone #2
#
# File: stressTestApp.sh
#
# 	    Description:
#        This script will initialize several processes of test suite instance to ruch 
#        the test simutaneously. Each process of test suite will iterate serveral times.
#        The amout of instances and iteration of test suite is provided by the user.

#   DEPENDENCIES, LIMITATIONS, & DESIGN NOTES:
#       Dependencies : 
# 		

#       Design Notes :
#           1. Each process of test suite will have their individual
#			   log file to avoid a race condition.
#           2. The log files shall be merged at the end of execution
#           3. 
#           4. The combined log is then parsed to determine overall PASS/FAIL.
#           5. Results and statistics are calculated and displayed to the user.
#           6. Console output is logged to $LOG_FILE
#       Limitations :
#           1. Due to memory limitations on SU's CS1 server, errors may be seen
#              if too many background instances are requested (e.g. >= 5).
#
#   Example Usage:
#   "./stressTestApp.sh 2 5"  # Launches two instances of the test suite in the
#                               background, each executing 5 loops.
#===============================================================================

set -o pipefail
set -o nounset

#==========================================================================
# Constants
#==========================================================================

ARGS=2
LOG_PREFIX="stressTestLog"
LOG_EXT="txt"
LOG_FILE="${LOG_PREFIX}_main.${LOG_EXT}"

#===============================================================================
# Script
#===============================================================================

# Check number of arguments
if [ "$#" != "$ARGS" ]; then
    echo "ERROR: incorrect number of arguments provided!"
    echo "   ./stressTestApp <numInstances> <numIter>"
    exit 1
fi

# Basic input validation for number of iterations
if [ "$1" -le "0" ]; then
    echo "ERROR: Instances of test suite should be more than 0"
    exit 2
fi

# Basic input validation for number of iterations
if [ "$2" -le "0" ]; then
    echo "ERROR: Iteration of testing for each test suite should be more than 0!"
    exit 3
fi

# Remove previous log files
echo Removing stale log files...
rm -f -v $LOG_PREFIX*.$LOG_EXT


numInstances=$1
numIter=$2


echo "Start time:" $(date) | tee -a $LOG_FILE
start=$SECONDS

# initialize processes to run testsuite simultaneously
for i in `seq 1 $numInstances`; do
    echo "Test suite No.$i of $numInstances..." | tee -a $LOG_FILE
    
    (
        # assign number of each process
        fileIdx=$i

        ./runTestSuite.sh $numIter >> ${LOG_PREFIX}_$fileIdx.${LOG_EXT}
    ) &

    # Add block PID to list
    pidList[$i]=$!
done

# wait for each process to finish and join
for i in `seq 1 $numInstances`; do
    echo "Waiting for instance $i (pid=${pidList[$i]}) to complete..." | tee -a $LOG_FILE
    wait ${pidList[$i]}
done

stop=$SECONDS
echo "Finish time:" $(date) | tee -a $LOG_FILE

# Merge log files
sleep 2  
for i in `seq 1 $numInstances`; do
    cat ${LOG_PREFIX}_$i.${LOG_EXT} >> ${LOG_PREFIX}_combined.${LOG_EXT}
done

# Parse results and report PASS/FAIL
expectedNumPass=$numInstances
actualNumPass=$(grep PASS -c ${LOG_PREFIX}_combined.${LOG_EXT})

if [ "$actualNumPass" -eq "$expectedNumPass" ]; then
    result="PASS"
    rval=0
else
    result="FAIL"
    rval=4
fi

 
duration=$(( $stop - $start ))
passRate=$(bc -l <<< "scale=2; $actualNumPass/$numInstances*100")

echo ""                                                                                 | tee -a $LOG_FILE
echo "================================================================================" | tee -a $LOG_FILE
echo "Results"                                                                          | tee -a $LOG_FILE
echo "================================================================================" | tee -a $LOG_FILE
echo "Overall stress test result:       $result"                                        | tee -a $LOG_FILE
echo "Execution time:                   $duration [seconds]"                            | tee -a $LOG_FILE
echo ""                                                                                 | tee -a $LOG_FILE
echo "Expected # of passing instances:  $expectedNumPass"                               | tee -a $LOG_FILE
echo "Actual # of passing instances:    $actualNumPass"                                 | tee -a $LOG_FILE
echo "passing rate:         $passRate%"                                                 | tee -a $LOG_FILE
echo "================================================================================" | tee -a $LOG_FILE
echo ""

exit $rval
