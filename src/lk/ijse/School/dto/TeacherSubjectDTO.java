package lk.ijse.School.dto;


public class TeacherSubjectDTO {
    String teacherId;
    String subjectId;

    public TeacherSubjectDTO(String teacherId, String subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public TeacherSubjectDTO() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

}
