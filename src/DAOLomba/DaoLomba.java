/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOLomba;

import connection.Connector;
import interfaces.InterfaceLomba;
import java.util.List;
import model.ModelLomba;
import view.ViewLomba;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lab Informatika
 */
public class DaoLomba implements InterfaceLomba {

    ViewLomba viewLomba;
    InterfaceLomba iLomba;
    List<ModelLomba> listLomba;
    Connection connect;

    public DaoLomba() {
        connect = Connector.getConnection();

    }

    @Override
    public List<ModelLomba> getAll() {
        List<ModelLomba> listLomba = null;
        try {
            listLomba = new ArrayList<ModelLomba>();
            Statement st = connect.createStatement();
            ResultSet result = st.executeQuery("Select * From lomba;");
            while (result.next()) {
                ModelLomba lomba = new ModelLomba();
                lomba.setJudul(result.getString("judul"));
                lomba.setAlur(result.getDouble("alur"));
                lomba.setOrisinalitas(result.getDouble("orisinalitas"));
                lomba.setPemilihanKata(result.getDouble("pemilihanKata"));
                lomba.setNilai(result.getDouble("nilai"));
                listLomba.add(lomba);
            }
        } catch (SQLException e) {
            System.out.println("Read Err : " + e.getMessage());
        }
        return listLomba;
    }
    
//    public Double hasil(ModelLomba nilai){
//        double hasil;
//        hasil = nilai.getAlur() + nilai.getOrisinalitas() + nilai.getPemilihanKata()/3;
//        return hasil;
//    }

    @Override
    public void insertdata(ModelLomba lomba) {
        PreparedStatement statement = null;
        double hasil;
        
        try {
            statement = connect.prepareStatement("Insert into `lomba` (`judul`, `alur`, `orisinalitas`, `pemilihanKata`, `nilai`) Values (?,?,?,?,?);");
            hasil = lomba.getAlur() + lomba.getOrisinalitas() + lomba.getPemilihanKata()/3;
            statement.setString(1, lomba.getJudul());

            statement.setDouble(2, lomba.getAlur());
            statement.setDouble(3, lomba.getOrisinalitas());
            statement.setDouble(4, lomba.getOrisinalitas());
            statement.setDouble(5, lomba.getNilai());
            statement.execute();
            
            
        } catch (SQLException e) {
            System.out.println("Insert Err : " + e.getMessage());
        }
    }

    @Override
    public void updateData(ModelLomba update) {
               PreparedStatement statement = null;
        double hasil;
        
        try {
            statement = connect.prepareStatement("update lomba set alur=?, orisinalitas=?,pemilihanKata=?, nilai=? where judul=? ");
            hasil = update.getAlur() + update.getOrisinalitas() + update.getPemilihanKata()/3;
            

            statement.setDouble(1, update.getAlur());
            statement.setDouble(2, update.getOrisinalitas());
            statement.setDouble(3, update.getOrisinalitas());
            statement.setDouble(4, update.getNilai());
            statement.setString(5, update.getJudul());
            statement.execute();
            
            
        } catch (SQLException e) {
            System.out.println("Update Err : " + e.getMessage());
        }
    }

    @Override
    public void deleteData(String judul) {
         PreparedStatement statement = null;
         
         try {
            statement = connect.prepareStatement("delete from lomba where judul=?");
            statement.setString(1, judul);
            statement.execute();
        } catch (SQLException e) {
             System.out.println("Delete Err : " + e.getMessage());
        }
    }

}
