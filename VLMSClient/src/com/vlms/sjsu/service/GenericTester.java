/**
 * 
 */
package com.vlms.sjsu.service;

import java.rmi.RemoteException;

/**
 * @author vaishaksuresh
 *
 */
public class GenericTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceProxy proxy = new ServiceProxy();
		try {
			proxy.createUpdateUser(null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
