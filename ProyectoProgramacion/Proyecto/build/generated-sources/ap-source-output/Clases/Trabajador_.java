package Clases;

import Clases.Historial;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-28T12:59:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Trabajador.class)
public class Trabajador_ { 

    public static volatile SingularAttribute<Trabajador, String> password;
    public static volatile SingularAttribute<Trabajador, String> tipotrabajadorl;
    public static volatile CollectionAttribute<Trabajador, Historial> historialCollection;
    public static volatile SingularAttribute<Trabajador, Integer> idTrabajador;
    public static volatile SingularAttribute<Trabajador, String> nombre;
    public static volatile SingularAttribute<Trabajador, String> username;

}