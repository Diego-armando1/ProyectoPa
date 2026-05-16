package Clases;

import Clases.Paquete;
import Clases.Provincia;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-28T12:59:49", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile CollectionAttribute<Ciudad, Paquete> paqueteCollection;
    public static volatile SingularAttribute<Ciudad, Provincia> idProvincia;
    public static volatile SingularAttribute<Ciudad, Long> tarifabasekg;
    public static volatile SingularAttribute<Ciudad, String> nombre;
    public static volatile SingularAttribute<Ciudad, Integer> idCiudad;

}