package org.luisyang.integration.spring.service;

import org.luisyang.integration.spring.exception.PerformanceException;

public interface Performer {

	void perform() throws PerformanceException;

}
