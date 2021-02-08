package test;

public class studentGrade {
	private String ID;
	private String name;
	private String classId;
	private String tot_cred;
	private String major_name;
	private String course_id;
	private String sec_id;
	private String semester;
	private String year;
	private String grade;
	public studentGrade(String iD, String name, String classId, String tot_cred, String major_name, String course_id,
			String sec_id, String semester, String year, String grade) {
		super();
		ID = iD;
		this.name = name;
		this.classId = classId;
		this.tot_cred = tot_cred;
		this.major_name = major_name;
		this.course_id = course_id;
		this.sec_id = sec_id;
		this.semester = semester;
		this.year = year;
		this.grade = grade;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getTot_cred() {
		return tot_cred;
	}
	public void setTot_cred(String tot_cred) {
		this.tot_cred = tot_cred;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getSec_id() {
		return sec_id;
	}
	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
