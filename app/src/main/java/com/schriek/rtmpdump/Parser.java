package com.schriek.rtmpdump;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Parser {

	public Parser(Class c) {
		Method[] clazz = c.getMethods();
		
		for(Method e : clazz) {
			if(e.getAnnotations() != null) {
				for(Annotation a : e.getAnnotations()) {
					System.out.println(a);
				}
			}
		}
	}
}
