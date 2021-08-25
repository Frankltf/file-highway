package file.highway.demo;

/**
 * @Intro
 * @Author liutengfei
 */
public class Response {
    private String msg;
    private Integer status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Response(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public static Response Response(String msg, Integer status){
        Response response = new Response(msg, status);
        return response;
    }


}
