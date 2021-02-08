package test;

public class schedule {
	private String course_id;
	private String sec_id;
	private String semester;
	private String year;
	private String building;
	private String classroom;
	private String time_slot;
	private String instructor_id;
	private String instructor_name;
	
	

	 
	public schedule(String course_id, String sec_id, String semester, String year, String building, String classroom,
			String time_slot, String instructor_id, String instructor_name) {
		super();
		this.course_id = course_id;
		this.sec_id = sec_id;
		this.semester = semester;
		this.year = year;
		this.building = building;
		this.classroom = classroom;
		this.time_slot = time_slot;
		this.instructor_id = instructor_id;
		this.instructor_name = instructor_name;
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
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getTime_slot() {
		return time_slot;
	}
	public void setTime_slot(String time_slot) {
		this.time_slot = time_slot;
	}
	public String getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}
	public String getInstructor_name() {
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	
}
