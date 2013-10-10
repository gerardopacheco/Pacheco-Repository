package com.clinica.bean;

public class Usuario {

    private Long id_usu;
    private String log_usu;
    private String pass_usu;
    private Persona persona;
    private Perfil perfil;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }    

    public Long getId_usu() {
        return id_usu;
    }

    public void setId_usu(Long id_usu) {
        this.id_usu = id_usu;
    }

    public String getLog_usu() {
        return log_usu;
    }

    public void setLog_usu(String log_usu) {
        this.log_usu = log_usu;
    }

    public String getPass_usu() {
        return pass_usu;
    }

    public void setPass_usu(String pass_usu) {
        this.pass_usu = pass_usu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
