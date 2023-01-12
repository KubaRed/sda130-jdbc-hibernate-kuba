package hibernate;

import jdbc.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateDemo {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.configure("hibernate.cfg.xml"); // nie trzeba nazwy pliku dawac poniewaz szuka domyslnie
        // w katalogu resources

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //SessionFactiory - ciężki obiekt, potrzebny tylko jeden na całą aplikację

        //ORM - Object-Relation Mapper
        Book book = new Book("DDD Kompedium wiedzy", "Vaugh Vernon", 130);

        //Aby klasa była encją bazodanową, rozumianą przez hibernate musi:
        //być oznaczona adnotacją @Entity
        //mysi być wskazana przy konfiguracji (addAnnotatedClass)
        //musi być wskazane id adnotacją @Id
        //musi mieć bezparametrowy konstruktor
        //pamiętaj o otwarciu i zamknięciu tranzakcji oraz zamkknięciu sesji

        //Crud:
        //przygotowanie interakcji z bazą
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //nasze interakcje
        // session.save(book);
        //zakończenie interakcji
        transaction.commit();
        session.close();

        //cRud:
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Book aBook = session.get(Book.class, 3); //jeśli nie znajdzie to null

        System.out.println(aBook);
        transaction.commit();
        session.close();

        //crUd:
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Book book1 = session.get(Book.class, 3);
        book1.setPages(250);
        transaction.commit();
        session.close();

        //cruD
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Book book2 = session.get(Book.class, 5);
        //session.delete(book2);
        transaction.commit();
        session.close();

        //read - many
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<Book> books = session.createQuery("from Book B where B.id < 4", Book.class).getResultList();
        transaction.commit();
        session.close();

        System.out.println(books);

        sessionFactory.close();
        //należy zamknąc SessionFactory na koniec
    }
}
