package movies;


import java.awt.*;

//Single responsibility - pojedyncza odpowiedzialność
//Open-closed - kod powinien być otwarty na rozszerzanie i
// zamknięty na modyfikacje
//rozruch
public class MoviesApp {
    public static void main(String[] args) {
      //Menu.startMenu(); - tak gdyby metoda była statyczna
//        ConsoleController consoleController = new ConsoleController();
//        consoleController.startMenu();

        WindowController windowController = new WindowController();
        windowController.startMenu();

    }
}


//Część 2:
//Dodaj nową bazę danych
//Przygotuj odpowiedni sterownik i uzyskaj obiekt Connection
//Dodaj przez Javę odpowiednią tabelę do bazy danych (możesz wykorzystać klauzulę  “if not exists” aby nie dodawała się za każdym razem
//Zaktualizuj sposób zapisu filmu aby był dodawany do tabeli
//Zaktualizuj sposób wyświetlania filmów aby były wyświetlane z bazy danych
