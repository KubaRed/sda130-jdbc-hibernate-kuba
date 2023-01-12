package jdbc;

import javax.persistence.*;

@Entity
@Table(name = "books") // poprawa nazwy tabeli
                        // inaczej uzywa nazwy Book jak klasa
public class Book {
    @Id
    //@GeneratedValue
    private int id;
    @Column(name = "title") // mozna dodac jak sa inne nazwy w bazie
    private String title;
    private String author;
    private  int pages;

    public Book(){} // Wymagany przez hibernate;

    public Book( String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book(int id, String title, String author, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }

    public void setPages(int i) {
        pages = i;
    }
}
