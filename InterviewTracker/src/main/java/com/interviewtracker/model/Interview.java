package com.interviewtracker.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW")
public class Interview implements Serializable {

	    @Id
	    @Column(name = "INTERVIEW_ID", nullable = false)
	    private String interviewId;
	 
	    @Column(name = "INTERVIEWER_NAME")
	    private String interviewerName;

	    @Column(name = "INTERVIEW_NAME")
	    private String name;
	    
	    @Column(name = "USER_SKILLS")
	    private String userSkills;
	    
	    @Column(name = "INTERVIEW_STATUS")
	    private String interviewStatus;
	    
	    @Column(name = "REMARKS")
	    private String remarks;
	    
	    @Column(name="INTERVIEW_DATE")
		private LocalDate  interviewDate;
	    
	    @Column(name="INTERVIEW_TIME")
		private LocalDateTime  interviewTime;
	    
//	    @OneToMany(mappedBy="interview")
//		private java.util.Set<User> attendees;
	    
	    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH})
		@JoinTable(name = "USER_INTERVIEWS", joinColumns = { @JoinColumn(name = "INTERVIEW_ID") }, inverseJoinColumns = {
				@JoinColumn(name = "USER_ID") })
		private Set<User> attendees = new HashSet<>();

		

		public String getInterviewId() {
			return interviewId;
		}

		public void setInterviewId(String interviewId) {
			this.interviewId = interviewId;
		}

		public String getInterviewerName() {
			return interviewerName;
		}

		public void setInterviewerName(String interviewerName) {
			this.interviewerName = interviewerName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUserSkills() {
			return userSkills;
		}

		public void setUserSkills(String userSkills) {
			this.userSkills = userSkills;
		}

		public String getInterviewStatus() {
			return interviewStatus;
		}

		public void setInterviewStatus(String interviewStatus) {
			this.interviewStatus = interviewStatus;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public LocalDate getInterviewDate() {
			return interviewDate;
		}

		public void setInterviewDate(LocalDate interviewDate) {
			this.interviewDate = interviewDate;
		}

		public LocalDateTime getInterviewTime() {
			return interviewTime;
		}

		public void setInterviewTime(LocalDateTime interviewTime) {
			this.interviewTime = interviewTime;
		}

		public java.util.Set<User> getAttendees() {
			return attendees;
		}

		public void setAttendees(java.util.Set<User> attendees) {
			this.attendees = attendees;
		}

		public Interview() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    
}
