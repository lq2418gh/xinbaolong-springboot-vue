package com.bit.sc.module.attachment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.vo.AttachmentVO;

/**
 * Attachment管理的Dao
 * @author liuyancheng
 *
 */
public interface AttachmentMapper {
	/**
	 * 根据条件查询Attachment
	 * @param attachmentVO
	 * @return
	 */
	public List<Attachment> findByConditionPage(AttachmentVO attachmentVO);
	/**
	 * 查询所有Attachment
	 * @return
	 */
	public List<Attachment> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Attachment
	 * @param attachmentId	 	 
	 * @return
	 */
	public Attachment findByAttachmentId(@Param(value = "attachmentId") Long attachmentId);
	/**
	 * 保存Attachment
	 * @param attachment
	 */
	public void add(Attachment attachment);
	/**
	 * 更新Attachment
	 * @param attachment
	 */
	public void update(Attachment attachment);
	/**
	 * 删除Attachment
	 * @param ids
	 */
	public void batchDelete( @Param(value = "ids")List<Long> ids);
	/**
	 * 删除Attachment
	 * @param attachmentId
	 */
	public void delete(@Param(value = "attachmentId") Long attachmentId);

	void updateDataId(Attachment attachment);

	List<Attachment> findAllByParams(Attachment attachment);

	List<Attachment> batchSelect(@Param(value = "ids") Long[] ids);
}
