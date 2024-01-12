package com.pcwk.ehr.cmn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.proxy.annotation.GetCreator;

public interface PcwkLogger {
	
	Logger LOG = LogManager.getFormatterLogger(PcwkLogger.class);
}
