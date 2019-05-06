#!/bin/sh

# -----------------------------------------------------------------------------
# Stop Script for the noSeatBelt Server
# -----------------------------------------------------------------------------

ps aux | grep -P "xxl-job.jar" |grep -v "grep" | awk '{print "kill -9 "$2}' | sh
#readlink /u2s/slave/objext/objext/VideoObjectExtractionService | awk '{print "killall "$1}' | sh
#ps aux | grep `readlink /u2s/slave/objext/objext/VideoObjectExtractionService` | grep -v "grep" | awk '{print "kill -9 "$2}' | s
