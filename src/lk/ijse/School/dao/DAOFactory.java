package lk.ijse.School.dao;

import lk.ijse.School.dao.custom.impl.*;


public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CLASSROOM:
                return new ClassRoomDAOImpl();
            case HOSTAL:
                return new HostalDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case USER:
                return new UserDAOImpl();
            case TEACHER:
                return new TeacherDAOImpl();
            case TEACHERSUBJECT:
                return new TeacherAndSubjectDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CLASSROOM, HOSTAL, STUDENT, SUBJECT, USER,TEACHER,TEACHERSUBJECT
    }
}
