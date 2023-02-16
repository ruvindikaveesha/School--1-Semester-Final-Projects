package lk.ijse.School.entity;

public class Student {
    private String studentIndexNumber;
    private String studentFirstName;
    private String studentLastName;
    private String gender;
    private String religion;
    private String motherFirstName;
    private String birthDay;
    private String motherLastName;
    private String fatherFirstName;
    private String fatherLastName;
    private String fatherOccupation;
    private String studentAddress;
    private String contact;

    @Override
    public String toString() {
        return "Student{" +
                "studentIndexNumber='" + studentIndexNumber + '\'' +
                ", studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", gender='" + gender + '\'' +
                ", religion='" + religion + '\'' +
                ", motherFirstName='" + motherFirstName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", motherLastName='" + motherLastName + '\'' +
                ", fatherFirstName='" + fatherFirstName + '\'' +
                ", fatherLastName='" + fatherLastName + '\'' +
                ", fatherOccupation='" + fatherOccupation + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public Student(String studentIndexNumber, String studentFirstName, String studentLastName, String gender, String religion, String motherFirstName, String birthDay, String motherLastName, String fatherFirstName, String fatherLastName, String fatherOccupation, String studentAddress, String contact) {
        this.studentIndexNumber = studentIndexNumber;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.gender = gender;
        this.religion = religion;
        this.motherFirstName = motherFirstName;
        this.birthDay = birthDay;
        this.motherLastName = motherLastName;
        this.fatherFirstName = fatherFirstName;
        this.fatherLastName = fatherLastName;
        this.fatherOccupation = fatherOccupation;
        this.studentAddress = studentAddress;
        this.contact = contact;
    }

    public String getStudentIndexNumber() {
        return studentIndexNumber;
    }

    public void setStudentIndexNumber(String studentIndexNumber) {
        this.studentIndexNumber = studentIndexNumber;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMotherFirstName() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

















