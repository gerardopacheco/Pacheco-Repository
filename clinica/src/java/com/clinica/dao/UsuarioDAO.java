package com.clinica.dao;

import com.clinica.bean.Perfil;
import com.clinica.bean.Persona;
import com.clinica.bean.Usuario;
import com.clinica.util.DBconex;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    private Connection cn = DBconex.getConexion();
    private PreparedStatement pt;
    private ResultSet rs;
    private CallableStatement call;

    public Usuario validarUsuario(String plog_usu, String ppass_usu) {
        Usuario usuario = null;
        try {
            usuario = new Usuario();
            String cadena = "select * from usuario where log_usu = '" + plog_usu + "' and "
                    + "pass_usu = '" + ppass_usu + "'";
            pt = cn.prepareStatement(cadena);
            rs = pt.executeQuery();
            while (rs.next()) {

                Persona persona = new Persona();
                persona.setId_psn(Long.valueOf(rs.getString("id_psn")));

                Perfil perfil = new Perfil();
                perfil.setId_pf(Long.valueOf(rs.getString("id_pf")));

                usuario = new Usuario();
                usuario.setPersona(persona);
                usuario.setPerfil(perfil);
                usuario.setId_usu(Long.valueOf(rs.getString("id_usu")));                
                usuario.setLog_usu(rs.getString("log_usu"));
                usuario.setPass_usu(rs.getString("pass_usu"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return usuario;
    }
}
