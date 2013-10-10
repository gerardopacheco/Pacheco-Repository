package com.clinica.managedBean;

import com.clinica.bean.*;
import com.clinica.dao.EmpleadoDAO;
import com.clinica.dao.EspecialidadDAO;
import com.clinica.dao.UbigeoDAO;
import com.clinica.datamodel.EmpleadoDataModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "medicoMB")
@ViewScoped
public class MedicoMB {

    private String pdniMedi;
    private String pnombMedi;
    private Long pidEspeMedi;
    private String dniMedi;
    private String nombMedi;
    private String apepatMedi;
    private String apematMedi;
    private String sexoMedi;
    private Date fecnacMedi;
    private String lugnacMedi;
    private Long depaMedi;
    private Long provMedi;
    private Long distMedi;
    private String direMedi;
    private String telefMedi;
    private String movilMedi;
    private Long idEspeMedi;
    private List<SelectItem> comboDepartamento;
    private List<SelectItem> comboProvincia;
    private List<SelectItem> comboDistrito;
    private List<SelectItem> comboEspecialidad;
    public List<Empleado> listado_BusquedaMedico;
    public EmpleadoDataModel medicoDM;
    public Empleado medicoSelected;
    EmpleadoDAO empleadoDAO;

    public MedicoMB() {

        empleadoDAO = new EmpleadoDAO();
        listado_BusquedaMedico = new ArrayList<Empleado>();
        medicoDM = new EmpleadoDataModel(listado_BusquedaMedico);
    }

    public void preEditarMedico() {
        dniMedi = medicoSelected.getPersona().getDni_psn();
        nombMedi = medicoSelected.getPersona().getNomb_psn();
        apepatMedi = medicoSelected.getPersona().getApepat_psn();
        apematMedi = medicoSelected.getPersona().getApemat_psn();
        sexoMedi = medicoSelected.getPersona().getSexo_psn();
        fecnacMedi = new Date();
        lugnacMedi = medicoSelected.getPersona().getLugnac_psn();
        depaMedi = medicoSelected.getPersona().getDepa_psn();
        provMedi = medicoSelected.getPersona().getProv_psn();
        distMedi = medicoSelected.getPersona().getDist_psn();
        direMedi = medicoSelected.getPersona().getDire_psn();
        telefMedi = medicoSelected.getPersona().getTelef_psn();
        movilMedi = medicoSelected.getPersona().getMovil_psn();
        RequestContext.getCurrentInstance().update("frmEditarMedico:dlgEditarMedico");
        RequestContext.getCurrentInstance().execute("frmEditarMedico:dlgEditarMedico.show()");
    }

    public void preRegistrarMedico() {
        this.limpiar();
        RequestContext.getCurrentInstance().update("frmAgregarMedico:dlgAgregarMedico");
        RequestContext.getCurrentInstance().execute("frmAgregarMedico:dlgAgregarMedico.show()");
    }

    public void busquedaMedico() {
        listado_BusquedaMedico = empleadoDAO.lista_busquedaMedico(pdniMedi, pnombMedi, pidEspeMedi);
        medicoDM = new EmpleadoDataModel(listado_BusquedaMedico);
        RequestContext.getCurrentInstance().update("frmConsultaMedico:tblConsultaMedico");
    }

