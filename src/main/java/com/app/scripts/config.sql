DROP KEYSPACE whoami;

CREATE KEYSPACE whoami WITH strategy_class = 'SimpleStrategy' AND strategy_options:replication_factor = 1;

CREATE TABLE whoami.userinfo (
    id bigint PRIMARY KEY,
    address text,
    dateofbirth timestamp,
    email text,
    feedenabled boolean,
    firstname text,
    gender text,
    password text,
    terms boolean,
    username text,
    zipcode text
) WITH bloom_filter_fp_chance = 0.01
    AND caching = '{"keys":"ALL", "rows_per_partition":"NONE"}'
    AND comment = ''
    AND compaction = {'min_threshold': '4', 'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32'}
    AND compression = {'sstable_compression': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.1
    AND speculative_retry = '99.0PERCENTILE';

CREATE INDEX username_secondary_index ON whoami.userinfo (username);
CREATE INDEX email_secondary_index ON whoami.userinfo (email);
CREATE INDEX zipcode_secondary_index ON whoami.userinfo (zipcode);
