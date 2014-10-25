package com.lazerycode.selenium.pages.internal;

public abstract class BasePage<T extends BasePage<T>> {

	@SuppressWarnings("unchecked")
	public T then() {
		return (T) this;
	}

	public abstract T get();

	public T get(String url) {
		return get();
	}
}
