package Clases;

import Clases.Paquete;
import Clases.Trabajador;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-28T12:59:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Historial.class)
public class Historial_ { 

    public static volatile SingularAttribute<Historial, Date> fechahora;
    public static volatile SingularAttribute<Historial, String> estado;
    public static volatile SingularAttribute<Historial, Paquete> idPaquete;
    public static volatile SingularAttribute<Historial, String> nombrereceptor;
    public static volatile SingularAttribute<Historial, Trabajador> idTrabajador;
    public static volatile SingularAttribute<Historial, Integer> idHistorial;
    public static volatile SingularAttribute<Historial, String> observacion;

}