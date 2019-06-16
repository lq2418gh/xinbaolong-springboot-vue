package com.bit.sc.module.news.vo;

import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.pojo.Announcement;
import lombok.Data;

import java.util.List;

@Data
public class AnnouncementAddressVO {

    private Announcement announcement;
    private List<AddressRelAnnouncement> addressRelAnnouncementList;
}
