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
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{print $9}' | tail -n1 | xargs)
disk_available=$(df -BM / | awk '{print $4}'| tail -n1 | cut -c -5 | xargs)

timestamp=$(vmstat -t | awk '{print $18" "$19}'| tail -n1 | xargs)

host_id=1;

insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES('$timestamp', '$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available')";

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?