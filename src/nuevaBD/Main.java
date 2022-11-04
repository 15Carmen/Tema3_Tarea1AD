package nuevaBD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String servidor = "jdbc:mysql://dns11036.phdns11.es";
    private static Connection connection;
    private static Statement st = null;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        crearConexion();


        String[] camposProfesores = {"IdProfesorado int PRIMARY KEY AUTO_INCREMENT", "Nombre varchar(45)", "Apellidos varchar(45)", "FechaNacimiento date", "Antiguedad int"};
        String[] camposAlumnos = {"IdAlumnado int PRIMARY KEY AUTO_INCREMENT", "Nombre varchar(45)", "Apellidos varchar(45)", "FechaNacimiento varchar(45)"};
        String[] camposMatricula = {"IdMatricula int AUTO_INCREMENT", "IdProfesorado int", "IdAlumnado int", "Asignatura varchar(45)", "Curso int",
                                    "PRIMARY KEY (IdMatricula)",
                                    "FOREIGN KEY (IdProfesorado) REFERENCES Profesores(IdProfesorado) ON DELETE CASCADE ON UPDATE CASCADE",
                                    "FOREIGN KEY (IdAlumnado) REFERENCES Alumnos(IdAlumnado) ON DELETE CASCADE ON UPDATE CASCADE"};
        //crearTablaProfesores(camposProfesores);
        //crearTablaAlumnos(camposAlumnos);
        //crearTablaMatricula(camposMatricula);
        //insertarProfesores();
        //insertarAlumnos();
        //insertarMatricula();
    }


    private static void crearConexion() {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(servidor, "cmartin", "Marnu");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearTablaProfesores(String[] camposProfesores) {
        String sql = "CREATE TABLE ad2223_cmartin.Profesores (";
        for (int i = 0; i < camposProfesores.length; i++) {
            sql += camposProfesores[i];
            if (i < camposProfesores.length - 1) {
                sql += ",";
            }
        }
        sql += " );";

        System.out.println(sql);

        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearTablaAlumnos(String[] camposAlumnos) {
        String sql = "CREATE TABLE ad2223_cmartin.Alumnos (";
        for (int i = 0; i < camposAlumnos.length; i++) {
            sql += camposAlumnos[i];
            if (i < camposAlumnos.length - 1) {
                sql += ",";
            }
        }
        sql += " );";

        System.out.println(sql);

        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearTablaMatricula(String[] camposMatricula) {
        String sql = "CREATE TABLE ad2223_cmartin.Matricula (";
        for (int i = 0; i < camposMatricula.length; i++) {
            sql += camposMatricula[i];
            if (i < camposMatricula.length - 1) {
                sql += ",";
            }
        }
        sql += " );";

        System.out.println(sql);

        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarProfesores() {
        try {
            List<String> lista = new ArrayList<>();

            try (FileReader fr = new FileReader("src/nuevaBD/Profesores.txt");
                 BufferedReader br = new BufferedReader(fr)) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    lista.add(linea);
                }

                String[] listaProfes = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    listaProfes[i] = lista.get(i);
                }

                st = connection.createStatement();
                for (String sql: listaProfes) {
                    st.executeUpdate(sql);
                }

                st.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertarAlumnos() {
        try {
            List<String> lista = new ArrayList<>();

            try (FileReader fr = new FileReader("src/nuevaBD/Alumnos.txt");
                 BufferedReader br = new BufferedReader(fr)) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    lista.add(linea);
                }

                String[] listaAlumnos = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    listaAlumnos[i] = lista.get(i);
                }

                st = connection.createStatement();
                for (String sql : listaAlumnos) {
                    st.executeUpdate(sql);
                }

                st.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarMatricula() {
        try {
            List<String> lista = new ArrayList<>();

            try (FileReader fr = new FileReader("src/nuevaBD/Matricula.txt");
                 BufferedReader br = new BufferedReader(fr)) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    lista.add(linea);
                }

                String[] listaAlumnos = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    listaAlumnos[i] = lista.get(i);
                }

                st = connection.createStatement();
                for (String sql : listaAlumnos) {
                    st.executeUpdate(sql);
                }

                st.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarTabla(){
        String sql = "DELETE FROM";

    }
}
