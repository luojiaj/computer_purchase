package com.cy.store.service;

import com.cy.store.entity.User;

//用户模块业务层接口
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User login(String username, String password);

    /**
     *
     * @param uid 用户的uid
     * @param username 用户的用户名
     * @param oldPassword 用户的老密码
     * @param newPassword 用户的新密码
     *                    其余的midified_user和modified_time可以自己设置
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户id查询用户的数据
     * @param uid 用户id
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户数据操作
     * @param uid 用户id
     * @param username 用户名称
     * @param user 用户对象的数据
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 更新用户头像的方法
     * @param uid 用户的uid
     * @param avatar 用户的头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);
}
