package com.example.core.contracts.domain.repositories;

public class Page {
	int number;
	int size;
	
	private Page(int number, int size) {
		super();
		this.number = number;
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}

	public static Page of(int number, int size) {
		return new Page(number, size);
	}

	public static Page of(int number) {
		return Page.of(number, 20);
	}
}
