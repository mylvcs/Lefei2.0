package database.UserDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by CSLab on 2018/1/15.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper (Cursor cursor){
        super(cursor);
    }
    public User getUser(){
        String UUIDString = getString(getColumnIndex(UserDbSchema.UserTable.Cols.UserID));
        String UserName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.UserName));
        String UserPwd= getString(getColumnIndex(UserDbSchema.UserTable.Cols.UserPassword));

        User user = new User(UUID.fromString(UUIDString));
        user.setUserName(UserName);
        user.setUserPassword(UserPwd);

        return user;
    }
}
