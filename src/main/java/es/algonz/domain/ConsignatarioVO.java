package es.algonz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONSIGNATARIO")
public class ConsignatarioVO implements Serializable
{
	private static final long serialVersionUID = 1579215533955599783L;

	//TODO: El codEmpresa quiza se relacione con el remitente
	@Column(name = "CodEmpresa")
	private String codEmpresa;
	
	//TODO: El codigo se obtiene de contadores?
	@Column(name = "Codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String codigo;
	
	@Column(name = "CodDpto")
	private String codDpto;
	@Column(name = "TipoCli")
	private String tipoCli;
	@Column(name = "Tipo")
	private String tipo;
	@Column(name = "Ruta")
	private String ruta;
	@Column(name = "NomFiscal")
	private String nomFiscal;
	@Column(name = "NomComercial")
	private String nomComercial;
	@Column(name = "Zona")
	private String zona;
	@Column(name = "Alias")
	private String alias;
	@Column(name = "Direccion")
	private String direccion;
	@Column(name = "Municipio")
	private String municipio;
	@Column(name = "Poblacion")
	private String poblacion;
	@Column(name = "Isla")
	private String isla;
	@Column(name = "Provincia")
	private String provincia;
	@Column(name = "CCAA")
	private String cCAA;
	@Column(name = "CodPostal")
	private String codPostal;
	@Column(name = "CodGeografico")
	private String codGeografico;
	@Column(name = "Pais")
	private String pais;
	@Column(name = "TipoDocumento")
	private String tipoDocumento;
	@Column(name = "Documento")
	private String documento;
	@Column(name = "CodEan")
	private String codEan;
	@Column(name = "CodProv")
	private String codProv;
	@Column(name = "Teleflin1")
	private String teleflin1;
	@Column(name = "Teleflin2")
	private String teleflin2;
	@Column(name = "Teleflin3")
	private String teleflin3;
	@Column(name = "TeleFax")
	private String teleFax;
	@Column(name = "TelefMovil")
	private String telefMovil;
	@Column(name = "eMail")
	private String eMail;
	@Column(name = "Url")
	private String url;
	@Column(name = "Repartidor")
	private String repartidor;
	@Column(name = "Tarifa")
	private String tarifa;
	@Column(name = "MinimoPortes")
	private String minimoPortes;
	@Column(name = "FmtEnvio")
	private String fmtEnvio;
	@Column(name = "Vendedor")
	private String vendedor;
	@Column(name = "Autorizacion")
	private String autorizacion;
	@Column(name = "FoPago")
	private String foPago;
	@Column(name = "DirecEnvio")
	private String direcEnvio;
	@Column(name = "NombCorreo")
	private String nombCorreo;
	@Column(name = "DirecCorreo")
	private String direcCorreo;
	@Column(name = "PobCorreo")
	private String pobCorreo;
	@Column(name = "ProCorreo")
	private String proCorreo;
	@Column(name = "CodPostalCorreo")
	private String codPostalCorreo;
	@Column(name = "ApdoCorreos")
	private String apdoCorreos;
	@Column(name = "DiaDescanso")
	private String diaDescanso;
	@Column(name = "Tratamiento")
	private String tratamiento;
	@Column(name = "CodAgencia")
	private String codAgencia;
	@Column(name = "Estado")
	private String estado;
	@Column(name = "ObsGeneral")
	private String obsGeneral;	
	@Column(name = "ObsInterna")
	private String obsInterna;
	
	@OneToOne
	@JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario")
	private UsuarioVO usuario;
	
	public String getObsGeneral() {
		return obsGeneral;
	}
	public void setObsGeneral(String obsGeneral) {
		this.obsGeneral = obsGeneral;
	}
	public String getObsInterna() {
		return obsInterna;
	}
	public void setObsInterna(String obsInterna) {
		this.obsInterna = obsInterna;
	}
	public String getFmtEnvio() {
		return fmtEnvio;
	}
	public void setFmtEnvio(String fmtEnvio) {
		this.fmtEnvio = fmtEnvio;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}
	public String getFoPago() {
		return foPago;
	}
	public void setFoPago(String foPago) {
		this.foPago = foPago;
	}
	public String getDirecEnvio() {
		return direcEnvio;
	}
	public void setDirecEnvio(String direcEnvio) {
		this.direcEnvio = direcEnvio;
	}
	public String getNombCorreo() {
		return nombCorreo;
	}
	public void setNombCorreo(String nombCorreo) {
		this.nombCorreo = nombCorreo;
	}
	public String getDirecCorreo() {
		return direcCorreo;
	}
	public void setDirecCorreo(String direcCorreo) {
		this.direcCorreo = direcCorreo;
	}
	public String getPobCorreo() {
		return pobCorreo;
	}
	public void setPobCorreo(String pobCorreo) {
		this.pobCorreo = pobCorreo;
	}
	public String getProCorreo() {
		return proCorreo;
	}
	public void setProCorreo(String proCorreo) {
		this.proCorreo = proCorreo;
	}
	public String getCodPostalCorreo() {
		return codPostalCorreo;
	}
	public void setCodPostalCorreo(String codPostalCorreo) {
		this.codPostalCorreo = codPostalCorreo;
	}
	public String getApdoCorreos() {
		return apdoCorreos;
	}
	public void setApdoCorreos(String apdoCorreos) {
		this.apdoCorreos = apdoCorreos;
	}
	public String getDiaDescanso() {
		return diaDescanso;
	}
	public void setDiaDescanso(String diaDescanso) {
		this.diaDescanso = diaDescanso;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getCodAgencia() {
		return codAgencia;
	}
	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodDpto() {
		return codDpto;
	}
	public void setCodDpto(String codDpto) {
		this.codDpto = codDpto;
	}
	public String getTipoCli() {
		return tipoCli;
	}
	public void setTipoCli(String tipoCli) {
		this.tipoCli = tipoCli;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getNomComercial() {
		return nomComercial;
	}
	public void setNomComercial(String nomComercial) {
		this.nomComercial = nomComercial;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getIsla() {
		return isla;
	}
	public void setIsla(String isla) {
		this.isla = isla;
	}
	public String getcCAA() {
		return cCAA;
	}
	public void setcCAA(String cCAA) {
		this.cCAA = cCAA;
	}
	public String getCodGeografico() {
		return codGeografico;
	}
	public void setCodGeografico(String codGeografico) {
		this.codGeografico = codGeografico;
	}
	public String getCodEan() {
		return codEan;
	}
	public void setCodEan(String codEan) {
		this.codEan = codEan;
	}
	public String getCodProv() {
		return codProv;
	}
	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}
	public String getTeleflin2() {
		return teleflin2;
	}
	public void setTeleflin2(String teleflin2) {
		this.teleflin2 = teleflin2;
	}
	public String getTeleflin3() {
		return teleflin3;
	}
	public void setTeleflin3(String teleflin3) {
		this.teleflin3 = teleflin3;
	}
	public String getTeleFax() {
		return teleFax;
	}
	public void setTeleFax(String teleFax) {
		this.teleFax = teleFax;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRepartidor() {
		return repartidor;
	}
	public void setRepartidor(String repartidor) {
		this.repartidor = repartidor;
	}
	public String getTarifa() {
		return tarifa;
	}
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	public String getMinimoPortes() {
		return minimoPortes;
	}
	public void setMinimoPortes(String minimoPortes) {
		this.minimoPortes = minimoPortes;
	}
	public String getCodEmpresa()
	{
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa)
	{
		this.codEmpresa = codEmpresa;
	}
	public String getCodigo()
	{
		return codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public String getNomFiscal()
	{
		return nomFiscal;
	}
	public void setNomFiscal(String nomFiscal)
	{
		this.nomFiscal = nomFiscal;
	}
	public String getDireccion()
	{
		return direccion;
	}
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	public String getPoblacion()
	{
		return poblacion;
	}
	public void setPoblacion(String poblacion)
	{
		this.poblacion = poblacion;
	}
	public String getProvincia()
	{
		return provincia;
	}
	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}
	public String getCodPostal()
	{
		return codPostal;
	}
	public void setCodPostal(String codPostal)
	{
		this.codPostal = codPostal;
	}
	public String getPais()
	{
		return pais;
	}
	public void setPais(String pais)
	{
		this.pais = pais;
	}
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento()
	{
		return documento;
	}
	public void setDocumento(String documento)
	{
		this.documento = documento;
	}
	public String getTeleflin1()
	{
		return teleflin1;
	}
	public void setTeleflin1(String teleflin1)
	{
		this.teleflin1 = teleflin1;
	}
	public String getTelefMovil()
	{
		return telefMovil;
	}
	public void setTelefMovil(String telefMovil)
	{
		this.telefMovil = telefMovil;
	}
	public String geteMail()
	{
		return eMail;
	}
	public void seteMail(String eMail)
	{
		this.eMail = eMail;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConsignatarioVO) {
			ConsignatarioVO other = (ConsignatarioVO) obj;
			if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
				return false;
			}
			return true;
		}
		return false;
	}

}
