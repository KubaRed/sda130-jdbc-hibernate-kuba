package hibernate;

import jdbc.Book;
import jdbc.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemoGame {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Game.class);
        configuration.configure("hibernate.cfg.xml"); // nie trzeba nazwy pliku dawac poniewaz szuka domyslnie
        // w katalogu resources

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Game game = new Game("Wow", 200.0, "PC");


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(game);

        transaction.commit();
        session.close();


        sessionFactory.close();
    }
}
