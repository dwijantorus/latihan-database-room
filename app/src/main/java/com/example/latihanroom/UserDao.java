package com.example.latihanroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM user WHERE name LIKE :name")
//    List<User> findByName(String name);

//    @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%'")
//    List<User> findByName(String name);

    @Insert
    void insert(User user);

//    @Insert
//    void insertAll(User... users);

    @Delete
    void delete(User user);
}
