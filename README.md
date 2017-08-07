Easy Socket Transmission
========================
_by @angrykoala_

## Description
Simple abstract class which allow to children objects to send or receive themselves as serialized objects through Sockets connections.

## Usage
To use an _Easy Socket Transmission_, extend it to your class
```Java
public class MyClass extends EasySocketTransmission{
//Your class implementation
}
```
Simple methods _send_ and _receive_ (now part of your class) will allow you to transmit your serialized object:
```Java
//try to send myClass to given host by default port
myClass.send("somehost");
//waits until an object is send through the default port and cast it into MyClass, MyClass.receive() would work in the exact same way
myClass=(MyClass) EasySocketTransmission.receive();
```
>Compile with `javac EasySocketTransmission.java`

## Class Overview

### Variables
* `public static final int defaultPort=4242` default port to be used when port not valid or not specified in _send_/_receive_ (4242 by default)

* `public static final String defaultHost="localhost"` default host to use when not specified, by default the _localhost_

### Methods
**Public**
* `void send(String host,int port) throws IOException` sends the object to given host and port (where should be a Socket listening)

* `void send(String host) throws IOException` sends the object to given host and _defautPort_ (4242)

* `void send(int port) throws IOException` sends the object to _defaultHost_ ("LocalHost") though given port (will use default if 0 or invalid port given)

* `void send() throws IOException` sends the object to _defaultHost_ using _defaultPort_

* `static EasySocketTransmission receive(int port,int timeout) throws IOException, ClassNotFoundException` listen to given port, throw a _java.net.SocketTimeoutException_ at specified timeout, if timeout is 0 or less, it will wait indefinitely until an object is received. When and object is received (for example a _send_ was executed on other host) it will close the sockets and return an an _EasySocketTransmission_ object (which can be casted to any children class)

* `static EasySocketTransmission receive(int port) throws ClassNotFoundException, IOException` same as before, but will wait indefinitely until an object is received

* `static EasySocketTransmission receive() throws ClassNotFoundException, IOException` Will listen to _defaultPort_ indefinitely

**Private**
* `static boolean checkPort(int port)` checks if given port is a valid port (between 0 and 65535, both not included, return false if not valid

* `static boolean checkHost(String host)` checks given host is a valid string (not null nor empty), return false if not valid
