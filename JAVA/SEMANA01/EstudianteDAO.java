package UTN.datos;

import UTN.dominio.Estudiante;
import static UTN.conexion.Conexion.getCONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Método listar
    public List<Estudiante>listarEstudiantes(){
        List<Estudiante>estudiantes = new ArrayList<>();
        //Creamos algunos objetos que son necesarios para comunicarnos con la bd
        PreparedStatement ps;//nos ayuda a preparar(introduce) la sentencia sql
        ResultSet rs;//nos permite almacenar (obtener) el resultado obtenido desde la bd
        //Creamos un objeto de tipo conexion
        Connection con = getCONNECTION();
        String sql = "SELECT * FROM estudiantes ORDER BY idestudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiantes"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                //falta agregarlo a la lista
                estudiantes.add(estudiante);

            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al seleccionar  datos:"+e.getMessage());
        }
        finally {
           try{
               con.close();
           }catch(Exception e){
               System.out.println("Ocurrio un error al cerrar la conexion:"+e.getMessage());
           }
        }//fin finally
        return estudiantes;
    }//fin metodo listar

    //método por id-> fin by id
    public boolean buscarEstudianteporId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getCONNECTION();
        String sql = "SELECT * FROM estudiantes WWHERE idestudiantes=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;//se encontro un registro
            }//fin if
        }catch(Exception e){
            System.out.println("Ocurrio un error al buscar estudiante:"+e.getMessage());
        }//fin catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion:"+e.getMessage());
            }//fin catch
        }//fin finally
        return false;
    }
    //Metodo agregar un nuevo estudiante
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getCONNECTION();
        String sql = "INSERT INTO estudiantes(nombre,apellido,telefono,email)VALUES (?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return  true;

        }catch(Exception e){
            System.out.println("Ocurrio un error ");
        }//fin catch
        finally{
          try{
              con.close();
          }catch(Exception e){
              System.out.println("Error al cerrar la conexion:"+e.getMessage());
          }//fin catch
        }//fin finally
        return false;
    }//fin metodo agregar estudiante

    //metodo para modificar
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getCONNECTION();
        String sql = "UPDATE estudiante SET nombre =?,apellido=?,telefono=?,email=? WHERE idestudiantes=?";
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.setInt(5,estudiante.getIdEstudiante());
            ps.execute();
            return  true;
        }catch(Exception e){
            System.out.println("Error al modificarse estudiante:"+e.getMessage());
        }//fin catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion:"+e.getMessage());
            }//fin catch
        }//fin finally
        return false;
    }//fin metodo modificar estudiante


    public static void main(String[] args) {
        var estudianteDao =  new EstudianteDAO();

        //Modificar estudiante
        //var estudianteModificado = new Estudiante(1,"juan","perez","456123","jperez@email.com");
        //var modificado = estudianteDao.modificarEstudiante(estuduanteModificado);
        //if(modificado)
            //System.out.println("estudiante modificado:"+estuduanteModificado);
        //else
            //System.out.println("no se modifico el estudiante: "+estuduanteModificado);

        //Agregar estudiante
       // var nuevoEstudiante = new Estudiante("Carlos","Lara","5465544223","clara@gmail.com");
        //var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        //if(agregado)
          //  System.out.println("estudiante agregado:"+nuevoEstudiante);
        //else
        //    System.out.println("no se ha agregado estudiante:"+ nuevoEstudiante);


            public boolean eliminarEstudiante(Estudiante estudiante){
                PreparedStatement ps;
                Connection con = getConnection();
                String sql = 'DELETE FROM estudiantes2022 WHERE idestudiantes2022=?';
                try{
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, estudiante.getIdEstudiante());
                    ps.execute();
                    return true;
                }catch (Exception e){
                    System.out.println("Error al eliminar estudiante: "+e.getMessage());
                }
                finally{
                    try{
                        con.close();
                    }catch (Exception e){
                        System.out.println("Error al cerrar la conexion: "+e.getMessage());
                    }
                }
                return false;
        }

        //Eliminar estudiante con id 3
          var estudianteEliminar = new Estudiante(4);
          var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
          if (eliminado)
              System.out.println("Estudiante eliminado: "+estudianteEliminar);
          else
              System.out.println("No se elimino estudiante: "+estudianteEliminar);

        // Listar los estudiantes
        System.out.println("listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);//FUNCION LAMBDA para imprimir


        //Buscar por id
        //var estudiante1 = new Estudiante(1);
        //System.out.println("Estudiantes antes de la busqueda :"+estudiante1);
        //var encontrado = estudianteDao.buscarEstudianteporId(estudiante1);
        //if(encontrado)
        //    System.out.println("Estudiante encontrado:"+estudiante1);
        //else
        //    System.out.println("No se encontro el estudiante: "+estudiante1.getIdEstudiante());

    }
}
