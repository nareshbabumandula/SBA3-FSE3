package com.interviewtracker.model;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    /**
     *
     */

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "MOBILE_NO")
    private String mobileNo;
    
//    @ManyToOne
//	@JoinColumn(name="INTERVIEW_ID", nullable=true)
//	private Interview interview;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH}, mappedBy = "attendees")
	private Set<Interview> interviews = new HashSet<>();

    public User() {
        super();
    }

	

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public Set<Interview> getInterviews() {
		return interviews;
	}



	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}
	
	
}