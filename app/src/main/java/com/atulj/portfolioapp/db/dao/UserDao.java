package com.atulj.portfolioapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.atulj.portfolioapp.db.entities.User;

@Dao
public interface UserDao {

    @Query("select * from user where id=:id")
    User getUser(int id);

    @Query("select exists(select * from User where user_name=:username)")
    boolean usernameExists(String username);

    @Query("select * from User where user_name=:username and password=:password")
    User validateUsernameAndPassword(String username, String password);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}
