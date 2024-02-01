package lk.playtech.chatapplicationinp.dto;



public class UserDTO {

    private String txtUserName;

    public UserDTO(String userNAME) {

        this.txtUserName=userNAME;
    }

    public String gettxtUserName() {
        return txtUserName;
    }
}
