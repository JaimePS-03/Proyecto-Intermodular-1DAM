#!/bin/bash
cd /etc/netplan
sudo echo -e "
    enp0s8:
      dhcp4: false
      addresses: [192.168.1.10/24]
      gateway4: 192.168.1.10
      nameservers:
         addresses: [208.67.222.222]"
sudo netplan apply
ping 192.168.1.11