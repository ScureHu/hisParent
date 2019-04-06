package entity;

public class Result {
    //返回是否成功
    private boolean flag;
    //返回代码
    private Integer code;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    //返回成功
    public static Result success(Object data,String message){
        Result result = new Result();
        result.setFlag(true);
        result.setCode(StatusCode.OK);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    //不带信息返回成功
    public static Result success(Object data){
        Result result = new Result();
        result.setFlag(true);
        result.setCode(StatusCode.OK);
        result.setData(data);
        return result;
    }
    //返回成功不带数据，只有成功信息
    public static Result success(String message){
        Result result = new Result();
        result.setFlag(true);
        result.setCode(StatusCode.OK);
        result.setMessage(message);
        return result;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
