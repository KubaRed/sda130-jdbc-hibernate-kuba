package movies.controller;

import movies.service.MovieService;
import movies.model.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//pozwala na interakcję z aplikacją (wprowadzanie danych, prezentacja rezultatów)
public class ConsoleController extends Controller {

    public void showMessage(String message){
        System.out.println(message);
    }
    public int readInt(String message){
        try{
            String input = readString(message);
            return Integer.parseInt(message);
        }catch(NumberFormatException e){
            showMessage("Należy podać liczbę");
            return readInt(message);
        }
    }
    public String readString(String message){
        return message;
    }
}
