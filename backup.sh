#!/bin/bash
backdir="/etc /home /root /var/spool/mail"
basedir=/backup
[ ! -d "$basedir" ] && mkdir $basedir
time=$(date "+%Y%m%d-%H%M%S")
backfile=$basedir/backup$time.tar.gz
tar -zcvf $backfile $backdir
~
