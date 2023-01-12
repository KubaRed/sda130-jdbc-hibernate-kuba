package jdbc;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    //@GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;
    private double price;
    private String platform;

    public Game(){}

    public Game(String title, double price, String platform) {
        this.title = title;
        this.price = price;
        this.platform = platform;
    }
}