    public void registrarMedico() {

        if (dniMedi.trim().length() == 0) {
            String msj = "Dni no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (nombMedi.trim().length() == 0) {
            String msj = "Nombre no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (apepatMedi.trim().length() == 0) {
            String msj = "Apellido Paterno no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (apematMedi.trim().length() == 0) {
            String msj = "Apellido Materno no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (depaMedi == 0) {
            String msj = "Debe seleccionar un Departamento";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (provMedi == 0) {
            String msj = "Debe seleccionar una Provincia";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (distMedi == 0) {
            String msj = "Debe seleccionar un Distrito";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else if (idEspeMedi == 0) {
            String msj = "Debe seleccionar una Especialidad";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmAgregarMedico:msgAgregarMedico");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Perfil perfil = new Perfil();
            perfil.setId_pf(2l);

            Persona persona = new Persona();
            persona.setDni_psn(dniMedi);
            persona.setNomb_psn(nombMedi);
            persona.setApepat_psn(apepatMedi);
            persona.setApemat_psn(apematMedi);
            persona.setSexo_psn(sexoMedi);
            persona.setFecnac_psn(sdf.format(fecnacMedi));
            persona.setLugnac_psn(lugnacMedi);
            persona.setDepa_psn(depaMedi);
            persona.setProv_psn(provMedi);
            persona.setDist_psn(distMedi);
            persona.setDire_psn(direMedi);
            persona.setTelef_psn(telefMedi);
            persona.setMovil_psn(movilMedi);

            Especialidad especialidad = new Especialidad();
            especialidad.setId_espe(idEspeMedi);

            Empleado empleado = new Empleado();
            empleado.setPerfil(perfil);
            empleado.setPersona(persona);
            empleado.setEspecialidad(especialidad);

            int i = empleadoDAO.registraMedico(empleado);

            if (i > 0) {
                String msj = "Medico Registrado Satisfactoriamente";
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msj, null);
                FacesContext.getCurrentInstance().addMessage(null, fm);
                listado_BusquedaMedico = empleadoDAO.lista_busquedaMedico(pdniMedi, pnombMedi, pidEspeMedi);
                medicoDM = new EmpleadoDataModel(listado_BusquedaMedico);

            } else {
                String msj = "Error al Intentar Registar Medico";
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
                FacesContext.getCurrentInstance().addMessage(null, fm);

            }
            RequestContext.getCurrentInstance().update("frmConsultaMedico:tblConsultaMedico");
            RequestContext.getCurrentInstance().update("frmConsultaMedico:msgBandejaMedico");
            RequestContext.getCurrentInstance().execute("frmAgregarMedico:dlgAgregarMedico.hide()");
        }



    }

    public void editarMedico() {

        if (dniMedi.trim().length() == 0) {
            String msj = "Dni no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (nombMedi.trim().length() == 0) {
            String msj = "Nombre no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (apepatMedi.trim().length() == 0) {
            String msj = "Apellido Paterno no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (apematMedi.trim().length() == 0) {
            String msj = "Apellido Materno no puede ser vacio";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (depaMedi == 0) {
            String msj = "Debe seleccionar un Departamento";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (provMedi == 0) {
            String msj = "Debe seleccionar una Provincia";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (distMedi == 0) {
            String msj = "Debe seleccionar un Distrito";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else if (idEspeMedi == 0) {
            String msj = "Debe seleccionar una Especialidad";
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update("frmEditarMedico:msgEditarMedico");
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Perfil perfil = new Perfil();
            perfil.setId_pf(2l);

            Persona persona = new Persona();
            persona.setId_psn(medicoSelected.getPersona().getId_psn());
            persona.setDni_psn(dniMedi);
            persona.setNomb_psn(nombMedi);
            persona.setApepat_psn(apepatMedi);
            persona.setApemat_psn(apematMedi);
            persona.setSexo_psn(sexoMedi);
            persona.setFecnac_psn(sdf.format(fecnacMedi));
            persona.setLugnac_psn(lugnacMedi);
            persona.setDepa_psn(depaMedi);
            persona.setProv_psn(provMedi);
            persona.setDist_psn(distMedi);
            persona.setDire_psn(direMedi);
            persona.setTelef_psn(telefMedi);
            persona.setMovil_psn(movilMedi);

            Especialidad especialidad = new Especialidad();
            especialidad.setId_espe(idEspeMedi);

            Empleado empleado = new Empleado();
            empleado.setId_emp(medicoSelected.getId_emp());
            empleado.setPerfil(perfil);
            empleado.setPersona(persona);
            empleado.setEspecialidad(especialidad);

            int i = empleadoDAO.editaMedico(empleado);

            if (i > 0) {
                String msj = "Informacion de Medico Actualizada Satisfactoriamente";
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msj, null);
                FacesContext.getCurrentInstance().addMessage(null, fm);
                listado_BusquedaMedico = empleadoDAO.lista_busquedaMedico(pdniMedi, pnombMedi, pidEspeMedi);
                medicoDM = new EmpleadoDataModel(listado_BusquedaMedico);

            } else {
                String msj = "Error al Intentar Actualizar Informacion Medico";
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null);
                FacesContext.getCurrentInstance().addMessage(null, fm);

            }
            RequestContext.getCurrentInstance().update("frmConsultaMedico:tblConsultaMedico");
            RequestContext.getCurrentInstance().update("frmConsultaMedico:msgBandejaMedico");
            RequestContext.getCurrentInstance().execute("frmEditarMedico:dlgEditarMedico.hide()");

        }



    }

    private void limpiar() {
        dniMedi = "";
        nombMedi = "";
        apepatMedi = "";
        apematMedi = "";
        sexoMedi = "";
        fecnacMedi = new Date();
        lugnacMedi = "";
        depaMedi = 0l;
        provMedi = 0l;
        distMedi = 0l;
        direMedi = "";
        telefMedi = "";
        movilMedi = "";
        idEspeMedi = 0l;
        medicoSelected = null;
    }

    public String getPdniMedi() {
        return pdniMedi;
    }

    public void setPdniMedi(String pdniMedi) {
        this.pdniMedi = pdniMedi;
    }

    public String getPnombMedi() {
        return pnombMedi;
    }

    public void setPnombMedi(String pnombMedi) {
        this.pnombMedi = pnombMedi;
    }

    public List<Empleado> getListado_BusquedaMedico() {
        return listado_BusquedaMedico;
    }

    public void setListado_BusquedaMedico(List<Empleado> listado_BusquedaMedico) {
        this.listado_BusquedaMedico = listado_BusquedaMedico;
    }

    public EmpleadoDataModel getMedicoDM() {
        return medicoDM;
    }

    public void setMedicoDM(EmpleadoDataModel medicoDM) {
        this.medicoDM = medicoDM;
    }

    public Empleado getMedicoSelected() {
        return medicoSelected;
    }

    public void setMedicoSelected(Empleado medicoSelected) {
        this.medicoSelected = medicoSelected;
    }

    public String getApematMedi() {
        return apematMedi;
    }

    public void setApematMedi(String apematMedi) {
        this.apematMedi = apematMedi;
    }

    public String getApepatMedi() {
        return apepatMedi;
    }

    public void setApepatMedi(String apepatMedi) {
        this.apepatMedi = apepatMedi;
    }

    public Long getDepaMedi() {
        return depaMedi;
    }

    public void setDepaMedi(Long depaMedi) {
        this.depaMedi = depaMedi;
    }

    public String getDireMedi() {
        return direMedi;
    }

    public void setDireMedi(String direMedi) {
        this.direMedi = direMedi;
    }

    public Long getDistMedi() {
        return distMedi;
    }

    public void setDistMedi(Long distMedi) {
        this.distMedi = distMedi;
    }

    public String getDniMedi() {
        return dniMedi;
    }

    public void setDniMedi(String dniMedi) {
        this.dniMedi = dniMedi;
    }

    public Long getIdEspeMedi() {
        return idEspeMedi;
    }

    public void setIdEspeMedi(Long idEspeMedi) {
        this.idEspeMedi = idEspeMedi;
    }

    public Date getFecnacMedi() {
        return fecnacMedi;
    }

    public void setFecnacMedi(Date fecnacMedi) {
        this.fecnacMedi = fecnacMedi;
    }

    public String getLugnacMedi() {
        return lugnacMedi;
    }

    public void setLugnacMedi(String lugnacMedi) {
        this.lugnacMedi = lugnacMedi;
    }

    public String getMovilMedi() {
        return movilMedi;
    }

    public void setMovilMedi(String movilMedi) {
        this.movilMedi = movilMedi;
    }

    public String getNombMedi() {
        return nombMedi;
    }

    public void setNombMedi(String nombMedi) {
        this.nombMedi = nombMedi;
    }

    public Long getProvMedi() {
        return provMedi;
    }

    public void setProvMedi(Long provMedi) {
        this.provMedi = provMedi;
    }

    public String getSexoMedi() {
        return sexoMedi;
    }

    public void setSexoMedi(String sexoMedi) {
        this.sexoMedi = sexoMedi;
    }

    public String getTelefMedi() {
        return telefMedi;
    }

    public void setTelefMedi(String telefMedi) {
        this.telefMedi = telefMedi;
    }

    public List<SelectItem> getComboDepartamento() {
        comboDepartamento = new ArrayList();
        UbigeoDAO ubigeo = new UbigeoDAO();
        List<Departamento> listado = ubigeo.lista_departamento();
        for (Departamento depa : listado) {
            SelectItem opt = new SelectItem();
            opt.setLabel(depa.getDesc_depa());
            opt.setValue(depa.getId_depa());
            comboDepartamento.add(opt);
        }
        return comboDepartamento;
    }

    public void setComboDepartamento(List<SelectItem> comboDepartamento) {
        this.comboDepartamento = comboDepartamento;
    }

    public List<SelectItem> getComboDistrito() {
        if (depaMedi != null && provMedi != null) {
            comboDistrito = new ArrayList();
            UbigeoDAO ubigeo = new UbigeoDAO();
            List<Distrito> listado = ubigeo.lista_distrito(depaMedi, provMedi);
            for (Distrito dist : listado) {
                SelectItem opt = new SelectItem();
                opt.setLabel(dist.getDesc_dist());
                opt.setValue(dist.getId_dist());
                comboDistrito.add(opt);
            }
        }
        return comboDistrito;
    }

    public void setComboDistrito(List<SelectItem> comboDistrito) {
        this.comboDistrito = comboDistrito;
    }

    public List<SelectItem> getComboProvincia() {
        if (depaMedi != null) {
            comboProvincia = new ArrayList();
            UbigeoDAO ubigeo = new UbigeoDAO();
            List<Provincia> listado = ubigeo.lista_provincia(depaMedi);
            for (Provincia prov : listado) {
                SelectItem opt = new SelectItem();
                opt.setLabel(prov.getDesc_prov());
                opt.setValue(prov.getId_prov());
                comboProvincia.add(opt);
            }
        }

        return comboProvincia;
    }

    public void setComboProvincia(List<SelectItem> comboProvincia) {
        this.comboProvincia = comboProvincia;
    }

    public List<SelectItem> getComboEspecialidad() {
        comboEspecialidad = new ArrayList();
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        List<Especialidad> listado = especialidadDAO.lista_especialidades();
        for (Especialidad especialidad : listado) {
            SelectItem opt = new SelectItem();
            opt.setLabel(especialidad.getNomb_espe());
            opt.setValue(especialidad.getId_espe());
            comboEspecialidad.add(opt);
        }
        return comboEspecialidad;
    }

    public void setComboEspecialidad(List<SelectItem> comboEspecialidad) {
        this.comboEspecialidad = comboEspecialidad;
    }

    public Long getPidEspeMedi() {
        return pidEspeMedi;
    }

    public void setPidEspeMedi(Long pidEspeMedi) {
        this.pidEspeMedi = pidEspeMedi;
    }
}
