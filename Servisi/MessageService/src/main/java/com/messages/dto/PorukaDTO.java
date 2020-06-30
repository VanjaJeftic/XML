package com.messages.dto;

import java.time.LocalDateTime;

public class PorukaDTO {

	    private Long id;

	    private String sadrzaj;
	  
	    private LocalDateTime datum;

	    private Long bundle;

	    private boolean iznm;
	    
	    private String kreator;
	    
	    private String dat;
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getSadrzaj() {
			return sadrzaj;
		}

		public void setSadrzaj(String sadrzaj) {
			this.sadrzaj = sadrzaj;
		}

		public LocalDateTime getDatum() {
			return datum;
		}

		public void setDatum(LocalDateTime datum) {
			this.datum = datum;
		}

		
		
		
		public Long getBundle() {
			return bundle;
		}

		public void setBundle(Long bundle) {
			this.bundle = bundle;
		}

		public boolean isIznm() {
			return iznm;
		}

		public void setIznm(boolean iznm) {
			this.iznm = iznm;
		}
		
		

		public String getKreator() {
			return kreator;
		}

		public void setKreator(String kreator) {
			this.kreator = kreator;
		}

		public String getDat() {
			return dat;
		}

		public void setDat(String dat) {
			this.dat = dat;
		}

		public PorukaDTO() {
			super();
		}
	    
	    
	    
}
