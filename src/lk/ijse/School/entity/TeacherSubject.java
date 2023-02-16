package lk.ijse.School.entity;

public class TeacherSubject {
    String teacherId;
    String subjectId;

    public TeacherSubject(String teacherId, String subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public TeacherSubject() {
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
