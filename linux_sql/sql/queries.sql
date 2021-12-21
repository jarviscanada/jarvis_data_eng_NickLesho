SELECT cpu_number,id AS host_id,total_mem
FROM host_info
GROUP BY id, cpu_number
ORDER BY total_mem ASC;

SELECT host_id, hostname, host_usage.timestamp AS timestamp, AVG(memory_free) AS avg_used_mem_percentage
FROM host_usage
INNER JOIN host_info
    ON host_usage.host_id = host_info.id
GROUP BY host_id, hostname, date_trunc('hour', host_usage.timestamp) + date_part('minute', host_usage.timestamp):: int / 5 * interval '5 min'
ORDER BY host_id ASC;

SELECT COUNT(host_id), timestamp
FROM host_usage
GROUP BY host_id, date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min'
ORDER BY host_id ASC;