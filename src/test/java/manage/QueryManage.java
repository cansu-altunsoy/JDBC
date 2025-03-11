package manage;

public class QueryManage {




    private String croneSchedulesQuery = " SELECT name FROM  cron_schedules LIMIT 2";

    private  String userMobileUpdateQuery = " UPDATE users  SET mobile = ?  WHERE username LIKE ? ";

    private String deviceTokenInsertQuery ="INSERT INTO device_tokens (id, user_id, is_app, token) VALUES (?,?,?, 'NewwToken');";



    //==============SELECT==========================
    public String getCroneSchedulesQuery() {

        return croneSchedulesQuery;
    }


    //==============UPDATE========================

    public String getUserMobileUpdateQuery() {

        return userMobileUpdateQuery;
    }


    //===============INSERT==============

    public String getDeviceTokenInsertQuery() {
        return deviceTokenInsertQuery;
    }



















}
