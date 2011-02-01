package websvc;

import javax.jws.WebService;

import org.wc.mytapovan.domain.Employee;

@WebService
public interface HelloWorld {

    String sayHi(String text);


    /* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
     * support interfaces directly.  Special XmlAdapter classes need to
     * be written to handle them
     */
    String sayHiToUser(Employee user);


    /* Map passing
     * JAXB also does not support Maps.  It handles Lists great, but Maps are
     * not supported directly.  They also require use of a XmlAdapter to map
     * the maps into beans that JAXB can use. 
     */
   // @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
  //  Map<Integer, User> getUsers();
}