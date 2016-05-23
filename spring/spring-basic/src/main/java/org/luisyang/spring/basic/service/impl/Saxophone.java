package org.luisyang.spring.basic.service.impl;

import org.luisyang.spring.basic.service.Instrument;

public class Saxophone implements Instrument {

	@Override
	public void play() {
		System.out.println("TOOT TOOT TOOT");
	}

}
