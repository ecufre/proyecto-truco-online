package controladores;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import entities.CategoriaEntity;
import entities.GrupoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import entities.PartidaEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 config.addAnnotatedClass(CategoriaEntity.class);
             config.addAnnotatedClass(GrupoEntity.class);
             config.addAnnotatedClass(JugadorEntity.class);
             config.addAnnotatedClass(ParejaEntity.class);
             config.addAnnotatedClass(PartidaEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}