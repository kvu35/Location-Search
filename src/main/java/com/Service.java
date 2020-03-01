package com;

public interface Service<T> {
	public void register();
	public T getContext();
	public void end();
}