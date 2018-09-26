#!/bin/bash
handle()
{
	if [ $2 = 0 ] ; then
		restart $1
	elif [ $2 = 1 ] ; then 
		start $1
	elif [ $2 = 2 ] ; then
		stop $1
	fi
}
restart() 
{	
	stop $1;
	start $1;
}

stop()
{
	ps aux| grep $1| grep -v grep|awk '{print $2}'|xargs kill -9
}

start() 
{
      nohup java -server -Xms1g -Xmx1g -Xmn700m -Xss256K -XX:PermSize=128m -XX:MaxPermSize=128m -XX:GCTimeRatio=19 -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=1 -XX:SurvivorRatio=4 -XX:CMSInitiatingOccupancyFraction=70 -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -Xloggc:logs/$1_gc.log -Xloggc:logs/$1_gc.log -jar ./lib/$1-1.0.0.jar >/dev/null 2>&1 &
}

type=0 #0
server=trace-service-start #1

if [ $# -gt 0 ] ; then
	case $1 in
			-restart)
				type=0
				;;
			-start)
				type=1
				;;
			-stop)
				type=2
				;;		
			*)
				echo "./run.sh -[restart|start|stop]"
				exit 1
				;;
	esac
else
	echo "./run.sh -[restart|start|stop]"
	exit 1
fi

scriptDir=$(cd "$(dirname "$0")"; pwd)
cd $scriptDir/../

handle $server $type
