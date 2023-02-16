package lk.ijse.School.entity;

public class Subject {
    private String subjectID;
    private String subjectName;
    private String meadium;




    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMeadium() {
        return meadium;
    }

    public void setMeadium(String meadium) {
        this.meadium = meadium;
    }



    public Subject(String subjectID, String subjectName, String meadium) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.meadium = meadium;

    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectID + '\'' +
                ", subjctName='" + subjectName + '\'' +
                ", meadium='" + meadium + '\'' +
                '}';
    }



}


