package com.tcarisland;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	for(int i = 0; i < 10; i++) {
    		new Thread(() -> System.out.println("Hello " + Thread.currentThread().getId())).start();
    	}
    }
}
