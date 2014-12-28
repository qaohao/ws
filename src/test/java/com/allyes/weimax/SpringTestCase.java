package com.allyes.weimax;

import org.junit.runner.RunWith;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-unit.xml")
public abstract class SpringTestCase extends ApplicationObjectSupport {}