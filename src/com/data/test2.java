package com.data;

public class test2 {
	public static void main(String[] args) {
		UserData ud = new UserData();
		System.out.println("Host Name: " + ud.getHostName());

		System.out.println("MAC: " + ud.getMacAddressByNetworkInterface());
		System.out.println("Local IP: " + ud.getLocalIpAddress());
		System.out.println("External IP: " + ud.getExternalIP());
	}
}
