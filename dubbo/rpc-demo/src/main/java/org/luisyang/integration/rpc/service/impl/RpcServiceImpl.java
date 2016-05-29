package org.luisyang.integration.rpc.service.impl;

import org.luisyang.integration.rpc.service.RpcService;

public class RpcServiceImpl implements RpcService {

	@Override
	public String echo(String ping) {
		return ping != null ? ping + " ---> I am Ok." : " I am ok.";
	}

}
