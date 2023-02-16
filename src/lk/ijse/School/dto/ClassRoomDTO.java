package lk.ijse.School.dto;


public class ClassRoomDTO {
    String clssID;
    String className;
    String sectionID;

    public ClassRoomDTO() {
    }

    public ClassRoomDTO(String clssID, String className, String sectionID) {
        this.clssID = clssID;
        this.className = className;
        this.sectionID = sectionID;

    }


    public String getClssID() {
        return clssID;
    }

    public void setClssID(String clssID) {
        this.clssID = clssID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }
}
