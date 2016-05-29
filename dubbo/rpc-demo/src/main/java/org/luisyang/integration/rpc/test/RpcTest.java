package org.luisyang.integration.rpc.test;

import java.net.InetSocketAddress;

import org.luisyang.integration.rpc.exporter.RpcExporter;
import org.luisyang.integration.rpc.importer.RpcImporter;
import org.luisyang.integration.rpc.service.RpcService;
import org.luisyang.integration.rpc.service.impl.RpcServiceImpl;

public class RpcTest {

	public static void main(String[] args) {
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					RpcExporter.exporter("localhost", 8088);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}).start();
		
		
		RpcImporter<RpcService> importer = new RpcImporter<RpcService>();
		RpcService service = importer.importer(RpcServiceImpl.class, new InetSocketAddress("localhost", 8088));
		System.out.println(service.echo("Are you Ok ?"));
		
	}
	
}
