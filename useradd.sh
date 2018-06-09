#!/bin/bash
groupadd vbirdgroup
for username in vbirduser1 vbirduser2 vbirduser3 vbirduser4 vbirduser5
do
   useradd -G vbirdgroup $username
   echo "password" | passwd --stdin $username
done
