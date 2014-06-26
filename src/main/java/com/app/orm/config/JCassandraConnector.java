package com.app.orm.config;

/**
 * User: Jamal
 * Date: 6/26/14
 * Time: 11:19 PM
 */

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class JCassandraConnector {
    private Cluster cluster;
    private Session session;


    public void connect(String node) {
        cluster = Cluster.builder()
                .addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect("whoami");
    }

    public Session getSession() {
        connect("127.0.0.1");
        return session;
    }
}
