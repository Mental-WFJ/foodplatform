package com.frontEnd.dao;

import com.frontEnd.entity.Member;

import java.util.List;

public interface MemberDao {
    /**
     * 根据查询条件查询会员列表
     * @param member 查询条件
     * @return 会员列表
     */
    List<Member> select(Member member);

    /**
     * 创建会员
     */
    int insert(Member member);

    /**
     * 修改会员信息
     */
    int update(Member member);


}