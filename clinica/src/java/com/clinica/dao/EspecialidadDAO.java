
package com.clinica.dao;

import com.clinica.bean.Especialidad;
import com.clinica.util.DBconex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EspecialidadDAO {

    private Connection cn = DBconex.getConexion();
    private PreparedStatement pt;
    private ResultSet rs;
    
    public List<Especialidad> lista_especialidades(){
        List<Especialidad> lista = null;
        try{
            lista = new ArrayList<Especialidad>();
            String cadena = "select * from especialidad order by nomb_espe asc";            
            pt = cn.prepareStatement(cadena);
            rs = pt.executeQuery();              
            Especialidad especialidad = new Especialidad();
            while(rs.next()){                
                especialidad = new Especialidad();
                especialidad.setId_espe(Long.valueOf(rs.getString("id_espe")));
                especialidad.setNomb_espe(rs.getString("nomb_espe"));
                lista.add(especialidad);
            }
        }catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
        return lista;
    }
    
}
