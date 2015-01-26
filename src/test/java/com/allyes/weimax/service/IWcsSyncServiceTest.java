package com.allyes.weimax.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allyes.weimax.SpringTestCase;

public class IWcsSyncServiceTest extends SpringTestCase {
	@Autowired
	private IWcsSyncService wcsSyncService;
	
	@Test
	public void testSyncSubusers() {
		wcsSyncService.syncSubusers();
	}
	
}
