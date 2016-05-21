package org.luisyang.integration.spring.service.impl;

import org.luisyang.integration.spring.service.Instrument;

public class Saxophone implements Instrument {

	@Override
	public void play() {
		System.out.println("TOOT TOOT TOOT");
	}

}
