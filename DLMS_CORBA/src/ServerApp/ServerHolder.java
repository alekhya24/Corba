package ServerApp;

/**
* ServerApp/ServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/alekh/Downloads/W-2019/6231/Assignments/Assignment2/DLMS_CORBA/src/idl/Server.idl
* Tuesday, February 26, 2019 10:32:32 o'clock AM EST
*/

public final class ServerHolder implements org.omg.CORBA.portable.Streamable
{
  public ServerApp.Server value = null;

  public ServerHolder ()
  {
  }

  public ServerHolder (ServerApp.Server initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ServerApp.ServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ServerApp.ServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ServerApp.ServerHelper.type ();
  }

}
