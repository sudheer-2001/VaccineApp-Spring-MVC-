package com.tap.vaccine.email;

import org.springframework.stereotype.Component;

@Component
public class Email {

	public Email() {
		System.out.println(this.getClass().getSimpleName() + " created");
	}

}
