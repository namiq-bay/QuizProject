package com.data;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import java.net.DatagramSocket;
import java.net.*;
import java.io.*;

public class UserData {

	public String getExternalIP() {

		String ip = "Unknown";
		try {

			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine(); // you get the IP as a String
		} catch (Exception e) {
		}
		return ip;
	}

	public String getLocalIpAddress() {
		String ip = "Unknown";

		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return ip;
	}

	public String getMacAddressByNetworkInterface() {
		try {
			List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface ni : nis) {
				if (!ni.getName().equalsIgnoreCase("wlan0"))
					continue;
				byte[] macBytes = ni.getHardwareAddress();
				if (macBytes != null && macBytes.length > 0) {
					StringBuilder res1 = new StringBuilder();
					for (byte b : macBytes) {
						res1.append(String.format("%02x:", b));
					}
					return res1.deleteCharAt(res1.length() - 1).toString();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return "Unknown";
	}

	public String getHostName() {
		String hostname = "Unknown";

		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException ex) {
		}
		return hostname;
	}
}
