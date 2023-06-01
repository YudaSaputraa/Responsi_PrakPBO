/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAOLomba.DaoLomba;
import com.mysql.cj.xdevapi.Statement;
import connection.Connector;
import interfaces.InterfaceLomba;
import java.util.List;
import model.ModelLomba;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TableModelLomba;
import view.ViewLomba;

/**
 *
 * @author Lab Informatika
 */
public class ControllerLomba {

    InterfaceLomba iLomba;
    List<ModelLomba> listLomba;

    Connection connect;
    ResultSet result;
    ViewLomba viewLomba;

    public ControllerLomba(ViewLomba viewLomba) {
        this.viewLomba = viewLomba;
        iLomba = new DaoLomba();
        connect = Connector.getConnection();

    }

    public void readData() {
        listLomba = iLomba.getAll();
        TableModelLomba tableLomba = new TableModelLomba(listLomba);
        viewLomba.getTableData().setModel(tableLomba);
    }

    public void insertData() {
        ModelLomba lomba = new ModelLomba();
        Double hasil = Double.parseDouble(viewLomba.getAlurField().getText()) + Double.parseDouble(viewLomba.getOrisinalitasField().getText()) + Double.parseDouble(viewLomba.getPemilihanKataField().getText()) / 3;
        lomba.setJudul(viewLomba.getJudulField().getText());
        lomba.setAlur(Double.parseDouble(viewLomba.getAlurField().getText()));
        lomba.setOrisinalitas(Double.parseDouble(viewLomba.getOrisinalitasField().getText()));
        lomba.setPemilihanKata(Double.parseDouble(viewLomba.getPemilihanKataField().getText()));
        lomba.setNilai(hasil);
        iLomba.insertdata(lomba);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil ");

    }

    public void selectField(int row) {
        viewLomba.getJudulField().setText(listLomba.get(row).getJudul());
        viewLomba.getAlurField().setText(listLomba.get(row).getAlur().toString());
        viewLomba.getOrisinalitasField().setText(listLomba.get(row).getOrisinalitas().toString());
        viewLomba.getPemilihanKataField().setText(listLomba.get(row).getPemilihanKata().toString());
        viewLomba.getJudulField().disable();
    }

    public void updateData() {
        ModelLomba lomba = new ModelLomba();
        try {
            Double hasil = Double.parseDouble(viewLomba.getAlurField().getText()) + Double.parseDouble(viewLomba.getOrisinalitasField().getText()) + Double.parseDouble(viewLomba.getPemilihanKataField().getText()) / 3;
            lomba.setJudul(viewLomba.getJudulField().getText());
            lomba.setAlur(Double.parseDouble(viewLomba.getAlurField().getText()));
            lomba.setOrisinalitas(Double.parseDouble(viewLomba.getOrisinalitasField().getText()));
            lomba.setPemilihanKata(Double.parseDouble(viewLomba.getPemilihanKataField().getText()));
            lomba.setNilai(hasil);
            iLomba.updateData(lomba);
            JOptionPane.showMessageDialog(null, "Update Data Berhasil ");
        } catch (Exception e) {
            System.out.println("Err update : " + e.getMessage());
        }

    }

    public void reset() {
        viewLomba.getJudulField().setText("");
        viewLomba.getAlurField().setText("");
        viewLomba.getOrisinalitasField().setText("");
        viewLomba.getPemilihanKataField().setText("");
        viewLomba.getJudulField().enable();
    }

    public void deleteData() {
        ModelLomba lomba = new ModelLomba();
        iLomba.deleteData(viewLomba.getJudulField().getText());

    }

    public void searchData(String key) {
        PreparedStatement statement;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("judul");
        model.addColumn("alur");
        model.addColumn("orisinalitas");

        model.addColumn("pemilihanKata");
        model.addColumn("nilai");

        viewLomba.getTableData().setModel(model);
        try {
            statement = connect.prepareStatement(key);
            ResultSet result = statement.executeQuery("Select * from lomba where judul like '" + key + "'");
            while (result.next()) {
                model.addRow(new Object[]{
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),});

            }
viewLomba.getTableData().setModel(model);
        } catch (Exception e) {
        }
    }
}
