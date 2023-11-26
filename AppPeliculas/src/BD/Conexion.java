package BD;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diez60
 */
public class Conexion {

      public static Connection getConexion(){
          
          Connection con = null;
           
          try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas","root","");
              System.out.println("Conexion exitosa");
          } catch (Exception e) {
              System.out.println(e.getMessage());
              System.out.println("Error en la conexion BD");

          }
          return con;
      }
}
