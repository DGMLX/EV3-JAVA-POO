
package Controlador;


import BD.Conexion;
import static BD.Conexion.getConexion;
import Modelo.Pelicula;
import Vista.ListarPelicula;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Registro {
    PreparedStatement ps;
    ResultSet rs;
      
    public Registro() {
    }
    
    public static boolean agregarPelicula(Pelicula pelicula){
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "INSERT INTO pelicula(codigo,titulo,formato,duracion,categoria,director,estreno)VALUE (?,?,?,?,?,?,?)";
            PreparedStatement insert = conexion.prepareStatement(sql);
            insert.setString(1,pelicula.getCodigo());
            insert.setString(2,pelicula.getTitulo());
            insert.setString(3,pelicula.getFormato());
            insert.setInt(4,pelicula.getDuracion());
            insert.setString(5,pelicula.getCategoria());
            insert.setString(6,pelicula.getDirector());
            insert.setBoolean(7,pelicula.isEstreno());
            insert.execute();
            insert.close();
            conexion.close();
            System.out.println("Pelicula agregada");
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar la pelicula");
            System.out.println(e.getMessage());
            return false;
        }finally{
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la BD");
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    public static boolean listarDatos(ListarPelicula listPeli){    
        
        String sql = "Select * from pelicula";
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("codigo");
        modelo.addColumn("titulo");
        modelo.addColumn("formato");
        modelo.addColumn("duracion");
        modelo.addColumn("categoria");
        modelo.addColumn("director");
        modelo.addColumn("estreno");
        listPeli.jTable1.setModel(modelo);

        String [] datos = new String[7];
        Connection conexion = Conexion.getConexion();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);  
                datos[2] = rs.getString(3);
                if(datos[2].equalsIgnoreCase("V")){
                    datos[2] = "VHS";
                }else{
                    datos[2] = "Blue-Ray";
                }
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                modelo.addRow(datos);
            }
            System.out.println("Listando peliculas...");
            return true;
        }catch(Exception e){
            System.out.println("Error al listar las peliculas");
            System.out.println(e.getMessage());
            return false;
        }finally{
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la BD");
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static boolean eliminarPelicula(ListarPelicula listPeli){
        int fila = listPeli.jTable1.getSelectedRow();
        Connection conexion = Conexion.getConexion();
        try {
            if(fila==-1){
                JOptionPane.showMessageDialog(listPeli, "Debes seleccionar una fila para eliminar.");
                return false;
            }else{
                String valor = listPeli.jTable1.getValueAt(fila, 0).toString();
                
                PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM pelicula WHERE codigo ='"+valor+"'");
                eliminar.executeUpdate();
                listarDatos(listPeli);
                System.out.println("Pelicula eliminada");
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar la pelicula");
            System.out.println(e.getMessage());
            return false;
        }finally{
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la BD");
                System.out.println(e.getMessage());
            }
        }
    }
  
    
    public static boolean buscarPeli(ListarPelicula listPeli,String cod){
        String sql = "SELECT * FROM pelicula WHERE codigo = '"+cod+"'";
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("codigo");
        modelo.addColumn("titulo");
        modelo.addColumn("formato");
        modelo.addColumn("duracion");
        modelo.addColumn("categoria");
        modelo.addColumn("director");
        modelo.addColumn("estreno");
        listPeli.jTable1.setModel(modelo);

        String [] datos = new String[7];
        Connection conexion = Conexion.getConexion();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);  
                datos[2] = rs.getString(3);
                if(datos[2].equalsIgnoreCase("V")){
                    datos[2] = "VHS";
                }else{
                    datos[2] = "Blue-Ray";
                }
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                if(rs.getString(1).equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(listPeli, "Codigo invalido, la pelicula no se encuentra en el listado.");
                    return false;
                }
                modelo.addRow(datos);
            }
            System.out.println("Filtrando peliculas...");
            return true;
        }catch(Exception e){
            System.out.println("Error al listar las peliculas");
            System.out.println(e.getMessage());
            return false;
        }finally{
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la BD");
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    
    
    public static boolean modPelicula(ListarPelicula listPeli,Pelicula pelicula){
        Connection conexion = getConexion();
        PreparedStatement insert = null;
        int fila = listPeli.jTable1.getSelectedRow();
        String valor = listPeli.jTable1.getValueAt(fila,0).toString();

        String sql="update pelicula set codigo=?,titulo=?,formato=?,duracion=?,categoria=?,director=?,estreno=? WHERE codigo = '"+valor+"'";   
        
        try{
            insert = conexion.prepareStatement(sql);
            insert.setString(1,pelicula.getCodigo());
            insert.setString(2,pelicula.getTitulo());
            insert.setString(3,pelicula.getFormato());
            insert.setInt(4,pelicula.getDuracion());
            insert.setString(5,pelicula.getCategoria());
            insert.setString(6,pelicula.getDirector());
            insert.setBoolean(7,pelicula.isEstreno());
            insert.executeUpdate();
            insert.close();
            System.out.println("Pelicula modificada");
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar la pelicula");
            System.out.println(e.getMessage());
            return false;
        }finally{
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la BD");
                System.out.println(e.getMessage());
            }
        }
       }
    
    
    }
    
    

