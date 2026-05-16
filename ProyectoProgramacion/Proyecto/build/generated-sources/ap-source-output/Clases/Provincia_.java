package Clases;

import Clases.Ciudad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-28T12:59:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, Integer> idProvincia;
    public static volatile CollectionAttribute<Provincia, Ciudad> ciudadCollection;
    public static volatile SingularAttribute<Provincia, String> nombre;

}