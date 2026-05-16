package Clases;

import Clases.Ciudad;
import Clases.Cliente;
import Clases.Historial;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-28T12:59:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, Cliente> idRemitente;
    public static volatile SingularAttribute<Paquete, String> estadoactual;
    public static volatile SingularAttribute<Paquete, Long> peso;
    public static volatile CollectionAttribute<Paquete, Historial> historialCollection;
    public static volatile SingularAttribute<Paquete, Integer> idPaquete;
    public static volatile SingularAttribute<Paquete, String> direccionentrega;
    public static volatile SingularAttribute<Paquete, String> codigoseguimiento;
    public static volatile SingularAttribute<Paquete, String> tipoenvio;
    public static volatile SingularAttribute<Paquete, Date> fecharegistro;
    public static volatile SingularAttribute<Paquete, Cliente> idDestinatario;
    public static volatile SingularAttribute<Paquete, Ciudad> idCiudad;
    public static volatile SingularAttribute<Paquete, Long> costototal;

}