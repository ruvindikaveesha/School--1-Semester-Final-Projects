package lk.ijse.School.bo;

import lk.ijse.School.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case CLASSROOMMANAGE :
                return new ClassRoomManageBOImpl();
            case HOSTALMANAGE:
                return new HostalManageBOImpl();
            case STUDENTMANAGE:
                return new StudentManageBOImpl();
            case SUBJECTMANAGE:
                return new SubjectManageBOImpl();
            case TEACHERMANAGE:
                return new TeacherManageBOImpl();
            case USERMANAGE:
                return new UserManageBOImpl();

            default:
                return null;
        }
    }

    public enum BoTypes {
        CLASSROOMMANAGE,HOSTALMANAGE,STUDENTMANAGE,SUBJECTMANAGE,TEACHERMANAGE,USERMANAGE
    }
}
