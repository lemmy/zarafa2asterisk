/*******************************************************************************
 * Copyright (c) 2010 Markus Alexander Kuppe.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Markus Alexander Kuppe (ecf-dev_eclipse.org <at> lemmster <dot> de) - initial API and implementation
 ******************************************************************************/
package org.kuppe.zarafa2asterisk;

import java.io.IOException;

import org.asteriskjava.fastagi.DefaultAgiServer;
import org.asteriskjava.fastagi.MappingStrategy;
import org.asteriskjava.fastagi.StaticMappingStrategy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	
	private DefaultAgiServer server;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		new Thread(new Runnable() {
			
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
		        server = new DefaultAgiServer();
		        
		        // configure mapping strategy for asterisk-java
		        MappingStrategy mappingStrategy = new StaticMappingStrategy(new CallerIdAgiScript());
                server.setMappingStrategy(mappingStrategy);

                // configure the agi port
                int port = Integer.parseInt(System.getProperty("org.kuppe.zarafa2asterisk.agi.port", "4573"));
                server.setPort(port);

                // finally run the server
                try {
					server.startup();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		
		if(server != null) {
			server.shutdown();
		}
		server = null;
	}
}
