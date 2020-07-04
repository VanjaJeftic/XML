package com.agentApp.app.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agentApp.app.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "last_password_reset_date")
    private java.sql.Timestamp lastPasswordResetDate;
	
	@Column(name = "enabled")
    private boolean enabled;
	
	@Column(name = "lastname", nullable = true)
	private String lastname;
	
	@Column(name = "firstname", nullable = true)
	private String firstname;
	
	@Column(name = "adress", nullable = true)
	private String adress;

	@Column(name = "email", nullable = false,unique = true)
	private String email;

	@Column(name = "city", nullable = true)
	private String city;
	
	@Column(name = "country", nullable = true)
	private String country;

	@Column(name = "maticnibroj", nullable = true)
	private String maticnibroj;

	@Column(name = "nazivfirme", nullable = true)
	private String nazivfirme;

	@Column(name = "phonenumber", nullable = true)
	private String phoneNumber;
	
	@Column(name = "uloga", nullable = false)
	private String uloga;
	
	@Column(name = "aktiviran", nullable = false)
	private boolean nalogAktiviran;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(cascade =  {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)

	@JoinTable(name = "user_authority", 
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Vozilo> vozila = new HashSet<Vozilo>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Cenovnik> cenovnik = new HashSet<Cenovnik>();


	public User(UserDTO dto) {
		this.id = dto.getId();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.lastPasswordResetDate = dto.getLastPasswordResetDate();
		this.enabled = dto.isEnabled();
		this.lastname = dto.getLastname();
		this.firstname = dto.getFirstname();
		this.adress = dto.getAdress();
		this.email = dto.getEmail();
		this.city = dto.getCity();
		this.country = dto.getCountry();
		this.phoneNumber = dto.getPhoneNumber();
		this.uloga = dto.getUloga();
		this.nalogAktiviran = dto.isNalogAktiviran();
		this.roles = dto.getAuthorities();
		this.maticnibroj=dto.getMaticnibroj();
		this.nazivfirme=dto.getNazivfirme();


	}

	public Set<Vozilo> getVozila() {
		return vozila;
	}

	public void setVozila(Set<Vozilo> vozila) {
		this.vozila = vozila;
	}

	public User() {
		
	}

	public String getMaticnibroj() {
		return maticnibroj;
	}



	public void setMaticnibroj(String maticnibroj) {
		this.maticnibroj = maticnibroj;
	}

	public String getNazivfirme() {
		return nazivfirme;
	}

	public void setNazivfirme(String nazivfirme) {
		this.nazivfirme = nazivfirme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	public void setAuthorities(List<Authority> authorities) {
        this.roles = authorities;
    }
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		 if(!this.roles.isEmpty()){
	            Authority r = roles.iterator().next();
	            List<Permission> privileges = new ArrayList<Permission>();
	            for(Permission p : r.getPermissions()){
	                privileges.add(p);
	            }
	            return privileges;
	        }
	        return null;
	}
	
	public java.sql.Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
	public void setLastPasswordResetDate(java.sql.Timestamp now) {
			this.lastPasswordResetDate = now;
    }
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@JsonIgnore
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isNalogAktiviran() {
		return nalogAktiviran;
	}

	public void setNalogAktiviran(boolean nalogAktiviran) {
		this.nalogAktiviran = nalogAktiviran;
	}

	public List<Authority> getRoles() {
		return roles;
	}

	public void setRoles(List<Authority> roles) {
		this.roles = roles;
	}

	public Set<Cenovnik> getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Set<Cenovnik> cenovnik) {
		this.cenovnik = cenovnik;
	}
	
	
	
	
}
