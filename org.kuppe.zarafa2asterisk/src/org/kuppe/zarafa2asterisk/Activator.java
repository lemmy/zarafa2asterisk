/**
 * Copyright (c) 2010-2011 Markus Alexander Kuppe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Markus Alexander Kuppe (github.com <at> lemmster <dot> de) - initial API and implementation
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
