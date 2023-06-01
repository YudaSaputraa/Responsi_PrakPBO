/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.ModelLomba;

/**
 *
 * @author Lab Informatika
 */
public interface InterfaceLomba {
    public List<ModelLomba> getAll();
    public void insertdata(ModelLomba lomba);
    public void updateData(ModelLomba update);
    public void deleteData(String judul);
    
}
