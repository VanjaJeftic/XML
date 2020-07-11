package com.zahtev.dto;

public class VoziloDTO {

	private Long id;
	private String markaVozila;
	private String modelVozila;
	private String klasaVozila;
	private String vrstaMenjaca;
	private String tipGoriva;
	private String predjeniKm;
	private String brsedistadeca;
	private UserDTO user;
	
	public VoziloDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getMarkaVozila() {
		return markaVozila;
	}

	public void setMarkaVozila(String markaVozila) {
		this.markaVozila = markaVozila;
	}

	public String getModelVozila() {
		return modelVozila;
	}

	public void setModelVozila(String modelVozila) {
		this.modelVozila = modelVozila;
	}

	public String getKlasaVozila() {
		return klasaVozila;
	}

	public void setKlasaVozila(String klasaVozila) {
		this.klasaVozila = klasaVozila;
	}

	public String getVrstaMenjaca() {
		return vrstaMenjaca;
	}

	public void setVrstaMenjaca(String vrstaMenjaca) {
		this.vrstaMenjaca = vrstaMenjaca;
	}

	public String getTipGoriva() {
		return tipGoriva;
	}

	public void setTipGoriva(String tipGoriva) {
		this.tipGoriva = tipGoriva;
	}

	public String getPredjeniKm() {
		return predjeniKm;
	}

	public void setPredjeniKm(String predjeniKm) {
		this.predjeniKm = predjeniKm;
	}

	public String getBrsedistadeca() {
		return brsedistadeca;
	}

	public void setBrsedistadeca(String brsedistadeca) {
		this.brsedistadeca = brsedistadeca;
	}

	@Override
	public String toString() {
		return "VoziloDTO [id=" + id + ", markaVozila=" + markaVozila + ", modelVozila=" + modelVozila
				+ ", klasaVozila=" + klasaVozila + ", vrstaMenjaca=" + vrstaMenjaca + ", tipGoriva=" + tipGoriva
				+ ", predjeniKm=" + predjeniKm + ", brsedistadeca=" + brsedistadeca + ", user=" + user + "]";
	}
	
}
