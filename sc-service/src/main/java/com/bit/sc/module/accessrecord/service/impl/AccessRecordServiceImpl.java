package com.bit.sc.module.accessrecord.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.module.accessrecord.service.AccessRecordService;
import com.bit.sc.module.accessrecord.vo.AccessRecordVo;
import com.bit.sc.utils.CheckUtil;
import com.bit.sc.utils.DateUtil;
import com.bit.utils.StringUtil;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bit.sc.utils.CheckUtil.notEmpty;
import static com.bit.sc.utils.CheckUtil.notNull;

/**
 * 访问日志的Service实现类
 *
 * @author mifei
 */
@Service("accessRecordService")
public class AccessRecordServiceImpl extends BaseService implements AccessRecordService {

    private static final Logger logger = LoggerFactory.getLogger(AccessRecordServiceImpl.class);


    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 分页查询
     * @param accessRecordVo
     * @return
     */
    @Override
    public BaseVo findPage(AccessRecordVo accessRecordVo) {
        Query query = new Query();

        CheckUtil.notEmpty(accessRecordVo.getStartTime(),"开始时间不能为空");
        CheckUtil.notEmpty(accessRecordVo.getEndTime(),"结束时间不能为空");

        Criteria c = Criteria.where("record_time").gte(accessRecordVo.getStartTime());
        c.andOperator(Criteria.where("record_time").lte(accessRecordVo.getEndTime()));

        if (StringUtil.isNotEmpty(accessRecordVo.getIdCard())){
            c.and("id_card").is(accessRecordVo.getIdCard());
        }
        if (accessRecordVo.getStatus()!=null){
            c.and("status").is(accessRecordVo.getStatus());
        }
        if (StringUtil.isNotEmpty(accessRecordVo.getName())){
            c.and("name").is(accessRecordVo.getName());
        }
        if (StringUtil.isNotEmpty(accessRecordVo.getAddress())){
            c.and("address").is(accessRecordVo.getAddress());
        }

        query.addCriteria(c);
        //从第几条开始
        query.skip((accessRecordVo.getPageNum()-1)*accessRecordVo.getPageSize());
        //设置查询条数
        query.limit(accessRecordVo.getPageSize());
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "record_time")));
        List<Map> list = mongoTemplate.find(query, Map.class, accessRecordVo.getTableName());
        BaseVo baseVo = new BaseVo();
        baseVo.setData(list);
        return baseVo;
    }


}
