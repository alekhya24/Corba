package Server;

import java.io.File;
import java.util.HashMap;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import ServerApp.ServerHelper;
import Util.Constants;
import Util.Servers;

public class Server {
	static HashMap<String,ServerImpl> serverData;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ORB orb = ORB.init(args, null);      
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();
		new File(Constants.LOG_DIR).mkdirs();
		new File(Constants.LOG_DIR+Servers.CON.getserverName().toString()).mkdirs();
		new File(Constants.LOG_DIR+Servers.MCG.getserverName().toString()).mkdir();
		new File(Constants.LOG_DIR+Servers.MON.getserverName().toString()).mkdir();	
		serverData = new HashMap<>();
for (Servers location : Servers.values()) {
		ServerImpl serverImpl = new ServerImpl(location);
		serverData.put(location.toString(),serverImpl);
		serverImpl.setORB(orb); 
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverImpl);
		ServerApp.Server href = ServerHelper.narrow(ref);

		org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		NameComponent path[] = ncRef.to_name(location.toString().toUpperCase() );
		ncRef.rebind(path, href);

		System.out.println(location.toString().toUpperCase()+" Server ready and waiting ...");
}
		// wait for invocations from clients
		for (;;){
			orb.run();
		}


	}

}
