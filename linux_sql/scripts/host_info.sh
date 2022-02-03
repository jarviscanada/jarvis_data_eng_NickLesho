#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
    echo 'Error: requires psql_host, psql_port, db_name, psql_user, psql_password'
    exit 1
  fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
id=$(hostid)

cpu_number=$(lscpu | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(lscpu | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(lscpu  | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(lscpu | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(lscpu | egrep "^L2 cache:" | awk '{print $3}' | cut -c -3 | xargs)
total_mem=$(cat /proc/meminfo | grep MemTotal | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | awk '{print $18" "$19}'| tail -n1 | xargs)

insert_stmt="INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) VALUES ('$id', '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')";

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?