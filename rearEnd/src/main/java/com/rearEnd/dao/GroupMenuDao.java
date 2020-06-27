package com.rearEnd.dao;

import com.rearEnd.entity.GroupMenu;

import java.util.List;

public interface GroupMenuDao {
	
	/**
	 * 根据管理员组主键，删除管理员组与菜单之间的关联关系
	 * @param groupId 管理员组主键
	 * @return 影响行数
	 */
	int deleteByGroupId(Long groupId);
	
	/**
	 * 批量新增
	 * @param list
	 * @return 影响行数
	 */
	int insertBatch(List<GroupMenu> list);
}