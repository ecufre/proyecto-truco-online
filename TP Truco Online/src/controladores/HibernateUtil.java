package controladores;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.BazaEntity;
import entities.CantoEntity;
import entities.CartaEntity;
import entities.CategoriaEntity;
import entities.GrupoEntity;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ManoEntity;
import entities.MazoEntity;
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
             config.addAnnotatedClass(CartaEntity.class);
             config.addAnnotatedClass(BazaEntity.class);
             config.addAnnotatedClass(CantoEntity.class);
             config.addAnnotatedClass(JuegoEntity.class);
             config.addAnnotatedClass(ManoEntity.class);
             config.addAnnotatedClass(MazoEntity.class);
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