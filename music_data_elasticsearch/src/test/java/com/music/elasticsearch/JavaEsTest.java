package com.music.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class JavaEsTest {
    private String IP;
    private int PORT;
    @Before
    public void init() {
        this.IP = "139.224.66.83";
        this.PORT = 9300;

    }

    @Test
    public void esClient() {
        try {
            Settings settings = Settings.builder().put("cluster.name","docker-cluster").build();
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(IP),PORT));
            System.out.println(client.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
