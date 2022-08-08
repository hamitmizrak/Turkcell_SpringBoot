package com.hamitmizrak.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("sayisal_department")
public class Sayisal implements IChooise {
	
	@Override
	public String message(String name) {
		return "Sayısal Bölüm: " + name;
	}
	
}