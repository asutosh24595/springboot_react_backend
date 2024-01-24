package in.asutosh.reactWithSpringBoot.miniProject.response;

public class EmpResponse {
    private String succMsg;
    private String errMsg;

    public String getSuccMsg() {
        return succMsg;
    }

    public void setSuccMsg(String succMsg) {
        this.succMsg = succMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "EmpResponse{" +
                "succMsg='" + succMsg + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

