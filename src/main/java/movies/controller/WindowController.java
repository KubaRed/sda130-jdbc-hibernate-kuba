package movies.controller;

import movies.service.MovieService;
import movies.model.Movie;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class WindowController extends Controller {

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
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
        return JOptionPane.showInputDialog(message);
    }
}
