package connects;

import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Connect {

  private DirContext connection;

  public void newConnection() {
    Properties env = new Properties();
    String ldapURL = "ldap://localhost:389";
    String ldapUser = "admin";
    String ldapPassword = "admin_pass";
    String ldapBase = "dc=ramhlocal,dc=com";
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapURL);
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "cn=" + ldapUser + "," + ldapBase);
    env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
    try {
      connection = new InitialDirContext(env);
      System.out.println("Conectado na LDAP: " + connection);
    } catch (AuthenticationException ex) {
      System.out.println(ex.getMessage());
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public void closeConnection() {
    try {
      connection.close();
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public DirContext getConnection() {
    return this.connection;
  }

  public void setConnection(DirContext connection) {
    this.connection = connection;
  }

}